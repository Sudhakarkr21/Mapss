package com.example.mapss.ui.fragments

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapss.R
import com.example.mapss.dapters.RunAdapter
import com.example.mapss.other.Constants.REQUEST_CODE_LOCATION_PERMISSION
import com.example.mapss.other.TrackingUtility
import com.example.mapss.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_run.*
import kotlinx.android.synthetic.main.fragment_setup.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


@AndroidEntryPoint
class RunFragment : Fragment(R.layout.fragment_run) , EasyPermissions.PermissionCallbacks ,
        AdapterView.OnItemSelectedListener{

    private val viewModel : MainViewModel by viewModels()

    private lateinit var runAdapter: RunAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        setupRecyclerView()

        viewModel.runsSortedByDate.observe(viewLifecycleOwner, Observer {
            runAdapter.submitList(it)
        })
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
        }

        spFilter!!.setOnItemSelectedListener(this)
    }

    private fun setupRecyclerView() = rvRuns.apply {
        runAdapter = RunAdapter()
        adapter = runAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }


    private fun requestPermissions(){
        if (TrackingUtility.hasLocationPermission(requireContext())){
            return
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
            EasyPermissions.requestPermissions(
                    this,
                    "You need to accept location permission to use this app..",
                    REQUEST_CODE_LOCATION_PERMISSION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    "You need to accept location permission to use this app..",
                    REQUEST_CODE_LOCATION_PERMISSION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            AppSettingsDialog.Builder(this).build().show()
        }else {
            requestPermissions()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selectedVal = resources.getStringArray(R.array.filter_options)[p2]
        when(selectedVal){
            "Date" ->
                viewModel.runsSortedByDate.observe(viewLifecycleOwner, Observer {
                    runAdapter.submitList(it)
                })
            "Running Time" ->
                viewModel.runsSortedByTimeInMillis.observe(viewLifecycleOwner, Observer {
                    runAdapter.submitList(it)
                })
            "Distance" ->
                viewModel.runsSortedByDistance.observe(viewLifecycleOwner, Observer {
                    runAdapter.submitList(it)
                })
            "Average Speed" ->
                viewModel.runsSortedByAvgSpeed.observe(viewLifecycleOwner, Observer {
                    runAdapter.submitList(it)
                })
            "Calories Burned" ->
                viewModel.runsSortedByCaloriesBurned.observe(viewLifecycleOwner, Observer {
                    runAdapter.submitList(it)
                })
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}