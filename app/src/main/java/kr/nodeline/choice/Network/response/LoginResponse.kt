package kr.nodeline.choice.Network.response

data class LoginResponse (
    override var status: Int,
    override var message: String,
    override var timestamp: String,
    var `responseData`: Data
) : CommonResponse() {

    data class Data(
        var page: String,
        var authId: Long,
        var accessJwt: String
    )

}