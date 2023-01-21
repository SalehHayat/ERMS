package com.kust.erms_company.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kust.erms_company.data.authRepository.AuthRepository
import com.kust.erms_company.data.model.Company
import com.kust.erms_company.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _register = MutableLiveData<UiState<String>>()
    val register : LiveData<UiState<String>>
        get() = _register

    private val _login = MutableLiveData<UiState<String>>()
    val login : LiveData<UiState<String>>
        get() = _login

    private val _forgotPassword = MutableLiveData<UiState<String>>()
    val forgotPassword : LiveData<UiState<String>>
        get() = _forgotPassword

    fun register (
        email: String,
        password: String,
        company : Company
    ) {
        _register.value = UiState.Loading
        authRepository.registerCompany(email, password, company) {
            _register.value = it
        }
    }

    fun login (
        email: String,
        password: String
    ) {
        _login.value = UiState.Loading
        authRepository.loginCompany(email, password) {
            _login.value = it
        }
    }

    fun forgotPassword (
        email: String
    ) {
        _forgotPassword.value = UiState.Loading
        authRepository.forgotPassword(email) {
            _forgotPassword.value = it
        }
    }

    fun logout (result : () -> Unit) {
        authRepository.logoutCompany(result)
    }
}