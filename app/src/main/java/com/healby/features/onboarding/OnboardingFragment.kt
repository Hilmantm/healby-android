package com.healby.features.onboarding

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.healby.R
import com.healby.databinding.FragmentOnboardingBinding
import com.healby.utilities.Constant.Companion.POSITION
import com.healby.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {

    private val onboardingViewModel: OnboardingViewModel by viewModels()
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(POSITION) }?.apply {
            onboardingViewModel.setPosition(getInt(POSITION))
        }

        onboardingViewModel.getOnboardingPosition().observe(viewLifecycleOwner, Observer { position ->
            when(position) {
                1 -> {
                    setContent(
                        getString(R.string.onboarding_title_1),
                        getString(R.string.onboarding_desc_1),
                        ContextCompat.getDrawable(requireContext(), R.drawable.onboarding_image_1)!!)
                }
                2 -> {
                    setContent(
                        getString(R.string.onboarding_title_2),
                        getString(R.string.onboarding_desc_2),
                        ContextCompat.getDrawable(requireContext(), R.drawable.onboarding_image_2)!!)
                }
                3 -> {
                    setContent(
                        getString(R.string.onboarding_title_3),
                        getString(R.string.onboarding_desc_3),
                        ContextCompat.getDrawable(requireContext(), R.drawable.onboarding_image_3)!!)
                }
            }
        })
    }

    private fun setContent(title: String, desc: String, image: Drawable) {
        binding.onboardingTitle.text = title
        binding.onboardingDesc.text = desc
        Glide.with(binding.onboardingImage.context).load(image).into(binding.onboardingImage)
    }
}