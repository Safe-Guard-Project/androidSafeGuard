package tn.esprit.safeguardapplication.UI.Activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.databinding.ActivityCourrBinding
import tn.esprit.safeguardapplication.models.Commentaire
import tn.esprit.safeguardapplication.models.Cours
import tn.esprit.safeguardapplication.viewmodels.CommentaireViewModel
import tn.esprit.safeguardapplication.viewmodels.FeedViewModel


class CourrActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCourrBinding
    private lateinit var viewModel: CommentaireViewModel
    private lateinit var viewModell: FeedViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCourrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarCourr.toolbar)
        // Replace with the actual way you get the Cours object



        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_courr)


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_introduction, R.id.nav_cause, R.id.nav_consequence , R.id.nav_signe , R.id.nav_agir, R.id.nav_quiz
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        viewModel = ViewModelProvider(this).get(CommentaireViewModel::class.java)
        ///////modifier cm
        val  commentaireId = intent.getStringExtra("commentaireId")
        if(!commentaireId.isNullOrEmpty()){
            binding.idcmm.setText(intent.getStringExtra("commentaireText"))
        }
       // val  commentaireText = intent.getStringExtra("commentaireText")


/////post Comment
        binding.buttonSend.setOnClickListener(){
           
            val commentaire = Commentaire(
                _id = "id comment",
                textComment = binding.idcmm.text.toString() ,
                idCoursProgramme = "655cb526ae95847e25b00ef4",
                idUser = "655cb526ae95847e25b00ef4"
            )
            viewModel.viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = viewModel.addComment(commentaire)


                    if (response.isSuccessful) {

                        Log.d("coursActivity", "Request successful")
                    } else {
                        Log.e("coursActivity", "Request failed: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("coursActivity", "Exception: ${e.message}")
                }
            }
        }

        }





    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.courr, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_courr)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}