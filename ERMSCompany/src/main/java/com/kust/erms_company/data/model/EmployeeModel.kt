package com.kust.erms_company.data.model

data class EmployeeModel(
    var id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val city: String = "",
    val country: String = "",
    val companyName: String = "",
    var companyId: String = "",
    val designation: String = "",
    val salary: String = "",
    val points: String = "",
    val isManager : Boolean = false,
    val image: Int = 0
)
