package kr.nodeline.choice.Network.response

data class KaraokeResponse (
    override var status: Int,
    override var message: String,
    override var timestamp: String,
    var `responseData`: Data
) : CommonResponse() {

    data class Data(
        var karaoke: Karaoke,
        var karaokes: List<Karaoke>,
        // 즐겨찾기 결과 0: 제거, 1: 추가됨
        var favResult: Int
    )

    data class Karaoke(
        var karaokeId: Long,
        var name: String,
        var tel: String,
        var address: String,
        var introduction: String,
        var favCount: Int,
        var rating: Int,    // 별점 총 점수
        var ratingCount: Int,   // 별점 평가 수 ( rating / ratingCount ) = 1.0 ~ 5.0
        var thumbnailProfilePath: String,
        var originalProfilePath: String,
        var latitude: Double,
        var longitude: Double,
        var images: List<KaraokeImage>
    )

    data class KaraokeImage(
        var fileId: Long,
        var path: String,
        var order: Int // 순서
    )

}