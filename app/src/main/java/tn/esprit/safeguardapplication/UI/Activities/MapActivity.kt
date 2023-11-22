package tn.esprit.safeguardapplication.UI.Activities




import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.geojson.Polygon
import com.mapbox.mapboxsdk.plugins.annotation.CircleManager
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.dsl.generated.interpolate
import com.mapbox.maps.extension.style.layers.addLayer
import com.mapbox.maps.extension.style.layers.generated.fillLayer
import com.mapbox.maps.extension.style.sources.addSource
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.OnMoveListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.search.ResponseInfo
import com.mapbox.search.SearchEngine
import com.mapbox.search.SearchEngineSettings
import com.mapbox.search.offline.OfflineResponseInfo
import com.mapbox.search.offline.OfflineSearchEngine
import com.mapbox.search.offline.OfflineSearchEngineSettings
import com.mapbox.search.offline.OfflineSearchResult
import com.mapbox.search.record.HistoryRecord
import com.mapbox.search.result.SearchResult
import com.mapbox.search.result.SearchSuggestion
import com.mapbox.search.ui.adapter.engines.SearchEngineUiAdapter
import com.mapbox.search.ui.view.CommonSearchViewConfiguration
import com.mapbox.search.ui.view.DistanceUnitType
import com.mapbox.search.ui.view.SearchResultsView
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.databinding.ActivityMapBinding
import tn.esprit.safeguardapplication.util.LocationPermissionHelper
import tn.esprit.safeguardapplication.viewmodels.TrajetSecuriseViewModel
import tn.esprit.safeguardapplication.viewmodels.ZoneDeDangerViewModel
import java.lang.ref.WeakReference
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MapActivity() : AppCompatActivity() {

    private lateinit var locationPermissionHelper: LocationPermissionHelper

    private val onIndicatorBearingChangedListener = OnIndicatorBearingChangedListener {
        mapView.getMapboxMap().setCamera(CameraOptions.Builder().bearing(it).build())
    }

    private val onIndicatorPositionChangedListener = OnIndicatorPositionChangedListener {
        mapView.getMapboxMap().setCamera(CameraOptions.Builder().center(it).build())
        mapView.gestures.focalPoint = mapView.getMapboxMap().pixelForCoordinate(it)
    }

    private val onMoveListener = object : OnMoveListener {
        override fun onMoveBegin(detector: MoveGestureDetector) {
            onCameraTrackingDismissed()
        }

        override fun onMove(detector: MoveGestureDetector): Boolean {
            return false
        }

        override fun onMoveEnd(detector: MoveGestureDetector) {}
    }

    private lateinit var mapView: MapView
    private lateinit var binding: ActivityMapBinding

    private lateinit var zoneDeDangerViewModel: ZoneDeDangerViewModel
    private lateinit var trajetSecuriseViewModel: TrajetSecuriseViewModel

    private lateinit var searchEngine: SearchEngine
    private lateinit var circleManager: CircleManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapView = binding.mapView

        trajetSecuriseViewModel = ViewModelProvider(this).get(TrajetSecuriseViewModel::class.java)
        trajetSecuriseViewModel.getTrajetSecurise().observe(this, { trajetSecuriseList ->
            if (trajetSecuriseList != null && trajetSecuriseList.isNotEmpty()) {
                val useRedIcon = trajetSecuriseList.any { it.etat } // Check the etat value
                updateLocationPuckIcon(useRedIcon)
            } else {
                Log.e(TAG, "Error getting TrajetSecurise or list is empty")
            }
        })



        mapView?.getMapboxMap()?.loadStyleUri(
            Style.MAPBOX_STREETS)
        locationPermissionHelper = LocationPermissionHelper(WeakReference(this))
        locationPermissionHelper.checkPermissions {
            onMapReady(100.0,9.5607653,33.7931605)

        }
        searchEngine = SearchEngine.createSearchEngineWithBuiltInDataProviders(
            SearchEngineSettings(getString(R.string.mapbox_access_token))
        )
        val queryEditText = findViewById<EditText>(R.id.query_edit_text)
        val searchResultsView = findViewById<SearchResultsView>(R.id.search_results_view)
        searchResultsView.initialize(
            SearchResultsView.Configuration(
                commonConfiguration = CommonSearchViewConfiguration(DistanceUnitType.IMPERIAL)
            )
        )

        val offlineSearchEngine = OfflineSearchEngine.create(
            OfflineSearchEngineSettings(getString(R.string.mapbox_access_token))
        )

        val    searchEngineUiAdapter = SearchEngineUiAdapter(
            view = searchResultsView,
            searchEngine = searchEngine,
            offlineSearchEngine = offlineSearchEngine,
        )

        searchEngineUiAdapter.addSearchListener(object : SearchEngineUiAdapter.SearchListener {

            private fun showToast(message: String) {
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }

            override fun onSuggestionsShown(suggestions: List<SearchSuggestion>, responseInfo: ResponseInfo) {
                // not implemented
            }

            override fun onSearchResultsShown(
                suggestion: SearchSuggestion,
                results: List<SearchResult>,
                responseInfo: ResponseInfo
            ) {
                // not implemented
            }

            override fun onOfflineSearchResultsShown(results: List<OfflineSearchResult>, responseInfo: OfflineResponseInfo) {
                // not implemented
            }

            override fun onSuggestionSelected(searchSuggestion: SearchSuggestion): Boolean {
                return false
            }

            override fun onSearchResultSelected(searchResult: SearchResult, responseInfo: ResponseInfo) {
                // Assuming you have a reference to your MapboxMap object as 'mapboxMap'
                val newCameraPosition = CameraOptions.Builder()
                    .center(searchResult.coordinate)
                    .zoom(14.0) // or any other zoom level you prefer
                    .build()
                mapView.getMapboxMap().setCamera(newCameraPosition)
            }

            override fun onOfflineSearchResultSelected(searchResult: OfflineSearchResult, responseInfo: OfflineResponseInfo) {
                val newCameraPosition = CameraOptions.Builder()
                    .center(searchResult.coordinate)
                    .zoom(14.0) // or any other zoom level you prefer
                    .build()
                mapView.getMapboxMap().setCamera(newCameraPosition)
            }

            override fun onError(e: Exception) {
                showToast("Error happened: $e")
            }

            override fun onHistoryItemClick(historyRecord: HistoryRecord) {
                val newCameraPosition = CameraOptions.Builder()
                    .center(historyRecord.coordinate)
                    .zoom(14.0) // or any other zoom level you prefer
                    .build()
                mapView.getMapboxMap().setCamera(newCameraPosition)
            }

            override fun onPopulateQueryClick(suggestion: SearchSuggestion, responseInfo: ResponseInfo) {
                queryEditText.setText(suggestion.name)
            }

            override fun onFeedbackItemClick(responseInfo: ResponseInfo) {
                // not implemented
            }
        })
        queryEditText.setOnClickListener {
            // Trigger the search when the EditText is clicked
            searchEngineUiAdapter.search(queryEditText.text.toString())
        }
        queryEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, after: Int) {
                if (s.length >= 3) {
                    searchEngineUiAdapter.search(s.toString())
                } else {
                    // No need to manually set the adapter to null, let the SearchEngineUiAdapter handle it
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // not implemented
            }

            override fun afterTextChanged(e: Editable) {
                // not implemented
            }
        })


        // Set up map tap listener to hide the SearchResultsView
        mapView.getMapboxMap().addOnMapClickListener { point ->
            toggleSearchBarVisibility(false)
            false // return false to indicate the click event is not consumed
        }

        // Set up edit text focus listener to toggle the visibility of SearchResultsView
        queryEditText.setOnFocusChangeListener { _, hasFocus ->
            toggleSearchBarVisibility(hasFocus)
        }

        zoneDeDangerViewModel = ViewModelProvider(this).get(ZoneDeDangerViewModel::class.java)

        zoneDeDangerViewModel.getZoneDeDanger().observe(this, { zoneDeDanger ->
            if (zoneDeDanger != null) {
                Log.d(TAG, " aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  ZoneDeDanger: $zoneDeDanger")
            } else {
                Log.e(TAG, "Error getting ZoneDeDanger")
            }

        })



    }

    private fun addAnnotationToMap() {
// Create an instance of the Annotation API and get the PointAnnotationManager.
        bitmapFromDrawableRes(
            this@MapActivity,
            R.drawable.red_marker
        )?.let {
            val annotationApi = mapView?.annotations
            val pointAnnotationManager = annotationApi?.createPointAnnotationManager(mapView!!)
// Set options for the resulting symbol layer.
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
// Define a geographic coordinate.
                .withPoint(Point.fromLngLat(45.1, -74.0))
// Specify the bitmap you assigned to the point annotation
// The bitmap will be added to map style automatically.
                .withIconImage(it)
// Add the resulting pointAnnotation to the map.
            pointAnnotationManager?.create(pointAnnotationOptions)
        }
    }
    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
