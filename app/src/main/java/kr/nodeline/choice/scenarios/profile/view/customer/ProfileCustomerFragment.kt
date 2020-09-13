package kr.nodeline.choice.scenarios.profile.view.customer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import kotlinx.android.synthetic.main.profile_customer_fragment.*

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.commons.MyApplication
import kr.nodeline.choice.databinding.ProfileCustomerFragmentBinding
import kr.nodeline.choice.databinding.SingerDetailFragmentBinding
import kr.nodeline.choice.scenarios.login.LoginActivity
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileCustomerFragment : BaseKotlinFragment<ProfileCustomerFragmentBinding, LoginViewModel>() {

    private val TAG = "ProfileCustomerFragment"

    override val layoutResourceId: Int
        get() = R.layout.profile_customer_fragment
    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {


    }


}
