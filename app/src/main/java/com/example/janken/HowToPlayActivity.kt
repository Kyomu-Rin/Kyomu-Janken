package com.example.janken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.janken.databinding.ActivityHowToPlayBinding

class HowToPlayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHowToPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_play)

        binding = ActivityHowToPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // もどるボタンをタップした時
        binding.button.setOnClickListener { onButtonTapped(it) }

        // 遊び方の文章表示
        binding.HowToPlayText.setText(
                "誰でも簡単に遊べるじゃんけんアプリです\n" +
                "①ゲーム画面でグーチョキパーのボタンをタップしよう！\n" +
                "②結果は勝ち負けのみですぐに表示されるよ\n" +
                "③また、結果に応じてCOMからコメント！" +
                "（一定の条件を満たすと特別なコメントが…??）\n" +
                "④さらに結果画面では勝負回数などの記録があるよ\n\n" +
                "Let's　enjoy　playing!!\n" +
                "きょむじゃんけんを心ゆくまでお楽しみください^^\n")
    }

    fun onButtonTapped (view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}