package com.proway.resumogeral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.proway.resumogeral.model.Credentials
import com.proway.resumogeral.model.LoginResponse
import com.proway.resumogeral.service.RetrofitBuilder
import com.proway.resumogeral.utils.snackBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), Callback<LoginResponse> {

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
            dispararRequestLogin(credentials)
        } else {
            // Caso os dados estejam invalidos chamamos um funcao snackBar que foi criado usando extensions,
            // sendo assim esta esta acessivel dentro de qualquer activity.
            snackBar(inputEmail, R.string.usuario_invalido)
        }
    }

    /**
     * Chama esta função depois de validar os campos informados pelo user.
     * Irá disparar um request para a API.
     *
     */
    private fun dispararRequestLogin(credentials: Credentials) {
        // Chama a implementaçao do Authentication
        val interfaceDeAuthImplementada = RetrofitBuilder.getAuthenticationServices()
        // Da instancia do authentication chama a funcao de login passando as credentials por parametro
        val call = interfaceDeAuthImplementada.login(credentials)
        // Coloca a chama na fila para execução (Neste momento é que é de fato disparado para o server/api
        // é obrigatório implementar a interface CallBack<T>, é nela que o retrofit devolvera a resposta do server/api
        call.clone().enqueue(this)
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

    /**
     * Retrofit chama automaticamente esta função quando recebe uma resposta do server/api
     */
    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        if (response.body() != null) {
            val loginResponse = response.body()!!
            if (loginResponse.isError()) {
                snackBar(inputEmail, R.string.usuario_invalido)
            } else {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        } else {
            snackBar(inputEmail, R.string.usuario_invalido)
        }
    }

    /**
     * Retrofit chama automaticamente esta função quando um erro de conexão ou conversão json.
     */
    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
          snackBar(inputEmail, R.string.usuario_invalido)
    }


}
