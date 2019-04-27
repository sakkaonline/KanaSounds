package jp.sakkaonline.kanasounds

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button

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
val Seion_RomajiList: List<String> = listOf(
    "A", "I", "U", "E", "O",
    "Ka", "Ki", "Ku", "Ke", "Ko",
    "Sa", "Si", "Su", "Se", "So",
    "Ta", "Ti", "Tu", "Te", "To",
    "Na", "Ni", "Nu", "Ne", "No",
    "Ha", "Hi", "Hu", "He", "Ho",
    "Ma", "Mi", "Mu", "Me", "Mo",
    "Ya", "Yu", "Yo",
    "Ra", "Ri", "Ru", "Re", "Ro",
    "Wa", "Wo", "N")
var mSet_Seion_KanaList = Seion_HiraganaList

class Chart1Activity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_back -> {
            val intent = Intent(this, Chart6Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_go -> {
            val intent = Intent(this, Chart2Activity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart1)

        val toolbar1 = findViewById<View>(R.id.toolbar1) as Toolbar
        setSupportActionBar(toolbar1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (mLearningCharacter == HIRAGANA) {
            mSet_Seion_KanaList = Seion_HiraganaList
        } else if (mLearningCharacter == KATAKANA){
            mSet_Seion_KanaList = Seion_KatakanaList
        } else {
            mSet_Seion_KanaList = Seion_RomajiList
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
            mButtons[i].setText(mSet_Seion_KanaList[i])
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
