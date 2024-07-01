package com.example.foodrecipeapp2.models

class Recipe {
    var vegetarian: Boolean = false
    var vegan: Boolean = false
    var glutenFree: Boolean = false
    var dairyFree: Boolean = false
    var veryHealthy: Boolean = false
    var cheap: Boolean = false
    var veryPopular: Boolean = false
    var sustainable: Boolean = false
    var lowFodmap: Boolean = false
    var weightWatcherSmartPoints: Int = 0
    var gaps: String? = null
    var preparationMinutes: Any? = null
    var cookingMinutes: Any? = null
    @JvmField
    var aggregateLikes: Int = 0
    var healthScore: Int = 0
    var creditsText: String? = null
    var license: String? = null
    var sourceName: String? = null
    var pricePerServing: Double = 0.0
    var extendedIngredients: ArrayList<ExtendedIngredient>? = null
    var id: Int = 0
    @JvmField
    var title: String? = null
    @JvmField
    var readyInMinutes: Int = 0
    @JvmField
    var servings: Int = 0
    var sourceUrl: String? = null
    @JvmField
    var image: String? = null
    var imageType: String? = null
    var summary: String? = null
    var cuisines: ArrayList<Any>? = null
    var dishTypes: ArrayList<String>? = null
    var diets: ArrayList<String>? = null
    var occasions: ArrayList<Any>? = null
    var instructions: String? = null
    var analyzedInstructions: ArrayList<AnalyzedInstruction>? = null
    var originalId: Any? = null
    var spoonacularScore: Double = 0.0
    var spoonacularSourceUrl: String? = null
}
