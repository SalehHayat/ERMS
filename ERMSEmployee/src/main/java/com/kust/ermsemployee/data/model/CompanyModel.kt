package com.kust.ermsemployee.data.model

data class CompanyModel(
    var id: String = "",
    val name: String = "",
    val address: String = "",
    val city: String = "",
    val country: String = "",
    val email: String = "",
    val phone: String = "",
    val website: String = "",
    var logo: String = ""
)
