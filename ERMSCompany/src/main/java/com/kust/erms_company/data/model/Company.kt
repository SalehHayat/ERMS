package com.kust.erms_company.data.model

data class Company(
    var id: String = "",
    val name: String = "",
    val address: String = "",
    val city: String = "",
    val country: String = "",
    val email: String = "",
    val phone: String = "",
    val website: String = "",
    val logo: String = ""
)
