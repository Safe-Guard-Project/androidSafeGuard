package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.databinding.RecyclerinfoBinding
import tn.esprit.safeguardapplication.models.Information

class InformationAdapter (val infoList: MutableList<Information>) : RecyclerView.Adapter<InformationAdapter.InformationHolder> () {

    inner class InformationHolder(val binding : RecyclerinfoBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationHolder {
        val binding =  RecyclerinfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return InformationHolder(binding)

    }

    override fun getItemCount() = infoList.size

    override fun onBindViewHolder(holder: InformationHolder, position: Int) {
        with(holder){
            with (infoList[position]){
                binding.imageView3.setImageResource(image)
                binding.infoTitle.text= titre
                binding.descriptionInfo.text= descriptionInformation

            }

        }
    }
}