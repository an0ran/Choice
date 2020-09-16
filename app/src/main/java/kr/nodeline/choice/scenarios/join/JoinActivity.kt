package kr.nodeline.choice.scenarios.join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.nodeline.choice.R
import kr.nodeline.choice.scenarios.join.view.JoinKaraokeStep1Fragment
import kr.nodeline.choice.scenarios.login.view.LoginSelectFragment

class JoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        supportFragmentManager.beginTransaction().replace(R.id.container_activity_join,
            JoinKaraokeStep1Fragment()
        ).commit()
    }
}
