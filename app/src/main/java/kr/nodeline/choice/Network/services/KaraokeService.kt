package kr.nodeline.choice.Network.services

import io.reactivex.Single
import kr.nodeline.choice.Network.response.KaraokeResponse
import retrofit2.http.*

interface KaraokeService {

    @GET("api/karaoke/{page}")
    fun getKaraokeList(
        @Path("page") page: Int, @Query("size") size: Int, @Query("latitude") latitude: Double, @Query("longitude") longitude: Double
    ) : Single<KaraokeResponse>

    @GET("api/karaoke/{karaokeId}")
    fun getKaraokeOne(
        @Path("karaokeId") karaokeId: Long
    ) : Single<KaraokeResponse>

    @POST("api/karaoke/fav/{karaokeId}")
    fun favKaraoke(
        @Path("karaokeId") karaokeId: Long, @Header("X-AUTH-TOKEN") token: String
    ) : Single<KaraokeResponse>

}