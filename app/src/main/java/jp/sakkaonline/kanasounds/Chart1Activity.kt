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
import android.widget.Toast.LENGTH_SHORT

// Lists for this activity

val chart1HiraganaList: List<String> = listOf(
    "あ", "い", "う", "え", "お","か", "き", "く", "け", "こ",
    "さ", "し", "す", "せ", "そ","た", "ち", "つ", "て", "と",
    "な", "に", "ぬ", "ね", "の","は", "ひ", "ふ", "へ", "ほ",
    "ま", "み", "む", "め", "も","や", "ゆ", "よ",
    "ら", "り", "る", "れ", "ろ","わ", "を", "ん")
val chart1KatakanaList: List<String> = listOf(
    "ア", "イ", "ウ", "エ", "オ","カ", "キ", "ク", "ケ", "コ",
    "サ", "シ", "ス", "セ", "ソ","タ", "チ", "ツ", "テ", "ト",
    "ナ", "ニ", "ヌ", "ネ", "ノ","ハ", "ヒ", "フ", "ヘ", "ホ",
    "マ", "ミ", "ム", "メ", "モ","ヤ", "ユ", "ヨ",
    "ラ", "リ", "ル", "レ", "ロ","ワ", "ヲ", "ン")
val chart1RomajiList: List<String> = listOf(
    "A", "I", "U", "E", "O","Ka", "Ki", "Ku", "Ke", "Ko",
    "Sa", "Si", "Su", "Se", "So","Ta", "Ti", "Tu", "Te", "To",
    "Na", "Ni", "Nu", "Ne", "No","Ha", "Hi", "Hu", "He", "Ho",
    "Ma", "Mi", "Mu", "Me", "Mo","Ya", "Yu", "Yo",
    "Ra", "Ri", "Ru", "Re", "Ro","Wa", "Wo", "N")

val chart1ManAList: MutableList<Int> = mutableListOf(
    R.raw.man_a_a, R.raw.man_a_i, R.raw.man_a_u, R.raw.man_a_e, R.raw.man_a_o,
    R.raw.man_a_ka, R.raw.man_a_ki, R.raw.man_a_ku, R.raw.man_a_ke, R.raw.man_a_ko,
    R.raw.man_a_sa, R.raw.man_a_si, R.raw.man_a_su, R.raw.man_a_se, R.raw.man_a_so,
    R.raw.man_a_ta, R.raw.man_a_ti, R.raw.man_a_tu, R.raw.man_a_te, R.raw.man_a_to,
    R.raw.man_a_na, R.raw.man_a_ni, R.raw.man_a_nu, R.raw.man_a_ne, R.raw.man_a_no,
    R.raw.man_a_ha, R.raw.man_a_hi, R.raw.man_a_hu, R.raw.man_a_he, R.raw.man_a_ho,
    R.raw.man_a_ma, R.raw.man_a_mi, R.raw.man_a_mu, R.raw.man_a_me, R.raw.man_a_mo,
    R.raw.man_a_ya, R.raw.man_a_yu, R.raw.man_a_yo,
    R.raw.man_a_ra, R.raw.man_a_ri, R.raw.man_a_ru, R.raw.man_a_re, R.raw.man_a_ro,
    R.raw.man_a_wa, R.raw.man_a_wo, R.raw.man_a_n)
val chart1ManBList: MutableList<Int> = mutableListOf(
    R.raw.man_b_a, R.raw.man_b_i, R.raw.man_b_u, R.raw.man_b_e, R.raw.man_b_o,
    R.raw.man_b_ka, R.raw.man_b_ki, R.raw.man_b_ku, R.raw.man_b_ke, R.raw.man_b_ko,
    R.raw.man_b_sa, R.raw.man_b_si, R.raw.man_b_su, R.raw.man_b_se, R.raw.man_b_so,
    R.raw.man_b_ta, R.raw.man_b_ti, R.raw.man_b_tu, R.raw.man_b_te, R.raw.man_b_to,
    R.raw.man_b_na, R.raw.man_b_ni, R.raw.man_b_nu, R.raw.man_b_ne, R.raw.man_b_no,
    R.raw.man_b_ha, R.raw.man_b_hi, R.raw.man_b_hu, R.raw.man_b_he, R.raw.man_b_ho,
    R.raw.man_b_ma, R.raw.man_b_mi, R.raw.man_b_mu, R.raw.man_b_me, R.raw.man_b_mo,
    R.raw.man_b_ya, R.raw.man_b_yu, R.raw.man_b_yo,
    R.raw.man_b_ra, R.raw.man_b_ri, R.raw.man_b_ru, R.raw.man_b_re, R.raw.man_b_ro,
    R.raw.man_b_wa, R.raw.man_b_wo, R.raw.man_b_n)
