package com.example.gestionpresupuesto.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetCreator
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetCreatorDirections
import com.example.gestionpresupuesto.viewmodels.SharedViewModel
import com.google.android.material.snackbar.Snackbar


class ItemsAdapter(
    var productList: MutableList<Product>,
    val context: Context,
    private val sharedViewModel: SharedViewModel,
    private val budgetCreator: BudgetCreator,
    private val finish_button: Button

) : RecyclerView.Adapter<ItemsAdapter.MainHolder>() {
    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        fun setName(name: String) {
            val txt: TextView = view.findViewById(R.id.product_item_detail_for_budget_txt_name_item)
            txt.text = name
        }

        fun setPrice(price: Double) {
            val pri: TextView = view.findViewById(R.id.product_item_detail_for_budget_txt_price_item)
            pri.text = price.toString()
        }

        fun setStock(stock: Int) {
            val sto: TextView = view.findViewById(R.id.product_item_detail_for_budget_txt_stock_item)
            sto.text = stock.toString()
        }

        fun setImage(img: String) {
            var imgURL : ImageView = view.findViewById(R.id.product_item_detail_for_budget_img_item)
            Glide.with(imgURL).load(img).override(200,200).into(imgURL)
        }

        fun getProductItemDetail(): View {
            return view.findViewById(R.id.product_item_detail)
        }


        fun getCard () : CardView {
            return view.findViewById(R.id.card)
        }


        fun getDecreaseButton(): Button {
            val checkBox: Button = view.findViewById(R.id.decrease_button)
            return checkBox

        }

        fun getIncreaseButton(): Button {
            val button: Button = view.findViewById(R.id.increase_button)
            return button

        }

        fun setQuantityInput(value: Int) {
            val quantity: TextView = view.findViewById(R.id.item_quantity)
            quantity.text = value.toString()

        }

        fun getQuantityInput(): TextView {
            val quantity: TextView = view.findViewById(R.id.item_quantity)
            return quantity

        }

        fun increase() {
            val quantity: TextView = view.findViewById(R.id.item_quantity)
            var value = quantity.text.toString().toInt()
            value++
            setQuantityInput(value)


        }

        fun decrease() {

            val quantity: TextView = view.findViewById(R.id.item_quantity)
            var value = quantity.text.toString().toInt()
            if (value >= 1)
                value--
            setQuantityInput(value)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product_for_budget, parent, false)
            return (MainHolder(view))

    }


    override fun onBindViewHolder(holder: MainHolder, position: Int) {

        holder.setName(productList[position].name)
        holder.setPrice(productList[position].price)
        holder.setStock(productList[position].stock)
        holder.setImage(productList[position].imageURL)

        holder.setQuantityInput(0);

        if(sharedViewModel.getState().isNotEmpty()){

           var quantity = searchStateByInternalProductCode(productList[position].internalProductCode)?.quantity

            if (quantity != null) {

                holder.setQuantityInput(quantity.toInt())
            }

        } else {

            holder.setQuantityInput(0);

        }

        holder.getIncreaseButton().setOnClickListener() {

            holder.increase()

            var quantity = holder.getQuantityInput().text.toString().toInt()

            var state = searchStateByInternalProductCode(productList[position].internalProductCode)

            if(state != null) {

                state.quantity = quantity.toString().toInt()

            } else {

                sharedViewModel.saveState.add(Item(productList[position].internalProductCode, productList[position].name,
                    productList[position].description, productList[position].price, quantity))

            }
        }


        holder.getDecreaseButton().setOnClickListener() {
            holder.decrease()



            var quantity = holder.getQuantityInput().text.toString().toInt()

            if(quantity != 0) {

                var state = searchStateByInternalProductCode(productList[position].internalProductCode)

                if(state != null) {

                    state.quantity = quantity.toString().toInt()

                } else {

                    sharedViewModel.saveState.add(Item(productList[position].internalProductCode, productList[position].name,
                        productList[position].description, productList[position].price, quantity))

                }

            }
        }

        finish_button.setOnClickListener() {

            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("¿Finalizar carga?")
                .setCancelable(false)
                .setPositiveButton("Aceptar", DialogInterface.OnClickListener {
                        dialog, id ->

                    var stateList = sharedViewModel.getState()

                    if (stateList.size != 0) {

                        for (item in stateList) {

                            if (item.quantity != 0) {
                                sharedViewModel.getBudgetToCreate().value?.productsItems?.add(item)
                            }
                        }

                        val action = BudgetCreatorDirections.actionBudgetCreator2ToMainBudgetList()
                        holder.itemView.findNavController().navigate(action)




                        budgetCreator.saveBudgetToCreate()

                    }  else  {
                        Snackbar.make(holder.itemView, "No cargó ningún producto", Snackbar.LENGTH_LONG).show()
                    }

        })
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("")
            alert.show()
        }
}

    override fun getItemCount(): Int {
        return productList.size
    }

    fun searchOnItemListByProductInternalCode(list : MutableList<Item>, internalProductCode : String) : Item {

        return list.first { it.internalProductCode == internalProductCode }
    }

    fun searchProductByInternalProductCode(list : MutableList<Product>, internalProductCode : String) : Product {

        return list.first { it.internalProductCode == internalProductCode }
    }

    fun searchStateByInternalProductCode(internalProductCode : String) : Item? {

        return sharedViewModel.getState().firstOrNull() { it.internalProductCode == internalProductCode }
    }

}