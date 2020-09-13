package kr.nodeline.choice.Network.response

abstract class CommonResponse {
    abstract var status: Int
    abstract var message: String
    abstract var timestamp: String
}