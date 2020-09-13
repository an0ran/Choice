package kr.nodeline.choice.scenarios.karaoke.view

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.KaraokeDetailFragmentBinding
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class KaraokeDetailFragment : BaseKotlinFragment<KaraokeDetailFragmentBinding, LoginViewModel>() {

    private val TAG = "KaraokeDetailFragment"

    override val layoutResourceId: Int
        get() = R.layout.karaoke_detail_fragment
    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

}
