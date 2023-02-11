package com.kust.erms_company.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kust.erms_company.databinding.FragmentFeaturesBinding


class FeaturesFragment : Fragment() {

    val TAG = "FeaturesFragment"

    private var _binding: FragmentFeaturesBinding? = null
    private val binding get() = _binding!!

    private val adapter : FeaturesAdapter? = null

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

        val layout = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        binding.rvFeatures.layoutManager = layout

        binding.rvFeatures.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}