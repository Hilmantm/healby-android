package com.healby.features.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.healby.R
import com.healby.adapter.ONBOARDING_TOTAL_ITEM
import com.healby.adapter.OnboardingAdapter
import com.healby.databinding.ActivityOnboardingBinding
import com.healby.utilities.Preferences
import com.healby.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private lateinit var onboardingAdapter: OnboardingAdapter
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = Preferences(applicationContext)
        preferences.setPreferenceFirstTimeInstall(false)
        onboardingAdapter = OnboardingAdapter(this)
        binding.onboardingViewpager.adapter = onboardingAdapter
        binding.onboardingViewpager.apply {
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        binding.onboardingViewpagerIndicator.setViewPager2(binding.onboardingViewpager)

        binding.onboardingViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.d("OnboardingActivity", "onPageSelected: $position")
                if ((position+1) < ONBOARDING_TOTAL_ITEM) {
                    binding.onboardingStartBtn.visibility = View.GONE
                    binding.onboardingSkipBtn.visibility = View.VISIBLE
                    binding.onboardingContinueBtn.visibility = View.VISIBLE
                } else {
                    binding.onboardingStartBtn.visibility = View.VISIBLE
                    binding.onboardingSkipBtn.visibility = View.GONE
                    binding.onboardingContinueBtn.visibility = View.GONE
                }
            }
        })

        binding.onboardingContinueBtn.setOnClickListener {
            val currentItem = binding.onboardingViewpager.currentItem
            if(currentItem < ONBOARDING_TOTAL_ITEM) {
                binding.onboardingViewpager.currentItem = currentItem + 1
            }
        }

        binding.onboardingSkipBtn.setOnClickListener {
            binding.onboardingViewpager.currentItem = ONBOARDING_TOTAL_ITEM
        }
    }
}