package com.kust.erms_company.ui.employee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kust.erms_company.data.model.EmployeeModel
import com.kust.erms_company.data.repositroy.EmployeeRepository
import com.kust.erms_company.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private val _addEmployee = MutableLiveData<UiState<Pair<EmployeeModel,String>>>()
    val addEmployee : MutableLiveData<UiState<Pair<EmployeeModel, String>>>
        get() = _addEmployee

    private val _updateEmployee = MutableLiveData<UiState<Pair<EmployeeModel, String>>>()
    val updateEmployee : MutableLiveData<UiState<Pair<EmployeeModel, String>>>
        get() = _updateEmployee

    private val _deleteEmployee = MutableLiveData<UiState<String>>()
    val deleteEmployee : MutableLiveData<UiState<String>>
        get() = _deleteEmployee

    private val _getEmployeeList = MutableLiveData<UiState<List<EmployeeModel>>>()
    val getEmployeeList : MutableLiveData<UiState<List<EmployeeModel>>>
        get() = _getEmployeeList

    fun addEmployee(employeeModel: EmployeeModel) {
        _addEmployee.value = UiState.Loading
        employeeRepository.addEmployee(employeeModel) {
            _addEmployee.value = it
        }
    }

    fun updateEmployee(employeeModel: EmployeeModel) {
        _updateEmployee.value = UiState.Loading
        employeeRepository.updateEmployee(employeeModel) {
            _updateEmployee.value = it
        }
    }

    fun deleteEmployee(employeeModel: EmployeeModel) {
        _deleteEmployee.value = UiState.Loading
        employeeRepository.deleteEmployee(employeeModel) {
            _deleteEmployee.value = it
        }
    }

    fun getEmployeeList() {
        _getEmployeeList.value = UiState.Loading
        employeeRepository.getEmployeeList {
            _getEmployeeList.value = it
        }
    }
}