val chart1WomanAList: MutableList<Int> = mutableListOf(
    R.raw.woman_a_a, R.raw.woman_a_i, R.raw.woman_a_u, R.raw.woman_a_e, R.raw.woman_a_o,
    R.raw.woman_a_ka, R.raw.woman_a_ki, R.raw.woman_a_ku, R.raw.woman_a_ke, R.raw.woman_a_ko,
    R.raw.woman_a_sa, R.raw.woman_a_si, R.raw.woman_a_su, R.raw.woman_a_se, R.raw.woman_a_so,
    R.raw.woman_a_ta, R.raw.woman_a_ti, R.raw.woman_a_tu, R.raw.woman_a_te, R.raw.woman_a_to,
    R.raw.woman_a_na, R.raw.woman_a_ni, R.raw.woman_a_nu, R.raw.woman_a_ne, R.raw.woman_a_no,
    R.raw.woman_a_ha, R.raw.woman_a_hi, R.raw.woman_a_hu, R.raw.woman_a_he, R.raw.woman_a_ho,
    R.raw.woman_a_ma, R.raw.woman_a_mi, R.raw.woman_a_mu, R.raw.woman_a_me, R.raw.woman_a_mo,
    R.raw.woman_a_ya, R.raw.woman_a_yu, R.raw.woman_a_yo,
    R.raw.woman_a_ra, R.raw.woman_a_ri, R.raw.woman_a_ru, R.raw.woman_a_re, R.raw.woman_a_ro,
    R.raw.woman_a_wa, R.raw.woman_a_wo, R.raw.woman_a_n)
val chart1WomanBList: MutableList<Int> = mutableListOf(
    R.raw.woman_b_a, R.raw.woman_b_i, R.raw.woman_b_u, R.raw.woman_b_e, R.raw.woman_b_o,
    R.raw.woman_b_ka, R.raw.woman_b_ki, R.raw.woman_b_ku, R.raw.woman_b_ke, R.raw.woman_b_ko,
    R.raw.woman_b_sa, R.raw.woman_b_si, R.raw.woman_b_su, R.raw.woman_b_se, R.raw.woman_b_so,
    R.raw.woman_b_ta, R.raw.woman_b_ti, R.raw.woman_b_tu, R.raw.woman_b_te, R.raw.woman_b_to,
    R.raw.woman_b_na, R.raw.woman_b_ni, R.raw.woman_b_nu, R.raw.woman_b_ne, R.raw.woman_b_no,
    R.raw.woman_b_ha, R.raw.woman_b_hi, R.raw.woman_b_hu, R.raw.woman_b_he, R.raw.woman_b_ho,
    R.raw.woman_b_ma, R.raw.woman_b_mi, R.raw.woman_b_mu, R.raw.woman_b_me, R.raw.woman_b_mo,
    R.raw.woman_b_ya, R.raw.woman_b_yu, R.raw.woman_b_yo,
    R.raw.woman_b_ra, R.raw.woman_b_ri, R.raw.woman_b_ru, R.raw.woman_b_re, R.raw.woman_b_ro,
    R.raw.woman_b_wa, R.raw.woman_b_wo, R.raw.woman_b_n)

class Chart1Activity : AppCompatActivity() {

