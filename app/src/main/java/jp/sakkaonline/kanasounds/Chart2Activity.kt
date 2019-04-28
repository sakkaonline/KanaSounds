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
val chart2HiraganaList: List<String> = listOf(
    "が", "ぎ", "ぐ", "げ", "ご","ざ", "じ", "ず", "ぜ", "ぞ",
    "だ", "ぢ", "づ", "で", "ど","ば", "び", "ぶ", "べ", "ぼ")
val chart2KatakanaList: List<String> = listOf(
    "ガ", "ギ", "グ", "ゲ", "ゴ","ザ", "ジ", "ズ", "ゼ", "ゾ",
    "ダ", "ヂ", "ヅ", "デ", "ド","バ", "ビ", "ブ", "ベ", "ボ")
val chart2RomajiList: List<String> = listOf(
    "Ga", "Gi", "Gu", "Ge", "Go","Za", "Zi", "Zu", "Ze", "Zo",
    "Da", "Di", "Du", "De", "Do","Ba", "Bi", "Bu", "Be", "Bo")
val chart2ManAList: MutableList<Int> = mutableListOf(
    R.raw.man_a_ga, R.raw.man_a_gi, R.raw.man_a_gu, R.raw.man_a_ge, R.raw.man_a_go,
    R.raw.man_a_za, R.raw.man_a_zi, R.raw.man_a_zu, R.raw.man_a_ze, R.raw.man_a_zo,
    R.raw.man_a_da, R.raw.man_a_di, R.raw.man_a_du, R.raw.man_a_de, R.raw.man_a_do,
    R.raw.man_a_ba, R.raw.man_a_bi, R.raw.man_a_bu, R.raw.man_a_be, R.raw.man_a_bo)
val chart2ManBList: MutableList<Int> = mutableListOf(
    R.raw.man_b_ga, R.raw.man_b_gi, R.raw.man_b_gu, R.raw.man_b_ge, R.raw.man_b_go,
    R.raw.man_b_za, R.raw.man_b_zi, R.raw.man_b_zu, R.raw.man_b_ze, R.raw.man_b_zo,
    R.raw.man_b_da, R.raw.man_b_di, R.raw.man_b_du, R.raw.man_b_de, R.raw.man_b_do,
    R.raw.man_b_ba, R.raw.man_b_bi, R.raw.man_b_bu, R.raw.man_b_be, R.raw.man_b_bo)
val chart2WomanAList: MutableList<Int> = mutableListOf(
    R.raw.woman_a_ga, R.raw.woman_a_gi, R.raw.woman_a_gu, R.raw.woman_a_ge, R.raw.woman_a_go,
    R.raw.woman_a_za, R.raw.woman_a_zi, R.raw.woman_a_zu, R.raw.woman_a_ze, R.raw.woman_a_zo,
    R.raw.woman_a_da, R.raw.woman_a_di, R.raw.woman_a_du, R.raw.woman_a_de, R.raw.woman_a_do,
    R.raw.woman_a_ba, R.raw.woman_a_bi, R.raw.woman_a_bu, R.raw.woman_a_be, R.raw.woman_a_bo)
val chart2WomanBList: MutableList<Int> = mutableListOf(
    R.raw.woman_b_ga, R.raw.woman_b_gi, R.raw.woman_b_gu, R.raw.woman_b_ge, R.raw.woman_b_go,
    R.raw.woman_b_za, R.raw.woman_b_zi, R.raw.woman_b_zu, R.raw.woman_b_ze, R.raw.woman_b_zo,
    R.raw.woman_b_da, R.raw.woman_b_di, R.raw.woman_b_du, R.raw.woman_b_de, R.raw.woman_b_do,
    R.raw.woman_b_ba, R.raw.woman_b_bi, R.raw.woman_b_bu, R.raw.woman_b_be, R.raw.woman_b_bo)

class Chart2Activity : AppCompatActivity() {

    var chart2SetKanaList: List<String> = chart2HiraganaList
    var chart2SetVoiceIds = mutableListOf<Int>()
    var chart2SetVoiceList: List<Int> = chart2ManAList
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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_prev -> {
            val intent = Intent(this, Chart1Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_next -> {
            val intent = Intent(this, Chart3Activity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart2)

        val toolbar2 = findViewById<View>(R.id.toolbar2) as Toolbar
        setSupportActionBar(toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (mMenuLanguage){
            ENGLISH -> setTitle( ENGLISH_StartButtonList[1] )
            JPN_HIRAGANA -> setTitle( JPN_HIRAGANA_StartButtonList[1])
            JPN_KANJI -> setTitle(JPN_KANJI_StartButtonList[1])
            JPN_ROMAJI -> setTitle(JPN_ROMAJI_StartButtonList[1])
            else -> {
                Toast.makeText(applicationContext, "Please back to home and set menu language.",
                    Toast.LENGTH_LONG).show()
            }
        }
        when (mVoiceType) {
            MAN_A -> chart2SetVoiceList = chart2ManAList
            MAN_B -> chart2SetVoiceList = chart2ManBList
            WOMAN_A -> chart2SetVoiceList = chart2WomanAList
            WOMAN_B -> chart2SetVoiceList = chart2WomanBList
            else -> {
                Toast.makeText(
                    applicationContext, "Please back to home and set the voice type.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        when (mLearningCharacter) {
            HIRAGANA -> chart2SetKanaList = chart2HiraganaList
            KATAKANA -> chart2SetKanaList = chart2KatakanaList
            ROMAJI -> chart2SetKanaList = chart2RomajiList
            else -> {
                Toast.makeText(
                    applicationContext, "Please back to home and set the learning language.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        Log.d("KanaSounds", "chart2 onCreate finished")
    }

    override fun onResume() {
        super.onResume()

        soundpool = SoundPool.Builder()
            .setAudioAttributes(audioattributes)
            .setMaxStreams(2)
            .build()

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

        for (i in mButtons.indices) {
            mButtons[i].setText(chart2SetKanaList[i])
            chart2SetVoiceIds.add(i, soundpool.load(this, chart2SetVoiceList[i], 1))
            // load
            Log.d("KanaSounds", "load $i")
        }

        // load が終わってから
        soundpool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
            for (i in mButtons.indices) {
                mButtons[i].setOnClickListener {
                    soundpool.play(chart2SetVoiceIds[i], 1.0f, 1.0f, 0, 0, 1.0f)
                    Log.d("KanaSounds", "set $i")
                }
            }
        })

        Log.d("KanaSounds", "resume Chart2")
    }

    override fun onPause() {
        super.onPause()

        soundpool.release()
        soundpool = null

        Log.d("KanaSounds", "pause Chart2")

    }

}