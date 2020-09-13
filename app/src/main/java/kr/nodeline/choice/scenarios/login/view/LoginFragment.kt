package kr.nodeline.choice.scenarios.login.view

import android.content.Intent
import android.content.pm.PackageInfo
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.gms.location.LocationResult
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.login_fragment.*
import kr.nodeline.choice.scenarios.main.MainActivity
import kr.nodeline.choice.commons.MyApplication

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.commons.utils.GPSProvider
import kr.nodeline.choice.commons.utils.locationListener
import kr.nodeline.choice.databinding.LoginFragmentBinding
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseKotlinFragment<LoginFragmentBinding, LoginViewModel>() {

    private val TAG = "LoginFragment"

    override val layoutResourceId: Int
        get() = R.layout.login_fragment
    override val viewModel: LoginViewModel by viewModel()

    private lateinit var callback: SessionCallback

    var location: GPSProvider? = null



    var joinType = "customer"
    var loginType = "kakao"
    var accessToken = ""

    lateinit var info: PackageInfo

    override fun initStartView() {

        info = context!!.packageManager.getPackageInfo(context!!.packageName, 0)

        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    override fun initDataBinding() {

        viewModel.loginResult.observe(this, Observer {
            if (it.status == 200) {
                MyApplication.prefs.joinType = joinType
                MyApplication.prefs.jwt = it.responseData.accessJwt
                startActivity(Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                this.activity!!.finish()
            } else {
                Toast.makeText(context!!, "${it.message}", Toast.LENGTH_LONG).show()
                if (it.responseData.page == "auth_phone") {
                    val loginAuthPhoneFragment = LoginAuthPhoneFragment()
                    loginAuthPhoneFragment.accessToken = accessToken
                    loginAuthPhoneFragment.joinType = joinType
                    loginAuthPhoneFragment.loginType = loginType
                    fragmentManager!!.beginTransaction().replace(R.id.container_login, loginAuthPhoneFragment).addToBackStack(null).commit()
                }
            }
        })

    }

    override fun initAfterBinding() {

        btn_login_naver.setOnClickListener {

        }
    }



    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    private inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            // 로그인 세션이 열렸을 때
            UserManagement.getInstance().me( object : MeV2ResponseCallback() {
                override fun onSuccess(result: MeV2Response?) {
                    // 로그인이 성공했을 때
                    loginType = "kakao"
                    accessToken = Session.getCurrentSession().tokenInfo.accessToken
                    viewModel.login(accessToken, joinType, loginType, info.versionName)
                }

                override fun onSessionClosed(errorResult: ErrorResult?) {
                    // 로그인 도중 세션이 비정상적인 이유로 닫혔을 때
                    Toast.makeText(
                        context!!,
                        "세션이 닫혔습니다. 다시 시도해주세요 : ${errorResult.toString()}",
                        Toast.LENGTH_SHORT).show()
                }
            })
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            // 로그인 세션이 정상적으로 열리지 않았을 때
            if (exception != null) {
                com.kakao.util.helper.log.Logger.e(exception)
//                Toast.makeText(
//                    context!!,
//                    "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요 : $exception",
//                    Toast.LENGTH_SHORT).show()
            }
        }
    }

}
