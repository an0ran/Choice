package kr.nodeline.choice.scenarios.setting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.SettingMyPaymentFragmentBinding
import kr.nodeline.choice.databinding.SettingPasswordFragmentBinding
import kr.nodeline.choice.scenarios.setting.viewmodel.SettingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingPasswordFragment : BaseKotlinFragment<SettingPasswordFragmentBinding, SettingViewModel>() {

    private val TAG = "SettingPasswordFragment"

    override val layoutResourceId: Int
        get() = R.layout.setting_password_fragment
    override val viewModel: SettingViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }


}
