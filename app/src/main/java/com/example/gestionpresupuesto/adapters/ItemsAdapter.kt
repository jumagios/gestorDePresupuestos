package com.example.gestionpresupuesto.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.NewBudgetFragment
import com.example.gestionpresupuesto.viewmodels.BugdetCreatorViewModel
import com.example.gestionpresupuesto.viewmodels.NewBudgetViewModel
import com.example.gestionpresupuesto.viewmodels.SharedViewModel
import com.google.android.material.snackbar.Snackbar


class ItemsAdapter(
    var productList: MutableList<Product>,
    val context: Context,
    private val sharedViewModel: SharedViewModel,
    private val budgetCreator: NewBudgetFragment,
    private val switch: Switch

) : RecyclerView.Adapter<ItemsAdapter.MainHolder>() {
    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }


        fun getIncreaseButton(): Button {
            val checkBox: Button = view.findViewById(R.id.increase_button)
            return checkBox

        }

        fun getDecreaseButton(): Button {
            val button: Button = view.findViewById(R.id.decrease_button)
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

        switch.setOnClickListener() {

            if (switch.isChecked) {

                var stateList = sharedViewModel.getState()

                if(stateList.size != 0) {

                    for (item in stateList) {

                        if(item.quantity != 0) {
                            sharedViewModel.getBudgetToCreate().value?.productsItems?.add(item)
                        }
                    }

                }

                budgetCreator.saveBudgetToCreate()


                } else {

                // no hay items



            }

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