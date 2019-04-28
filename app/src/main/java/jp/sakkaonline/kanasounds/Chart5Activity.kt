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
val chart5HiraganaList: List<String> = listOf(
    "ぎゃ", "ぎゅ", "ぎょ","じゃ", "じゅ", "じょ",
    "ぢゃ", "ぢゅ", "ぢょ","びゃ", "びゅ", "びょ")
val chart5KatakanaList: List<String> = listOf(
    "ギャ", "ギュ", "ギョ","ジャ", "ジュ", "ジョ",
    "ヂャ", "ヂュ", "ヂョ","ビャ", "ビュ", "ビョ")
val chart5RomajiList: List<String> = listOf(
    "Gya", "Gyu", "Gyo","Jya", "Jyu", "Jyo",
    "Dya", "Dyu", "Dyo","Bya", "Byu", "Byo")
val chart5ManAList: MutableList<Int> = mutableListOf(
    R.raw.man_a_gya, R.raw.man_a_gyu, R.raw.man_a_gyo,R.raw.man_a_jya, R.raw.man_a_jyu, R.raw.man_a_jyo,
    R.raw.man_a_dya, R.raw.man_a_dyu, R.raw.man_a_dyo,R.raw.man_a_bya, R.raw.man_a_byu, R.raw.man_a_byo)
val chart5ManBList: MutableList<Int> = mutableListOf(
    R.raw.man_b_gya, R.raw.man_b_gyu, R.raw.man_b_gyo,R.raw.man_b_jya, R.raw.man_b_jyu, R.raw.man_b_jyo,
    R.raw.man_b_dya, R.raw.man_b_dyu, R.raw.man_b_dyo,R.raw.man_b_bya, R.raw.man_b_byu, R.raw.man_b_byo)
val chart5WomanAList: MutableList<Int> = mutableListOf(
    R.raw.woman_a_gya, R.raw.woman_a_gyu, R.raw.woman_a_gyo,R.raw.woman_a_jya, R.raw.woman_a_jyu, R.raw.woman_a_jyo,
    R.raw.woman_a_dya, R.raw.woman_a_dyu, R.raw.woman_a_dyo,R.raw.woman_a_bya, R.raw.woman_a_byu, R.raw.woman_a_byo)
val chart5WomanBList: MutableList<Int> = mutableListOf(
    R.raw.woman_b_gya, R.raw.woman_b_gyu, R.raw.woman_b_gyo,R.raw.woman_b_jya, R.raw.woman_b_jyu, R.raw.woman_b_jyo,
    R.raw.woman_b_dya, R.raw.woman_b_dyu, R.raw.woman_b_dyo,R.raw.woman_b_bya, R.raw.woman_b_byu, R.raw.woman_b_byo)

class Chart5Activity : AppCompatActivity() {

    var chart5SetKanaList: List<String> = chart5HiraganaList
    var chart5SetVoiceIds = mutableListOf<Int>()
    var chart5SetVoiceList: List<Int> = chart5ManAList
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
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart5)

        val toolbar5 = findViewById<View>(R.id.toolbar5) as Toolbar
        setSupportActionBar(toolbar5)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (mMenuLanguage){
            ENGLISH -> setTitle( ENGLISH_StartButtonList[4] )
            JPN_HIRAGANA -> setTitle( JPN_HIRAGANA_StartButtonList[4])
            JPN_KANJI -> setTitle(JPN_KANJI_StartButtonList[4])
            JPN_ROMAJI -> setTitle(JPN_ROMAJI_StartButtonList[4])
            else -> {
                Toast.makeText(applicationContext, "Please back to home and set menu language.",
                    Toast.LENGTH_LONG).show()
            }
        }
        when (mVoiceType) {
            MAN_A -> chart5SetVoiceList = chart5ManAList
            MAN_B -> chart5SetVoiceList = chart5ManBList
            WOMAN_A -> chart5SetVoiceList = chart5WomanAList
            WOMAN_B -> chart5SetVoiceList = chart5WomanBList
            else -> {
                Toast.makeText(
                    applicationContext, "Please back to home and set the voice type.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        when (mLearningCharacter) {
            HIRAGANA -> chart5SetKanaList = chart5HiraganaList
            KATAKANA -> chart5SetKanaList = chart5KatakanaList
            ROMAJI -> chart5SetKanaList = chart5RomajiList
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

        for (i in mButtons.indices) {
            mButtons[i].setText(chart5SetKanaList[i])
            chart5SetVoiceIds.add(i, soundpool.load(this, chart5SetVoiceList[i], 1))
            // load
            Log.d("KanaSounds", "load $i")
        }

        // load が終わってから
        soundpool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
            for (i in mButtons.indices) {
                mButtons[i].setOnClickListener {
                    soundpool.play(chart5SetVoiceIds[i], 1.0f, 1.0f, 0, 0, 1.0f)
                    Log.d("KanaSounds", "set $i")
                }
            }
        })

        Log.d("KanaSounds", "resume Chart5")
    }

    override fun onPause() {
        super.onPause()

        soundpool.release()
        soundpool = null

        Log.d("KanaSounds", "pause Chart5")
    }

}
