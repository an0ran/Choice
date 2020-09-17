package kr.nodeline.choice.scenarios.login.view

import android.content.Intent
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.ChangeBounds
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import gun0912.tedkeyboardobserver.BaseKeyboardObserver
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import gun0912.tedkeyboardobserver.TedRxKeyboardObserver
import kotlinx.android.synthetic.main.login_karaoke_fragment.*

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.commons.utils.hideKeyboard
import kr.nodeline.choice.databinding.LoginFragmentBinding
import kr.nodeline.choice.databinding.LoginKaraokeFragmentBinding
import kr.nodeline.choice.scenarios.join.JoinActivity
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import kr.nodeline.choice.scenarios.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginKaraokeFragment : BaseKotlinFragment<LoginKaraokeFragmentBinding, LoginViewModel>() {

    private val TAG = "LoginFragment"

    override val layoutResourceId: Int
        get() = R.layout.login_karaoke_fragment
    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {

        var constraintKeyboardOpen = ConstraintSet()
        constraintKeyboardOpen.clone(container_login_karaoke_fragment)
        constraintKeyboardOpen.setVerticalBias(iv_login_karaoke_logo.id, 0.05F)
        constraintKeyboardOpen.constrainPercentHeight(iv_login_karaoke_logo.id, 0.1F)
        constraintKeyboardOpen.constrainPercentWidth(iv_login_karaoke_logo.id, 0.2F)

        var constrainKeyboardOpenReset = ConstraintSet()
        constrainKeyboardOpenReset.clone(container_login_karaoke_fragment)

        TedRxKeyboardObserver(activity!!)
            .listen()
            .subscribe {
                if (it) {
                    constraintKeyboardOpen.applyTo(container_login_karaoke_fragment)
                } else {
                    constrainKeyboardOpenReset.applyTo(container_login_karaoke_fragment)
                }
            }

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

        view!!.setOnClickListener {
            view!!.hideKeyboard()
            et_login_karaoke_id.clearFocus()
            et_login_karaoke_password.clearFocus()
        }

        btn_login_karaoke_new.setOnClickListener {
            startActivity(Intent(context, JoinActivity::class.java))
        }

    }

}