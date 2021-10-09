package com.enjay.quotify.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.enjay.quotify.Quote
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val context: Context) : ViewModel() {

    private var quoteList : Array<Quote> = emptyArray()
    private var index : Int = 0

    init {
        quoteList = getQuotesFromAssets(context)

        Log.e("Dhaval", "QUOTE LIST : ${quoteList[0]}")
    }


    fun getQuote() : Quote = quoteList[index]

    fun getNextQuote() : Quote = quoteList[++index]
    fun getPreviousQuote() : Quote = quoteList[--index]

    private fun getQuotesFromAssets(context: Context) : Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)

        Log.e("Dhaval", "getQuotesFromAssets: JSON : $json")

        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }
}