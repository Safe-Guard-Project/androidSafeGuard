package tn.esprit.safeguardapplication.UI.Activities.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.UI.adapters.ProgrammeAdapter
import tn.esprit.safeguardapplication.databinding.ActivityProgrammeBinding
import tn.esprit.safeguardapplication.models.Programme

class ProgrammeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgrammeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgrammeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvId.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvId.adapter = ProgrammeAdapter(getprogrammeList(this))

    }
    private fun getprogrammeList(context: Context) : MutableList<Programme>{
        return  mutableListOf(
            Programme(R.drawable.tsunamii,"Tsunami","decourvrire le phenomene de tsunamiiiiiiiiiii"),
            Programme(R.drawable.tremblement ,"Tremblement","decourvrire le phenomene de tremblement de terre")
        )

    }
}