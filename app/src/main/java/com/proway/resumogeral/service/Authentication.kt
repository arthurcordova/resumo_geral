package com.proway.resumogeral.service

import com.proway.resumogeral.model.Credentials
import com.proway.resumogeral.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Authentication {

    /**
     * Retrofit irá ler esta classe para montar uma implementaçao
     * Ele se baseia nas annotations. Ex: @POST, @Body, @GET, @Query, etc..
     */
    @POST("/auth/login")
    fun login(@Body credentials: Credentials): Call<LoginResponse>

}