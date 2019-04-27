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

val chart2HiraganaList: List<String> = listOf(
    "が", "ぎ", "ぐ", "げ", "ご",
    "ざ", "じ", "ず", "ぜ", "ぞ",
    "だ", "ぢ", "づ", "で", "ど",
    "ば", "び", "ぶ", "べ", "ぼ")
val chart2KatakanaList: List<String> = listOf(
    "ガ", "ギ", "グ", "ゲ", "ゴ",
    "ザ", "ジ", "ズ", "ゼ", "ゾ",
    "ダ", "ヂ", "ヅ", "デ", "ド",
    "バ", "ビ", "ブ", "ベ", "ボ")
val chart2RomajiList: List<String> = listOf(
    "Ga", "Gi", "Gu", "Ge", "Go",
    "Za", "Zi", "Zu", "Ze", "Zo",
    "Da", "Di", "Du", "De", "Do",
    "Ba", "Bi", "Bu", "Be", "Bo")
var chart2SetKanaList = chart2HiraganaList

class Chart2Activity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_back -> {
            val intent = Intent(this, Chart1Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_go -> {
            val intent = Intent(this, Chart3Activity::class.java)
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
        setContentView(R.layout.activity_chart2)

        val toolbar2 = findViewById<View>(R.id.toolbar2) as Toolbar
        setSupportActionBar(toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (mLearningCharacter == HIRAGANA) {
            chart2SetKanaList = chart2HiraganaList
        } else if (mLearningCharacter == KATAKANA){
            chart2SetKanaList = chart2KatakanaList
        } else {
            chart2SetKanaList = chart2RomajiList
        }

        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.ga_button) as Button,
            findViewById<View>(R.id.gi_button) as Button,
            findViewById<View>(R.id.gu_button) as Button,
            findViewById<View>(R.id.ge_button) as Button,
            findViewById<View>(R.id.go_button) as Button,
            findViewById<View>(R.id.za_button) as Button,
            findViewById<View>(R.id.zi_button) as Button,
            findViewById<View>(R.id.zu_button) as Button,
            findViewById<View>(R.id.ze_button) as Button,
            findViewById<View>(R.id.zo_button) as Button,
            findViewById<View>(R.id.da_button) as Button,
            findViewById<View>(R.id.di_button) as Button,
            findViewById<View>(R.id.du_button) as Button,
            findViewById<View>(R.id.de_button) as Button,
            findViewById<View>(R.id.do_button) as Button,
            findViewById<View>(R.id.ba_button) as Button,
            findViewById<View>(R.id.bi_button) as Button,
            findViewById<View>(R.id.bu_button) as Button,
            findViewById<View>(R.id.be_button) as Button,
            findViewById<View>(R.id.bo_button) as Button
        )

        for (i in mButtons.indices){
            mButtons[i].setText(chart2SetKanaList[i])
            mButtons[i].setOnClickListener{Log.d("KanaSounds", "$i")}
        }
        Log.d("KanaSounds", "finish")
    }
}
