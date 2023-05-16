package com.example.deltatask1

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    lateinit var WordView: TextView
    lateinit var hscore: TextView
    lateinit var ClueView: TextView
    lateinit var enterTheWord: EditText
    lateinit var entertheClue: EditText
    lateinit var startbtn: Button
    lateinit var savebtn: Button
    lateinit var speedrun: Button
    lateinit var highscore1: TextView
    lateinit var gridbx1 : EditText
    lateinit var gridbx2 : EditText
    lateinit var daynightswitch : Switch
    lateinit var background : ConstraintLayout
    lateinit var classicbtn : Button


    var wordmap = HashMap<Int,String>()
    var cluemap = HashMap<Int,String>()
    var wordkey : Int = 0
    var cluekey : Int = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WordView = findViewById(R.id.textView2)
        ClueView = findViewById(R.id.textView3)
        enterTheWord = findViewById(R.id.editTextTextPersonName3)
        entertheClue = findViewById(R.id.editTextTextPersonName4)
        startbtn = findViewById(R.id.classicbtn)
        savebtn = findViewById(R.id.savebtn)
        gridbx1 = findViewById(R.id.editTextNumber1)
        gridbx2 = findViewById(R.id.editTextNumber2)
        daynightswitch = findViewById(R.id.daynightmode)
        background = findViewById(R.id.constraintback)
        classicbtn = findViewById(R.id.classicbtn)

        speedrun = findViewById(R.id.button3)

        daynightswitch.setOnCheckedChangeListener { buttonView, isChecked ->

            if(!isChecked)
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                background.setBackgroundResource(R.color.black)

            }

        }

        speedrun.isEnabled = false
        classicbtn.isEnabled = false

    savebtn.setOnClickListener {

        var wordName : String = enterTheWord.text.toString()
        var clueName : String = entertheClue.text.toString()
        wordmap.put(wordkey,wordName)
        cluemap.put(cluekey,clueName)
        Log.d("wordmap","$wordmap")
        Log.d("cluemap","$cluemap")
        startbtn.isEnabled = true
        entertheClue.text = null
        enterTheWord.text = null
        wordkey++
        cluekey++

        wordName.uppercase()

        if (wordmap.size == 0)
        {
            speedrun.isEnabled = false
            classicbtn.isEnabled = false
        }
        else
        {
            speedrun.isEnabled = true
            classicbtn.isEnabled = true
        }

    }
        speedrun.setOnClickListener {
            val dialogBinding1 = layoutInflater.inflate(R.layout.timeinput, null)
            val mydialog1 = Dialog(this)
            mydialog1.setContentView(dialogBinding1)
            mydialog1.setCancelable(true)
            mydialog1.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val timeinputed = dialogBinding1.findViewById<EditText>(R.id.alert_message)
            val okbtn = dialogBinding1.findViewById<Button>(R.id.alert_yes)

            okbtn.setOnClickListener {
                val gottenSecond: Long = timeinputed.text.toString().toLong()
                var wordName: String = enterTheWord.text.toString()
                var clueName: String = entertheClue.text.toString()
                val number1 = gridbx1.text.toString().toIntOrNull()
                val number2 = gridbx2.text.toString().toIntOrNull()

                val gridsize = number1?.times(number2 ?: 0) ?: 0
                Log.d("MainActivity1", "gridsize $gridsize is $gridsize}")

                var intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("wordName", wordName)
                intent.putExtra("clueName", clueName)
                intent.putExtra("gridsize", gridsize)
                intent.putExtra("number", number1)
                intent.putExtra("number2",number2)
                intent.putExtra("wordmap", wordmap)
                intent.putExtra("cluemap", cluemap)
                intent.putExtra("gottenSecond", gottenSecond)
                startActivity(intent)
                overridePendingTransition(R.anim.rtol, R.anim.rtol)

                Log.d("MainActivity1", "Letter at position $wordName is $wordName}")
                Log.d("MainActivity1", "clue letter is $clueName is $clueName}")
            }

            mydialog1.show()
        }

        classicbtn.setOnClickListener {
            var wordName : String = enterTheWord.text.toString().uppercase()
            var clueName : String = entertheClue.text.toString()
            val number1 = gridbx1.text.toString().toIntOrNull()
            val number2 = gridbx2.text.toString().toIntOrNull()

            val gridsize = number1?.times(number2 ?: 0) ?: 0
            Log.d("MainActivity1", "gridsize $gridsize is $gridsize}")

            var intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("wordName", wordName)
            intent.putExtra("clueName", clueName)
            intent.putExtra("gridsize",gridsize)
            intent.putExtra("number",number1)
            intent.putExtra("number2",number2)
            intent.putExtra("wordmap",wordmap)
            intent.putExtra("cluemap",cluemap)
            startActivity(intent)
            overridePendingTransition(R.anim.rtol, R.anim.rtol)

            Log.d("MainActivity1", "Letter at position $wordName is $wordName}")
            Log.d("MainActivity1", "clue letter is $clueName is $clueName}")

        }

    }

    fun timeInput() {

    }
}