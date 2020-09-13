package kr.nodeline.choice.scenarios.karaoke.view

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.KaraokeListFragmentBinding
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class KaraokeListFragment : BaseKotlinFragment<KaraokeListFragmentBinding, LoginViewModel>() {

    private val TAG = "KaraokeListFragment"

    override val layoutResourceId: Int
        get() = R.layout.karaoke_list_fragment
    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

}