    var chart1SetKanaList: List<String> = chart1HiraganaList
    var chart1SetVoiceIds = mutableListOf<Int>()
    var chart1SetVoiceList : List<Int> = chart1ManAList
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
            val intent = Intent(this, Chart6Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_next -> {
            val intent = Intent(this, Chart2Activity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart1)

        val toolbar1 = findViewById<View>(R.id.toolbar1) as Toolbar
        setSupportActionBar(toolbar1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (mVoiceType){
            MAN_A -> chart1SetVoiceList = chart1ManAList
            MAN_B -> chart1SetVoiceList = chart1ManBList
            WOMAN_A -> chart1SetVoiceList = chart1WomanAList
            WOMAN_B -> chart1SetVoiceList = chart1WomanBList
            else -> {
                Toast.makeText(applicationContext, "Please back to home and set the voice type.",
                    Toast.LENGTH_LONG).show()
            }
        }
        when (mLearningCharacter){
            HIRAGANA -> chart1SetKanaList = chart1HiraganaList
            KATAKANA -> chart1SetKanaList = chart1KatakanaList
            ROMAJI -> chart1SetKanaList = chart1RomajiList
            else -> {
                Toast.makeText(applicationContext, "Please back to home and set the learning language.",
                    Toast.LENGTH_LONG).show()
            }
        }
        Log.d("KanaSounds", "onCreate finished")
    }

    override fun onResume() {
        super.onResume()
        Log.d("KanaSounds", "resume Chart1")
        soundpool = SoundPool.Builder()
            .setAudioAttributes(audioattributes)
            .setMaxStreams(2)
            .build()
        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.a_button) as Button,
            findViewById<View>(R.id.i_button) as Button,
            findViewById<View>(R.id.u_button) as Button,
            findViewById<View>(R.id.e_button) as Button,
            findViewById<View>(R.id.o_button) as Button,
            findViewById<View>(R.id.ka_button) as Button,
            findViewById<View>(R.id.ki_button) as Button,
            findViewById<View>(R.id.ku_button) as Button,
            findViewById<View>(R.id.ke_button) as Button,
            findViewById<View>(R.id.ko_button) as Button,
            findViewById<View>(R.id.sa_button) as Button,
            findViewById<View>(R.id.si_button) as Button,
            findViewById<View>(R.id.su_button) as Button,
            findViewById<View>(R.id.se_button) as Button,
            findViewById<View>(R.id.so_button) as Button,
            findViewById<View>(R.id.ta_button) as Button,
            findViewById<View>(R.id.ti_button) as Button,
            findViewById<View>(R.id.tu_button) as Button,
            findViewById<View>(R.id.te_button) as Button,
            findViewById<View>(R.id.to_button) as Button,
            findViewById<View>(R.id.na_button) as Button,
            findViewById<View>(R.id.ni_button) as Button,
            findViewById<View>(R.id.nu_button) as Button,
            findViewById<View>(R.id.ne_button) as Button,
            findViewById<View>(R.id.no_button) as Button,
            findViewById<View>(R.id.ha_button) as Button,
            findViewById<View>(R.id.hi_button) as Button,
            findViewById<View>(R.id.hu_button) as Button,
            findViewById<View>(R.id.he_button) as Button,
            findViewById<View>(R.id.ho_button) as Button,
            findViewById<View>(R.id.ma_button) as Button,
            findViewById<View>(R.id.mi_button) as Button,
            findViewById<View>(R.id.mu_button) as Button,
            findViewById<View>(R.id.me_button) as Button,
            findViewById<View>(R.id.mo_button) as Button,
            findViewById<View>(R.id.ya_button) as Button,
            findViewById<View>(R.id.yu_button) as Button,
            findViewById<View>(R.id.yo_button) as Button,
            findViewById<View>(R.id.ra_button) as Button,
            findViewById<View>(R.id.ri_button) as Button,
            findViewById<View>(R.id.ru_button) as Button,
            findViewById<View>(R.id.re_button) as Button,
            findViewById<View>(R.id.ro_button) as Button,
            findViewById<View>(R.id.wa_button) as Button,
            findViewById<View>(R.id.wo_button) as Button,
            findViewById<View>(R.id.n_button) as Button
        )

        for (i in mButtons.indices) {
            mButtons[i].setText(chart1SetKanaList[i])
            chart1SetVoiceIds.add(i, soundpool.load(this, chart1SetVoiceList[i], 1))
            // load
            Log.d("KanaSounds", "set buttons $i")
        }

        // load が終わってから
        soundpool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
            for (i in mButtons.indices) {
                mButtons[i].setOnClickListener {
                    Log.d("KanaSounds", "$i")
                    soundpool.play(chart1SetVoiceIds[i], 1.0f, 1.0f, 0, 0, 1.0f)
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        Log.d("KanaSounds", "pause Chart1")
        soundpool.release();
        soundpool = null;
    }

}