package com.kust.erms_company.ui.employee

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kust.erms_company.R
import com.kust.erms_company.data.model.EmployeeModel
import com.kust.erms_company.databinding.FragmentManageEmployeeBinding
import com.kust.erms_company.util.UiState
import com.kust.erms_company.util.toast
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class ManageEmployeeFragment : Fragment() {

    val TAG = "ManageEmployeeFragment"
    private var _binding : FragmentManageEmployeeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : EmployeeViewModel by viewModels()

    private val adapter by lazy { EmployeeListingAdapter() }

    private val progressDialog : ProgressDialog by lazy {
        ProgressDialog(requireContext())
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentManageEmployeeBinding.inflate(inflater, container, false)
        observer()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()

        binding.rvEmployee.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEmployee.adapter = adapter

        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)

    }

    private fun observer() {
        viewModel.getEmployeeList.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    progressDialog.show()
                }
                is UiState.Success -> {
                    adapter.employees = it.data as MutableList<EmployeeModel>
                    adapter.updateList(it.data.toMutableList())
                    progressDialog.dismiss()
                }
                is UiState.Error -> {
                    progressDialog.dismiss()
                    toast(it.error)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}