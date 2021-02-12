package com.healby.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.healby.features.onboarding.OnboardingFragment
import com.healby.utilities.Constant.Companion.POSITION

const val ONBOARDING_TOTAL_ITEM = 3

class OnboardingAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = ONBOARDING_TOTAL_ITEM

    override fun createFragment(position: Int): Fragment {
        val fragment = OnboardingFragment()
        fragment.arguments = Bundle().apply {
            putInt(POSITION, position + 1)
        }
        return fragment
    }
}