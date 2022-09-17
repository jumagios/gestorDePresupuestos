package com.example.gestionpresupuesto.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.entities.Product

class MainAdapter (var productList : MutableList<Product>,
                    var onClick : (Int) -> Unit) : RecyclerView.Adapter<MainAdapter.MainHolder>()
{
    class MainHolder (v: View) : RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}