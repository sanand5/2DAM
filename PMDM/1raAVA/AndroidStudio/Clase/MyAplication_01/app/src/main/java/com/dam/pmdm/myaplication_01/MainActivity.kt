package com.dam.pmdm.myaplication_01

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var swich: Switch
    private lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        swich = findViewById(R.id.switch1)
        text = findViewById(R.id.textView)

        button.setOnClickListener(){
            text.text = "jejjejej"
        }

    }
}