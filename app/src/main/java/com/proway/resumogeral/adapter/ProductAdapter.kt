package com.proway.resumogeral.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proway.resumogeral.R
import com.proway.resumogeral.model.Product

/**
 * Funciona como o coração do recyclerView. É aqui onde vamos alimentar a lista e definir o
 * layout oara cada linha.
 */
class ProductAdapter(val onClick: (Product) -> Unit) : RecyclerView.Adapter<ProductViewHolder>() {

    /**
     * Lista que o adapter irá trabalhar como base de dados
     */
    private var listOfProducts = mutableListOf<Product>()

    /**
     * Vincula o arquivo de layout para cada linha com o viewHolder
     * Obs: Para cada item da lista o adapter cria automaticamente um viewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false).apply {
            return ProductViewHolder(this)
        }
    }

    /**
     * Está função é chamada automaticamente pelo próprio adapter. É aqui que ela vai passar
     * item a item e onde podemos pegar a posição do array para mostrar na linha
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        listOfProducts[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener { onClick(this) }
        }
    }

    /**
     * Conta o número de elementos no array para construir toda a lista
     */
    override fun getItemCount(): Int = listOfProducts.size

    /**
     * Criamos esta função para quando recebermos um nova lista do server/api
     * atualizar nossa lista
     */
    fun update(newList: List<Product>) {
        listOfProducts = mutableListOf()
        listOfProducts.addAll(newList)
        notifyDataSetChanged()
    }

}


/**
 * Esta classe é responsável em manipular dados da linha x componentes da tela.
 * É aqui que pegamos os dados que estao no array e definimos em quais componentes
 * vamos mostrar na tela.
 *
 * Obs: lembrando que se tivermos 100 elementos na lista irá passar por esta classe 100x.
 *
 */
class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
    private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    private val subtitleTextView: TextView = itemView.findViewById(R.id.subtitleTextView)
    private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

    fun bind(product: Product) {
        titleTextView.text = product.title
        subtitleTextView.text = product.description

        priceTextView.text = "R$ ${product.price}"

        Glide.with(itemView.context)
            .load(product.image)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(productImageView)

    }


}