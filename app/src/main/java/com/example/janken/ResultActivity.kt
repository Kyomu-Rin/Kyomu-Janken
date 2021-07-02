package com.example.janken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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