package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.adapters.BudgetAdapter
import com.example.gestionpresupuesto.adapters.MainProductListAdapter
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.viewmodels.BudgetListViewModel
import com.example.gestionpresupuesto.viewmodels.MainProductListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class MainProductList : Fragment() {

    companion object {
        fun newInstance() = MainProductList()
    }

    lateinit var v : View

    lateinit var recProducts : RecyclerView
    private lateinit var viewModel : MainProductListViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var buttonAdd : FloatingActionButton
    private lateinit var searchView : SearchView
    private lateinit var productListAdapter : MainProductListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_main_product_list, container, false)
        searchView = v.findViewById(R.id.searchViewProduct)
        buttonAdd = v.findViewById(R.id.floating_action_button_user)
        recProducts = v.findViewById(R.id.rec_products)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainProductListViewModel::class.java)

    }


    override fun onStart() {
        super.onStart()

        viewModel.getAllProducts()
        
        viewModel.productList.observe(viewLifecycleOwner, Observer { result ->

            searchView.setQuery("",false)
            var productList = result
            recProducts.setHasFixedSize(true)
            linearLayoutManager = LinearLayoutManager(context)
            recProducts.layoutManager = linearLayoutManager
            productListAdapter = MainProductListAdapter(productList,requireContext()) {
                onItemClick(it)
            }

            recProducts.adapter = productListAdapter

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query : String?): Boolean {
                    search(productList,query)
                    return true
                }

            })



        })

        buttonAdd.setOnClickListener {
            val action = MainProductListDirections.actionMainProductListToProductCreator2()
            v.findNavController().navigate(action)
        }


    }

    private fun search(productList : MutableList<Product>, query: String?) {

        val temporalBudgetList = mutableListOf<Product>()
        val queryLowerCase = query!!.lowercase(Locale.getDefault())
        var cont = 0
        productList.forEach {
            if (it.name.lowercase().contains(queryLowerCase)) {
                temporalBudgetList.add(it)

            }

            var auxiliarAdapter = MainProductListAdapter(temporalBudgetList, requireContext()){
                onItemClick(it)
            }
            cont += cont
            recProducts.setAdapter(auxiliarAdapter)

        }
    }



    fun onItemClick ( position : Int )  {
        Snackbar.make(v,position.toString(), Snackbar.LENGTH_SHORT).show()
    }

}