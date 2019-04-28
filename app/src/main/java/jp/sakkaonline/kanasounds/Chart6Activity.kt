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

val chart6HiraganaList: List<String> = listOf(
    "ぴゃ", "ぴゅ", "ぴょ")
val chart6KatakanaList: List<String> = listOf(
    "ピャ", "ピュ", "ピョ")
val chart6RomajiList: List<String> = listOf(
    "Pya", "Pyu", "Pyo")
val chart6ManAList: MutableList<Int> = mutableListOf(
    R.raw.man_a_pya, R.raw.man_a_pyu, R.raw.man_a_pyo)
val chart6ManBList: MutableList<Int> = mutableListOf(
    R.raw.man_b_pya, R.raw.man_b_pyu, R.raw.man_b_pyo)
val chart6WomanAList: MutableList<Int> = mutableListOf(
    R.raw.woman_a_pya, R.raw.woman_a_pyu, R.raw.woman_a_pyo)
val chart6WomanBList: MutableList<Int> = mutableListOf(
    R.raw.woman_b_pya, R.raw.woman_b_pyu, R.raw.woman_b_pyo)

class Chart6Activity : AppCompatActivity() {

    var chart6SetKanaList: List<String> = chart6HiraganaList
    var chart6SetVoiceIds = mutableListOf<Int>()
    var chart6SetVoiceList: List<Int> = chart6ManAList
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
            val intent = Intent(this, Chart5Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_next -> {
            val intent = Intent(this, Chart1Activity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart6)

        val toolbar6 = findViewById<View>(R.id.toolbar6) as Toolbar
        setSupportActionBar(toolbar6)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (mMenuLanguage){
            ENGLISH -> setTitle( ENGLISH_StartButtonList[5] )
            JPN_HIRAGANA -> setTitle( JPN_HIRAGANA_StartButtonList[5])
            JPN_KANJI -> setTitle(JPN_KANJI_StartButtonList[5])
            JPN_ROMAJI -> setTitle(JPN_ROMAJI_StartButtonList[5])
            else -> {
                Toast.makeText(applicationContext, "Please back to home and set menu language.",
                    Toast.LENGTH_LONG).show()
            }
        }
        when (mVoiceType) {
            MAN_A -> chart6SetVoiceList = chart6ManAList
            MAN_B -> chart6SetVoiceList = chart6ManBList
            WOMAN_A -> chart6SetVoiceList = chart6WomanAList
            WOMAN_B -> chart6SetVoiceList = chart6WomanBList
            else -> {
                Toast.makeText(
                    applicationContext, "Please back to home and set the voice type.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        when (mLearningCharacter) {
            HIRAGANA -> chart6SetKanaList = chart6HiraganaList
            KATAKANA -> chart6SetKanaList = chart6KatakanaList
            ROMAJI -> chart6SetKanaList = chart6RomajiList
            else -> {
                Toast.makeText(
                    applicationContext, "Please back to home and set the learning language.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        Log.d("KanaSounds", "chart5 onCreate finished")
    }
    override fun onResume() {
        super.onResume()

        soundpool = SoundPool.Builder()
            .setAudioAttributes(audioattributes)
            .setMaxStreams(2)
            .build()

        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.pya_button) as Button,
            findViewById<View>(R.id.pyu_button) as Button,
            findViewById<View>(R.id.pyo_button) as Button
        )

        for (i in mButtons.indices) {
            mButtons[i].setText(chart6SetKanaList[i])
            chart6SetVoiceIds.add(i, soundpool.load(this, chart6SetVoiceList[i], 1))
            // load
            Log.d("KanaSounds", "load $i")
        }

        // load が終わってから
        soundpool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
            for (i in mButtons.indices) {
                mButtons[i].setOnClickListener {
                    soundpool.play(chart6SetVoiceIds[i], 1.0f, 1.0f, 0, 0, 1.0f)
                    Log.d("KanaSounds", "set $i")
                }
            }
        })

        Log.d("KanaSounds", "resume Chart6")
    }

    override fun onPause() {
        super.onPause()

        soundpool.release()
        soundpool = null

        Log.d("KanaSounds", "pause Chart6")
    }

}

