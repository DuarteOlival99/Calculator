package com.example.fichaexercicios.data.sensors.location

import android.content.Context
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import kotlin.random.Random.Default.Companion

class FusedLocation(context: Context) : LocationCallback() {

    private val TAG = FusedLocation::class.java.simpleName
    // intervalos de tempo em que a localizacao é verificada, 20 segundos
    private val TIME_BETWEEN_UPDATES = 20 * 1000L
    // Este atributo é utilizado para configurar os pedidos de localizacao
    private var locationRequest: LocationRequest? = null
    //Este atributo sera utilizado para acedermos à API da Fused location
    private var client = FusedLocationProviderClient(context)

    init {
        //configurar a precisao e os tempos entre atualizacoes da localizacao
        locationRequest = LocationRequest()
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest?.interval = TIME_BETWEEN_UPDATES

        //instanciar o objeto que permite definir as configuracoes
        val locationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)
            .build()

        //Aplicar as configuracoes ao servico de localizacao
        LocationServices.getSettingsClient(context)
            .checkLocationSettings(locationSettingsRequest)
    }

    companion object {

        private var listener: OnLocationChangedListener? = null
        private var instance: FusedLocation? = null

        fun registerListener (listener: OnLocationChangedListener) {
            this.listener = listener
        }

        fun unregisterListener() {
            listener = null
        }

        fun notifyListeners (locationResult: LocationResult) {
            listener?.onLocationChanged(locationResult)
        }

        fun start (context: Context) {
            instance = if (instance == null) FusedLocation(context) else instance
            instance?.startLocationUpdates()
        }

    }

    private fun startLocationUpdates() {
        client.requestLocationUpdates(locationRequest, this, Looper.myLooper())
    }

    override fun onLocationResult(locationResult: LocationResult?) {
        Log.i(TAG, locationResult?.lastLocation.toString())
        locationResult?.let { notifyListeners(it) }
        super.onLocationResult(locationResult)
    }

}