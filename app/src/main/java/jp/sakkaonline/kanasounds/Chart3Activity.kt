package jp.sakkaonline.kanasounds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Chart3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart3)

        val mMenuLanguage = intent.getIntExtra("mMenuLanguage",0)
        val mVoiceType = intent.getIntExtra("mVoiceType",0)
        val mLearningCharacter = intent.getIntExtra("mLearningCharacter",0)
    }
}
