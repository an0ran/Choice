package kr.nodeline.choice.scenarios.singer.view

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.SingerDetailFragmentBinding
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SingerDetailFragment : BaseKotlinFragment<SingerDetailFragmentBinding, LoginViewModel>() {

    private val TAG = "SingerDetailFragment"

    override val layoutResourceId: Int
        get() = R.layout.singer_detail_fragment
    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

}
