package com.example.janken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.janken.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // スタートボタンをタップ
        binding.GameStartButton.setOnClickListener { onGameStartButtonTapped(it) }

        // あそびかたボタンをタップ
        binding.HowToPlayButton.setOnClickListener { onHowToPlayButtonTapped(it) }

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            clear()
        }
    }

    fun onGameStartButtonTapped (view: View?) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun onHowToPlayButtonTapped (view: View?) {
        val intent = Intent(this, HowToPlayActivity::class.java)
        startActivity(intent)
    }
}