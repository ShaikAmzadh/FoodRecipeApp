package com.example.foodrecipeapp2.Listeners

import com.example.foodrecipeapp2.models.SimilarRecipeResponse

interface SimilarRecipesListener {
    fun didFetch(response:List<SimilarRecipeResponse>?,message:String)
    fun didError(message:String?)
}