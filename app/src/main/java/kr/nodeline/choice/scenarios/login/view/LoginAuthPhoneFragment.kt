package kr.nodeline.choice.scenarios.login.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.login_auth_phone_fragment.*
import kr.nodeline.choice.scenarios.main.MainActivity
import kr.nodeline.choice.commons.MyApplication

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.LoginAuthPhoneFragmentBinding
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginAuthPhoneFragment : BaseKotlinFragment<LoginAuthPhoneFragmentBinding, LoginViewModel>() {

    private val TAG = "LoginAuthPhoneFragment"

    override val layoutResourceId: Int
        get() = R.layout.login_auth_phone_fragment
    override val viewModel: LoginViewModel by viewModel()

    var joinType = "customer"
    var loginType = "kakao"
    var accessToken = ""

    var authId: Long = 0L

    lateinit var timer: CountDownTimer

    override fun initStartView() {

        et_login_auth_phone_phoneNum.requestFocus()
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et_login_auth_phone_phoneNum, 0)

    }

    override fun initDataBinding() {

        viewModel.authPhoneStep1Result.observe(this, Observer {
            if (it.status == 200) {

                val dialog = AlertDialog.Builder(context)
                dialog.setMessage("인증번호를 발송하였습니다.")
                dialog.setPositiveButton("확인") { dialog, _ ->
                    dialog.dismiss()
                }
                dialog.show()

                et_login_auth_phone_phoneNum.isEnabled = false
                btn_login_auth_phone_req.isEnabled = false
                et_login_auth_phone_authNum.isInvisible = false
                btn_login_auth_phone_done.isInvisible = false
                tv_login_auth_phone_msg.isInvisible = false
                authId = it.responseData.authId

                val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(et_login_auth_phone_authNum, 0)

                timer = object : CountDownTimer(160 * 1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        tv_login_auth_phone_msg.text = "${millisUntilFinished / 1000}초 이후 재전송 가능"
                    }
                    override fun onFinish() {
                        btn_login_auth_phone_req.isEnabled = true
                        btn_login_auth_phone_req.text = "재전송"
                        tv_login_auth_phone_msg.text = "재전송 해주세요."
                    }
                }
                timer.start()

            } else {
                Toast.makeText(context!!, "${it.message}", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.authPhoneStep2Result.observe(this, Observer {
            if (it.status == 200) {
                MyApplication.prefs.jwt = it.responseData.accessJwt
                startActivity(Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                this.activity!!.finish()
            } else {
                Toast.makeText(context!!, "${it.message}", Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun initAfterBinding() {

        btn_login_auth_phone_req.setOnClickListener {
            if (et_login_auth_phone_phoneNum.text.isNullOrEmpty()) {
                Toast.makeText(context!!, "휴대폰 번호를 입력해 주세요.", Toast.LENGTH_LONG).show()
            } else {
                viewModel.authPhoneStep1(et_login_auth_phone_phoneNum.text.toString())
            }
        }

        btn_login_auth_phone_done.setOnClickListener {
            if (et_login_auth_phone_authNum.text.isNullOrEmpty()) {
                Toast.makeText(context!!, "인증번호를 입력해 주세요.", Toast.LENGTH_LONG).show()
            } else {
                val info = context!!.packageManager.getPackageInfo(context!!.packageName, 0)
                viewModel.authPhoneStep2(accessToken, joinType, loginType, authId, et_login_auth_phone_authNum.text.toString(), info.versionName)
            }
        }

    }

    override fun onDestroy() {
        if (timer != null) {
            timer.cancel()
        }
        super.onDestroy()
    }

}
