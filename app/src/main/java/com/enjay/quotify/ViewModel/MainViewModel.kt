package com.enjay.quotify.ViewModel

import androidx.lifecycle.ViewModel
import com.enjay.quotify.Quote

class MainViewModel : ViewModel() {

    private lateinit var quoteList : Array<Quote>
    private var index : Int = 0

    fun getQuote() : Quote {
        return quoteList[index]
    }

    fun getNextQuote() : Quote = quoteList[++index]
    fun getPreviousQuote() : Quote = quoteList[--index]
}