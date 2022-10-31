package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestionpresupuesto.adapters.MainProductListAdapter
import com.example.gestionpresupuesto.databinding.FragmentMainProductListBinding
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.viewmodels.MainProductListViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainProductList : Fragment() {

    companion object {
        fun newInstance() = MainProductList()
    }

    private val viewModel: MainProductListViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var productListAdapter : MainProductListAdapter
    private lateinit var binding: FragmentMainProductListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainProductListBinding.inflate(layoutInflater)
        binding.floatingActionButtonUser.hide()
        return binding.root
    }


    override fun onStart() {

        super.onStart()

        viewModel.getIsAdmin()

        viewModel.isAdmin.observe(viewLifecycleOwner, Observer { isAdmin ->

            if(viewModel.isAdmin.value == true) {

                binding.floatingActionButtonUser.show()

            }

            viewModel.getAllProducts()

            viewModel.productList.observe(viewLifecycleOwner, Observer { result ->

                binding.searchViewProduct.setQuery("",false)
                var productList = result
                binding.recProducts.setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(context)
                binding.recProducts.layoutManager = linearLayoutManager

                productListAdapter = MainProductListAdapter(productList,requireContext()) {
                    onItemClick(it)
                }

                binding.recProducts.adapter = productListAdapter

                binding.searchViewProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(query : String?): Boolean {
                        search(productList,query)
                        return true
                    }

                })

            })

        })


        binding.floatingActionButtonUser.setOnClickListener {
            val action = MainProductListDirections.actionMainProductListToProductCreator2()
            binding.root.findNavController().navigate(action)
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
            binding.recProducts.setAdapter(auxiliarAdapter)

        }
    }

    fun onItemClick ( position : Int )  {
        Snackbar.make(binding.root,position.toString(), Snackbar.LENGTH_SHORT).show()
    }

}