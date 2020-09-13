package kr.nodeline.choice.scenarios.splash

import io.reactivex.Single
import kr.nodeline.choice.commons.MyApplication
import kr.nodeline.choice.Network.RetrofitAPI
import kr.nodeline.choice.Network.response.SplashResponse

class SplashModelImpl: SplashModel {
    override fun intro(): Single<SplashResponse> {
        return RetrofitAPI.getInstance().memberService.intro(MyApplication.prefs.jwt.toString())
    }
}