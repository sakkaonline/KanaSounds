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

val chart6HiraganaList: List<String> = listOf(
    "ぴゃ", "ぴゅ", "ぴょ")
val chart6KatakanaList: List<String> = listOf(
    "ピャ", "ピュ", "ピョ")
val chart6RomajiList: List<String> = listOf(
    "Pya", "Pyu", "Pyo")
var chart6SetKanaList = chart6HiraganaList

class Chart6Activity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_back -> {
            val intent = Intent(this, Chart5Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_go -> {
            val intent = Intent(this, Chart1Activity::class.java)
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
        setContentView(R.layout.activity_chart6)

        val toolbar6 = findViewById<View>(R.id.toolbar6) as Toolbar
        setSupportActionBar(toolbar6)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (mLearningCharacter == HIRAGANA) {
            chart6SetKanaList = chart6HiraganaList
        } else if (mLearningCharacter == KATAKANA){
            chart6SetKanaList = chart6KatakanaList
        } else {
            chart6SetKanaList = chart6RomajiList
        }

        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.pya_button) as Button,
            findViewById<View>(R.id.pyu_button) as Button,
            findViewById<View>(R.id.pyo_button) as Button
        )

        for (i in mButtons.indices){
        mButtons[i].setText(chart6SetKanaList[i])
        mButtons[i].setOnClickListener{ Log.d("KanaSounds", "$i")}
    }
    Log.d("KanaSounds", "finish")
}
}
