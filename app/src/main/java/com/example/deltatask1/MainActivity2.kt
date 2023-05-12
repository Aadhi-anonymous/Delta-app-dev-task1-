package com.example.deltatask1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    lateinit var gridView: GridView
    lateinit var resultText: TextView
    lateinit var clueText: TextView
    lateinit var checkbtn : Button
    lateinit var resetbtn : Button
    lateinit var infobtn : Button
    lateinit var scoreCardView: View
    lateinit var heartimg1 : ImageView
    lateinit var heartimg2 : ImageView
    lateinit var heartimg3 : ImageView

    var shuffledText = CharArray(16)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        gridView = findViewById(R.id._dynamic)
        resultText = findViewById(R.id.resultText)
        checkbtn = findViewById(R.id.checkbtn)
        resetbtn = findViewById(R.id.resetbtn)
        infobtn = findViewById(R.id.infobtn)
        heartimg1 = findViewById(R.id.heart1)
        heartimg2 = findViewById(R.id.heart2)
        heartimg3 = findViewById(R.id.heart3)


        scoreCardView = layoutInflater.inflate(R.layout.fdf, null)
        val scoreTextView = scoreCardView.findViewById<TextView>(R.id.score_text_view)
        val playAgainButton = scoreCardView.findViewById<Button>(R.id.play_again_button)


        var yourScore  : Int = 0


        infobtn.setOnClickListener{

            val dialogBinding1 = layoutInflater.inflate(R.layout.clue_dialogbox,null)
            val mydialog1 = Dialog(this)
            mydialog1.setContentView(dialogBinding1)
            mydialog1.setCancelable(true)
            mydialog1.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val cluemessage = dialogBinding1.findViewById<TextView>(R.id.alert_message)
            val okbtn = dialogBinding1.findViewById<Button>(R.id.alert_yes)
            okbtn.setOnClickListener{mydialog1.dismiss()}
            var clueName1: String = intent.getStringExtra("clueName").toString()
            cluemessage.text = clueName1.toString()

            mydialog1.show()


        }

        var wordName1: String = intent.getStringExtra("wordName").toString()



        // Shuffle the text using a shuffling algorithm
        var chars = wordName1.toCharArray()
        var charList = chars.toList().shuffled()
        charList.forEachIndexed { index, char -> shuffledText[index] = char }

        // Fill remaining characters with random letters
        for (i in charList.size..shuffledText.lastIndex) {
            shuffledText[i] = getRandomLetter()
        }

        shuffledText.shuffle()

        // Pass the shuffled text to the adapter
        val adapter = AlphabetAdapter(this, shuffledText.toList())
        gridView.adapter = adapter

        // Debug print the shuffled text
        shuffledText.forEachIndexed { index, char ->
            Log.d("MainActivity2", "Letter at position $index is $char")

        }

        gridView.setOnItemClickListener { adapterView, view, position, id ->

            val letter = view.findViewById<TextView>(R.id.textView).text.toString()
            resultText.text = "${resultText.text}$letter"
        }
        var Life : Int = 3


        var playagain = scoreCardView.findViewById<Button>(R.id.play_again_button)
        var homebtn = scoreCardView.findViewById<Button>(R.id.score_home)


        checkbtn.setOnClickListener{



            if(wordName1 == resultText.text){
                Toast.makeText(applicationContext,"Wow!! This is the right answer",Toast.LENGTH_SHORT).show()
                yourScore = yourScore + 10
                scoreTextView.text = "Your score: $yourScore"

                val builder = AlertDialog.Builder(this)
                builder.setView(scoreCardView)
                val dialog = builder.create()
                dialog.show()
                var playagain = scoreCardView.findViewById<Button>(R.id.play_again_button)
                var homebtn = scoreCardView.findViewById<Button>(R.id.score_home)
                playagain.setOnClickListener { dialog.dismiss() }
                homebtn.setOnClickListener { var intent2 = Intent(this,MainActivity::class.java)
                    startActivity(intent2)
                    overridePendingTransition(R.anim.ltor, R.anim.rtol)}



            }
            else if (Life == 0)
                {
                    scoreTextView.text = "Your score: $yourScore"

                    val builder = AlertDialog.Builder(this)
                    builder.setView(scoreCardView)
                    val dialog = builder.create()
                    dialog.show()


                    playagain.setOnClickListener{
                        dialog.dismiss()
                        Life = Life + 3
                    }
                    homebtn.setOnClickListener{
                        var intent1 = Intent(this,MainActivity::class.java)
                        startActivity(intent1)
                    }
                    heartimg1.setImageResource(R.drawable.heartbrown)


                }
            else{

                Toast.makeText(applicationContext,"Wrong,Use the info option to get the clue",Toast.LENGTH_SHORT).show()

                // Shuffle the text using a shuffling algorithm
                var chars = wordName1.toCharArray()
                var charList = chars.toList().shuffled()
                var shuffledChars = CharArray(charList.size)
                charList.forEachIndexed { index, char -> shuffledChars[index] = char }

                // Convert the shuffled text into a character array
                var shuffledText = CharArray(16)
                for (i in 0 until shuffledText.size) {
                    if (i < shuffledChars.size) {
                        shuffledText[i] = shuffledChars[i]
                    } else {
                        shuffledText[i] = getRandomLetter()
                    }
                }

                adapter.shuffledText = shuffledText.toList()
                adapter.notifyDataSetChanged()

                yourScore = yourScore - 5
                Life--

            }

            if (Life == 0)
            {
                scoreTextView.text = "Your score: $yourScore"

                val builder1 = AlertDialog.Builder(this)
                builder1.setView(scoreCardView)
                val dialog1 = builder1.create()
                dialog1.show()


                playagain.setOnClickListener{
                    dialog1.dismiss()
                    Life = Life + 3
                }
                homebtn.setOnClickListener{
                    var intent1 = Intent(this,MainActivity::class.java)
                    startActivity(intent1)
                }
                heartimg1.setImageResource(R.drawable.heartbrown)


            }



            if(Life == 2)
            {
                heartimg3.setImageResource(R.drawable.heartbrown)
            }

            if(Life == 1)
            {
                heartimg2.setImageResource(R.drawable.heartbrown)
            }

            Log.d("scoremessage", "score $yourScore is $yourScore")
            Log.d("yourLife", "life $Life is $Life")
        }

        resetbtn.setOnClickListener{
            resultText.text = null
            Toast.makeText(applicationContext,"Text is cleared",Toast.LENGTH_SHORT).show()


        }

    }
    fun getRandomLetter(): Char {
            val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            return alphabet.random()
        }



}
