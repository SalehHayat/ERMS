package com.kust.erms_company.data.repositroy

import com.kust.erms_company.data.model.EmployeeModel
import com.kust.erms_company.util.UiState

interface EmployeeRepository {
    fun addEmployee(employeeModel: EmployeeModel, result: (UiState<String>) -> Unit)
    fun updateEmployee(employeeModel: EmployeeModel, result: (UiState<String>) -> Unit)
    fun deleteEmployee(employeeModel: EmployeeModel, result: (UiState<String>) -> Unit)
    fun getEmployeeList(result: (UiState<List<EmployeeModel>>) -> Unit)
}