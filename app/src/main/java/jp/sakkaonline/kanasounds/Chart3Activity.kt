package jp.sakkaonline.kanasounds

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast

// Lists for this activity
val chart3HiraganaList: List<String> = listOf(
    "ぱ", "ぴ", "ぷ", "ぺ", "ぽ")
val chart3KatakanaList: List<String> = listOf(
    "パ", "ピ", "プ", "ペ", "ポ")
val chart3RomajiList: List<String> = listOf(
    "Pa", "Pi", "Pu", "Pe", "Po")
val chart3ManAList: MutableList<Int> = mutableListOf(
    R.raw.man_a_pa, R.raw.man_a_pi, R.raw.man_a_pu, R.raw.man_a_pe, R.raw.man_a_po)
val chart3ManBList: MutableList<Int> = mutableListOf(
    R.raw.man_b_pa, R.raw.man_b_pi, R.raw.man_b_pu, R.raw.man_b_pe, R.raw.man_b_po)
val chart3WomanAList: MutableList<Int> = mutableListOf(
    R.raw.woman_a_pa, R.raw.woman_a_pi, R.raw.woman_a_pu, R.raw.woman_a_pe, R.raw.woman_a_po)
val chart3WomanBList: MutableList<Int> = mutableListOf(
    R.raw.woman_b_pa, R.raw.woman_b_pi, R.raw.woman_b_pu, R.raw.woman_b_pe, R.raw.woman_b_po)

class Chart3Activity : AppCompatActivity() {

    var chart3SetKanaList: List<String> = chart3HiraganaList
    var chart3SetVoiceIds = mutableListOf<Int>()
    var chart3SetVoiceList: List<Int> = chart3ManAList
    var audioattributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
        .build()
    var soundpool = SoundPool.Builder()
        .setAudioAttributes(audioattributes)
        .setMaxStreams(2)
        .build()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_prev -> {
            val intent = Intent(this, Chart2Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_next -> {
            val intent = Intent(this, Chart4Activity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart3)

        val toolbar3 = findViewById<View>(R.id.toolbar3) as Toolbar
        setSupportActionBar(toolbar3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (mMenuLanguage){
            ENGLISH -> setTitle( ENGLISH_StartButtonList[2] )
            JPN_HIRAGANA -> setTitle( JPN_HIRAGANA_StartButtonList[2])
            JPN_KANJI -> setTitle(JPN_KANJI_StartButtonList[2])
            JPN_ROMAJI -> setTitle(JPN_ROMAJI_StartButtonList[2])
            else -> {
                Toast.makeText(applicationContext, "Please back to home and set menu language.",
                    Toast.LENGTH_LONG).show()
            }
        }
        when (mVoiceType) {
            MAN_A -> chart3SetVoiceList = chart3ManAList
            MAN_B -> chart3SetVoiceList = chart3ManBList
            WOMAN_A -> chart3SetVoiceList = chart3WomanAList
            WOMAN_B -> chart3SetVoiceList = chart3WomanBList
            else -> {
                Toast.makeText(
                    applicationContext, "Please back to home and set the voice type.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        when (mLearningCharacter) {
            HIRAGANA -> chart3SetKanaList = chart3HiraganaList
            KATAKANA -> chart3SetKanaList = chart3KatakanaList
            ROMAJI -> chart3SetKanaList = chart3RomajiList
            else -> {
                Toast.makeText(
                    applicationContext, "Please back to home and set the learning language.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        Log.d("KanaSounds", "chart3 onCreate finished")
    }

    override fun onResume() {
        super.onResume()

        soundpool = SoundPool.Builder()
            .setAudioAttributes(audioattributes)
            .setMaxStreams(2)
            .build()

        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.pa_button) as Button,
            findViewById<View>(R.id.pi_button) as Button,
            findViewById<View>(R.id.pu_button) as Button,
            findViewById<View>(R.id.pe_button) as Button,
            findViewById<View>(R.id.po_button) as Button
        )

        for (i in mButtons.indices) {
            mButtons[i].setText(chart3SetKanaList[i])
            chart3SetVoiceIds.add(i, soundpool.load(this, chart3SetVoiceList[i], 1))
            // load
            Log.d("KanaSounds", "load $i")
        }

        // load が終わってから
        soundpool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
            for (i in mButtons.indices) {
                mButtons[i].setOnClickListener {
                    soundpool.play(chart3SetVoiceIds[i], 1.0f, 1.0f, 0, 0, 1.0f)
                    Log.d("KanaSounds", "set $i")
                }
            }
        })

        Log.d("KanaSounds", "resume Chart3")
    }

    override fun onPause() {
        super.onPause()

        soundpool.release()
        soundpool = null

        Log.d("KanaSounds", "pause Chart3")
    }

}