package kr.nodeline.choice.scenarios.splash

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.gms.location.LocationResult
import kotlinx.android.synthetic.main.activity_main.*
import kr.nodeline.choice.scenarios.main.MainActivity
import kr.nodeline.choice.R
import kr.nodeline.choice.commons.MyApplication
import kr.nodeline.choice.commons.utils.GPSProvider
import kr.nodeline.choice.commons.utils.locationListener
import kr.nodeline.choice.scenarios.login.LoginActivity
import java.security.MessageDigest

class SplashActivity : AppCompatActivity() {

    private val TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // getAppKeyHash()

        val model = SplashModelImpl()
        val viewModel = SplashViewModel(model)

        viewModel.run {
            splash()
        }

        viewModel.splashResult.observe(this, Observer {
            if (it.status == 200) {
                startActivity(Intent(applicationContext, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                finish()
            } else {
                startActivity(Intent(applicationContext, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                finish()
            }
        })

    }

    override fun onPause() {
        super.onPause()

        overridePendingTransition(0, 0)
    }

    // 앱의 해쉬 키 얻는 함수
    private fun getAppKeyHash() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e("Hash key", something)
            }
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString())
        }

    }
}
