package com.example.janken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.janken.databinding.ActivityMainBinding
import com.example.janken.databinding.ActivitySecretBinding

class SecretActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecretBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)

        binding = ActivitySecretBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}