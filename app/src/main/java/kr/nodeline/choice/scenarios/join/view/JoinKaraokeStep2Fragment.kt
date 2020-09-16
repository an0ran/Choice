package kr.nodeline.choice.scenarios.join.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.join_karaoke_step2_fragment.*

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.JoinKaraokeStep1FragmentBinding
import kr.nodeline.choice.databinding.JoinKaraokeStep2FragmentBinding
import kr.nodeline.choice.scenarios.join.viewmodel.JoinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class JoinKaraokeStep2Fragment : BaseKotlinFragment<JoinKaraokeStep2FragmentBinding, JoinViewModel>() {

    private val TAG = "JoinKaraokeStep2Fragment"

    override val layoutResourceId: Int
        get() = R.layout.join_karaoke_step2_fragment
    override val viewModel: JoinViewModel by viewModel()

    var isIdConfirm = false

    override fun initStartView() {

        et_join_karaoke_step2_id.textChanges()
            .subscribe {
                if (isIdConfirm) {
                    isIdConfirm = false
                }
                updateNextButton()
            }
        et_join_karaoke_step2_pw.textChanges()
            .subscribe {
                updateNextButton()
            }
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

        btn_join_karaoke_step2_back.setOnClickListener {
            fragmentManager!!.popBackStack()
        }

        btn_join_karaoke_step2_idConfirm.setOnClickListener {
            isIdConfirm = true
            updateNextButton()
        }

        btn_join_karaoke_step2_next.setOnClickListener {
            val inputId = et_join_karaoke_step2_id.text.toString()
            val inputPw = et_join_karaoke_step2_pw.text.toString()
            if (!inputId.isNullOrEmpty() && !inputPw.isNullOrEmpty() && isIdConfirm) {
                var joinKaraokeStep3Fragment = JoinKaraokeStep3Fragment()
                joinKaraokeStep3Fragment.loginId = inputId
                joinKaraokeStep3Fragment.loginPw = inputPw

                fragmentManager!!.beginTransaction().replace(R.id.container_activity_join, joinKaraokeStep3Fragment, "JoinKaraokeStep3Fragment").addToBackStack(null).commit()
            }
        }

    }

    private fun updateNextButton() {
        if (!et_join_karaoke_step2_id.text.isNullOrEmpty() && !et_join_karaoke_step2_pw.text.isNullOrEmpty() && isIdConfirm) {
            btn_join_karaoke_step2_next.setBackgroundColor(Color.parseColor("#FF18244F"))
            btn_join_karaoke_step2_next.isEnabled = true
        } else {
            btn_join_karaoke_step2_next.setBackgroundColor(Color.parseColor("#3618244F"))
            btn_join_karaoke_step2_next.isEnabled = false
        }
    }

}
