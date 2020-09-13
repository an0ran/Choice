package kr.nodeline.choice.Network.services

import io.reactivex.Single
import kr.nodeline.choice.Network.response.LoginResponse
import kr.nodeline.choice.Network.response.SplashResponse
import retrofit2.http.*

interface MemberService {

    @GET("api/intro")
    fun intro(
        @Header("X-AUTH-TOKEN") token: String
    ) : Single<SplashResponse>

    @GET("api/member/oauth/{joinType}/{loginType}")
    fun login(
        @Header("Authorization") accessToken: String,  @Path("joinType") joinType: String, @Path("loginType") loginType: String,
        @Query("pushToken") pushToken: String, @Query("device") device: String, @Query("osv") osv: String, @Query("appv") appv: String
    ) : Single<LoginResponse>

    @GET("api/member/phone/{phone}")
    fun authPhoneStep1(
        @Path("phone") phone: String
    ) : Single<LoginResponse>

    @POST("api/member/phone/{joinType}/{loginType}/{authId}")
    fun authPhoneStep2(
        @Header("Authorization") accessToken: String,
        @Path("joinType") joinType: String, @Path("loginType") loginType: String, @Path("authId") authId: Long,
        @Query("authKey") authKey: String, @Query("device") device: String, @Query("osv") osv: String, @Query("appv") appv: String
    ) : Single<LoginResponse>
}