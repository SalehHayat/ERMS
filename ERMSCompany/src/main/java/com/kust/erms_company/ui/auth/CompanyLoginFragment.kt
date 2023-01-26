package com.kust.erms_company.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kust.erms_company.R
import com.kust.erms_company.databinding.FragmentCompanyLoginBinding
import com.kust.erms_company.util.UiState
import com.kust.erms_company.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CompanyLoginFragment : Fragment() {

    private var _binding: FragmentCompanyLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompanyLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()

        binding.btnLogin.setOnClickListener {
            if (validation()) {
                val email = binding.editTextEmail.text.toString()
                val password = binding.editTextPassword.text.toString()
                viewModel.login(email, password)
            }
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_companyLoginFragment_to_companyRegistrationFragment)
        }

    }

    private fun observer() {
        viewModel.login.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    binding.btnLogin.text = getString(R.string.login)
                    binding.progressBar.visibility = View.GONE
                    toast(state.data)
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnLogin.text = getString(R.string.login)
                    toast(state.error)
                }
                is UiState.Loading -> {
                    binding.btnLogin.text = ""
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun validation(): Boolean {
        var valid = true
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (email.isEmpty()) {
            binding.editTextEmail.error = "Please enter email"
            valid = false
            binding.editTextEmail.requestFocus()
        } else {
            binding.editTextEmail.error = null
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Please enter password"
            valid = false
            binding.editTextPassword.requestFocus()
        } else {
            binding.editTextPassword.error = null
        }

        return valid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}