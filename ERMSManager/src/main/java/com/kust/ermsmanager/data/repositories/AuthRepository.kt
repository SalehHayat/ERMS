package com.kust.ermsmanager.data.repositories

import com.kust.ermsmanager.data.models.CompanyModel
import com.kust.ermsmanager.utils.UiState

interface AuthRepository {
    fun registerCompany(email : String, password : String, companyModel: CompanyModel, result : (UiState<String>) -> Unit)
    fun loginCompany(email : String, password : String, result : (UiState<String>) -> Unit)
    fun forgotPassword(email : String, result : (UiState<String>) -> Unit)
    fun logoutCompany(result : () -> Unit)
    fun updateCompanyInformation(companyModel: CompanyModel, result : (UiState<String>) -> Unit)
    fun isUserLoggedIn() : Boolean
}