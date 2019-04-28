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

val chart4HiraganaList: List<String> = listOf(
    "きゃ", "きゅ", "きょ",
    "しゃ", "しゅ", "しょ",
    "ちゃ", "ちゅ", "ちょ",
    "にゃ", "にゅ", "にょ",
    "ひゃ", "ひゅ", "ひょ",
    "みゃ", "みゅ", "みょ",
    "りゃ", "りゅ", "りょ")
val chart4KatakanaList: List<String> = listOf(
    "キャ", "キュ", "キョ",
    "シャ", "シュ", "ショ",
    "チャ", "チュ", "チョ",
    "ニャ", "ニュ", "ニョ",
    "ヒャ", "ヒュ", "ヒョ",
    "ミャ", "ミュ", "ミョ",
    "リャ", "リュ", "リョ")
val chart4RomajiList: List<String> = listOf(
    "Kya", "Kyu", "Kyo",
    "Sya", "Syu", "Syo",
    "Tya", "Tyu", "Tyo",
    "Nya", "Nyu", "Nyo",
    "Hya", "Hyu", "Hyo",
    "Mya", "Myu", "Myo",
    "Rya", "Ryu", "Ryo")
var chart4SetKanaList = chart4HiraganaList

class Chart4Activity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_prev -> {
            val intent = Intent(this, Chart3Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_next -> {
            val intent = Intent(this, Chart5Activity::class.java)
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
        setContentView(R.layout.activity_chart4)

        val toolbar4 = findViewById<View>(R.id.toolbar4) as Toolbar
        setSupportActionBar(toolbar4)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (mLearningCharacter == HIRAGANA) {
            chart4SetKanaList = chart4HiraganaList
        } else if (mLearningCharacter == KATAKANA){
            chart4SetKanaList = chart4KatakanaList
        } else {
            chart4SetKanaList = chart4RomajiList
        }

        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.kya_button) as Button,
            findViewById<View>(R.id.kyu_button) as Button,
            findViewById<View>(R.id.kyo_button) as Button,
            findViewById<View>(R.id.sya_button) as Button,
            findViewById<View>(R.id.syu_button) as Button,
            findViewById<View>(R.id.syo_button) as Button,
            findViewById<View>(R.id.tya_button) as Button,
            findViewById<View>(R.id.tyu_button) as Button,
            findViewById<View>(R.id.tyo_button) as Button,
            findViewById<View>(R.id.nya_button) as Button,
            findViewById<View>(R.id.nyu_button) as Button,
            findViewById<View>(R.id.nyo_button) as Button,
            findViewById<View>(R.id.hya_button) as Button,
            findViewById<View>(R.id.hyu_button) as Button,
            findViewById<View>(R.id.hyo_button) as Button,
            findViewById<View>(R.id.mya_button) as Button,
            findViewById<View>(R.id.myu_button) as Button,
            findViewById<View>(R.id.myo_button) as Button,
            findViewById<View>(R.id.rya_button) as Button,
            findViewById<View>(R.id.ryu_button) as Button,
            findViewById<View>(R.id.ryo_button) as Button
        )

        for (i in mButtons.indices){
            mButtons[i].setText(chart4SetKanaList[i])
            mButtons[i].setOnClickListener{ Log.d("KanaSounds", "$i")}
        }
        Log.d("KanaSounds", "finish")
    }
}
