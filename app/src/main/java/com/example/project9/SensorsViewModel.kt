package com.example.project9

import android.content.Intent
import android.hardware.SensorManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import kotlin.math.sqrt


class SensorsViewModel
    /**
     * ViewModel class for managing sensor-related functionality in the application.
     */
    : ViewModel() {
    /** Tag for logging purposes. */
    private val TAG = "SensorViewModel"

    /** MeasurableSensor instance representing the accelerometer sensor. */
    private lateinit var accelerometerSensor: MeasurableSensor
    private var uri: Uri? = null

    /** MutableLiveData indicating a shake event. */
    val shakeEvent = MutableLiveData<Boolean>()

    private var accelerometerData = floatArrayOf(
        SensorManager.GRAVITY_EARTH, SensorManager.GRAVITY_EARTH, 0.0F
    )

    // Callback function triggered on a shake event.
    private fun onShake() {
        shakeEvent.value = true
    }
    fun initializeAccel(sAccelerometer: MeasurableSensor) {
        /**
         * Initializes the accelerometer sensor and sets up the listener for sensor value changes.
         *
         * @param sAccelerometer The MeasurableSensor representing the accelerometer.
         */

        accelerometerSensor = sAccelerometer
        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { a ->
            val x: Float = a[0]
            val y: Float = a[1]
            val z: Float = a[2]
            accelerometerData[1] = accelerometerData[0]
            accelerometerData[0] = sqrt((x * x).toDouble() + y * y + z * z).toFloat()
            val delta: Float = accelerometerData[0] - accelerometerData[1]
            accelerometerData[2] = accelerometerData[2] * 0.9f + delta


            // Check for a shake event based on accelerometer data
            if (accelerometerData[2] > 8) {
                onShake()

            }
        }

    }
}
