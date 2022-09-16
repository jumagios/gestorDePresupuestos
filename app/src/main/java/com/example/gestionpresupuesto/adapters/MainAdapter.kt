package com.example.gestionpresupuesto.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.entities.Product

class MainAdapter (var productList : MutableList<Product>,
                    var onClick : (Int) -> Unit) : RecyclerView.Adapter<MainAdapter.MainHolder>
{
    class MainHolder (v: View) : RecyclerView
}