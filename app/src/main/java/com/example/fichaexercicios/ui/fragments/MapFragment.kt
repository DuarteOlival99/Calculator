package com.example.fichaexercicios.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.fichaexercicios.R
import com.example.fichaexercicios.data.sensors.location.FusedLocation
import com.example.fichaexercicios.data.sensors.location.OnLocationChangedListener
import com.example.fichaexercicios.ui.utils.Extensions
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.map_fragment.*
import kotlinx.android.synthetic.main.map_fragment.view.*


const val REQUEST_CODE = 100

class MapFragment : PermissionedFragment(REQUEST_CODE),
    OnMapReadyCallback, OnLocationChangedListener {

    private val extensions = Extensions()
    private var map: GoogleMap? = null
    private var zoom = true
    private var location : Location? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.map_fragment, container, false)
        view.map_view.onCreate(savedInstanceState)
        return view
    }

    override fun onStart() {
        super.onRequestPermissions(activity?.baseContext!!, arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION))
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FusedLocation.start(activity!!.applicationContext)
    }

    override fun onRequestPermissionSucces() {
        Log.i("Mapa", "teste")
        FusedLocation.registerListener(this)
        map_view.getMapAsync(this)
        zoomMyLocation()
        map_view.onResume()
    }

    fun redesenhaMapa(latitude: Double, longitude: Double) {
        Log.i("redesenhaMapa", "teste")
        map_view.getMapAsync(OnMapReadyCallback {
                googleMap -> this

            //val latitude = location.latitude
            //val longitude = location.longitude
            Log.i("local", latitude.toString())
            Log.i("local", longitude.toString())
            val location1 = LatLng(latitude, longitude)
            googleMap.addMarker(MarkerOptions().position(location1).title("My location"))
            if (zoom){
                //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,10f))
                zoom = false
            }

        })
    }

    override fun onRequestPermissionFailure() {
        //implementar msg de erro
        Log.i("localizacao" , "erro")
    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        //fazer um for para percorrer todos os parques e com latitude e longitude adicionar um
        //marker e o titulo como nome do parque
        this.map!!.addMarker(MarkerOptions().position(LatLng(0.0,0.0)).title("onMapReady"))
        if (ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        this.map!!.isMyLocationEnabled = true
        this.map!!.addMarker(MarkerOptions().position(extensions.LocationToLatLng(location!!)).title("casa"))
        var locationUniversidade: Location = Location("teste")
        locationUniversidade.latitude = 38.757988
        locationUniversidade.longitude = -9.153075
        this.map!!.addMarker(MarkerOptions().position(extensions.LocationToLatLng(locationUniversidade)).title("universidade"))
        //medir distancia entre casa e universidade
        //1 maneira com localizacoes do tipo Location
        val distancia = location!!.distanceTo(locationUniversidade)
        Log.i("distancia", distancia.toString())
        if (distancia > 1000.0f) {
            val distance = distancia / 1000.0f
            val dist : String = "$distance KM"
            Log.i("distancia", dist)
        }

        zoomMyLocation()
        //2 maneira com localizacoes so com os LatLng ou seja so latitude e longitude
        val results = FloatArray(1)
        Location.distanceBetween(
            location!!.latitude, location!!.longitude,
            locationUniversidade.latitude, locationUniversidade.longitude,
            results
        )
        results to Float
        Log.i("distancia", results[0].toString())
    }

    fun zoomMyLocation(){
        if (location != null) {
            map!!.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    extensions.LocationToLatLng(location!!), 13f
                )
            )
            val cameraPosition = CameraPosition.Builder()
                .target(
                    extensions.LocationToLatLng(location!!)
                ) // Sets the center of the map to location user
                .zoom(17f) // Sets the zoom
                .bearing(90f) // Sets the orientation of the camera to east
                .tilt(40f) // Sets the tilt of the camera to 30 degrees
                .build() // Creates a CameraPosition from the builder
            map!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    fun zoomMyLocationOnChanged(){
        if (location != null) {
            map!!.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    extensions.LocationToLatLng(location!!), 13f
                )
            )
            val cameraPosition = CameraPosition.Builder()
                .target(
                    extensions.LocationToLatLng(location!!)
                ) // Sets the center of the map to location user
                .zoom(17f) // Sets the zoom
                    //.zoom(Constants.MAX_ZOOM) // Sets the zoom to current zoom map
                .bearing(90f) // Sets the orientation of the camera to east
                .tilt(40f) // Sets the tilt of the camera to 30 degrees
                .build() // Creates a CameraPosition from the builder
            map!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    override fun onLocationChanged(locastionResult: LocationResult) {
        val location = locastionResult.lastLocation

        if (this.location == null) {
            Log.i("muda = null", "teste")
            location.let { this.location = it }
        }else if (( this.location!!.latitude != location.latitude) ||
            ( this.location!!.longitude != location.longitude ) ){
            Log.i("muda", "teste")
            location.let { this.location = it }
            map!!.addMarker(MarkerOptions().position(extensions.LocationToLatLng(location)).title("My location"))
            zoomMyLocationOnChanged()
        }else{
            Log.i("igual", "teste")
        }

        Log.i("localizacaoMap", location.toString())
    }


}