package com.example.janken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.janken.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // グー・チョキ・パーをそれぞれタップした時
        binding.GuButton.setOnClickListener{ onJankenButtonTapped(it) }
        binding.ChokiButton.setOnClickListener{ onJankenButtonTapped(it) }
        binding.PaButton.setOnClickListener{ onJankenButtonTapped(it) }

        // もどるボタンをタップした時
        binding.ReturnMainButton.setOnClickListener { onReturnMainButtonTapped(it) }
    }

    // グーチョキパーのいずれかのボタンをタップした時の処理
    fun onJankenButtonTapped (view: View?) {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }

    fun onReturnMainButtonTapped (view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}