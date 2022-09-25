package com.example.gestionpresupuesto.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Budget

class BudgetAdapter (
    var budgetList: MutableList<Budget>,
    val context: Context,
        ) : RecyclerView.Adapter<BudgetAdapter.MainHolder>() {

        class MainHolder (v: View) : RecyclerView.ViewHolder(v) {
            private var view: View
            init {
                this.view = v
            }

            fun setClientDomicile(name: String) {
                val txt: TextView = view.findViewById(R.id.txt_budget_name_item)
                txt.text = name
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_budget,parent,false)
            return (MainHolder(view))
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            holder.setClientDomicile(budgetList[position].clientDomicile)


        }

        override fun getItemCount(): Int {
            return budgetList.size
        }
    }