// copying drawable object to not manipulate on the same reference
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }
    private companion object {
        private const val PERMISSIONS_REQUEST_LOCATION = 0

        fun Context.isPermissionGranted(permission: String): Boolean {
            return ContextCompat.checkSelfPermission(
                this, permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun onMapReady(
        radiusInKilometerss: Double,
        longitude:Double,
        latitude:Double,

    ) {
        mapView?.getMapboxMap()?.loadStyleUri(
            Style.MAPBOX_STREETS
        ) {

            addCircleToMap(radiusInKilometerss,longitude,latitude,it)


            addAnnotationToMap()
            initLocationComponent()
            setupGesturesListener()

            val latitude = userLatitude
            val longitude = userLongitude

            if (latitude != null && longitude != null) {
                mapView.getMapboxMap().setCamera(
                    CameraOptions.Builder()
                        .center(Point.fromLngLat(longitude, latitude))
                        .zoom(1.0)
                        .build()
                )
            }
        }
    }

    private fun toggleSearchBarVisibility(visible: Boolean) {
        val visibility = if (visible) View.VISIBLE else View.GONE
        findViewById<SearchResultsView>(R.id.search_results_view).visibility = visibility
    }


    private fun addCircleToMap(radiusInKilometerss : Double,longitude:Double,latitude :Double,it: Style){
        // Define the center of the circle
        val circleCenter = Point.fromLngLat(longitude, latitude)


        val circlePoints = ArrayList<Point>()
        val steps = 64
        val distanceX = radiusInKilometerss / (111.32 * cos(Math.toRadians(circleCenter.latitude())))
        val distanceY = radiusInKilometerss / 110.574

        for (i in 0 until steps) {
            val theta = 2.0 * PI * i / steps
            val x = distanceX * cos(theta)
            val y = distanceY * sin(theta)
            circlePoints.add(Point.fromLngLat(circleCenter.longitude() + x, circleCenter.latitude() + y))
        }

        val polygon = Polygon.fromLngLats(listOf(circlePoints))
        val featureCollection = FeatureCollection.fromFeature(Feature.fromGeometry(polygon))

        val source = geoJsonSource("circle-source") {
            data(featureCollection.toJson()) // Convert FeatureCollection to JSON string
        }
        it.addSource(source)

        val circleLayer = fillLayer("circle-layer", "circle-source") {
            fillColor("#ff0000") // Set the color of the circle
            fillOpacity(0.5) // Set opacity as a Double value
        }

        it.addLayer(circleLayer)



    }


    private fun setupGesturesListener() {
        mapView.gestures.addOnMoveListener(onMoveListener)
    }

    private var userLatitude: Double? = null
    private var userLongitude: Double? = null

    private fun initLocationComponent() {
        val locationComponentPlugin = mapView.location
        locationComponentPlugin.updateSettings {
            this.enabled = true
            this.locationPuck = LocationPuck2D(
                bearingImage = AppCompatResources.getDrawable(
                    this@MapActivity,
                    R.drawable.baseline_location_on_24
                ),
                shadowImage = AppCompatResources.getDrawable(
                    this@MapActivity,
                    R.drawable.baseline_location_on_24
                ),
                scaleExpression = interpolate {
                    linear()
                    zoom()
                    stop {
                        literal(0.0)
                        literal(0.6)
                    }
                    stop {
                        literal(20.0)
                        literal(1.0)
                    }
                }.toJson()
            )
        }

        locationComponentPlugin.addOnIndicatorPositionChangedListener { point ->
            userLatitude = point.latitude()
            userLongitude = point.longitude()
            //Log.d("User Location", "Latitude: $userLatitude, Longitude: $userLongitude")
        }

        locationComponentPlugin.addOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener)
    }

    private fun onCameraTrackingDismissed() {
        mapView.location
            .removeOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener)
        mapView.location
            .removeOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener)
        mapView.gestures.removeOnMoveListener(onMoveListener)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    private fun updateLocationPuckIcon(useRedIcon: Boolean) {
        val iconResId = if (useRedIcon) R.drawable.baseline_location_on_red else R.drawable.baseline_location_on_24
        mapView.location.updateSettings {
            this.locationPuck = LocationPuck2D(
                bearingImage = AppCompatResources.getDrawable(this@MapActivity, iconResId),
                shadowImage = AppCompatResources.getDrawable(this@MapActivity, iconResId),
                // ... other settings
            )
        }
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.location
            .removeOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener)
        mapView.location
            .removeOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener)
        mapView.gestures.removeOnMoveListener(onMoveListener)
    }

}

