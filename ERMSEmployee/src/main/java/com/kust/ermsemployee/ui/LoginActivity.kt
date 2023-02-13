package com.kust.ermsemployee.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kust.ermsemployee.R
import com.kust.ermsemployee.databinding.ActivityLoginBinding
import com.kust.ermsemployee.utils.UiState
import com.kust.ermsemployee.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        observer()

        binding.btnLogin.setOnClickListener {
            if (validation()){
                viewModel.login(
                    email = binding.editTextEmail.text.toString(),
                    password = binding.editTextPassword.text.toString()
                )
            }
        }
    }

    private fun observer() {
        viewModel.login.observe(this) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.btnLogin.text = ""
                    binding.progressBar.show()
                }
                is UiState.Error -> {
                    binding.btnLogin.text = "Login"
                    binding.progressBar.hide()
                    toast(state.error)
                }
                is UiState.Success -> {
                    binding.btnLogin.text = "Login"
                    binding.progressBar.hide()
                    toast(state.data)
                }
            }
        }
    }

    private fun validation () : Boolean {
        if (binding.editTextEmail.text.toString().isEmpty()){
            binding.editTextEmail.error = "Email is required"
            binding.editTextEmail.requestFocus()
            return false
        }
        if (binding.editTextPassword.text.toString().isEmpty()){
            binding.editTextPassword.error = "Password is required"
            binding.editTextPassword.requestFocus()
            return false
        }
        return true
    }
}