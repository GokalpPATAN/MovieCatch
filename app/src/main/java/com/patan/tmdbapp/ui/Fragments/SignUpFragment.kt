package com.patan.tmdbapp.ui.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {
    @Inject
    lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpButton.setOnClickListener {
            register(it)
        }
    }

    private fun register(view: View) {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(requireContext(), "Sign Up is successful", Toast.LENGTH_LONG).show()
                val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(requireContext(), "There is an error occured", Toast.LENGTH_LONG).show()
        }
    }
}