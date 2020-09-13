package kr.nodeline.choice.scenarios.splash

import io.reactivex.Single
import kr.nodeline.choice.Network.response.SplashResponse

interface SplashModel {
    fun intro(): Single<SplashResponse>
}