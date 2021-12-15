package com.example.coinmanager.map

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
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

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

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

        var tvCoordinates: TextView = view.findViewById(R.id.textView3)
        tvCoordinates.setOnClickListener {
            repository.updateCoinsWatchlist()
            Toast.makeText(requireContext(), "OK", Toast.LENGTH_LONG).show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        /*
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

         */
        googleMap.isMyLocationEnabled = true


        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = false


        val fhTechnikum = LatLng(48.2394545741155, 16.37725972514961)
        googleMap.addMarker(
            MarkerOptions().position(fhTechnikum).title("This is the FH Technikum Vienna!")
        )
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(fhTechnikum))

        val cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(fhTechnikum, 15f)
        googleMap.animateCamera(cameraUpdate)


        googleMap.setOnMapClickListener { coordinates ->
            Log.e("Map", "Clicked: $coordinates")

            var tvLat = view?.findViewById<TextView>(R.id.tvLat)
            tvLat?.setText(coordinates.latitude.toString())

            var tvLng = view?.findViewById<TextView>(R.id.tvLng)
            tvLng?.setText(coordinates.longitude.toString())

            val data = "Any data object can be used"
            val markerOptions = MarkerOptions()
                .position(coordinates)
                .title("Marker")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round))
            val marker = googleMap.addMarker(markerOptions)
            marker?.tag = data

        }
/*
        googleMap.setOnMarkerClickListener {
            it.remove()
            false
        }
 */

    }
}