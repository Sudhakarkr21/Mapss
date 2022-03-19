package com.example.mapss.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mapss.R
import com.example.mapss.other.Constants.KEY_NAME
import com.example.mapss.other.Constants.KEY_WIEGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFieldFromSharedPref()

        btnApplyChanges.setOnClickListener(View.OnClickListener {
            val success = applyChangesToSharedPref()
            if (success){
                Snackbar.make(view,"Saved changes",Snackbar.LENGTH_LONG).show()
            }else{
                Snackbar.make(view,"Please fill out all fields",Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun loadFieldFromSharedPref(){
        val usertext = sharedPreferences.getString(KEY_NAME,"")
        etName.setText(usertext)
        val weight = sharedPreferences.getFloat(KEY_WIEGHT,0f)
        etWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPref() : Boolean{
        val nameText = etName.text.toString()
        val weight = etWeight.text.toString()

        if (nameText.isEmpty() || weight.isEmpty()){
            return false
        }

        sharedPreferences.edit()
                .putString(KEY_NAME,nameText)
                .putFloat(KEY_WIEGHT,weight.toFloat())
                .apply()
        val toolbarText = "Leg's go $nameText"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }
}