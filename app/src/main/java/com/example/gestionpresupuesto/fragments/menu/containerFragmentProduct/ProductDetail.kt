package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.databinding.FragmentProductDetailBinding
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.viewmodels.ProductDetailViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


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

   /* override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    override fun onStart() {
        super.onStart()

        //here we set the data of the product we previously click
        var productDetails = ProductDetailArgs.fromBundle(requireArguments()).product
        binding.productname.editText?.setText(productDetails.name)
        binding.productdescription.editText?.setText(productDetails.description)
        binding.productcategory.editText?.setText(productDetails.category)
        binding.productInternalCode.editText?.setText(productDetails.internalProductCode)
        binding.productProviderCode.editText?.setText(productDetails.providerProductCode)
        binding.productPrice.editText?.setText(productDetails.price.toString())
        binding.productStock.editText?.setText(productDetails.stock.toString())
        Glide.with(binding.firebaseImage).load(productDetails.imageURL).override(100,100).into(binding.firebaseImage)




        //here we disable the input for every field


        binding.txtProductName.setRawInputType(InputType.TYPE_NULL)
        binding.txtProdDescription.setRawInputType(InputType.TYPE_NULL)
        binding.txtProdCategory.setRawInputType(InputType.TYPE_NULL)
        binding.txtProdIntCode.setRawInputType(InputType.TYPE_NULL)
        binding.txtProdProvCode.setRawInputType(InputType.TYPE_NULL)
        binding.txtProdPrice.setRawInputType(InputType.TYPE_NULL)
        binding.txtProdStock.setRawInputType(InputType.TYPE_NULL)

        //ACA TENGO QUE MAPEAR LOS DATOS DE productDetails CON EL LOS CAMPOS DEL XML


        //when we click on update the inputs are enabled
        binding.productUpdateButton.setOnClickListener{

            if(binding.productUpdateButton.text.toString() == viewModel.getUpdatedTxt()){
                println("Si entre")
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Aviso")
                builder.setMessage("¿Seguro que desea cambiar los datos?")
                builder.setPositiveButton("Confirmar") { _, _ ->
                    Toast.makeText(context,
                        "Confirmar", Toast.LENGTH_SHORT).show()

                    viewModel.updateProduct(productDetailsUpdated(productDetails))
                }
                builder.setNegativeButton("Cancelar") { _, _ ->
                    Toast.makeText(context,
                        "Cancelar", Toast.LENGTH_SHORT).show()
                }

                builder.show()

            }

            binding.txtProductName.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.txtProdDescription.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.txtProdCategory.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.txtProdIntCode.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.txtProdProvCode.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.txtProdPrice.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.txtProdStock.setRawInputType(InputType.TYPE_CLASS_TEXT)

            Snackbar.make(binding.root,viewModel.getSnackbarText(), Snackbar.LENGTH_SHORT).show()

            binding.productUpdateButton.setText(viewModel.getUpdatedTxt())


        }








        binding.productDeleteButton.setOnClickListener{
            viewModel.deleteProduct(productDetails)

            val action = ProductDetailDirections.actionProductDetailToMainProductList()
            binding.root.findNavController().navigate(action)
        }

    }

    private fun productDetailsUpdated(productDetails: Product): Product {
        var productUpdated = Product()
        productDetails.name = binding.productname.editText?.text.toString()
        productDetails.description = binding.productdescription.editText?.text.toString()
        productDetails.providerProductCode = binding.productProviderCode.editText?.text.toString()
        productDetails.internalProductCode = binding.productProviderCode.editText?.text.toString()
        productDetails.price = binding.productPrice.editText?.text.toString().toDouble()
        productDetails.stock = binding.productStock.editText?.text.toString().toInt()
        productDetails.category = binding.productcategory.editText?.text.toString()



        productUpdated = productDetails

        return productUpdated

    }


}