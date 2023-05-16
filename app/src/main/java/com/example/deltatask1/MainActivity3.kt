package com.example.deltatask1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity3 : AppCompatActivity() {
    lateinit var gridView: GridView
    lateinit var resultText: TextView
    lateinit var clueText: TextView
    lateinit var checkbtn : Button
    lateinit var resetbtn : Button
    lateinit var infobtn : Button
    lateinit var clock: TextView
    lateinit var scoreCardView: View
    lateinit var heartimg1 : ImageView
    lateinit var heartimg2 : ImageView
    lateinit var heartimg3 : ImageView

    var yourScore: Int = 0


    fun scoredialogBox()
    {
        val scoreTextView = scoreCardView.findViewById<TextView>(R.id.score_text_view)
        var playagain = scoreCardView.findViewById<Button>(R.id.play_again_button)
        var homebtn = scoreCardView.findViewById<Button>(R.id.score_home)
        scoreTextView.text = "Your score: $yourScore"

        val builder = AlertDialog.Builder(this)
        builder.setView(scoreCardView)
        val dialog2 = builder.create()
        dialog2.show()


        playagain.setOnClickListener {
            dialog2.dismiss()

        }
        homebtn.setOnClickListener {
            var intent1 = Intent(this, MainActivity::class.java)
            overridePendingTransition(R.anim.ltor, R.anim.rtol)
            startActivity(intent1)
        }
    }



    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

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
        var gridsize1: Int = intent.getIntExtra("gridsize", 0).toInt()
        var shuffledText = CharArray(gridsize1)
        val cluemap1: HashMap<Int, String> =
            intent.getSerializableExtra("cluemap") as HashMap<Int, String>



        var i: Int = 0


        infobtn.setOnClickListener {

            val dialogBinding1 = layoutInflater.inflate(R.layout.clue_dialogbox, null)
            val mydialog1 = Dialog(this)
            mydialog1.setContentView(dialogBinding1)
            mydialog1.setCancelable(true)
            mydialog1.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val cluemessage = dialogBinding1.findViewById<TextView>(R.id.alert_message)
            val okbtn = dialogBinding1.findViewById<Button>(R.id.alert_yes)
            okbtn.setOnClickListener { mydialog1.dismiss() }


            cluemessage.text = cluemap1[i]
            mydialog1.show()




        }


        val wordmap1: HashMap<Int, String> =
            intent.getSerializableExtra("wordmap") as HashMap<Int, String>
        resultText.text = dash(wordmap1[i].toString())




        Log.d("gridsize1", "gridsize at second page is $gridsize1")
        Log.d("transwordclue", "secondpage $wordmap1")




        // Shuffle the text using a shuffling algorithm
        var chars = wordmap1[i]!!.toCharArray()
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

        var f =0
        gridView.setOnItemClickListener { adapterView, view, position, id ->
            if(f!=0){
                val letter = view.findViewById<TextView>(R.id.textView).text.toString()

                resultText.text = resultText.text.substring(0,f) + letter + resultText.text.substring(f + 1)

            }
            else{
                val letter = view.findViewById<TextView>(R.id.textView).text.toString()

                resultText.text = letter + resultText.text.substring(1)
            }
            f=f+1
        }

        var number1 = intent.getIntExtra("number", 0).toInt()


        gridView.numColumns = number1

        var Life: Int = 3


        var playagain = scoreCardView.findViewById<Button>(R.id.play_again_button)
        var homebtn = scoreCardView.findViewById<Button>(R.id.score_home)




        checkbtn.setOnClickListener {
            f=0
            if (wordmap1[i] == resultText.text) {
                Toast.makeText(
                    applicationContext,
                    "Wow!! This is the right answer",
                    Toast.LENGTH_SHORT
                ).show()
                yourScore = yourScore + 10
                scoreTextView.text = "Your score: $yourScore"
                i++

                if (i == wordmap1.size) {
                    // Show scorecard and reset the game
                    scoreTextView.text = "Your score: $yourScore"
                    val builder = AlertDialog.Builder(this)
                    builder.setView(scoreCardView)
                    val dialog2 = builder.create()
                    dialog2.show()

                    playagain.setOnClickListener {
                        dialog2.dismiss()
                        resetGame(wordmap1, shuffledText)
                    }
                    homebtn.setOnClickListener {
                        var intent1 = Intent(this, MainActivity::class.java)
                        overridePendingTransition(R.anim.ltor, R.anim.rtol)
                        startActivity(intent1)
                    }
                    return@setOnClickListener
                }
                // Shuffle the text using a shuffling algorithm
                val chars1 = wordmap1[i]!!.toCharArray()
                val charList1 = chars1.toList().shuffled()
                val newShuffledText = CharArray(gridsize1)

                // Fill the shuffled text with the shuffled letters and random letters
                for (j in charList1.indices) {
                    newShuffledText[j] = charList1[j]
                }
                for (j in charList1.size..newShuffledText.lastIndex) {
                    newShuffledText[j] = getRandomLetter()
                }

                newShuffledText.shuffle()

                // Pass the new shuffled text to the adapter
                val adapter = AlphabetAdapter(this, newShuffledText.toList())
                gridView.adapter = adapter

                // Clear the result text view for the new word
                resultText.text=dash(wordmap1[i].toString())



            } else if (Life == 0) {
                scoreTextView.text = "Your score: $yourScore"

                val builder = AlertDialog.Builder(this)
                builder.setView(scoreCardView)
                val dialog2 = builder.create()
                dialog2.show()


                playagain.setOnClickListener {
                    dialog2.dismiss()

                }
                homebtn.setOnClickListener {
                    var intent1 = Intent(this, MainActivity::class.java)
                    overridePendingTransition(R.anim.ltor, R.anim.rtol)
                    startActivity(intent1)
                }
                heartimg1.setImageResource(R.drawable.heartbrown)


            } else {

                Toast.makeText(
                    applicationContext,
                    "Wrong,Use the info option to get the clue",
                    Toast.LENGTH_SHORT
                ).show()

                // Shuffle the text using a shuffling algorithm


                yourScore = yourScore - 5
                Life--

            }

            if (Life == 0) {
                scoreTextView.text = "Your score: $yourScore"

                val builder1 = AlertDialog.Builder(this)
                builder1.setView(scoreCardView)
                val dialog2 = builder1.create()
                dialog2.show()


                playagain.setOnClickListener {
                    dialog2.dismiss()
                    Life = Life + 3
                }
                homebtn.setOnClickListener {
                    var intent1 = Intent(this, MainActivity::class.java)
                    startActivity(intent1)
                    overridePendingTransition(R.anim.ltor, R.anim.rtol)
                }
                heartimg1.setImageResource(R.drawable.heartbrown)


            }



            if (Life == 2) {
                heartimg3.setImageResource(R.drawable.heartbrown)
            }

            if (Life == 1) {
                heartimg2.setImageResource(R.drawable.heartbrown)
            }

            Log.d("scoremessage", "score $yourScore is $yourScore")
            Log.d("yourLife", "life $Life is $Life")
        }


        resetbtn.setOnClickListener {
            resultText.text = dash(wordmap1[i].toString())
            f=0

            Toast.makeText(applicationContext, "Text is cleared", Toast.LENGTH_SHORT).show()


        }

    }


    private fun resetGame(wordmap1: HashMap<Int, String>, shuffledText: CharArray) {

    }

    fun getRandomLetter(): Char {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        return alphabet.random()
    }


    fun dash(word:String) : String
    {
        var a : Int  = 0
        var b : String = ""

        while (a <word.length)
        {
            b+="#"
            a+=1
        }
        return b
    }
}