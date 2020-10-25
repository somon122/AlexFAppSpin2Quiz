package com.world_tech_point.visiting_earnapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import fr.quentinklein.slt.LocationTracker
import fr.quentinklein.slt.ProviderError

class LocationTest : AppCompatActivity() {

    lateinit var latitudeTv: TextView
    lateinit var longitudeTv: TextView
    lateinit var startStopBtn: Button

    var tracker: LocationTracker = LocationTracker(
            minTimeBetweenUpdates = 1000L, // one second
            minDistanceBetweenUpdates = 1F, // one meter
            shouldUseGPS = true,
            shouldUseNetwork = true,
            shouldUsePassive = true
    ).also {
        it.addListener(object : LocationTracker.Listener {
            override fun onLocationFound(location: Location) {
               /* tv_lat.text = location.latitude.toString()
                tv_long.text = location.longitude.toString()*/
                longitudeTv.setText(location.longitude.toString())
                latitudeTv.setText(location.latitude.toString())

            }

            override fun onProviderError(providerError: ProviderError) {
                TODO("Not yet implemented")
            }

        })
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_test)



        latitudeTv = findViewById(R.id.tv_lat)
        longitudeTv = findViewById(R.id.tv_long)
        startStopBtn = findViewById(R.id.btn_start_stop)
    }

    override fun onStart() {
        super.onStart()
        initTracker()
    }

    override fun onDestroy() {
        super.onDestroy()
        tracker.stopListening(clearListeners = true);
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        initTracker()
    }

    private fun initTracker() {
        val hasFineLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        val hasCoarseLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if (!hasFineLocation || !hasCoarseLocation) {
            return ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1337);
        }
        startStopBtn.setOnClickListener {
            startStop()
        }
    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun startStop() {
        if (tracker.isListening) {
            startStopBtn.text = "Start"
            tracker.stopListening()
        } else {
            startStopBtn.text = "Stop"
            tracker.startListening(context = baseContext)
        }
    }

}