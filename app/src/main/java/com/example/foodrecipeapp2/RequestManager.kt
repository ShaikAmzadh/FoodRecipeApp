package com.example.foodrecipeapp2

import android.content.Context
import com.example.foodrecipeapp2.Listeners.RandomRecipeResponseListener
import com.example.foodrecipeapp2.Listeners.RecipeDetailsListener
import com.example.foodrecipeapp2.models.RandomRecipeApiResponse
import com.example.foodrecipeapp2.models.RecipeDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RequestManager // Why this constructor
    (var context: Context) {
    var retrofit: Retrofit =
        Retrofit.Builder() // base Url of spoonacular is "https://api.spoonacular.com/"
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRandomrecipes(listener: RandomRecipeResponseListener,tags:List<String>) {
        val callRandomRecipes = retrofit.create(
            CallRandomRecipes::class.java
        )
        val call = callRandomRecipes.callRandomrecipe(
            context.getString(R.string.api_key), "10",tags
        )
        call.enqueue(object : Callback<RandomRecipeApiResponse?> {
            override fun onResponse(
                call: Call<RandomRecipeApiResponse?>,
                response: Response<RandomRecipeApiResponse?>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch(response.body(), response.message())
            }

            override fun onFailure(call: Call<RandomRecipeApiResponse?>, throwable: Throwable) {
                listener.didError(throwable.message)
            }
        })
    }
    fun getRecipeDetails(listener:RecipeDetailsListener,id:Int) {
        val callRecipeDetails=retrofit.create(
            CallRecipeDetails::class.java
        )
        val call=callRecipeDetails.callRecipeDetails(id,context.getString(R.string.api_key_2))

        call.enqueue(object : Callback<RecipeDetailsResponse?>{
            override fun onResponse(
                p0: Call<RecipeDetailsResponse?>,
                p1: Response<RecipeDetailsResponse?>
            ) {
                if(!p1.isSuccessful){
                    listener.didError(p1.message())
                    return
                }
                listener.didFetch(p1.body(),p1.message())
            }

            override fun onFailure(p0: Call<RecipeDetailsResponse?>, p1: Throwable) {
               listener.didError(p1.message)
            }

        })
    }

    private interface CallRandomRecipes {
        // Call<T> is a interface
        @GET("recipes/random")
        fun callRandomrecipe(
            @Query("apiKey") apiKey: String?,
            @Query("number") number: String?,
            @Query("tags") tags:List<String>
        ): Call<RandomRecipeApiResponse>
    }
    private interface CallRecipeDetails {
        @GET("recipes/{id}/information")
        fun callRecipeDetails(
            @Path("id") id:Int,
            @Query("apiKey") apiKey:String
        ):Call<RecipeDetailsResponse>

    }
}