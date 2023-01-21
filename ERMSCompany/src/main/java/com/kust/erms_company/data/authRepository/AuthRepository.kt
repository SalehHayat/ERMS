package com.kust.erms_company.data.authRepository

import com.kust.erms_company.data.model.Company
import com.kust.erms_company.util.UiState

interface AuthRepository {
    fun registerCompany(email : String, password : String, company: Company, result : (UiState<String>) -> Unit)
    fun loginCompany(email : String, password : String, result : (UiState<String>) -> Unit)
    fun forgotPassword(email : String, result : (UiState<String>) -> Unit)
    fun logoutCompany(result : () -> Unit)
    fun updateCompany(company: Company, result : (UiState<String>) -> Unit)
}