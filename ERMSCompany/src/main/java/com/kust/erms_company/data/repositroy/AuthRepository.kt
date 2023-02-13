package com.kust.erms_company.data.repositroy

import com.kust.erms_company.data.model.CompanyModel
import com.kust.erms_company.utils.UiState

interface AuthRepository {
    fun registerCompany(email : String, password : String, companyModel: CompanyModel, result : (UiState<String>) -> Unit)
    fun loginCompany(email : String, password : String, result : (UiState<String>) -> Unit)
    fun forgotPassword(email : String, result : (UiState<String>) -> Unit)
    fun logoutCompany(result : () -> Unit)
    fun updateCompanyInformation(companyModel: CompanyModel, result : (UiState<String>) -> Unit)
    fun isUserLoggedIn() : Boolean
}