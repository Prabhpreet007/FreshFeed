package com.example.freshfeed

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// https://newsapi.org/v2/top-headlines?country=us&apiKey=f176732c40504f2aa7d73d151fb661dd

const val API_KEY="f176732c40504f2aa7d73d151fb661dd"
const val BASE_URL="https://newsapi.org/"

interface NewsInterface{

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country:String):Call<News>

}
object NewsService{
    val newsInstance:NewsInterface
    init {
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance=retrofit.create(NewsInterface::class.java)
    }
}