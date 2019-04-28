package jp.sakkaonline.kanasounds

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

const val PREFS_NAME = "KanaSoundsSettingsFile"
const val MenuLanguageKEY = "menu_language"
const val VoiceTypeKEY = "voice_type"
const val LearningCharacterKEY = "learning_character"

const val ENGLISH = 0
const val JPN_HIRAGANA = 1
const val JPN_KANJI = 2
const val JPN_ROMAJI = 3

const val MAN_A = 0
const val MAN_B = 1
const val WOMAN_A = 2
const val WOMAN_B = 3

const val HIRAGANA = 0
const val KATAKANA = 1
const val ROMAJI = 2

val ENGLISH_VoiceTypeList = listOf<String>("Man A", "Man B", "Woman A", "Woman B")
val ENGLISH_LearningCharacterList = listOf<String>("Hiragana", "Katakana","Ro-ma Ji")
val ENGLISH_StartButtonList = listOf<String>(
    "1 Se i sounds","2 Da ku sounds","3 Han Da ku sounds",
    "4 Yo u sounds", "5 Yo u Da ku sounds", "6 Han Yo u Da ku sounds")
val JPN_HIRAGANA_VoiceTypeList = listOf<String>("だんせい Ａ", "だんせい Ｂ", "じょせい Ａ", "じょせい Ｂ")
val JPN_HIRAGANA_LearningCharacterList = listOf<String>("ひらがな", "かたかな","ろーまじ")
val JPN_HIRAGANA_StartButtonList = listOf<String>(
    "１　せいおん","２　だくおん","３　はんだくおん",
    "４　ようおん", "５　ようだくおん", "６　はんようだくおん")
val JPN_KANJI_VoiceTypeList = listOf<String>("男性 Ａ", "男性 Ｂ", "女性 A", "女性 B")
val JPN_KANJI_LearningCharacterList = listOf<String>("平仮名", "片仮名","ローマ字")
val JPN_KANJI_StartButtonList = listOf<String>(
    "１　清音","２　濁音","３　半濁音",
    "４　拗音", "５　拗濁音", "６　半拗濁音")
val JPN_ROMAJI_VoiceTypeList = listOf<String>("Dansei A", "Dansei B", "Jyosei A", "Jyosei B")
val JPN_ROMAJI_LearningCharacterList = listOf<String>("Hiragana", "Katakana","Ro-ma ji")
val JPN_ROMAJI_StartButtonList = listOf<String>(
    "1 Se i on","2 Da ku on","3 Han Da ku on",
    "4 Yo u on", "5 Yo u Da ku on", "6 Han Yo u Da ku on")

