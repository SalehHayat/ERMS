package com.kust.erms_company.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kust.erms_company.data.model.FeatureModel
import com.kust.erms_company.databinding.DashboardFeatureItemBinding

class FeaturesAdapter (
    val onItemClicked : ((Int, FeatureModel) -> Unit)? = null
        ) : RecyclerView.Adapter<FeaturesAdapter.ViewHolder>() {

    private var features: MutableList<FeatureModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = DashboardFeatureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feature = features[position]
        holder.bind(feature, position)
    }

    override fun getItemCount(): Int {
        return features.size
    }

    inner class ViewHolder(private val binding: DashboardFeatureItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feature: FeatureModel, position: Int) {
            binding.featureImage.setImageResource(feature.image)
            binding.featureTitle.text = feature.title
            binding.featureSubTitle.text = feature.subTitle

            binding.card.setOnClickListener {
                onItemClicked?.invoke(position, feature)
            }
        }
    }
}