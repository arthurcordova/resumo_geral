package com.proway.resumogeral.service

import com.proway.resumogeral.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    /**
     * Retrofit irá ler esta classe para montar uma implementaçao
     * Ele se baseia nas annotations. Ex: @POST, @Body, @GET, @Query, etc..
     */
    @GET("/products")
    fun getProducts(): Call<List<Product>>

}