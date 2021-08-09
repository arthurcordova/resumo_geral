package com.proway.resumogeral.model

import com.google.gson.annotations.SerializedName

/**
 * Criado Data class somente para repassar os valores que o usuário informou.
 *
 */
data class Credentials(
    /**
     *  Add esta annotation para converter o nome do nosso atributo email -> username.
     *  Fazemos isso pq precisamos deixar exatamente como a API pede.
     */
    @SerializedName("username")
    // No app vamos trabalhar com o atributo email.
    val email: String,

    /**
     * Quando o nome do atributo é igual ao que esta na API não
     * é necessário add a annotation.
     */
    val password: String
) {

    /**
     * Funcao criada para auxiliar a validaçao dos campos na tela
     */
    fun checkUserName(): Boolean {
        return email.isNotEmpty()
    }

    /**
     * Funcao criada para auxiliar a validaçao dos campos na tela
     */
    fun checkPassword(): Boolean {
        return password.isNotEmpty()
    }


}
