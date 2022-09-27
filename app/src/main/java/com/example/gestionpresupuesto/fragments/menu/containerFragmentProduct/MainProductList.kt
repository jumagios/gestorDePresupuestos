package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.adapters.MainProductListAdapter
import com.example.gestionpresupuesto.entities.Product
import com.google.android.material.snackbar.Snackbar

class MainProductList : Fragment() {

    lateinit var v : View

    lateinit var recProducts : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var productListAdapter : MainProductListAdapter

    var products : MutableList<Product> = ArrayList<Product>()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_main_product_list, container, false)
        recProducts = v.findViewById(R.id.rec_products)

        return v
    }


    override fun onStart() {
        super.onStart()

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false))

        recProducts.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recProducts.layoutManager = linearLayoutManager

        productListAdapter = MainProductListAdapter(products,requireContext()){ pos->
            onItemClick(pos)
        }

        recProducts.adapter = productListAdapter


    }

    fun onItemClick ( position : Int )  {
        Snackbar.make(v,position.toString(), Snackbar.LENGTH_SHORT).show()
    }

}