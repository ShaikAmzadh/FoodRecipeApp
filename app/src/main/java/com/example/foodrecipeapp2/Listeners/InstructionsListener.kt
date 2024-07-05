package com.example.foodrecipeapp2.Listeners

import com.example.foodrecipeapp2.models.InstructionsResponse

interface InstructionsListener {
    fun didFetch(response: List<InstructionsResponse>?, message:String)
    fun didError(message:String?)
}