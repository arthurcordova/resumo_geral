package com.proway.resumogeral.model

import com.google.gson.annotations.SerializedName

/**
 * Classe criada para receber a resposta do servidor ao chamar o
 * endpoint de /login
 */
data class LoginResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("msg")
    val msg: String?
) {

    /**
     * Criado função para validar se tem um erro de login ou não
     */
    fun isError() : Boolean {
        return token == null || token.isEmpty()
    }

}
