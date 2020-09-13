package kr.nodeline.choice.scenarios.main

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.gms.location.LocationResult
import kotlinx.android.synthetic.main.activity_main.*
import kr.nodeline.choice.commons.MyApplication
import kr.nodeline.choice.R
import kr.nodeline.choice.commons.utils.GPSProvider
import kr.nodeline.choice.commons.utils.locationListener
import kr.nodeline.choice.scenarios.karaoke.view.KaraokeListFragment
import kr.nodeline.choice.scenarios.profile.view.customer.ProfileCustomerFragment
import kr.nodeline.choice.scenarios.profile.view.karaoke.ProfileKaraokeFragment
import kr.nodeline.choice.scenarios.profile.view.singer.ProfileSingerFragment
import kr.nodeline.choice.scenarios.singer.view.SingerListFragment

class MainActivity : AppCompatActivity() {

    var location: GPSProvider? = null

    private val _locationResult = MutableLiveData<Boolean>()
    val locationResult: LiveData<Boolean>
        get() = _locationResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        location = GPSProvider(this, object : locationListener {
            override fun locationResponse(locationResult: LocationResult) {
                val latitude = locationResult.lastLocation.latitude.toString()
                val longitude = locationResult.lastLocation.longitude.toString()
                if (latitude.isNotEmpty()) {
                    MyApplication.prefs.latitude = latitude
                    MyApplication.prefs.longitude = longitude
                    val geocoder = Geocoder(applicationContext)
                    val address = geocoder.getFromLocation(latitude.toDouble(), longitude.toDouble(), 1)[0]
                    tv_main_title.text = (if(address.subLocality != null) address.subLocality else address.locality) + " " + address.thoroughfare
                    _locationResult.postValue(true)
                } else {
                    tv_main_title.text = "위치가 정확하지 않음"
                    _locationResult.postValue(false)
                }
            }
        })

        val joinType = MyApplication.prefs.joinType
        if (joinType == "singer") {
            if (location!!.validatePermissionBackgroundLocation()) {
                location!!.getLocation()
            } else {
                location!!.permissionBackGroundLocationRequest()
            }
        } else {
            if (location!!.validatePermissionFineLocation() || location!!.validatePermissionBackgroundLocation()) {
                location!!.getLocation()
            } else {
                location!!.permissionAllRequest()
            }
        }

        locationResult.observe(this, Observer {
            if (it) {
                location?.stopUpdateLocation()

            } else {
                // 위치를 허용해야 앱 사용 가능

            }
        })

        val karaokeListFragment = KaraokeListFragment()
        val singerListFragment = SingerListFragment()
        val profileFragment: Fragment?

        supportFragmentManager.beginTransaction().add(R.id.container_main, karaokeListFragment, "KaraokeListFragment").commit()

        profileFragment = when (joinType) {
            "karaoke" -> {
                ProfileKaraokeFragment()
            }
            "singer" -> {
                ProfileSingerFragment()
            }
            else -> {
                ProfileCustomerFragment()
            }
        }

        btn_main_tab_karaoke.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_main, karaokeListFragment, "KaraokeListFragment").commit()
            btn_main_tab_karaoke.setImageResource(R.drawable.main_karaoke_pressed)
            btn_main_tab_singer.setImageResource(R.drawable.main_singer_unpressed)
            btn_main_tab_my.setImageResource(R.drawable.main_my_unpressed)
        }

        btn_main_tab_singer.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_main, singerListFragment, "SingerListFragment").commit()
            btn_main_tab_karaoke.setImageResource(R.drawable.main_karaoke_unpressed)
            btn_main_tab_singer.setImageResource(R.drawable.main_singer_pressed)
            btn_main_tab_my.setImageResource(R.drawable.main_my_unpressed)
        }

        btn_main_tab_my.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_main, profileFragment, "ProfileCustomerFragment").commit()
            btn_main_tab_karaoke.setImageResource(R.drawable.main_karaoke_unpressed)
            btn_main_tab_singer.setImageResource(R.drawable.main_singer_unpressed)
            btn_main_tab_my.setImageResource(R.drawable.main_my_pressed)
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            location!!.REQUEST_CODE_ALL_LOCATION -> {
                grantResults.apply {
                    if (this.isNotEmpty()) {
                        if (grantResults.size > 0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED) {
                            location!!.getLocation()
                            return
                        } else {
                            var dialog = AlertDialog.Builder(this@MainActivity)
                            dialog.setTitle("확인")
                            dialog.setMessage("위치를 허용해야 앱 사용이 가능합니다.")
                            dialog.setPositiveButton("확인") { dialog, _ ->
                                location!!.permissionAllRequest()
                                dialog.dismiss()
                            }
                            dialog.show()
                        }
                    } else {
                        location!!.permissionAllRequest()
                    }
                }
            }
            location!!.REQUEST_CODE_BACKGROUND_LOCATION -> {
                grantResults.apply {
                    if (this.isNotEmpty()) {
                        if (grantResults.size > 0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED) {
                            location!!.getLocation()
                            return
                        } else {
                            var dialog = AlertDialog.Builder(this@MainActivity)
                            dialog.setTitle("확인")
                            dialog.setMessage("위치를 허용해야 앱 사용이 가능합니다.\n(가수로 로그인 했을 경우 항상허용 필수)")
                            dialog.setPositiveButton("확인") { dialog, _ ->
                                location!!.permissionBackGroundLocationRequest()
                                dialog.dismiss()
                            }
                            dialog.show()
                        }
                    } else {
                        location!!.permissionBackGroundLocationRequest()
                    }
                }
            }
        }
    }

}
