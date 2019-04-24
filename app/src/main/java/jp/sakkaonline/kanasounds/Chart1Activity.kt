package jp.sakkaonline.kanasounds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

val HiraganaList: List<String> = listOf(
    "あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ",
    "さ", "し", "す", "せ", "そ", "た", "ち", "つ", "て", "と", "な", "に", "ぬ", "ね", "の"
)
val KatakanaList: List<String> = listOf(
    "ア", "イ", "ウ", "エ", "オ", "カ", "キ", "ク", "ケ", "コ",
    "サ", "シ", "ス", "セ", "ソ", "タ", "チ", "ツ", "テ", "ト", "ナ", "ニ", "ヌ", "ネ", "ノ"
)

class Chart1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart1)

        // 戻るボタンを用意
        // 進むボタンを用意
        // mMenuLanguage / mVoiceType / mLearningCharacter チェック
        // Loading ... を表示
        // 50音ボタンに、文字、音声をセット

    }
}
