package com.enjay.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.enjay.quotify.ViewModel.MainViewModel
import com.enjay.quotify.ViewModel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val txtQuoteText : TextView
        get() = findViewById(R.id.txt_quote_text)

    private val txtQuoteAuthor : TextView
        get() = findViewById(R.id.txt_quote_author)

    private val floatingShare : FloatingActionButton
        get() = findViewById(R.id.floting_btn_share)

    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,ViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())
        Log.e("Dhaval", "onCreate: QUOTE : " + mainViewModel.getQuote())
    }

    fun setQuote(quote: Quote){
        txtQuoteText.text = quote.text
        txtQuoteAuthor.text = quote.author
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.getNextQuote())
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.getPreviousQuote())
    }

    fun onShare(view: View) {
        var shareQuoteIntent = Intent(Intent.ACTION_SEND)
        shareQuoteIntent.setType("text/plain")
        shareQuoteIntent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(shareQuoteIntent)
    }
}