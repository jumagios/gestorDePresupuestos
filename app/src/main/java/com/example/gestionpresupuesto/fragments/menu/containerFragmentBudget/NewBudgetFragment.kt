package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.adapters.ItemsAdapter
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.viewmodels.MainProductListViewModel
import com.example.gestionpresupuesto.viewmodels.NewBudgetViewModel

class NewBudgetFragment : Fragment() {


    lateinit var v : View
    private lateinit var viewModel: NewBudgetViewModel
    private val mainProductListViewModel : MainProductListViewModel by viewModels()
    lateinit var recyclerView : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var productItemsForBudget : ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_new_budget, container, false)
        recyclerView = v.findViewById(R.id.new_budget_items_recycler_view)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewBudgetViewModel::class.java)

    }

    override fun onStart() {

        super.onStart()

        mainProductListViewModel.getAllProducts()
        mainProductListViewModel.productList.observe(viewLifecycleOwner, Observer { result ->

                var productList = result

                var itemList = mutableListOf<Item>()


            try {

                recyclerView.setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(context)
                recyclerView.layoutManager = linearLayoutManager
                productItemsForBudget = ItemsAdapter(productList,itemList, requireContext())

                recyclerView.adapter = productItemsForBudget

            } catch (e : Exception) {
                e.message.toString()
            }

        })
}
}
