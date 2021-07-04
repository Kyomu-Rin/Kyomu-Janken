package com.example.janken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceManager
import com.example.janken.databinding.ActivityMainBinding
import com.example.janken.databinding.ActivitySecretBinding

class SecretActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecretBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)

        binding = ActivitySecretBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.SecretReturnButton.setOnClickListener { onSecretReturnButtonTapped(it) }

        binding.ResetButton1.setOnClickListener { onResetButtonTapped(it) }
        binding.ResetButton2.setOnClickListener { onResetButtonTapped(it) }
        binding.ResetButton3.setOnClickListener { onResetButtonTapped(it) }
        binding.ResetButton4.setOnClickListener { onResetButtonTapped(it) }

        binding.ResetRunButton.setOnClickListener { onResetRunButtonTapped(it) }
    }

    fun onResetButtonTapped (view: View?) {
        val reset = 1
        val count = saveSecretCount(reset)
    }

    fun onResetRunButtonTapped (view: View?) {
        val reset = 1
        val count = saveSecretCount(reset)
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }

    fun onSecretReturnButtonTapped (view: View?) {
        val reset = 0
        val count = saveSecretCount(reset)

        if (count == 3) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("VALUE", 9999)
            startActivity(intent)

        } else {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveSecretCount (reset: Int) : Int {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val secretCount = pref.getInt("SECRET_COUNT", 0)


        val edtSecretCount: Int =
            when {
                reset == 0 -> secretCount + 1
                else -> 0
            }

        val editor = pref.edit()
        editor.putInt("SECRET_COUNT", edtSecretCount)
            .apply()

        return edtSecretCount
    }
}