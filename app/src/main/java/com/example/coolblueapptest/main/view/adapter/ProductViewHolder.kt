package com.example.coolblueapptest.main.view.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coolblueapptest.R
import com.example.domain.entity.Product
import com.squareup.picasso.Picasso

class ProductViewHolder(private var parent: View) : RecyclerView.ViewHolder(parent) {

    private var title: TextView = parent.findViewById(R.id.title)
    private var image: ImageView = parent.findViewById(R.id.image)

    /**
     * Update view holder.
     */
    fun update(product: Product, onClickListener: View.OnClickListener) {
        // load image from url
        kotlin.runCatching {
            Picasso.get().load(product.productImage).into(image)
        }.onFailure {
            Log.e(TAG, "Error load Image: $it")
        }

        parent.setOnClickListener(onClickListener)
        parent.tag = product
        title.text = product.productName
    }

    companion object {
        private val TAG: String = ProductViewHolder::class.java.simpleName
    }
}