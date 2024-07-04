package com.example.foodrecipeapp2.models

class RecipeDetailsResponse {
    var id: Int = 0
    var title: String? = null
    var image: String? = null
    var imageType: String? = null
    var servings: Int = 0
    var readyInMinutes: Int = 0
    var license: String? = null
    var sourceName: String? = null
    var sourceUrl: String? = null
    var spoonacularSourceUrl: String? = null
    var healthScore: Double = 0.0
    var spoonacularScore: Double = 0.0
    var pricePerServing: Double = 0.0
    var analyzedInstructions: ArrayList<Any>? = null
    var cheap: Boolean = false
    var creditsText: String? = null
    var cuisines: ArrayList<Any>? = null
    var dairyFree: Boolean = false
    var diets: ArrayList<Any>? = null
    var gaps: String? = null
    var glutenFree: Boolean = false
    var instructions: String? = null
    var ketogenic: Boolean = false
    var lowFodmap: Boolean = false
    var occasions: ArrayList<Any>? = null
    var sustainable: Boolean = false
    var vegan: Boolean = false
    var vegetarian: Boolean = false
    var veryHealthy: Boolean = false
    var veryPopular: Boolean = false
    var whole30: Boolean = false
    var weightWatcherSmartPoints: Int = 0
    var dishTypes: ArrayList<String>? = null
    var extendedIngredients: ArrayList<ExtendedIngredient>? = null
    var summary: String? = null
    var winePairing: WinePairing? = null
}