var mMenuLanguage = 0
var mVoiceType = 0
var mLearningCharacter = 0
val mMenuLanguageList = listOf("English", "ひらがな", "漢字", "Ro-ma Ji")
var mVoiceTypeList = listOf<String>()
var mLearningCharacterList = listOf<String>()
var mStartButtonList = ENGLISH_StartButtonList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize (read shared preference and set variables)
        init()

        // Buttons
        val mButtons = arrayOf(
            findViewById<Button>(R.id.start_button1),
            findViewById<Button>(R.id.start_button2),
            findViewById<Button>(R.id.start_button3),
            findViewById<Button>(R.id.start_button4),
            findViewById<Button>(R.id.start_button5),
            findViewById<Button>(R.id.start_button6)
        )
        when (mMenuLanguage){
            ENGLISH -> mStartButtonList = ENGLISH_StartButtonList
            JPN_HIRAGANA -> mStartButtonList = JPN_HIRAGANA_StartButtonList
            JPN_KANJI -> mStartButtonList = JPN_KANJI_StartButtonList
            JPN_ROMAJI -> mStartButtonList = JPN_ROMAJI_StartButtonList
            else -> {
                Toast.makeText(applicationContext, "Please set menu language.",
                    Toast.LENGTH_LONG).show()
            }
        }
        for (i in mButtons.indices) {
            mButtons[i].setText(mStartButtonList[i])
            Log.d("KanaSounds", "button setText $i")
        }

        // Spinners settings
        val mMenuLanguageSpinner = findViewById<View>(R.id.menu_language_spinner) as Spinner
        val mMenuLanguageSpinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        val mVoiceTypeSpinner = findViewById<View>(R.id.voice_type_spinner) as Spinner
        val mVoiceTypeSpinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        val mLearningCharacterSpinner = findViewById<View>(R.id.learning_character_spinner) as Spinner
        val mLearningCharacterSpinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)

        mMenuLanguageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mMenuLanguageSpinnerAdapter.add(mMenuLanguageList[0])
        mMenuLanguageSpinnerAdapter.add(mMenuLanguageList[1])
        mMenuLanguageSpinnerAdapter.add(mMenuLanguageList[2])
        mMenuLanguageSpinnerAdapter.add(mMenuLanguageList[3])
        mMenuLanguageSpinner.adapter = mMenuLanguageSpinnerAdapter
        mMenuLanguageSpinner.setSelection(mMenuLanguage)
        mMenuLanguageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(arg0: AdapterView<*>?) {}
            override fun onItemSelected(parent1: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mMenuLanguage = mMenuLanguageSpinner.selectedItemPosition
                changeMenuLanguage()
                mMenuLanguageSpinnerAdapter.clear()
                mMenuLanguageSpinnerAdapter.add(mMenuLanguageList[0])
                mMenuLanguageSpinnerAdapter.add(mMenuLanguageList[1])
                mMenuLanguageSpinnerAdapter.add(mMenuLanguageList[2])
                mMenuLanguageSpinnerAdapter.add(mMenuLanguageList[3])
                mVoiceTypeSpinnerAdapter.clear()
                mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[0])
                mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[1])
                mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[2])
                mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[3])
                mLearningCharacterSpinnerAdapter.clear()
                mLearningCharacterSpinnerAdapter.add(mLearningCharacterList[0])
                mLearningCharacterSpinnerAdapter.add(mLearningCharacterList[1])
                mLearningCharacterSpinnerAdapter.add(mLearningCharacterList[2])
                when (mMenuLanguage){
                    ENGLISH -> mStartButtonList = ENGLISH_StartButtonList
                    JPN_HIRAGANA -> mStartButtonList = JPN_HIRAGANA_StartButtonList
                    JPN_KANJI -> mStartButtonList = JPN_KANJI_StartButtonList
                    JPN_ROMAJI -> mStartButtonList = JPN_ROMAJI_StartButtonList
                    else -> {
                        Toast.makeText(applicationContext, "Please set menu language.",
                            Toast.LENGTH_LONG).show()
                    }
                }
                for (i in mButtons.indices) {
                    mButtons[i].setText(mStartButtonList[i])
                    Log.d("KanaSounds", "button setText $i")
                }
            }
        }

        mVoiceTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[0])
        mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[1])
        mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[2])
        mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[3])
        mVoiceTypeSpinner.adapter = mVoiceTypeSpinnerAdapter
        mVoiceTypeSpinner.setSelection(mVoiceType)
        mVoiceTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(arg0: AdapterView<*>?) {}
            override fun onItemSelected(parent2: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mVoiceType = mVoiceTypeSpinner.selectedItemPosition
                changeVoiceType()
            }
        }

        mLearningCharacterSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mLearningCharacterSpinnerAdapter.add(mLearningCharacterList[0])
        mLearningCharacterSpinnerAdapter.add(mLearningCharacterList[1])
        mLearningCharacterSpinnerAdapter.add(mLearningCharacterList[2])
        mLearningCharacterSpinner.adapter = mLearningCharacterSpinnerAdapter
        mLearningCharacterSpinner.setSelection(mLearningCharacter)
        mLearningCharacterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(arg0: AdapterView<*>?) {}
            override fun onItemSelected(parent3: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mLearningCharacter = mLearningCharacterSpinner.selectedItemPosition
                changeLearningCharacter()
            }
        }

        // Buttons
        findViewById<Button>(R.id.start_button1).setOnClickListener(){
            val intent = Intent(this, Chart1Activity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.start_button2).setOnClickListener(){
            val intent = Intent(this, Chart2Activity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.start_button3).setOnClickListener(){
            val intent = Intent(this, Chart3Activity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.start_button4).setOnClickListener(){
            val intent = Intent(this, Chart4Activity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.start_button5).setOnClickListener(){
            val intent = Intent(this, Chart5Activity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.start_button6).setOnClickListener(){
            val intent = Intent(this, Chart6Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // Buttons
        val mButtons = arrayOf(
            findViewById<View>(R.id.start_button1) as Button,
            findViewById<View>(R.id.start_button2) as Button,
            findViewById<View>(R.id.start_button3) as Button,
            findViewById<View>(R.id.start_button4) as Button,
            findViewById<View>(R.id.start_button5) as Button,
            findViewById<View>(R.id.start_button6) as Button)

        init()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun init(){

        val settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        mMenuLanguage = settings.getInt(MenuLanguageKEY, JPN_HIRAGANA)
        mVoiceType = settings.getInt(VoiceTypeKEY, MAN_A)
        mLearningCharacter = settings.getInt(LearningCharacterKEY, HIRAGANA)

        if (mMenuLanguage == ENGLISH) {
            mVoiceTypeList = ENGLISH_VoiceTypeList
            mLearningCharacterList = ENGLISH_LearningCharacterList

        } else if (mMenuLanguage == JPN_HIRAGANA) {
            mVoiceTypeList = JPN_HIRAGANA_VoiceTypeList
            mLearningCharacterList = JPN_HIRAGANA_LearningCharacterList
        } else if (mMenuLanguage == JPN_KANJI) {
            mVoiceTypeList = JPN_KANJI_VoiceTypeList
            mLearningCharacterList = JPN_KANJI_LearningCharacterList
        } else {
            mVoiceTypeList = JPN_ROMAJI_VoiceTypeList
            mLearningCharacterList = JPN_ROMAJI_LearningCharacterList
        }

        Log.d("KanaSounds", "init() done")
    }

    fun changeMenuLanguage(){
        val settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt(MenuLanguageKEY, mMenuLanguage)
        editor.commit()
        if (mMenuLanguage == ENGLISH) {
            mVoiceTypeList = ENGLISH_VoiceTypeList
            mLearningCharacterList = ENGLISH_LearningCharacterList
        } else if (mMenuLanguage == JPN_HIRAGANA) {
            mVoiceTypeList = JPN_HIRAGANA_VoiceTypeList
            mLearningCharacterList = JPN_HIRAGANA_LearningCharacterList
        } else if (mMenuLanguage == JPN_KANJI) {
            mVoiceTypeList = JPN_KANJI_VoiceTypeList
            mLearningCharacterList = JPN_KANJI_LearningCharacterList
        } else if (mMenuLanguage == JPN_ROMAJI) {
            mVoiceTypeList = JPN_ROMAJI_VoiceTypeList
            mLearningCharacterList = JPN_ROMAJI_LearningCharacterList
        }
        Log.d("KanaSounds", "changeMenuLanguage() done : $mMenuLanguage")
    }

    fun changeVoiceType(){
        val settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt(VoiceTypeKEY, mVoiceType)
        editor.commit()
        Log.d("KanaSounds", "changeVoiceType() done : $mVoiceType")
    }

    fun changeLearningCharacter(){
        val settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt(LearningCharacterKEY, mLearningCharacter)
        editor.commit()
        Log.d("KanaSounds", "changeLearningCharacter() done : $mLearningCharacter")
    }


}