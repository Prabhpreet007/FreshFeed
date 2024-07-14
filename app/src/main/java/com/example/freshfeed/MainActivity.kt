package com.example.freshfeed

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.freshfeed.News
import com.example.freshfeed.NewsAdapter
import com.example.freshfeed.NewsService
import com.example.freshfeed.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapterr: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        getNews()
    }


    private fun getNews(){
        val news= NewsService.newsInstance.getHeadlines("in")
        news.enqueue(object:Callback<News>{
            override fun onResponse(p0: Call<News>, p1: Response<News>) {
                val news=p1.body()
                if(news!=null){
                    Log.d("CHEEZY",news.toString())
                    adapterr=NewsAdapter(this@MainActivity,news.articles)
                    findViewById<RecyclerView>(R.id.newsList).adapter=adapterr
                    findViewById<RecyclerView>(R.id.newsList).layoutManager=LinearLayoutManager(this@MainActivity)

                }

            }

            override fun onFailure(p0: Call<News>, p1: Throwable) {

                Log.d("CHEEZY","ERROR IN FETCHING NEWS",p1)
            }

        })
    }}