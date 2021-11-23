package com.example.coolblueapptest.main.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coolblueapptest.R
import com.example.domain.entity.Product

class ProductAdapter(
    private var products: List<Product>,
    private var onClickListener: View.OnClickListener) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_row_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.update(products[position], onClickListener)
    }

    override fun getItemCount() = products.size

    companion object {
        private val TAG = ProductAdapter::class.java.simpleName
    }
}