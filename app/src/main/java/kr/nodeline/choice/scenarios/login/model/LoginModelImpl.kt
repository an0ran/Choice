package kr.nodeline.choice.scenarios.login.model

import android.os.Build
import io.reactivex.Single
import kr.nodeline.choice.commons.MyApplication
import kr.nodeline.choice.Network.RetrofitAPI
import kr.nodeline.choice.Network.response.LoginResponse

class LoginModelImpl: LoginModel {

    override fun login(
        accessToken: String,
        joinType: String,
        loginType: String,
        appv: String
    ): Single<LoginResponse> {
        return RetrofitAPI.getInstance().memberService.login("Bearer $accessToken", joinType, loginType,
            MyApplication.prefs.pushToken.toString(), Build.MODEL, "AOS ${Build.VERSION.RELEASE}", appv)
    }

    override fun authPhoneStep1(phone: String): Single<LoginResponse> {
        return RetrofitAPI.getInstance().memberService.authPhoneStep1(phone)
    }

    override fun authPhoneStep2(
        accessToken: String,
        joinType: String,
        loginType: String,
        authId: Long,
        authKey: String,
        appv: String
    ): Single<LoginResponse> {
        return RetrofitAPI.getInstance().memberService.authPhoneStep2("Bearer $accessToken", joinType, loginType, authId, authKey, Build.MODEL, "AOS ${Build.VERSION.RELEASE}", appv)
    }

}