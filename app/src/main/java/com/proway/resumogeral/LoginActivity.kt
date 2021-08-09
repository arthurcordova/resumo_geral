package com.proway.resumogeral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.proway.resumogeral.model.Credentials
import com.proway.resumogeral.utils.snackBar

class LoginActivity : AppCompatActivity() {

    /**
     * Variaveis que irao receber o editText da tela.
     * Obs: neste momento ela está só declarada.
     * Se tentar acessar ela sem atribuir um valor ocorrera uma exception,
     * pois ela ainda não foi inicializada.
     *
     * Estas variaveis são acessiveis dentro do escopo da classe inteira.
     *
     */
    private lateinit var inputEmail: EditText
    private lateinit var inputSenha: EditText
    private lateinit var buttonEntrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loadComponents()
        loadEvents()
    }

    /**
     * Funçao criada para add eventos nos elementos. (Criado somente para organizar o código)
     * Neste caso vamos add o evento de click no botão
     */
    private fun loadEvents() {
        // Add evento de click no botao de entrar
        buttonEntrar.setOnClickListener {
            fazerLogin()
        }
    }

    /**
     * Funçao que sera disparada quando o user clicar no botao de entrar.
     */
    private fun fazerLogin() {
        // Criado variaveis para usar no escopo desta funçao. Irao armazenar o valor de email e senha que o user digitou.
        val email = inputEmail.text.toString()
        val senha = inputSenha.text.toString()

        // Criado variavel credentials, que recebe uma instancia de Credentials, onde passamos no construtor o email e senha.
        val credentials = Credentials(email, senha)
        // Com o objeto credentials carregado, podemos trabalhar com os atributos dele.
        // Neste caso chamamos as funcoes para verificar se o username e password estao validos
        if (credentials.checkPassword() && credentials.checkUserName()) {


        } else {
            // Caso os dados estejam invalidos chamamos um funcao snackBar que foi criado usando extensions,
            // sendo assim esta esta acessivel dentro de qualquer activity.
            snackBar(inputEmail, R.string.usuario_invalido)
        }
    }

    /**
     * Função criada somente para organizar o código e atribuir
     * o valor para as variaveis declaradas no escopo da classe.
     */
    private fun loadComponents() {
        // Busca o elemento com ID do xml e atribui a instacia dele para a variavel.
        inputEmail = findViewById(R.id.inputEmail)
        // Busca o elemento com ID do xml e atribui a instacia dele para a variavel.
        inputSenha = findViewById(R.id.inputPassword)
        // Busca o elemento com ID do xml e atribui a instacia dele para a variavel.
        buttonEntrar = findViewById(R.id.buttonEntrar)
    }


}
