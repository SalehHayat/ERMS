package com.kust.erms_company.data.repositroy

import com.kust.erms_company.data.model.EmployeeModel
import com.kust.erms_company.utils.UiState

interface EmployeeRepository {
    fun addEmployee(employeeModel: EmployeeModel, result: (UiState<Pair<EmployeeModel, String>>) -> Unit)
    fun updateEmployee(employeeModel: EmployeeModel, result: (UiState<Pair<EmployeeModel, String>>) -> Unit)
    fun deleteEmployee(employeeModel: EmployeeModel, result: (UiState<String>) -> Unit)
    fun getEmployeeList(employeeList : EmployeeModel?, result: (UiState<List<EmployeeModel>>) -> Unit)
}