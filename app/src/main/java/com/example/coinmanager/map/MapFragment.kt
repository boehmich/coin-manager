package com.example.coinmanager.map

import android.content.Context
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.coinmanager.R
import com.example.coinmanager.repository
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MapFragment : Fragment(), OnMapReadyCallback {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        googleMap.isMyLocationEnabled = true

        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = false


        val fhTechnikum = LatLng(48.2394545741155, 16.37725972514961)
        googleMap.addMarker(
            MarkerOptions().position(fhTechnikum).title(getString(R.string.fh))
        )
        //val cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(fhTechnikum, 15f)
        //googleMap.animateCamera(cameraUpdate)

        val cameraPosition = CameraPosition.builder()
            .target(fhTechnikum)
            .zoom(15f)
            .build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), object : GoogleMap.CancelableCallback {
            override fun onFinish() { /* Launch another camera update here */ }
            override fun onCancel() { /* Update has been canceled */}
        })


        googleMap.setOnMapClickListener { coordinates ->
            val tvLat = view?.findViewById<TextView>(R.id.tvLat)
            tvLat?.setText(coordinates.latitude.toString())

            val tvLng = view?.findViewById<TextView>(R.id.tvLng)
            tvLng?.setText(coordinates.longitude.toString())
        }

        googleMap.setOnMapLongClickListener { coordinates ->
            val data = "Any data object can be used"
            val markerOptions = MarkerOptions()
                .position(coordinates)
                .title(getString(R.string.marker))
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round))
            val marker = googleMap.addMarker(markerOptions)
            marker?.tag = data
        }

        googleMap.setOnMarkerClickListener {
            val materialAlertContext: Context = ContextThemeWrapper(requireContext(), R.style.Theme_CoinManager)
            MaterialAlertDialogBuilder(materialAlertContext)
                .setTitle(it.title)
                .setMessage(getString(R.string.map_delete_action, it.title))
                .setPositiveButton(getString(R.string.delete)) { _, _ ->
                    it.remove()
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                }
                .setCancelable(false)
                .show()
            false
        }

    }

}