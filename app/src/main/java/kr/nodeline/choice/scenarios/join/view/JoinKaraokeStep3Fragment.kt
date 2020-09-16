package kr.nodeline.choice.scenarios.join.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.join_karaoke_step2_fragment.*
import kotlinx.android.synthetic.main.join_karaoke_step3_fragment.*

import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.databinding.JoinKaraokeStep2FragmentBinding
import kr.nodeline.choice.databinding.JoinKaraokeStep3FragmentBinding
import kr.nodeline.choice.scenarios.join.viewmodel.JoinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class JoinKaraokeStep3Fragment : BaseKotlinFragment<JoinKaraokeStep3FragmentBinding, JoinViewModel>() {

    private val TAG = "JoinKaraokeStep3Fragment"

    override val layoutResourceId: Int
        get() = R.layout.join_karaoke_step3_fragment
    override val viewModel: JoinViewModel by viewModel()

    var loginId = ""
    var loginPw = ""

    override fun initStartView() {

        et_join_karaoke_step3_name.textChanges()
            .subscribe {
                updateNextButton()
            }

        et_join_karaoke_step3_address.textChanges()
            .subscribe {
                updateNextButton()
            }

        et_join_karaoke_step3_address_detail.textChanges()
            .subscribe {
                updateNextButton()
            }

        et_join_karaoke_step3_tel.textChanges()
            .subscribe {
                updateNextButton()
            }

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

        btn_join_karaoke_step3_back.setOnClickListener {
            fragmentManager!!.popBackStack()
        }

        btn_join_karaoke_step3_next.setOnClickListener {
            val name = et_join_karaoke_step3_name.text.toString()
            val address = et_join_karaoke_step3_address.text.toString()
            val addressDetail = et_join_karaoke_step3_address_detail.text.toString()
            val tel = et_join_karaoke_step3_tel.text.toString()
            if (!name.isNullOrEmpty() && !address.isNullOrEmpty() && !addressDetail.isNullOrEmpty() && !tel.isNullOrEmpty()) {
                val joinKaraokeStep4Fragment = JoinKaraokeStep4Fragment()
                joinKaraokeStep4Fragment.loginId = loginId
                joinKaraokeStep4Fragment.loginPw = loginPw
                joinKaraokeStep4Fragment.karaokeName = name
                joinKaraokeStep4Fragment.karaokeAddress = "$address $addressDetail"
                joinKaraokeStep4Fragment.karaokeTel = tel
                fragmentManager!!.beginTransaction().replace(R.id.container_activity_join, joinKaraokeStep4Fragment, "JoinKaraokeStep4Fragment").addToBackStack(null).commit()
            }
        }
    }

    private fun updateNextButton() {
        if (!et_join_karaoke_step3_name.text.isNullOrEmpty() && !et_join_karaoke_step3_address.text.isNullOrEmpty()
            && !et_join_karaoke_step3_address_detail.text.isNullOrEmpty() && !et_join_karaoke_step3_tel.text.isNullOrEmpty()) {
            btn_join_karaoke_step3_next.setBackgroundColor(Color.parseColor("#FF18244F"))
            btn_join_karaoke_step3_next.isEnabled = true
        } else {
            btn_join_karaoke_step3_next.setBackgroundColor(Color.parseColor("#3618244F"))
            btn_join_karaoke_step3_next.isEnabled = false
        }
    }

}
