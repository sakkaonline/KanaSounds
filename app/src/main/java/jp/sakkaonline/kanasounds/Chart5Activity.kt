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

val chart5HiraganaList: List<String> = listOf(
    "ぎゃ", "ぎゅ", "ぎょ",
    "じゃ", "じゅ", "じょ",
    "ぢゃ", "ぢゅ", "ぢょ",
    "びゃ", "びゅ", "びょ")
val chart5KatakanaList: List<String> = listOf(
    "ギャ", "ギュ", "ギョ",
    "ジャ", "ジュ", "ジョ",
    "ヂャ", "ヂュ", "ヂョ",
    "ビャ", "ビュ", "ビョ")
val chart5RomajiList: List<String> = listOf(
    "Gya", "Gyu", "Gyo",
    "Jya", "Jyu", "Jyo",
    "Dya", "Dyu", "Dyo",
    "Bya", "Byu", "Byo")
var chart5SetKanaList = chart5HiraganaList

class Chart5Activity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_prev -> {
            val intent = Intent(this, Chart4Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_next -> {
            val intent = Intent(this, Chart6Activity::class.java)
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
        setContentView(R.layout.activity_chart5)

        val toolbar5 = findViewById<View>(R.id.toolbar5) as Toolbar
        setSupportActionBar(toolbar5)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (mLearningCharacter == HIRAGANA) {
            chart5SetKanaList = chart5HiraganaList
        } else if (mLearningCharacter == KATAKANA){
            chart5SetKanaList = chart5KatakanaList
        } else {
            chart5SetKanaList = chart5RomajiList
        }

        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.gya_button) as Button,
            findViewById<View>(R.id.gyu_button) as Button,
            findViewById<View>(R.id.gyo_button) as Button,
            findViewById<View>(R.id.jya_button) as Button,
            findViewById<View>(R.id.jyu_button) as Button,
            findViewById<View>(R.id.jyo_button) as Button,
            findViewById<View>(R.id.dya_button) as Button,
            findViewById<View>(R.id.dyu_button) as Button,
            findViewById<View>(R.id.dyo_button) as Button,
            findViewById<View>(R.id.bya_button) as Button,
            findViewById<View>(R.id.byu_button) as Button,
            findViewById<View>(R.id.byo_button) as Button
        )

        for (i in mButtons.indices){
            mButtons[i].setText(chart5SetKanaList[i])
            mButtons[i].setOnClickListener{ Log.d("KanaSounds", "$i")}
        }
        Log.d("KanaSounds", "finish")
    }
}
