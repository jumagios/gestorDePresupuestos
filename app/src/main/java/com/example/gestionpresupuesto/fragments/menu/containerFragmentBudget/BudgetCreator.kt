package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.adapters.ItemsAdapter
import com.example.gestionpresupuesto.databinding.FragmentBudgetCreatorBinding
import com.example.gestionpresupuesto.databinding.FragmentProductDetailBinding
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.viewmodels.BudgetCreatorViewModel
import com.example.gestionpresupuesto.viewmodels.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

class BudgetCreator : Fragment() {

    lateinit var v : View
    private val budgetCreatorViewModel: BudgetCreatorViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by activityViewModels()
    lateinit var recyclerView : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var productItemsForBudget : ItemsAdapter
    private lateinit var searchView : SearchView
    private lateinit var finish_button : Button

    private lateinit var binding: FragmentBudgetCreatorBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBudgetCreatorBinding.inflate(inflater, container, false)
        recyclerView = binding.newBudgetItemsRecyclerView
        finish_button = binding.finishButton
        searchView = binding.searchProductInBudgetCreator

        return binding.root
    }

    override fun onStart() {

        super.onStart()

        try{

            var productList = sharedViewModel.getProductList()

            if(productList != null) {

                recyclerView.setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(context)
                recyclerView.layoutManager = linearLayoutManager
                productItemsForBudget = ItemsAdapter(productList, requireContext(), sharedViewModel,
                    this, finish_button)

                recyclerView.adapter = productItemsForBudget

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(query: String?): Boolean {
                        search(productList, query)
                        return true
                    }
                })
            }

        } catch (e : Exception){

            var debug =  e.message.toString()

        }
}

    fun saveBudgetToCreate() {

        try {

            var budgetToCreate = sharedViewModel.getBudgetToCreate()
            budgetCreatorViewModel.createBudget(budgetToCreate.value!!, this)

        } catch (e : Exception) {

            e.message.toString()
            Snackbar.make(binding.newBudgetItemsRecyclerView, "Error inesperado de sistema", Snackbar.LENGTH_LONG).show()

        }
     }

    fun showAlert() {
        sharedViewModel.clearState()
        Snackbar.make(binding.newBudgetItemsRecyclerView, "Presupuesto guardado con exito", Snackbar.LENGTH_LONG).show()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun search(productList : MutableList<Product>, query: String?) {


        val temporalProductList = mutableListOf<Product>()

        val queryLowerCase = query!!.lowercase(Locale.getDefault())

        if(temporalProductList != null) {

            productList.forEach {
                if (it.name.lowercase().contains(queryLowerCase)) {
                    temporalProductList.add(it)
                }

                var auxiliarAdapter = ItemsAdapter(temporalProductList, requireContext(),
                    sharedViewModel ,this, finish_button)

                recyclerView.setAdapter(auxiliarAdapter)

            }

        }
    }
}
