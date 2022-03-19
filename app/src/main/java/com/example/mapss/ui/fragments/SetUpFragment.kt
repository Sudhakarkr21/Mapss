package com.example.mapss.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.mapss.R
import com.example.mapss.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.mapss.other.Constants.KEY_NAME
import com.example.mapss.other.Constants.KEY_WIEGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setup.*
import javax.inject.Inject

@AndroidEntryPoint
class SetUpFragment : Fragment(R.layout.fragment_setup) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isFirstAppOpen) {
            val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.setUpFragment, true)
                    .build()

            findNavController().navigate(
                    R.id.action_setUpFragment_to_runFragment,
                    savedInstanceState,
                    navOptions)
        }

        tvContinue.setOnClickListener {
            val success = writePersnalDataSharePress()

            if (success) {
                findNavController().navigate(R.id.action_setUpFragment_to_runFragment)
            } else {
                Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_LONG).show()
            }


        }
    }

    private fun writePersnalDataSharePress(): Boolean {
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()) {
            return false
        }

        sharedPref.edit()
                .putString(KEY_NAME, name)
                .putFloat(KEY_WIEGHT, weight.toFloat())
                .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
                .apply()

        val toobarText = "Let's go, $name"
        requireActivity().tvToolbarTitle.text = toobarText
        return true
    }
}