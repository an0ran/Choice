package kr.nodeline.choice.scenarios.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinViewModel
import kr.nodeline.choice.Network.response.SplashResponse

class SplashViewModel(private val model: SplashModel): BaseKotlinViewModel() {

    private val TAG = "SplashViewModel"

    private val _splashResult = MutableLiveData<SplashResponse>()
    val splashResult: LiveData<SplashResponse>
        get() = _splashResult

    fun splash() {
        addDisposable(model.intro()
            .subscribeOn(Schedulers.io())
            .subscribe({
                _splashResult.postValue(it)
            }, {
                Log.d(TAG, "response error, message : ${it.localizedMessage}")
            })
        )
    }

}