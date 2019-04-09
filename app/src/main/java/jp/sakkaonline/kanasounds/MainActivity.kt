package jp.sakkaonline.kanasounds

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

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

val ENGLISH_VoiceTypeList = listOf<String>("Man A", "Man B", "Woman A", "Woman B")
val ENGLISH_LearningCharacterList = listOf<String>("Hiragana", "Katakana")
val JPN_HIRAGANA_VoiceTypeList = listOf<String>("だんせい Ａ", "だんせい Ｂ", "じょせい Ａ", "じょせい Ｂ")
val JPN_HIRAGANA_LearningCharacterList = listOf<String>("ひらがな", "かたかな")
val JPN_KANJI_VoiceTypeList = listOf<String>("男性 Ａ", "男性 Ｂ", "女性 A", "女性 B")
val JPN_KANJI_LearningCharacterList = listOf<String>("平仮名", "片仮名")
val JPN_ROMAJI_VoiceTypeList = listOf<String>("Dansei A", "Dansei B", "Jyosei A", "Jyosei B")
val JPN_ROMAJI_LearningCharacterList = listOf<String>("Hiragana", "Katakana")
val HiraganaList: List<String> = listOf(
    "あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ",
    "さ", "し", "す", "せ", "そ", "た", "ち", "つ", "て", "と", "な", "に", "ぬ", "ね", "の"
)
val KatakanaList: List<String> = listOf(
    "ア", "イ", "ウ", "エ", "オ", "カ", "キ", "ク", "ケ", "コ",
    "サ", "シ", "ス", "セ", "ソ", "タ", "チ", "ツ", "テ", "ト", "ナ", "ニ", "ヌ", "ネ", "ノ"
)

var mMenuLanguage = 0
var mVoiceType = 0
var mLearningCharacter = 0
val mMenuLanguageList = listOf("English", "ひらがな", "漢字", "Ro-ma Ji")
var mVoiceTypeList = listOf<String>()
var mLearningCharacterList = listOf<String>()


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize (read shared preference and set variables)
        init()

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
            }
        }

        mVoiceTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[0])
        mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[1])
        mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[2])
        mVoiceTypeSpinnerAdapter.add(mVoiceTypeList[3])
        mVoiceTypeSpinner.adapter = mVoiceTypeSpinnerAdapter
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
        mLearningCharacterSpinner.adapter = mLearningCharacterSpinnerAdapter
        mLearningCharacterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(arg0: AdapterView<*>?) {}
            override fun onItemSelected(parent3: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mLearningCharacter = mVoiceTypeSpinner.selectedItemPosition
                changeLearningCharacter()
            }
        }

        // Buttons
        val mStartButton1 = findViewById<Button>(R.id.start_button1) as Button
        mStartButton1.setOnClickListener { Log.d("KanaSounds", "Button1") }
        val mStartButton2 = findViewById<Button>(R.id.start_button2) as Button
        mStartButton2.setOnClickListener { Log.d("KanaSounds", "Button2") }
        val mStartButton3 = findViewById<Button>(R.id.start_button3) as Button
        mStartButton3.setOnClickListener { Log.d("KanaSounds", "Button3") }
        val mStartButton4 = findViewById<Button>(R.id.start_button4) as Button
        mStartButton4.setOnClickListener { Log.d("KanaSounds", "Button4") }
        val mStartButton5 = findViewById<Button>(R.id.start_button5) as Button
        mStartButton5.setOnClickListener { Log.d("KanaSounds", "Button5") }
    }

    override fun onResume() {
        super.onResume()

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



