package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import android.annotation.SuppressLint
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
import com.example.gestionpresupuesto.databinding.FragmentNewBudgetBinding
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.viewmodels.NewBudgetViewModel
import com.example.gestionpresupuesto.viewmodels.SharedViewModel
import java.util.*

class NewBudgetFragment : Fragment() {

    lateinit var v : View
    private val newBudgetViewModel: NewBudgetViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by activityViewModels()
    lateinit var recyclerView : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var productItemsForBudget : ItemsAdapter
    private lateinit var searchView : SearchView
    private lateinit var switch : Switch

    private var _binding: FragmentNewBudgetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewBudgetBinding.inflate(inflater, container, false)

        v = inflater.inflate(R.layout.fragment_new_budget, container, false)
        recyclerView = binding.newBudgetItemsRecyclerView
        switch = binding.finishSwitch
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
                    this, switch)

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

        var budgetToCreate = sharedViewModel.getBudgetToCreate()

            newBudgetViewModel.createBudget(budgetToCreate.value!!)
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
                    sharedViewModel ,this, switch)

                recyclerView.setAdapter(auxiliarAdapter)

            }

        }
    }
}
