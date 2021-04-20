package com.mahadream.wikiimagesearch.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mahadream.wikiimagesearch.R

class SearchDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_details)
        var imgView = findViewById<ImageView>(R.id.img_view)
        if (intent != null && intent.hasExtra("image")) {
            var url = intent.extras?.getString("image")
            Glide.with(this).load(url)
                .placeholder(resources.getDrawable(R.drawable.ic_launcher_foreground))
                .into(imgView)
        }
    }
}