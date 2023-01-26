package com.kust.erms_company.ui.auth

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import com.kust.erms_company.R
import com.kust.erms_company.data.model.CompanyModel
import com.kust.erms_company.databinding.FragmentCompanyRegistrationBinding
import com.kust.erms_company.util.UiState
import com.kust.erms_company.util.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CompanyRegistrationFragment : Fragment() {

    @Inject
    lateinit var storageReference: StorageReference

    @Inject
    lateinit var auth : FirebaseAuth

    private var _binding: FragmentCompanyRegistrationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    private lateinit var imageUri: Uri
    private lateinit var uploadedImageUri : Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCompanyRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()

        binding.btnRegister.setOnClickListener {
            if (validation()) {
                val email = binding.editTextEmail.text.toString()
                val password = binding.editTextPassword.text.toString()
                val company = getCompanyObj()
                viewModel.register(email, password, company)
            }
        }

        binding.companyLogo.setOnClickListener {
//                uploadImage()
        }
    }

    private fun observer() {
        viewModel.register.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    binding.btnRegister.text = getString(R.string.register)
                    binding.progressBar.visibility = View.GONE
                    toast(state.data)
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnRegister.text = getString(R.string.register)
                    toast(state.error)
                }
                is UiState.Loading -> {
                    binding.btnRegister.text = ""
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun getCompanyObj(): CompanyModel {
        return CompanyModel(
            id = "",
            name = binding.editTextCompanyName.text.toString(),
            address = binding.editTextAddress.text.toString(),
            city = binding.editTextCity.text.toString(),
            country = binding.editTextCountry.text.toString(),
            email = binding.editTextEmail.text.toString(),
            phone = binding.editTextPhone.text.toString(),
            website = "www.erms.com",
            logo = uploadedImageUri.toString()
        )
    }

    private fun validation(): Boolean {
        var valid = true
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (email.isEmpty()) {
            binding.editTextEmail.error = "Please enter email"
            valid = false
        } else {
            binding.editTextEmail.error = null
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Please enter password"
            valid = false
        } else {
            binding.editTextPassword.error = null
        }

        return valid
    }

//    private fun uploadImage() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        startActivityForResult(intent, 0)
//
//        auth.uid?.let {
//            storageReference.storage.getReference(FirebaseStorageConstants.COMPANY_PROFILE).child(
//                it
//            )
//        }
//        storageReference.putFile(imageUri)
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//                    storageReference.downloadUrl
//                        .addOnSuccessListener {
//                        uploadedImageUri = it
//                    }
//                }
//            }
//
//    }

//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (data != null) {
//            binding.companyLogo.setImageURI(data.data)
//            imageUri = data.data!!
//        }
//    }

}