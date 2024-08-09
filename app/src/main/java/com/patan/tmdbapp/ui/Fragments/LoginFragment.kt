package com.patan.tmdbapp.ui.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.MainActivity
import com.patan.tmdbapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            login(it)
        }
        binding.signUpButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
    }

    fun login(view: View) {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(activity, MainActivity::class.java)
                    activity?.startActivity(intent)
                    Toast.makeText(
                        requireContext(),
                        "Login successful.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    val user = auth.currentUser!!
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Login failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

    }
    override fun onDestroyView() {
        super.onDestroyView()
    }

}