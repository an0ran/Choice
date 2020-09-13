package kr.nodeline.choice.Network.response

data class SplashResponse (
    override var message: String,
    override var status: Int,
    override var timestamp: String,
    var `responseData`: Data
) : CommonResponse() {

    data class Data(
        var id: Long
    )

}