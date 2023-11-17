package com.example.project9

abstract class MeasurableSensor(
    protected val sensorType: Int
) {
    /**
     * An abstract class representing a measurable sensor.
     * Provides common functionality for sensors that measure numerical values.
     *
     * @param sensorType The type of the sensor as an integer.
     */
    protected var onSensorValuesChanged: ((List<Float>) -> Unit)? = null

    abstract val doesSensorExist: Boolean

    abstract fun startListening()
    abstract fun stopListening()

    fun setOnSensorValuesChangedListener(listener: (List<Float>) -> Unit) {
        /**
         * Sets the listener to be called when sensor values change.
         *
         * @param listener The listener function to be called with a list of new sensor values.
         */
        onSensorValuesChanged = listener
    }
}
