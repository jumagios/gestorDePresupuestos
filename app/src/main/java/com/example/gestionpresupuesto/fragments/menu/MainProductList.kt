package com.example.gestionpresupuesto.fragments.menu

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

        products.add(Product("1",1000.0,"Camara",15,"https://http2.mlstatic.com/D_NQ_NP_631530-MLA49805502511_042022-W.jpg"))
        products.add(Product("2",2500.0,"Camara2",24,"https://i.ytimg.com/an/IyLE_D7YDxo/11419294179261960037_mq.jpg?v=60e843fb"))
        products.add(Product("3",3500.0,"Camara3",0,"https://http2.mlstatic.com/D_NQ_NP_2X_603025-MLA41441242377_042020-F.webp"))
        products.add(Product("4",4400.0,"Camara4",12,"https://medias.musimundo.com/medias/00502017-145280-145280-01-145280-01.jpg-size300?context=bWFzdGVyfGltYWdlc3wyMDY3OHxpbWFnZS9qcGVnfGg3NS9oN2YvMTAzODE0MjY2ODgwMzAvMDA1MDIwMTctMTQ1MjgwLTE0NTI4MF8wMS0xNDUyODBfMDEuanBnX3NpemUzMDB8NDU0YTI4ZmQzY2RlYjI0NTE4NjFiYmNkODRhNjhjOGRlNWM3ZjAwYjJiZDcwNjRhOGNkMmRmNWIzY2JkNjM2ZQ"))
        products.add(Product("5",5500.0,"Camara5",250,"https://http2.mlstatic.com/D_NQ_NP_803358-MLA48686773748_122021-W.jpg"))
        products.add(Product("6",6500.50,"Camara6",4,"https://static.casadomo.com/media/2020/04/panasonic-trece-camaras-seguridad-seieu-vigilancia.png"))
        products.add(Product("7",75.23,"Camara7",0,"https://www.kindpng.com/picc/m/239-2392968_camara-de-seguridad-con-audio-hd-png-download.png"))
        products.add(Product("8",850.45,"Camara8",1,"https://http2.mlstatic.com/D_NQ_NP_634112-MLA43426622037_092020-O.jpg"))

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