package tn.esprit.safeguardapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import tn.esprit.safeguardapplication.UI.adapters.CatastropheAdapter
import tn.esprit.safeguardapplication.databinding.ActivityCatastropheBinding
import tn.esprit.t1.viewmodel.CatastropheViewModel


const val TAGI = "Main Activity"

class MainActivity : ComponentActivity() {

    private lateinit var catastropheAdapter: CatastropheAdapter
    private lateinit var binding: ActivityCatastropheBinding
    private lateinit var viewModel: CatastropheViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatastropheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CatastropheViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        // Launch CatastropheActivity
        launchCatastropheActivity()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "notification_channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
            .addOnCompleteListener { task ->
                var msg = "Subscribed Successfully"
                if (!task.isSuccessful) {
                    msg = "Subscription failed"
                }
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            }
    }

    private fun launchCatastropheActivity() {
        val intent = Intent(this, CatastropheActivity::class.java)
        startActivity(intent)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            binding.progressBar.isVisible = true

            viewModel.getCatastrophes().observe(this@MainActivity, { catastrophes ->
                binding.progressBar.isVisible = false

                if (catastrophes != null) {
                    catastropheAdapter.catastrophes = catastrophes
                } else {
                    Log.e(TAGI, "Error retrieving catastrophes")
                }
            })
        }
    }

    private fun setupRecyclerView() = binding.rvCatastrophe.apply {
        catastropheAdapter = CatastropheAdapter()
        adapter = catastropheAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}
