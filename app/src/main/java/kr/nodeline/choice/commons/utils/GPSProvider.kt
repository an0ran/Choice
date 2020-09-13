package kr.nodeline.choice.commons.utils

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

class GPSProvider (var activity: AppCompatActivity, locationListener: locationListener) {

    private val permissionFineLocation = android.Manifest.permission.ACCESS_FINE_LOCATION
    private val permissionCoarseLocation = android.Manifest.permission.ACCESS_COARSE_LOCATION
    private val permissionBackgroundLocation = android.Manifest.permission.ACCESS_BACKGROUND_LOCATION

    val REQUEST_CODE_ALL_LOCATION = 100
    val REQUEST_CODE_BACKGROUND_LOCATION = 101

    private var fusedLocationClient: FusedLocationProviderClient?=null

    private var locationRequest: LocationRequest?=null
    private var callbabck: LocationCallback?=null
    init {
        fusedLocationClient= FusedLocationProviderClient(activity.applicationContext)

        inicializeLocationRequest()
        callbabck=object: LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)

                locationListener.locationResponse(p0!!)
            }
        }
    }

    private fun inicializeLocationRequest() {
        locationRequest= LocationRequest()
        locationRequest?.interval=50000
        locationRequest?.fastestInterval=5000
        locationRequest?.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private fun validatePermissionsLocation(): Boolean{
        val fineLocationAvailable= ActivityCompat.checkSelfPermission(activity.applicationContext, permissionFineLocation) == PackageManager.PERMISSION_GRANTED
        val coarseLocationAvailable=
            ActivityCompat.checkSelfPermission(activity.applicationContext, permissionCoarseLocation) == PackageManager.PERMISSION_GRANTED

        return fineLocationAvailable && coarseLocationAvailable
    }

    fun validatePermissionFineLocation(): Boolean {
        return ActivityCompat.checkSelfPermission(activity.applicationContext, permissionFineLocation) == PackageManager.PERMISSION_GRANTED
    }

    fun validatePermissionBackgroundLocation(): Boolean {
        return ActivityCompat.checkSelfPermission(activity.applicationContext, permissionBackgroundLocation) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions(){
        val contextProvider=
            ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionFineLocation)

        if(contextProvider){
            Toast.makeText(activity.applicationContext, "Permission is required to obtain location", Toast.LENGTH_SHORT).show()
        }
        // permissionRequest()
    }

    fun permissionAllRequest(){
        ActivityCompat.requestPermissions(activity, arrayOf(permissionFineLocation, permissionCoarseLocation, permissionBackgroundLocation), REQUEST_CODE_ALL_LOCATION)
    }

    fun permissionBackGroundLocationRequest() {
        ActivityCompat.requestPermissions(activity, arrayOf(permissionBackgroundLocation), REQUEST_CODE_BACKGROUND_LOCATION)
    }

//    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray){
//        when(requestCode){
//            REQUEST_CODE_LOCATION->{
//                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    getLocation()
//                }else{
//                    Toast.makeText(activity.applicationContext, "You did not give permissions to get location", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
    fun stopUpdateLocation(){
        this.fusedLocationClient?.removeLocationUpdates(callbabck)
    }
    fun inicializeLocation(){
        if (validatePermissionsLocation()){
            getLocation()
        }else{
            requestPermissions()
        }
    }
    @SuppressLint("MissingPermission")
    fun getLocation() {
        // validatePermissionsLocation()
        fusedLocationClient?.requestLocationUpdates(locationRequest, callbabck, null)
    }

}

interface locationListener {
    fun locationResponse(locationResult: LocationResult)
}