package kr.nodeline.choice.scenarios.singer.view

import android.content.Intent
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import kotlinx.android.synthetic.main.profile_singer_fragment.*
import kotlinx.android.synthetic.main.singer_list_fragment.*
import kr.nodeline.choice.R
import kr.nodeline.choice.commons.BaseMVVM.BaseKotlinFragment
import kr.nodeline.choice.commons.MyApplication
import kr.nodeline.choice.databinding.SingerListFragmentBinding
import kr.nodeline.choice.scenarios.login.LoginActivity
import kr.nodeline.choice.scenarios.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SingerListFragment : BaseKotlinFragment<SingerListFragmentBinding, LoginViewModel>() {

    private val TAG = "SingerListFragment"

    override val layoutResourceId: Int
        get() = R.layout.singer_list_fragment
    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
        btn_logout.setOnClickListener {
            MyApplication.prefs.joinType = ""
            MyApplication.prefs.jwt = ""
            UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
                override fun onCompleteLogout() {
                    startActivity(Intent(context!!, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                    activity!!.finish()
                }
            })
        }
    }

}
