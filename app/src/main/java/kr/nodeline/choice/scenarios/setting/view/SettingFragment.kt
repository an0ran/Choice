package kr.nodeline.choice.scenarios.setting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.SettingFragmentBinding
import kr.nodeline.choice.databinding.SingerDetailFragmentBinding
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import kr.nodeline.choice.scenarios.setting.viewmodel.SettingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingFragment : BaseKotlinFragment<SettingFragmentBinding, SettingViewModel>() {

    private val TAG = "SettingFragment"

    override val layoutResourceId: Int
        get() = R.layout.setting_fragment
    override val viewModel: SettingViewModel by viewModel()

    override fun initStartView() {
//dddd
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

}
