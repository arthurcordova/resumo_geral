package com.proway.resumogeral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.proway.resumogeral.model.Product
import com.proway.resumogeral.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailActivity : AppCompatActivity(), Callback<Product> {

    private lateinit var productImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var subtitleTextView: TextView
    private lateinit var priceTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        loadComponents()

        loadProductDetail()
    }

    private fun loadProductDetail() {
        val productId = intent.getIntExtra(PARAMETER_PRODUCT, 0)
        val call = RetrofitBuilder.getProductServices().getProduct(productId)
        call.enqueue(this)
    }

    private fun loadComponents() {
        productImageView = findViewById(R.id.productImageView)
        titleTextView = findViewById(R.id.titleTextView)
        subtitleTextView = findViewById(R.id.subtitleTextView)
        priceTextView = findViewById(R.id.priceTextView)
    }

    private fun bind(product: Product) {
        titleTextView.text = product.title
        subtitleTextView.text = product.description

        priceTextView.text = "R$ ${product.price}"

        Glide.with(this)
            .load(product.image)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(productImageView)

    }

    companion object {
        const val PARAMETER_PRODUCT = "parameter_product_key"
    }

    override fun onResponse(call: Call<Product>, response: Response<Product>) {
        response.body()?.apply {
            bind(this)
        }
    }

    override fun onFailure(call: Call<Product>, t: Throwable) {
        println("Falha no carregamento do produto")
    }

}