package com.example.foodrecipeapp2.Listeners

import com.example.foodrecipeapp2.models.RandomRecipeApiResponse

// All methods are to be implemented in a interface.
interface RandomRecipeResponseListener {
    fun didFetch(response: RandomRecipeApiResponse?, message: String?)
    fun didError(message: String?)
}
