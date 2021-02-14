package com.healby.features.authentication

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.healby.R
import com.healby.databinding.FragmentRegisterBinding
import com.healby.viewmodel.RegisterFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val registerViewModel: RegisterFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fragmentRegisterLoginButton.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.fragmentRegisterPassword.authenticationPasswordVisibilityImageview.setOnClickListener {
            registerViewModel.setPasswordVisibility(!registerViewModel.getPasswordVisibility().value!!)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        registerViewModel.getPasswordVisibility().observe(viewLifecycleOwner, { visible ->
            if(visible) {
                binding.fragmentRegisterPassword.authenticationPasswordEdittext.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                binding.fragmentRegisterPassword.authenticationPasswordEdittext.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            setPasswordVisibilityIcon(binding.fragmentRegisterPassword.authenticationPasswordVisibilityImageview, visible)
            binding.fragmentRegisterPassword.authenticationPasswordEdittext.setSelection(binding.fragmentRegisterPassword.authenticationPasswordEdittext.text.length)
        })
    }

    private fun setPasswordVisibilityIcon(image: ImageView, visible: Boolean) {
        val visibilityIcon = if(visible) ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_visibility_off_24) else ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_visibility_24)
        Glide.with(image.context).load(visibilityIcon).into(image)
    }
}