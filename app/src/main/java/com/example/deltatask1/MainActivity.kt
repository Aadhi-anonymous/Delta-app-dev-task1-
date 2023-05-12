package com.example.deltatask1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var WordView: TextView
    lateinit var hscore: TextView
    lateinit var ClueView: TextView
    lateinit var enterTheWord: EditText
    lateinit var entertheClue: EditText
    lateinit var startbtn: Button
    lateinit var highscore1: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WordView = findViewById(R.id.textView2)
        ClueView = findViewById(R.id.textView3)
        enterTheWord = findViewById(R.id.editTextTextPersonName3)
        entertheClue = findViewById(R.id.editTextTextPersonName4)
        startbtn = findViewById(R.id.button2)

        // Disable the button initially
        startbtn.isEnabled = false

        // Add a TextWatcher to the word EditText field
        enterTheWord.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // Enable or disable the button based on whether the word EditText field is empty or not
                startbtn.isEnabled = !enterTheWord.text.toString().isEmpty()
            }
        })

        entertheClue.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // Enable or disable the button based on whether the word EditText field is empty or not
                startbtn.isEnabled = !entertheClue.text.toString().isEmpty()
            }
        })

        startbtn.setOnClickListener{

            var wordName : String = enterTheWord.text.toString()
            var clueName : String = entertheClue.text.toString()

            var intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("wordName", wordName)
            intent.putExtra("clueName", clueName)
            startActivity(intent)
            overridePendingTransition(R.anim.rtol, R.anim.rtol)

            Log.d("MainActivity1", "Letter at position $wordName is $wordName}")
            Log.d("MainActivity1", "clue letter is $clueName is $clueName}")
        }

    }
}