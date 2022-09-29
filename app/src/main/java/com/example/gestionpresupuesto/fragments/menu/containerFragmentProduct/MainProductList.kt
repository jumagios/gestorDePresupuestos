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
        //recProducts = v.findViewById(R.id.rec_products)

        return v
    }


    override fun onStart() {
        super.onStart()

        /**products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false,"https://http2.mlstatic.com/D_NQ_NP_631530-MLA49805502511_042022-W.jpg"))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false,"https://i.ytimg.com/an/IyLE_D7YDxo/11419294179261960037_mq.jpg?v=60e843fb"))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false,"https://http2.mlstatic.com/D_NQ_NP_2X_603025-MLA41441242377_042020-F.webp"))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false,"https://http2.mlstatic.com/D_NQ_NP_803358-MLA48686773748_122021-W.jpg"))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false,"https://static.casadomo.com/media/2020/04/panasonic-trece-camaras-seguridad-seieu-vigilancia.png"))

        products.add(Product("AAA01","AAC", "Camera","Security", "",
            600.7, 5,1664239724,false,"https://http2.mlstatic.com/D_NQ_NP_634112-MLA43426622037_092020-O.jpg"))
        */
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