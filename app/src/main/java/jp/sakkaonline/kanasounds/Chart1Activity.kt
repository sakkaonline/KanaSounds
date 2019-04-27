package jp.sakkaonline.kanasounds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

val Seion_Max_Number = 45
val Seion_HiraganaList: List<String> = listOf(
    "あ", "い", "う", "え", "お",
    "か", "き", "く", "け", "こ",
    "さ", "し", "す", "せ", "そ",
    "た", "ち", "つ", "て", "と",
    "な", "に", "ぬ", "ね", "の",
    "は", "ひ", "ふ", "へ", "ほ",
    "ま", "み", "む", "め", "も",
    "や", "ゆ", "よ",
    "ら", "り", "る", "れ", "ろ",
    "わ", "を", "ん")
val Seion_KatakanaList: List<String> = listOf(
    "ア", "イ", "ウ", "エ", "オ",
    "カ", "キ", "ク", "ケ", "コ",
    "サ", "シ", "ス", "セ", "ソ",
    "タ", "チ", "ツ", "テ", "ト",
    "ナ", "ニ", "ヌ", "ネ", "ノ",
    "ハ", "ヒ", "フ", "ヘ", "ホ",
    "マ", "ミ", "ム", "メ", "モ",
    "ヤ", "ユ", "ヨ",
    "ラ", "リ", "ル", "レ", "ロ",
    "ワ", "ヲ", "ン")
var mSet_KanaList = Seion_HiraganaList

class Chart1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart1)

        Log.d("KanaSounds","mLearningCharacter $mLearningCharacter")

        if (mLearningCharacter == KATAKANA){
            mSet_KanaList = Seion_KatakanaList
        } else {
            mSet_KanaList = Seion_HiraganaList
        }

        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.a_button) as Button,
            findViewById<View>(R.id.i_button) as Button,
            findViewById<View>(R.id.u_button) as Button,
            findViewById<View>(R.id.e_button) as Button,
            findViewById<View>(R.id.o_button) as Button,
            findViewById<View>(R.id.ka_button) as Button,
            findViewById<View>(R.id.ki_button) as Button,
            findViewById<View>(R.id.ku_button) as Button,
            findViewById<View>(R.id.ke_button) as Button,
            findViewById<View>(R.id.ko_button) as Button,
            findViewById<View>(R.id.sa_button) as Button,
            findViewById<View>(R.id.si_button) as Button,
            findViewById<View>(R.id.su_button) as Button,
            findViewById<View>(R.id.se_button) as Button,
            findViewById<View>(R.id.so_button) as Button,
            findViewById<View>(R.id.ta_button) as Button,
            findViewById<View>(R.id.ti_button) as Button,
            findViewById<View>(R.id.tu_button) as Button,
            findViewById<View>(R.id.te_button) as Button,
            findViewById<View>(R.id.to_button) as Button,
            findViewById<View>(R.id.na_button) as Button,
            findViewById<View>(R.id.ni_button) as Button,
            findViewById<View>(R.id.nu_button) as Button,
            findViewById<View>(R.id.ne_button) as Button,
            findViewById<View>(R.id.no_button) as Button,
            findViewById<View>(R.id.ha_button) as Button,
            findViewById<View>(R.id.hi_button) as Button,
            findViewById<View>(R.id.hu_button) as Button,
            findViewById<View>(R.id.he_button) as Button,
            findViewById<View>(R.id.ho_button) as Button,
            findViewById<View>(R.id.ma_button) as Button,
            findViewById<View>(R.id.mi_button) as Button,
            findViewById<View>(R.id.mu_button) as Button,
            findViewById<View>(R.id.me_button) as Button,
            findViewById<View>(R.id.mo_button) as Button,
            findViewById<View>(R.id.ya_button) as Button,
            findViewById<View>(R.id.yu_button) as Button,
            findViewById<View>(R.id.yo_button) as Button,
            findViewById<View>(R.id.ra_button) as Button,
            findViewById<View>(R.id.ri_button) as Button,
            findViewById<View>(R.id.ru_button) as Button,
            findViewById<View>(R.id.re_button) as Button,
            findViewById<View>(R.id.ro_button) as Button,
            findViewById<View>(R.id.wa_button) as Button,
            findViewById<View>(R.id.wo_button) as Button,
            findViewById<View>(R.id.n_button) as Button
            )

        for (i in mButtons.indices){
            mButtons[i].setText(mSet_KanaList[i])
            mButtons[i].setOnClickListener{Log.d("KanaSounds", "$i")}
            }

        Log.d("KanaSounds", "finish")
    }

    override fun onResume() {
        super.onResume()
        Log.d("KanaSounds", "resume Chart1")
    }

    override fun onPause() {
        super.onPause()
        Log.d("KanaSounds", "pause Chart1")
    }

}
