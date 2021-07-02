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
    }

    fun onButtonTapped (view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}