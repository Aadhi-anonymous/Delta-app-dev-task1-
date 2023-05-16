package com.example.deltatask1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AlphabetAdapter(

    var context: Context,
    var shuffledText: List<Char> = CharArray(16).toList()

): BaseAdapter() {
    override fun getCount(): Int {
        return shuffledText.size
        Log.d("AlphabetAdapter2", "Letter at position $shuffledText")
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.alphabetgrid, parent, false)

        var alphabet: TextView = view.findViewById(R.id.textView)

        alphabet.text = shuffledText[position].toString()

        alphabet.setBackgroundResource(R.drawable.text_selector)





        Log.d("AlphabetAdapter", "Letter at position $position is ${shuffledText[position]}")

        return view
    }
    fun getRandomLetter(): Char {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        return alphabet.random()
    }

    fun shuffle() {
        shuffledText = shuffledText.shuffled()
        notifyDataSetChanged()
    }


}


