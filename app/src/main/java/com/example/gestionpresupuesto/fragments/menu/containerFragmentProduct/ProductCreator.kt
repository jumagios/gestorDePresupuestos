package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.adapters.MainProductListAdapter
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.viewmodels.ProductCreatorViewModel

class ProductCreator : Fragment() {

    companion object {
        fun newInstance() = ProductCreator()
    }

    private lateinit var viewModel: ProductCreatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_creator, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductCreatorViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        /* ORIGINAL
    var productMock = Product("AAA01","AAC", "Camera","Security", "",
        600.7, 5,1664239724,false,"https://http2.mlstatic.com/D_NQ_NP_634112-MLA43426622037_092020-O.jpg")
    */

        //* ACTUALIZADO
        var productMock = Product("Domo2mpx","CCC", "Camera3","Security", "",
            700.7, 25,27102022,false,"https://http2.mlstatic.com/D_NQ_NP_634112-MLA43426622037_092020-O.jpg")

        viewModel.updateProduct(productMock)

    }


}