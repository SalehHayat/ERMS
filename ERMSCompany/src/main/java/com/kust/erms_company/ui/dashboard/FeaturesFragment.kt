package com.kust.erms_company.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kust.erms_company.R
import com.kust.erms_company.data.model.FeatureModel
import com.kust.erms_company.databinding.FragmentFeaturesBinding


private const val ARG_PARAM1 = "param1"

class FeaturesFragment : Fragment() {

    val TAG by lazy { "FeaturesFragment" }

    private var _binding: FragmentFeaturesBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { FeaturesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFeaturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var features = mutableListOf<FeatureModel>()

        features.add(FeatureModel("Create Event", R.drawable.avatar2))
        features.add(FeatureModel("Create Event", R.drawable.avatar3))
        features.add(FeatureModel("Create Event", R.drawable.avatar6))
        features.add(FeatureModel("Create Event", R.drawable.avatar4))
        features.add(FeatureModel("Create Event", R.drawable.avatar8))

        adapter.features = features

        val layout = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        binding.rvFeatures.layoutManager = layout

        binding.rvFeatures.adapter = adapter

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            FeaturesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}