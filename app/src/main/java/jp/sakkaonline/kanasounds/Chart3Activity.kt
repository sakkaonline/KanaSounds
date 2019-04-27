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

val chart3HiraganaList: List<String> = listOf(
    "ぱ", "ぴ", "ぷ", "ぺ", "ぽ")
val chart3KatakanaList: List<String> = listOf(
    "パ", "ピ", "プ", "ペ", "ポ")
val chart3RomajiList: List<String> = listOf(
    "Pa", "Pi", "Pu", "Pe", "Po")
var chart3SetKanaList = chart3HiraganaList

class Chart3Activity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_back -> {
            val intent = Intent(this, Chart2Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_go -> {
            val intent = Intent(this, Chart4Activity::class.java)
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
        setContentView(R.layout.activity_chart3)

        val toolbar3 = findViewById<View>(R.id.toolbar3) as Toolbar
        setSupportActionBar(toolbar3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (mLearningCharacter == HIRAGANA) {
            chart3SetKanaList = chart3HiraganaList
        } else if (mLearningCharacter == KATAKANA){
            chart3SetKanaList = chart3KatakanaList
        } else {
            chart3SetKanaList = chart3RomajiList
        }

        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.pa_button) as Button,
            findViewById<View>(R.id.pi_button) as Button,
            findViewById<View>(R.id.pu_button) as Button,
            findViewById<View>(R.id.pe_button) as Button,
            findViewById<View>(R.id.po_button) as Button
        )
        for (i in mButtons.indices){
            mButtons[i].setText(chart3SetKanaList[i])
            mButtons[i].setOnClickListener{ Log.d("KanaSounds", "$i")}
        }
        Log.d("KanaSounds", "finish")
    }
}