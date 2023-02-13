package com.kust.erms_company.data.repositroy

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kust.erms_company.data.model.EmployeeModel
import com.kust.erms_company.utils.FireStoreCollection
import com.kust.erms_company.utils.UiState

class EmployeeRepositoryImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseFirestore
) : EmployeeRepository {

    override fun addEmployee(
        employeeModel: EmployeeModel,
        result: (UiState<Pair<EmployeeModel, String>>) -> Unit
    ) {
        val document = database.collection(FireStoreCollection.EMPLOYEE).document()
        employeeModel.id = document.id
        employeeModel.companyId = auth.currentUser?.uid.toString()
        document.set(employeeModel).addOnSuccessListener {
            result.invoke(UiState.Success(Pair(employeeModel, "Employee added successfully")))
        }.addOnFailureListener {
            result(UiState.Error(it.message.toString()))
        }
    }

    override fun updateEmployee(
        employeeModel: EmployeeModel,
        result: (UiState<Pair<EmployeeModel, String>>) -> Unit
    ) {
        val document = database.collection(FireStoreCollection.EMPLOYEE).document(employeeModel.id)
        document.set(employeeModel).addOnSuccessListener {
            result.invoke(UiState.Success(Pair(employeeModel, "Employee updated successfully")))
        }.addOnFailureListener {
            result(UiState.Error(it.message.toString()))
        }
    }

    override fun deleteEmployee(employeeModel: EmployeeModel, result: (UiState<String>) -> Unit) {
        val document = database.collection(FireStoreCollection.EMPLOYEE).document(employeeModel.id)
        document.delete().addOnSuccessListener {
            result.invoke(UiState.Success("Employee deleted successfully"))
        }.addOnFailureListener {
            result(UiState.Error(it.message.toString()))
        }
    }

    override fun getEmployeeList(employeeList: EmployeeModel?, result: (UiState<List<EmployeeModel>>) -> Unit) {
        database.collection(FireStoreCollection.EMPLOYEE).get().addOnSuccessListener {
            val list = arrayListOf<EmployeeModel>()
            for (document in it) {
                val employee = document.toObject(EmployeeModel::class.java)
                list.add(employee)
            }
            result.invoke(UiState.Success(list))
        }.addOnFailureListener {
            result(UiState.Error(it.message.toString()))
        }
    }
}