package kr.nodeline.choice.scenarios.profile.view.karaoke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.ProfileCustomerFragmentBinding
import kr.nodeline.choice.databinding.ProfileKaraokeFragmentBinding
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileKaraokeFragment : BaseKotlinFragment<ProfileKaraokeFragmentBinding, LoginViewModel>() {

    private val TAG = "ProfileKaraokeFragment"

    override val layoutResourceId: Int
        get() = R.layout.profile_karaoke_fragment
    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

}
