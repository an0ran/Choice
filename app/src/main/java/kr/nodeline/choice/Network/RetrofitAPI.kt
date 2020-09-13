package kr.nodeline.choice.Network

import kr.nodeline.choice.Network.services.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitAPI {

    private val serverIp = "https://master.nodeline.kr:8080/"

    companion object {
        private val ourInstance = RetrofitAPI()
        fun getInstance() = ourInstance
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(serverIp)
        .build()

    val memberService: MemberService = retrofit.create(MemberService::class.java)
    val singerService: SingerService = retrofit.create(SingerService::class.java)
    val karaokeService: KaraokeService = retrofit.create(KaraokeService::class.java)
    var choiceService: ChoiceService = retrofit.create(ChoiceService::class.java)
    var paymentService: PaymentService = retrofit.create(PaymentService::class.java)

}