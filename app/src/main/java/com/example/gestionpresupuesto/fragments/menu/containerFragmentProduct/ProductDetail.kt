package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.gestionpresupuesto.databinding.FragmentProductDetailBinding
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetCreatorDirections
import com.example.gestionpresupuesto.viewmodels.ProductDetailViewModel
import com.google.android.material.snackbar.Snackbar


class ProductDetail : Fragment() {

    companion object {
        fun newInstance() = ProductDetail()
    }

    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onStart() {
        super.onStart()

        //here we set the data of the product we previously click
        var productDetails = ProductDetailArgs.fromBundle(requireArguments()).product
        binding.productname.setText(productDetails.name)
        binding.productdescription.setText(productDetails.description)
        binding.productcategory.setText(productDetails.category)
        binding.productInternalCode.setText(productDetails.internalProductCode)
        binding.productProviderCode.setText(productDetails.providerProductCode)
        binding.productPrice.setText(productDetails.price.toString())
        binding.productStock.setText(productDetails.stock.toString())
        Glide.with(binding.firebaseImage).load(productDetails.imageURL).override(100,100).into(binding.firebaseImage)




        //here we disable the input for every field

        binding.productname.setRawInputType(InputType.TYPE_NULL)
        binding.productdescription.setRawInputType(InputType.TYPE_NULL)
        binding.productcategory.setRawInputType(InputType.TYPE_NULL)
        binding.productInternalCode.setRawInputType(InputType.TYPE_NULL)
        binding.productProviderCode.setRawInputType(InputType.TYPE_NULL)
        binding.productPrice.setRawInputType(InputType.TYPE_NULL)
        binding.productStock.setRawInputType(InputType.TYPE_NULL)


        //when we click on update the inputs are enabled
        binding.updateButton.setOnClickListener{

            if(binding.updateButton.text.toString() == viewModel.getUpdatedTxt()){

                if(binding.productInternalCode.text.isNullOrBlank() || binding.productname.text.isNullOrBlank()
                    || binding.productdescription.text.isNullOrBlank() || binding.productcategory.text.isNullOrBlank()
                    || binding.productInternalCode.text.isNullOrBlank() || binding.productPrice.text.isNullOrBlank()
                    || binding.productStock.text.isNullOrBlank() ) {

                    Snackbar.make(binding.root, viewModel.getNullOrBlankTxt(), Snackbar.LENGTH_SHORT)
                        .show()
                } else {

                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Aviso")
                    builder.setMessage("¿Seguro que desea cambiar los datos?")
                    builder.setPositiveButton("Confirmar") { _, _ ->
                        Toast.makeText(
                            context,
                            "Confirmar", Toast.LENGTH_SHORT
                        ).show()

                        viewModel.updateProduct(productDetailsUpdated(productDetails))
                    }
                    builder.setNegativeButton("Cancelar") { _, _ ->
                        Toast.makeText(
                            context,
                            "Cancelar", Toast.LENGTH_SHORT
                        ).show()
                    }

                    builder.show()
                }
            }

            binding.productname.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.productdescription.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.productcategory.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.productInternalCode.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.productProviderCode.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.productPrice.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.productStock.setRawInputType(InputType.TYPE_CLASS_TEXT)

            if(binding.updateButton.text.toString() == "EDITAR") {
                Snackbar.make(binding.root, viewModel.getEditableText(), Snackbar.LENGTH_SHORT)
                    .show()
            }

            binding.updateButton.setText(viewModel.getUpdatedTxt())


        }

        binding.productDeleteButton.setOnClickListener{
            viewModel.deleteProduct(productDetails, this)


        }

    }

    private fun productDetailsUpdated(productDetails: Product): Product {

        productDetails.name = binding.productname.text.toString()
        productDetails.description = binding.productdescription.text.toString()
        productDetails.providerProductCode = binding.productProviderCode.text.toString()
        productDetails.internalProductCode = binding.productProviderCode.text.toString()
        productDetails.price = binding.productPrice.text.toString().toDouble()
        productDetails.stock = binding.productStock.text.toString().toInt()
        productDetails.category = binding.productcategory.text.toString()


        return productDetails

    }

    fun showAlert() {

        val oSnackbar = Snackbar.make(requireView(), "Producto eliminado con exito", Snackbar.LENGTH_LONG)
        oSnackbar.show()

        Handler(Looper.getMainLooper()).postDelayed({

            val action = ProductDetailDirections.actionProductDetailToMainProductList()
            binding.root.findNavController().navigate(action)

        }, 350)

    }


}