package com.kust.erms_company.ui.employee

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kust.erms_company.R
import com.kust.erms_company.data.model.EmployeeModel
import com.kust.erms_company.databinding.FragmentAddEmployeeBinding
import com.kust.erms_company.util.UiState
import com.kust.erms_company.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEmployeeFragment : Fragment() {

    private var _binding : FragmentAddEmployeeBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = AddEmployeeFragment()
    }

    private lateinit var viewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        // TODO: Use the ViewModel
        observer()

        binding.btnRegister.setOnClickListener {
            if (validation()) {
                viewModel.addEmployee(getObject())
            }
        }

    }

    private fun observer() {
        viewModel.addEmployee.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnRegister.text = ""
                }
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnRegister.text = getString(R.string.register)
                    findNavController().navigate(R.id.action_addEmployeeFragment_to_featuresFragment)
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnRegister.text = getString(R.string.register)
                    toast(it.error)
                }
            }
        }
    }

    private fun getObject() : EmployeeModel {
        return EmployeeModel(
            id = "",
            name = binding.etName.text.toString(),
            email = binding.etEmail.text.toString(),
            phone = binding.etPhone.text.toString(),
            address = binding.etAddress.text.toString(),
            city = "Bannu",
            country = "Pakistan",
            companyName = "KUST",
            companyId = "",
            designation = binding.etDesignation.text.toString(),
            salary = binding.etBasicPay.text.toString(),
            points = "5",
            isManager = false
        )
    }

    private fun validation() : Boolean {
        if (binding.etName.text.toString().isEmpty()) {
            binding.etName.error = "Name is required"
            return false
        }
        if (binding.etEmail.text.toString().isEmpty()) {
            binding.etEmail.error = "Email is required"
            return false
        }
//        if (binding.etPassword.text.toString().isEmpty()) {
//            binding.etPassword.error = "Password is required"
//            return false
//        }
//        if (binding.etConfirmPassword.text.toString().isEmpty()) {
//            binding.etConfirmPassword.error = "Confirm Password is required"
//            return false
//        }
//        if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
//            binding.etConfirmPassword.error = "Password not match"
//            return false
//        }
        if (binding.etPhone.text.toString().isEmpty()) {
            binding.etPhone.error = "Phone is required"
            return false
        }
        if (binding.etAddress.text.toString().isEmpty()) {
            binding.etAddress.error = "Address is required"
            return false
        }
        if (binding.etDesignation.text.toString().isEmpty()) {
            binding.etDesignation.error = "Position is required"
            return false
        }
        if (binding.etBasicPay.text.toString().isEmpty()) {
            binding.etBasicPay.error = "Salary is required"
            return false
        }
        if (binding.etGender.text.toString().isEmpty()) {
            binding.etGender.error = "Gender is required"
            return false
        }
        if (binding.etMartialStatus.text.toString().isEmpty()) {
            binding.etMartialStatus.error = "Martial Status is required"
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}