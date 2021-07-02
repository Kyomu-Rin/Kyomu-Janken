package com.example.janken

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceManager
import com.example.janken.databinding.ActivityGameBinding
import com.example.janken.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // もう一度ボタンをタップした時
        binding.ReturnGameButton.setOnClickListener { onReturnGameButtonTapped(it) }

        // リセットボタンをタップした時
        binding.ResetButton.setOnClickListener { onResetButtonTapped(it) }

        // 負けた場合の画像の順番
        /*
        0 ->　任せなさいマン
        1 ->　てへぺろガール
        2 ->　てへぺろおじさん

        3 ->　おしとやか？女子
        4 ->　鼻高々マン

        5 ->　斜に構えるマン
        6 ->　舌打ち女性

        7 ->　やれやれマン

        8 ->　お祈り女性

        9 ->　陰ながら応援マン

        ラスト -> 神
         */

        // 負けた場合のコメント2次元配列
        var LoseComment = mutableListOf(
            // 0 ->　任せなさいマン
            mutableListOf("もう一度やりましょう！", "じゃんけん得意です", "まだまだ！\nこれからですよ！！", "諦めなければ勝てます！", "まだまだいきますよ～"),

            // 1 ->　てへぺろガール
            mutableListOf("かっちゃった", "かち！！", "まけたことないんだ～", "わたしとってもつよいんだ～", "かった！かった！！"),

            // 2 ->　てへぺろおじさん
            mutableListOf("わしの勝ち！", "楽勝や", "何回でも勝っちゃいます", "強くてごめんよー", "勝利！"),
        )

        // 勝った場合の画像の順番
        /*
        0 ->　悔しいマン
        1 ->　悔しい女性
        2 ->　魂抜けたマン
         */

        // 勝った場合のコメント配列
        var WinComment = mutableListOf(
            // 0 ->　悔しいマン
            mutableListOf("なんで負けたんだ！！！", "ありえねぇ！", "まぐれに違いない！", "もう一回だ！", "イカサマに違いない！"),

            // 1 ->　悔しい女性
            mutableListOf("まさか負けるなんて！！！", "うそでしょ", "たまたまだわ\nもう一回やるわよ", "キーーーーー", "ありえない！"),

            // 2 ->　魂抜けたマン
            mutableListOf("ま、まさか負けるなんて…", "………", "信じられない", "嘘だと言って…", "ありえない…"),
        )

        // 勝負回数・連敗回数を取得する
        val result = (Math.random() * 1000).toInt()
        val (gameCount, losedCount) = saveData(result)
        val secondIndex = (Math.random() * 5).toInt()

        // 勝負回数・連敗回数を表示
        binding.GameCount.setText(gameCount.toString())
        binding.LosedCount.setText(losedCount.toString())

        var loseLength = 3

        if (losedCount >= 10) {
            binding.ResultComment.setText("連敗回数10回目です\nなかなか勝てないんですね")
            binding.ResultPicture.setImageResource(R.drawable.pose_warau_kuchi_kakusu_woman)

            // 3 ->　おしとやか？女子
            LoseComment.add(mutableListOf("弱いですね", "1", "2", "3", "4"))

            // 4 ->　鼻高々マン
            LoseComment.add(mutableListOf("1", "2", "3", "4", "5"))

            loseLength = 5
        } else if (losedCount >= 50) {
            binding.ResultComment.setText("連敗回数50回目です\nなかなか勝てないんですね")
            binding.ResultPicture.setImageResource(R.drawable.pose_warau_kuchi_kakusu_woman)

            // 5 ->　斜に構えるマン
            LoseComment.add(mutableListOf("1", "2", "3", "4", "5"))

            // 6 ->　舌打ち女性
            LoseComment.add(mutableListOf("1", "2", "3", "4", "5"))

            loseLength = 7

        } else if (losedCount >= 100) {
            binding.ResultComment.setText("連敗回数100回目です\nなかなか勝てないんですね")
            binding.ResultPicture.setImageResource(R.drawable.pose_warau_kuchi_kakusu_woman)

            // 7 ->　やれやれマン
            LoseComment.add(mutableListOf("1", "2", "3", "4", "5"))

            loseLength = 8

        } else if (losedCount >= 200) {
            binding.ResultComment.setText("連敗回数200回目です\nなかなか勝てないんですね")
            binding.ResultPicture.setImageResource(R.drawable.pose_warau_kuchi_kakusu_woman)

            // 8 ->　お祈り女性
            LoseComment.add(mutableListOf("1", "2", "3", "4", "5"))

            loseLength = 9

        } else if (losedCount >= 500) {
            binding.ResultComment.setText("連敗回数500回目です\nなかなか勝てないんですね")
            binding.ResultPicture.setImageResource(R.drawable.pose_warau_kuchi_kakusu_woman)

            // 9 ->　陰ながら応援マン
            LoseComment.add(mutableListOf("1", "2", "3", "4", "5"))

            loseLength = 10

        } else if (losedCount == 1000) {
            binding.ResultComment.setText("連敗回数1000回です\n残す特別コメントはあと1つ\n\nあなたなら見れるはず！！")
            binding.ResultPicture.setImageResource(R.drawable.pose_makasenasai)

        } else if (losedCount == 10000) {
            binding.ResultComment.setText("連敗回数10000回です\nここまできたあなたは神です\nお疲れ様でした")
            binding.ResultPicture.setImageResource(R.drawable.internet_god)
        }

        if (losedCount == 0){
            // 勝利パターン
            val firstIndex = (Math.random() * WinComment.size).toInt()

            val text = WinComment[firstIndex][secondIndex]
            binding.ResultComment.setText(text)

            when(firstIndex) {
                0 -> binding.ResultPicture.setImageResource(R.drawable.pose_kuyashii_man)
                1 -> binding.ResultPicture.setImageResource(R.drawable.pose_kuyashii_woman)
                2 -> binding.ResultPicture.setImageResource(R.drawable.tamashii_nukeru_man)
            }

        } else if (losedCount != 10 && losedCount != 50 && losedCount != 100 && losedCount != 200
            && losedCount != 500 && losedCount != 1000 && losedCount != 10000) {
            // 敗北パターン
            val firstIndex = (Math.random() * loseLength).toInt()

            val text = LoseComment[firstIndex][secondIndex]
            binding.ResultComment.setText(text)

            binding.ResultText.setText("YOU LOSE")
            binding.ResultText.setTextColor(Color.BLUE)

            binding.ResultComment.setBackgroundColor(Color.rgb(204, 229, 255))

            when(firstIndex) {
                0 -> binding.ResultPicture.setImageResource(R.drawable.pose_makasenasai)
                1 -> binding.ResultPicture.setImageResource(R.drawable.tehepero5_girl)
                2 -> binding.ResultPicture.setImageResource(R.drawable.tehepero4_oldman)
                3 -> binding.ResultPicture.setImageResource(R.drawable.pose_warau_kuchi_kakusu_woman)
                4 -> binding.ResultPicture.setImageResource(R.drawable.jiman_hanashi_man)
                5 -> binding.ResultPicture.setImageResource(R.drawable.pose_syanikamaeru_man)
                6 -> binding.ResultPicture.setImageResource(R.drawable.pose_shitauchi_woman)
                7 -> binding.ResultPicture.setImageResource(R.drawable.pose_yareyare_man)
                8 -> binding.ResultPicture.setImageResource(R.drawable.pose_inoru_woman)
                9 -> binding.ResultPicture.setImageResource(R.drawable.kagenagara_ouuen_man)
            }
        }

    }

    // データの保存 and 連敗回数を取得
    private fun saveData (result: Int) : Pair<Int, Int> {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val gameCount = pref.getInt("GAME_COUNT", 0)
        val losedCount = pref.getInt("LOSED_COUNT", 0)

        val edtLosedCount: Int =
            when {
                // 1より大きいときは全て負け判定
                result > 1 -> losedCount + 1
                else -> 0
            }

        val editor = pref.edit()
        editor.putInt("GAME_COUNT", gameCount + 1)
            .putInt("LOSED_COUNT", edtLosedCount)
            .apply()

        return Pair(gameCount + 1, edtLosedCount)
    }


    fun onReturnGameButtonTapped (view: View?) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun onResetButtonTapped (view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
