package com.example.gestionpresupuesto.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Product
import com.bumptech.glide.Glide
import com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct.MainProductListDirections

class MainProductListAdapter(
    private val isAdmin: Boolean,
    var productList: MutableList<Product>,
    val context: Context,
    var onClick: (Int) -> Unit
) : RecyclerView.Adapter<MainProductListAdapter.MainHolder>() {
    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        fun setName(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }

        fun setPrice(price: Double) {
            val pri: TextView = view.findViewById(R.id.txt_price_item)
            pri.text = price.toString()
        }

        fun setStock(stock: Int) {
            val sto: TextView = view.findViewById(R.id.txt_stock_item)
            sto.text = "Stock " + stock.toString()

        }

        fun setImage(img: String) {
            var imgURL: ImageView = view.findViewById(R.id.img_item)
            Glide.with(imgURL).load(img).override(200, 200).into(imgURL)

        }

        fun getProductItemDetail(): View {
            return view.findViewById(R.id.product_item_detail)
        }

        fun getCard(): CardView {
            return view.findViewById(R.id.card)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return (MainHolder(view))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {


            holder.setName(productList[position].name)
            holder.setPrice(productList[position].price)
            holder.setStock(productList[position].stock)
            holder.setImage(productList[position].imageURL)


        holder.getProductItemDetail().setOnClickListener {

            if (isAdmin) {
                val action =
                    MainProductListDirections.actionMainProductListToProductDetail(productList[position])
                //Este genera la cción de ir a el detalle del producto
                holder.itemView.findNavController().navigate(action)
                //Este ejecuta la acción de ir al action que generé
            }

            holder.getCard().setOnClickListener {
                onClick(position)

            }
        }
    }


    override fun getItemCount(): Int {
        return productList.size
    }
}