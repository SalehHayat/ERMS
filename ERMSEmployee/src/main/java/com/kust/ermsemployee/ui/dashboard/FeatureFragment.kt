package com.kust.ermsemployee.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kust.ermsemployee.R
import com.kust.ermsemployee.data.model.FeatureModel
import com.kust.ermsemployee.databinding.FragmentFeatureBinding
import com.kust.ermsemployee.ui.auth.AuthViewModel
import com.kust.ermsemployee.ui.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeatureFragment : Fragment() {

    private var _binding : FragmentFeatureBinding? = null
    private val binding get() = _binding!!

    private val authViewModel : AuthViewModel by viewModels()

    private val adapter by lazy { FeatureListingAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFeatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val features = mutableListOf<FeatureModel>()

        features.add(FeatureModel("Add Employee", R.drawable.avatar2))
        features.add(FeatureModel("Manage Employee", R.drawable.avatar2))
        features.add(FeatureModel("Select Manager", R.drawable.avatar2))
        features.add(FeatureModel("Setting", R.drawable.avatar2))
        features.add(FeatureModel("Profile", R.drawable.avatar2))
        features.add(FeatureModel("Logout", R.drawable.avatar2))

        adapter.features = features

        val layout = StaggeredGridLayoutManager( 2, LinearLayoutManager.VERTICAL)

        binding.rvFeatures.layoutManager = layout

        binding.rvFeatures.adapter = adapter

        adapter.setOnItemClickListener(object : FeatureListingAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when(position) {
                    0 -> {
                        // Add Employee
                    }
                    1 -> {
                        // Manage Employee
                    }
                    2 -> {
                        // Select Manager
                    }
                    3 -> {
                        // Setting
                    }
                    4 -> {
                        // Profile
                    }
                    5 -> {
                        Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT).show()
                        // Logout
                        authViewModel.logout {
                            val intent = Intent(requireContext(), LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}