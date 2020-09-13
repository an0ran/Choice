package kr.nodeline.choice.scenarios.profile.view.singer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.ProfileCustomerFragmentBinding
import kr.nodeline.choice.databinding.ProfileSingerFragmentBinding
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileSingerFragment : BaseKotlinFragment<ProfileSingerFragmentBinding, LoginViewModel>() {

    private val TAG = "ProfileSingerFragment"

    override val layoutResourceId: Int
        get() = R.layout.profile_singer_fragment
    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

}
