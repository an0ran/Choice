package kr.nodeline.choice.scenarios.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.nodeline.choice.R
import kr.nodeline.choice.scenarios.setting.view.SettingFragment

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportFragmentManager.beginTransaction().add(R.id.container_activity_setting, SettingFragment(), "SettingFragment")
            .commit()

    }
}
