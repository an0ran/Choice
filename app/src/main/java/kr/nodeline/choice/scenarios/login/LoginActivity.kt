package kr.nodeline.choice.scenarios.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kakao.auth.Session
import kr.nodeline.choice.R
import kr.nodeline.choice.scenarios.login.view.LoginSelectFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        supportFragmentManager.beginTransaction().replace(R.id.container_login,
            LoginSelectFragment()
        ).commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
