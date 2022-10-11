package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.ProductDetailViewModel

class ProductDetail : Fragment() {

    companion object {
        fun newInstance() = ProductDetail()
    }

    lateinit var v : View
    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_product_detail, container, false)
        updateButton = v.findViewById(R.id.product_update_button)
        deleteButton = v.findViewById(R.id.product_delete_button)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        var productDetails = ProductDetailArgs.fromBundle(requireArguments()).product

        //ACA TENGO QUE MAPEAR LOS DATOS DE productDetails CON EL LOS CAMPOS DEL XML


        updateButton.setOnClickListener{
        }

        deleteButton.setOnClickListener{
            viewModel.deleteProduct(productDetails)

        var action = ProductDetailDirections.actionProductDetailToMainProductList()
            v.findNavController().navigate(action)
        }

    }


}