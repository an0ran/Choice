package kr.nodeline.choice.Network.services

import io.reactivex.Single
import kr.nodeline.choice.Network.response.SingerResponse
import retrofit2.http.*

interface SingerService {

    @GET("api/singer/list/{page}")
    fun getSingerList(
        @Header("X-AUTH-TOKEN") token: String, @Path("page") page: Int,
        @Query("size") size: Int, @Query("latitude") latitude: Double, @Query("longitude") longitude: Double
    ) : Single<SingerResponse>

    @GET("api/singer/{singerId}")
    fun getSingerOne(
        @Path("singerId") singerId: Long
    ) : Single<SingerResponse>

    @POST("api/singer/fav/{singerId}")
    fun favSinger(
        @Header("X-AUTH-TOKEN") token: String, @Path("singerId") singerId: Long
    ) : Single<SingerResponse>

    @POST("api/singer/rating/{joinId}")
    fun setRatingSinger(
        @Header("X-AUTH-TOKEN") token: String, @Path("joinId") joinId: Long, @Query("rating") rating: Int
    ) : Single<SingerResponse>

}