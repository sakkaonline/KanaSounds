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
val chart4HiraganaList: List<String> = listOf(
    "きゃ", "きゅ", "きょ","しゃ", "しゅ", "しょ",
    "ちゃ", "ちゅ", "ちょ","にゃ", "にゅ", "にょ",
    "ひゃ", "ひゅ", "ひょ","みゃ", "みゅ", "みょ",
    "りゃ", "りゅ", "りょ")
val chart4KatakanaList: List<String> = listOf(
    "キャ", "キュ", "キョ","シャ", "シュ", "ショ","チャ", "チュ", "チョ",
    "ニャ", "ニュ", "ニョ","ヒャ", "ヒュ", "ヒョ","ミャ", "ミュ", "ミョ",
    "リャ", "リュ", "リョ")
val chart4RomajiList: List<String> = listOf(
    "Kya", "Kyu", "Kyo","Sya", "Syu", "Syo","Tya", "Tyu", "Tyo",
    "Nya", "Nyu", "Nyo","Hya", "Hyu", "Hyo","Mya", "Myu", "Myo",
    "Rya", "Ryu", "Ryo")
val chart4ManAList: MutableList<Int> = mutableListOf(
    R.raw.man_a_kya, R.raw.man_a_kyu, R.raw.man_a_kyo,R.raw.man_a_sha, R.raw.man_a_shu, R.raw.man_a_sho,
    R.raw.man_a_tya, R.raw.man_a_tyu, R.raw.man_a_tyo,R.raw.man_a_nya, R.raw.man_a_nyu, R.raw.man_a_nyo,
    R.raw.man_a_hya, R.raw.man_a_hyu, R.raw.man_a_hyo,R.raw.man_a_mya, R.raw.man_a_myu, R.raw.man_a_myo,
    R.raw.man_a_rya, R.raw.man_a_ryu, R.raw.man_a_ryo)
val chart4ManBList: MutableList<Int> = mutableListOf(
    R.raw.man_b_kya, R.raw.man_b_kyu, R.raw.man_b_kyo,R.raw.man_b_sha, R.raw.man_b_shu, R.raw.man_b_sho,
    R.raw.man_b_tya, R.raw.man_b_tyu, R.raw.man_b_tyo,R.raw.man_b_nya, R.raw.man_b_nyu, R.raw.man_b_nyo,
    R.raw.man_b_hya, R.raw.man_b_hyu, R.raw.man_b_hyo,R.raw.man_b_mya, R.raw.man_b_myu, R.raw.man_b_myo,
    R.raw.man_b_rya, R.raw.man_b_ryu, R.raw.man_b_ryo)
val chart4WomanAList: MutableList<Int> = mutableListOf(
    R.raw.woman_a_kya, R.raw.woman_a_kyu, R.raw.woman_a_kyo,R.raw.woman_a_sha, R.raw.woman_a_shu, R.raw.woman_a_sho,
    R.raw.woman_a_tya, R.raw.woman_a_tyu, R.raw.woman_a_tyo,R.raw.woman_a_nya, R.raw.woman_a_nyu, R.raw.woman_a_nyo,
    R.raw.woman_a_hya, R.raw.woman_a_hyu, R.raw.woman_a_hyo,R.raw.woman_a_mya, R.raw.woman_a_myu, R.raw.woman_a_myo,
    R.raw.woman_a_rya, R.raw.woman_a_ryu, R.raw.woman_a_ryo)
val chart4WomanBList: MutableList<Int> = mutableListOf(
    R.raw.woman_b_kya, R.raw.woman_b_kyu, R.raw.woman_b_kyo,R.raw.woman_b_sha, R.raw.woman_b_shu, R.raw.woman_b_sho,
    R.raw.woman_b_tya, R.raw.woman_b_tyu, R.raw.woman_b_tyo,R.raw.woman_b_nya, R.raw.woman_b_nyu, R.raw.woman_b_nyo,
    R.raw.woman_b_hya, R.raw.woman_b_hyu, R.raw.woman_b_hyo,R.raw.woman_b_mya, R.raw.woman_b_myu, R.raw.woman_b_myo,
    R.raw.woman_b_rya, R.raw.woman_b_ryu, R.raw.woman_b_ryo)

class Chart4Activity : AppCompatActivity() {

    var chart4SetKanaList: List<String> = chart4HiraganaList
    var chart4SetVoiceIds = mutableListOf<Int>()
    var chart4SetVoiceList: List<Int> = chart4ManAList
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
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart4)

        val toolbar4 = findViewById<View>(R.id.toolbar4) as Toolbar
        setSupportActionBar(toolbar4)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (mMenuLanguage){
            ENGLISH -> setTitle( ENGLISH_StartButtonList[3] )
            JPN_HIRAGANA -> setTitle( JPN_HIRAGANA_StartButtonList[3])
            JPN_KANJI -> setTitle(JPN_KANJI_StartButtonList[3])
            JPN_ROMAJI -> setTitle(JPN_ROMAJI_StartButtonList[3])
            else -> {
                Toast.makeText(applicationContext, "Please back to home and set menu language.",
                    Toast.LENGTH_LONG).show()
            }
        }
        when (mVoiceType) {
            MAN_A -> chart4SetVoiceList = chart4ManAList
            MAN_B -> chart4SetVoiceList = chart4ManBList
            WOMAN_A -> chart4SetVoiceList = chart4WomanAList
            WOMAN_B -> chart4SetVoiceList = chart4WomanBList
            else -> {
                Toast.makeText(
                    applicationContext, "Please back to home and set the voice type.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        when (mLearningCharacter) {
            HIRAGANA -> chart4SetKanaList = chart4HiraganaList
            KATAKANA -> chart4SetKanaList = chart4KatakanaList
            ROMAJI -> chart4SetKanaList = chart4RomajiList
            else -> {
                Toast.makeText(
                    applicationContext, "Please back to home and set the learning language.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        Log.d("KanaSounds", "chart4 onCreate finished")
    }

    override fun onResume() {
        super.onResume()

        soundpool = SoundPool.Builder()
            .setAudioAttributes(audioattributes)
            .setMaxStreams(2)
            .build()

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

        for (i in mButtons.indices) {
            mButtons[i].setText(chart4SetKanaList[i])
            chart4SetVoiceIds.add(i, soundpool.load(this, chart4SetVoiceList[i], 1))
            // load
            Log.d("KanaSounds", "load $i")
        }

        // load が終わってから
        soundpool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
            for (i in mButtons.indices) {
                mButtons[i].setOnClickListener {
                    soundpool.play(chart4SetVoiceIds[i], 1.0f, 1.0f, 0, 0, 1.0f)
                    Log.d("KanaSounds", "set $i")
                }
            }
        })

        Log.d("KanaSounds", "resume Chart4")
    }

    override fun onPause() {
        super.onPause()

        soundpool.release()
        soundpool = null

        Log.d("KanaSounds", "pause Chart4")
    }

}
