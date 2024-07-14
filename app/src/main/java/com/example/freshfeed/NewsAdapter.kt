package com.example.freshfeed

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsAdapter(val context: Context,val articles:List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewholder>() {


    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ArticleViewholder {
        val view=LayoutInflater.from(p0.context).inflate(R.layout.item_layout,p0,false)
        return ArticleViewholder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(p0: ArticleViewholder, p1: Int) {
        val article=articles[p1]
        p0.newsText.text=article.title
        p0.newsDescription.text=article.description
        Glide.with(context).load(article.urlToImage).into(p0.newsImage)
        p0.itemView.setOnClickListener{
            val intent = CustomTabsIntent.Builder()
                .build()
            intent.launchUrl(context, Uri.parse(article.url))
        }
    }


    class ArticleViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val newsImage=itemView.findViewById<ImageView>(R.id.newsImage)
        val newsText=itemView.findViewById<TextView>(R.id.newsTitle)
        val newsDescription=itemView.findViewById<TextView>(R.id.newsDescription)

    }
}