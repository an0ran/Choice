package kr.nodeline.choice.Network.response

import java.math.BigDecimal

data class SingerResponse (
    override var status: Int,
    override var message: String,
    override var timestamp: String,
    var `responseData`: Data
) : CommonResponse() {

    data class Data(
        var singer: Singer,
        var singers: List<Singer>,
        var favResult: Int
    )

    data class Singer(
        var id: Long,
        var name: String,
        var phone: String,
        var gender: String,
        var originalProfilePath: String,
        var thumbnailProfilePath: String,
        var birth: String,      // 생일
        var price: BigDecimal,  // 단가
        var body: String,       // 몸매
        var style: String,      // 스타일
        var personality: String,    // 성격
        var singingSkill: String,   // 노래실력
        var mainSong: String,       // 18번
        var introduction: String,   // 소개글
        var favCount: Int,
        var rating: Int,
        var ratingCount: Int,
        var region: String,         // 활동지역
        var isFav: Boolean,         // 내가 즐겨찾기 활성화 여부
        var images: List<KaraokeResponse.KaraokeImage>
    )

}