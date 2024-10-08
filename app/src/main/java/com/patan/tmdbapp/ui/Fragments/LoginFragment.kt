package com.patan.tmdbapp.ui.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.MainActivity
import com.patan.tmdbapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    @Inject
    lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = activity?.getSharedPreferences("userInfos", Context.MODE_PRIVATE)
        val emailFromShared = pref?.getString("email", "default").toString()
        val passwordFromShared = pref?.getString(
            "password", "default"
        ).toString()
        auth.signInWithEmailAndPassword(emailFromShared, passwordFromShared)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(activity, MainActivity::class.java)
                    activity?.startActivity(intent)
                    Toast.makeText(
                        requireContext(),
                        "Login successful.",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Login failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        binding.loginButton.setOnClickListener {
            login(it)
        }
        binding.signUpButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
    }

    private fun login(view: View) {
        val pref = activity?.getSharedPreferences("userInfos", Context.MODE_PRIVATE)
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val editor = pref?.edit()
        editor?.putString("email", email)
        editor?.putString("password", password)
        editor?.apply()
        editor?.commit()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(activity, MainActivity::class.java)
                activity?.startActivity(intent)
                Toast.makeText(
                    requireContext(),
                    "Login successful.",
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Login failed.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }

    }
}

