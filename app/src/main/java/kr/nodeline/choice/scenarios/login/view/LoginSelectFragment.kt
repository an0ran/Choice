package kr.nodeline.choice.scenarios.login.view

import kotlinx.android.synthetic.main.login_select_fragment.*

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.LoginSelectFragmentBinding
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginSelectFragment : BaseKotlinFragment<LoginSelectFragmentBinding, LoginViewModel>() {

    private val TAG = "LoginFragment"

    override val layoutResourceId: Int
        get() = R.layout.login_select_fragment
    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

        val fragment = LoginFragment()

        btn_login_select_customer.setOnClickListener {
            fragment.joinType = "customer"
            fragmentManager!!.beginTransaction().replace(R.id.container_login, fragment).commit()
        }

        btn_login_select_karaoke.setOnClickListener {
            val loginKaraokeFragment = LoginKaraokeFragment()
            fragmentManager!!.beginTransaction().replace(R.id.container_login, loginKaraokeFragment).commit()
        }

        btn_login_select_singer.setOnClickListener {
            fragment.joinType = "singer"
            fragmentManager!!.beginTransaction().replace(R.id.container_login, fragment).commit()
        }


    }

}
