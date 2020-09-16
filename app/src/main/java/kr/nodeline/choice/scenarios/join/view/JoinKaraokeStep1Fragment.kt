package kr.nodeline.choice.scenarios.join.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.join_karaoke_step1_fragment.*

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.JoinKaraokeStep1FragmentBinding
import kr.nodeline.choice.databinding.LoginFragmentBinding
import kr.nodeline.choice.scenarios.join.viewmodel.JoinViewModel
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class JoinKaraokeStep1Fragment : BaseKotlinFragment<JoinKaraokeStep1FragmentBinding, JoinViewModel>() {

    private val TAG = "JoinKaraokeFragment"

    override val layoutResourceId: Int
        get() = R.layout.join_karaoke_step1_fragment
    override val viewModel: JoinViewModel by viewModel()

    var serviceAndPersonalInfoAgree = false
    var personalInfoShareAgree = false

    override fun initStartView() {


    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

        btn_karaoke_step1_back.setOnClickListener {
            activity!!.finish()
        }

        btn_karaoke_step1_allAgree.setOnClickListener {
            if (serviceAndPersonalInfoAgree && personalInfoShareAgree) {
                serviceAndPersonalInfoAgree = false
                personalInfoShareAgree = false
            } else {
                serviceAndPersonalInfoAgree = true
                personalInfoShareAgree = true
            }

            setAgreeView()
        }

        btn_karaoke_step1_serviceAndPersonalInfoAgree.setOnClickListener {
            serviceAndPersonalInfoAgree = !serviceAndPersonalInfoAgree
            setAgreeView()
        }

        btn_karaoke_step1_personalInfoShareAgree.setOnClickListener {
            personalInfoShareAgree = !personalInfoShareAgree
            setAgreeView()
        }

        btn_karaoke_step1_serviceAndPersonalInfoAgree_detail.setOnClickListener {
            var joinTermsDetailFragment = JoinTermsDetailFragment()

            fragmentManager!!.beginTransaction().replace(R.id.container_activity_join, joinTermsDetailFragment).addToBackStack(null).commit()
        }

        btn_karaoke_step1_personalInfoShareAgree_detail.setOnClickListener {
            var joinTermsDetailFragment = JoinTermsDetailFragment()

            fragmentManager!!.beginTransaction().replace(R.id.container_activity_join, joinTermsDetailFragment, "JoinTermsDetailFragment").addToBackStack(null).commit()
        }

        btn_join_karaoke_step1_next.setOnClickListener {
            if (serviceAndPersonalInfoAgree && personalInfoShareAgree) {
                var joinKaraokeStep2Fragment = JoinKaraokeStep2Fragment()
                fragmentManager!!.beginTransaction().replace(R.id.container_activity_join, joinKaraokeStep2Fragment, "JoinKaraokeStep2Fragment").addToBackStack(null).commit()
            }
        }
    }

    private fun setAgreeView() {
        if (serviceAndPersonalInfoAgree) {
            btn_karaoke_step1_serviceAndPersonalInfoAgree.setImageResource(R.drawable.baseline_check_circle_black_18dp)
        } else {
            btn_karaoke_step1_serviceAndPersonalInfoAgree.setImageResource(R.drawable.baseline_check_circle_outline_black_18dp)
        }
        if (personalInfoShareAgree) {
            btn_karaoke_step1_personalInfoShareAgree.setImageResource(R.drawable.baseline_check_circle_black_18dp)
        } else {
            btn_karaoke_step1_personalInfoShareAgree.setImageResource(R.drawable.baseline_check_circle_outline_black_18dp)
        }
        if (serviceAndPersonalInfoAgree && personalInfoShareAgree) {
            btn_karaoke_step1_allAgree.setImageResource(R.drawable.baseline_check_circle_black_18dp)
            btn_join_karaoke_step1_next.setBackgroundColor(Color.parseColor("#FF18244F"))
            btn_join_karaoke_step1_next.isEnabled = true
        } else {
            btn_karaoke_step1_allAgree.setImageResource(R.drawable.baseline_check_circle_outline_black_18dp)
            btn_join_karaoke_step1_next.setBackgroundColor(Color.parseColor("#3618244F"))
            btn_join_karaoke_step1_next.isEnabled = false
        }
    }

}
