package kr.nodeline.choice.scenarios.login.model

import io.reactivex.Single
import kr.nodeline.choice.Network.response.LoginResponse

interface LoginModel {

    fun login(accessToken: String, joinType: String, loginType: String, appv: String): Single<LoginResponse>

    fun authPhoneStep1(phone: String): Single<LoginResponse>

    fun authPhoneStep2(accessToken: String, joinType: String, loginType: String, authId: Long, authKey: String, appv: String) : Single<LoginResponse>

}