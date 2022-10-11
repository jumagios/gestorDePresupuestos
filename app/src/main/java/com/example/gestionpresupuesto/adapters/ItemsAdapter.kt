package com.example.gestionpresupuesto.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.entities.Product


class ItemsAdapter(
    var productList: MutableList<Product>,
    val context: Context
) : RecyclerView.Adapter<ItemsAdapter.MainHolder>()
{
    class MainHolder (v: View) : RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }

        fun getCheckBox() : CheckBox {
            val button : CheckBox  = view.findViewById(R.id.checkbox_button)
            return button

        }

        fun getIncreaseButton() : Button{
            val checkBox  : Button = view.findViewById(R.id.increase_button)
            return checkBox

        }

        fun getDecreaseButton() : Button{
            val button : Button = view.findViewById(R.id.decrease_button)
            return button

        }

        fun setQuantityInput (value : Int)  {
            val quantity: TextView = view.findViewById(R.id.item_quantity)
            quantity.text = value.toString()

        }

        fun getQuantityInput () : TextView {
            val quantity: TextView = view.findViewById(R.id.item_quantity)
            return quantity

        }

        fun increase ()  {
            val quantity: TextView = view.findViewById(R.id.item_quantity)
            var value = quantity.text.toString().toInt()
            value++
            setQuantityInput(value)


        }

        fun decrease ()  {
            val quantity: TextView = view.findViewById(R.id.item_quantity)
            var value = quantity.text.toString().toInt()
            if(value >= 1)
            value--
            setQuantityInput(value)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_product_for_budget,parent,false)
        return (MainHolder(view))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {

        var items = mutableListOf<Item>()

        holder.setQuantityInput(0);

        holder.getIncreaseButton().setOnClickListener(){
            holder.increase()
        }

        holder.getDecreaseButton().setOnClickListener(){
            holder.decrease()
        }


        holder.getCheckBox().setOnClickListener(){

            var quantity = holder.getQuantityInput().text.toString().toInt()

            if(holder.getCheckBox().isChecked && quantity >= 1) {

               items.add(Item(productList[position].internalProductCode,productList[position].name,
                   productList[position].description, productList[position].price, quantity
                   ))

           } else if (holder.getCheckBox().isChecked && quantity == 0) {

               // error

            } else {

               var itemToRemove = searchOnItemListByProductInternalCode(items, productList[position].internalProductCode)
               items.remove(itemToRemove)
               var size = items.size.toString()

           }
        }
    }

    fun searchOnItemListByProductInternalCode(list : MutableList<Item>, internalProductCode : String) : Item {

        return list.first { it.internalProductCode == internalProductCode }
    }

    fun updateItemQuantity(quantity : Int, list : MutableList<Item> , internalProductCode : String){

        var item = searchOnItemListByProductInternalCode(list,internalProductCode)
        item.quantity = quantity

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}