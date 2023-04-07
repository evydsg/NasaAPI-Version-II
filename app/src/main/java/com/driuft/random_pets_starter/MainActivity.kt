package com.driuft.random_pets_starter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var nasaList: MutableList<String>
    private lateinit var rvNasa: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNasa = findViewById(R.id.nasa_list)
        nasaList = mutableListOf()
        getNasaImageURL()
    }
    @GlideModule
    class MyAppGlideModule : AppGlideModule() {

        override fun applyOptions(context: Context, builder: GlideBuilder) {
            super.applyOptions(context, builder)
            builder.apply { RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL) }
        }

    }
    private fun getNasaImageURL() {
        val client = AsyncHttpClient()

        client["https://api.nasa.gov/planetary/apod?api_key=YcWBOh5Le8HE1vHE4H7QBc9F3Ekzf7Hlu9GYzNQU&count=10", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("Nasa Successfull", "$json")
                val NasaImage = json.jsonArray.getJSONObject(0)
                val Nasaimage = json.jsonArray.getJSONObject(0).getString("url")

                for(i in 0 until  NasaImage.length())
                {
                    val Nasaimage = json.jsonArray.getJSONObject(i).getString("url")
                    nasaList.add(Nasaimage)
                }
                val adapter = NasaAdapter(nasaList)
                rvNasa.adapter = adapter
                rvNasa.layoutManager = LinearLayoutManager(this@MainActivity)
                rvNasa.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.HORIZONTAL))
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Nasa Error", errorResponse)
            }
        }]
    }
}



