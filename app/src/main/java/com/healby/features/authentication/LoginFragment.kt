package com.healby.features.authentication

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.healby.R
import com.healby.databinding.FragmentLoginBinding
import com.healby.viewmodel.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginFragmentViewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fragmentLoginRegisterButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.fragmentLoginPassword.authenticationPasswordVisibilityImageview.setOnClickListener {
            loginFragmentViewModel.setPasswordVisibility(!loginFragmentViewModel.getPasswordVisibility().value!!)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        loginFragmentViewModel.getPasswordVisibility().observe(viewLifecycleOwner, Observer { visible ->
            if(visible) {
                Glide
                    .with(binding.fragmentLoginPassword.authenticationPasswordVisibilityImageview.context)
                    .load(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_visibility_off_24))
                    .into(binding.fragmentLoginPassword.authenticationPasswordVisibilityImageview)
                binding.fragmentLoginPassword.authenticationPasswordEdittext.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                Glide
                    .with(binding.fragmentLoginPassword.authenticationPasswordVisibilityImageview.context)
                    .load(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_visibility_24))
                    .into(binding.fragmentLoginPassword.authenticationPasswordVisibilityImageview)
                binding.fragmentLoginPassword.authenticationPasswordEdittext.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            binding.fragmentLoginPassword.authenticationPasswordEdittext.setSelection(binding.fragmentLoginEmailEdittext.text.length)
        })
    }
}