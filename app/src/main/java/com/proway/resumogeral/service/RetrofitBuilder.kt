package com.proway.resumogeral.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Criado singleton para criar os serviços que serao consumidos
 */
object RetrofitBuilder {

    /**
     * Cria uma instacia do retrofit passando a url base.
     * Deixamos como private pq não interessa para as outras classes, oq nos interessa
     */
    private val retrofitFake = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    /**
     * Retorna a implementação da interface
     * Retrofit le a interface e devolve uma instacia implementada.
     */
    fun getAuthenticationServices(): Authentication {
        return retrofitFake.create(Authentication::class.java)
    }

}