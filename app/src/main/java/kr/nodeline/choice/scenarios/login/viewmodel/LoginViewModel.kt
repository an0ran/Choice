package kr.nodeline.choice.scenarios.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinViewModel
import kr.nodeline.choice.Network.response.LoginResponse
import kr.nodeline.choice.scenarios.login.model.LoginModel

class LoginViewModel(private val model: LoginModel) : BaseKotlinViewModel() {

    private val TAG = "LoginViewModel"

    private val _loginResult = MutableLiveData<LoginResponse>()
    val loginResult: LiveData<LoginResponse>
        get() = _loginResult

    private val _authPhoneStep1Result = MutableLiveData<LoginResponse>()
    val authPhoneStep1Result: LiveData<LoginResponse>
        get() = _authPhoneStep1Result

    private val _authPhoneStep2Result = MutableLiveData<LoginResponse>()
    val authPhoneStep2Result: LiveData<LoginResponse>
        get() = _authPhoneStep2Result

    fun login(accessToken: String, joinType: String, loginType: String, appv: String) {
        addDisposable(model.login(accessToken, joinType, loginType, appv)
            .subscribeOn(Schedulers.io())
            .subscribe({
                _loginResult.postValue(it)
            }, {
                Log.d(TAG, "response error, message : ${it.localizedMessage}")
            })
        )
    }

    fun authPhoneStep1(phone: String) {
        addDisposable(model.authPhoneStep1(phone)
            .subscribeOn(Schedulers.io())
            .subscribe({
                _authPhoneStep1Result.postValue(it)
            }, {
                Log.d(TAG, "response error, message : ${it.localizedMessage}")
            })
        )
    }

    fun authPhoneStep2(accessToken: String, joinType: String, loginType: String, authId: Long, authKey: String, appv: String) {
        addDisposable(model.authPhoneStep2(accessToken, joinType, loginType, authId, authKey, appv)
            .subscribeOn(Schedulers.io())
            .subscribe({
                _authPhoneStep2Result.postValue(it)
            }, {
                Log.d(TAG, "response error, message : ${it.localizedMessage}")
            })
        )
    }

}