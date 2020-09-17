package kr.nodeline.choice.scenarios.join.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.join_terms_detail_fragment.*

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.JoinKaraokeStep1FragmentBinding
import kr.nodeline.choice.databinding.JoinTermsDetailFragmentBinding
import kr.nodeline.choice.scenarios.join.viewmodel.JoinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class JoinTermsDetailFragment : BaseKotlinFragment<JoinTermsDetailFragmentBinding, JoinViewModel>() {

    private val TAG = "JoinTermsDetailFragment"

    override val layoutResourceId: Int
        get() = R.layout.join_terms_detail_fragment
    override val viewModel: JoinViewModel by viewModel()

    override fun initStartView() {

        tv_join_terms_detail_contents.movementMethod = ScrollingMovementMethod()

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
        btn_join_terms_back.setOnClickListener {
            fragmentManager!!.popBackStack()
        }
    }

}
