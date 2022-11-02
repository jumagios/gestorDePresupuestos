package com.example.gestionpresupuesto.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetListDirections

class BudgetAdapter(
    var budgetList: MutableList<Budget>,
    val context: Context) : RecyclerView.Adapter<BudgetAdapter.MainHolder>() {

    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View
        init {
            this.view = v
        }

        fun setClientDomicile(domicile: String) {
            val txt: TextView = view.findViewById(R.id.txt_budget_name_item)
            txt.text = domicile
        }


        fun getTxItem(): View {
            return view.findViewById(R.id.budget_item_details)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_budget, parent, false)
        return (MainHolder(view))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {

        if (!budgetList[position].isErased) {
            if(!budgetList[position].apartment.isNullOrBlank()) {
                holder.setClientDomicile(budgetList[position].clientDomicile + " Dpto: " + budgetList[position].apartment)
            } else {
                holder.setClientDomicile(budgetList[position].clientDomicile)
            }

        }

        holder.getTxItem().setOnClickListener {
             val action = BudgetListDirections.actionMainBudgetListToBudgetWithItemsDetailsFragment(budgetList[position])
             holder.itemView.findNavController().navigate(action)
        }
    }
        override fun getItemCount(): Int {
        return budgetList.size
        }
    }
