package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.adapters.ItemsAdapter
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.viewmodels.BugdetCreatorViewModel
import com.example.gestionpresupuesto.viewmodels.MainProductListViewModel
import com.example.gestionpresupuesto.viewmodels.NewBudgetViewModel
import com.example.gestionpresupuesto.viewmodels.SharedViewModel
import java.util.*

class NewBudgetFragment : Fragment() {

    lateinit var v : View
    private lateinit var viewModel: NewBudgetViewModel
    private val mainProductListViewModel : MainProductListViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private val budgetCreatorViewModel : BugdetCreatorViewModel by viewModels()

    lateinit var recyclerView : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var productItemsForBudget : ItemsAdapter

    private lateinit var searchView : SearchView

    private lateinit var switch : Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_new_budget, container, false)
        recyclerView = v.findViewById(R.id.new_budget_items_recycler_view)
        switch = v.findViewById(R.id.finish_switch)
        searchView = v.findViewById(R.id.searchProductInBudgetCreator)



        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewBudgetViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


    }

    override fun onStart() {

        super.onStart()



        try{


            var productList = sharedViewModel.getProductList()

            if(productList != null) {

                recyclerView.setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(context)
                recyclerView.layoutManager = linearLayoutManager
                productItemsForBudget = ItemsAdapter(productList, requireContext(), sharedViewModel
                    , switch)

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
                    sharedViewModel
                    , switch)
                recyclerView.setAdapter(auxiliarAdapter)

            }

        }
    }
}
