package com.example.foodrecipeapp2.Listeners

import com.example.foodrecipeapp2.models.RecipeDetailsResponse

interface RecipeDetailsListener {
    fun didFetch(response:RecipeDetailsResponse?,message:String?)

    fun didError(message: String?)
}