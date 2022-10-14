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
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.ProductDetailViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


class ProductDetail : Fragment() {

    companion object {
        fun newInstance() = ProductDetail()
    }

    lateinit var v : View
    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var productName : TextInputEditText
    private lateinit var productDescription : TextInputEditText
    private lateinit var productCategory : TextInputEditText
    private lateinit var productInternalCode : TextInputEditText
    private lateinit var productProviderCode: TextInputEditText
    private lateinit var productPrice : AppCompatEditText
    private lateinit var productStock : AppCompatEditText
    private lateinit var productImg : ImageView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_product_detail, container, false)
        updateButton = v.findViewById(R.id.product_update_button)
        deleteButton = v.findViewById(R.id.product_delete_button)
        productName = v.findViewById(R.id.txtProductName)
        productDescription = v.findViewById(R.id.txtProdDescription)
        productCategory = v.findViewById(R.id.txtProdCategory)
        productInternalCode = v.findViewById(R.id.txtProdIntCode)
        productProviderCode = v.findViewById(R.id.txtProdProvCode)
        productPrice = v.findViewById(R.id.txtProdPrice)
        productStock = v.findViewById(R.id.txtProdStock)
        productImg = v.findViewById(R.id.firebaseImage)
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
        productName.setText(productDetails.name)
        productDescription.setHint(productDetails.description)
        productCategory.setHint(productDetails.category)
        productInternalCode.setHint(productDetails.internalProductCode)
        productProviderCode.setHint(productDetails.providerProductCode)
        productPrice.setHint(productDetails.price.toString())
        productStock.setHint(productDetails.stock.toString())
        Glide.with(productImg).load(productDetails.imageURL).override(100,100).into(productImg)


        productName.setRawInputType(InputType.TYPE_NULL)
        productDescription.setRawInputType(InputType.TYPE_NULL)
        productCategory.setRawInputType(InputType.TYPE_NULL)
        productInternalCode.setRawInputType(InputType.TYPE_NULL)
        productProviderCode.setRawInputType(InputType.TYPE_NULL)
        productPrice.setRawInputType(InputType.TYPE_NULL)
        productStock.setRawInputType(InputType.TYPE_NULL)

        //ACA TENGO QUE MAPEAR LOS DATOS DE productDetails CON EL LOS CAMPOS DEL XML


        updateButton.setOnClickListener{

            if(updateButton.text.toString() == "Actualizar"){
                println("Si entre")
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Aviso")
                builder.setMessage("¿Seguro que desea cambiar los datos?")

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(context,
                        android.R.string.yes, Toast.LENGTH_SHORT).show()
                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(context,
                        android.R.string.no, Toast.LENGTH_SHORT).show()
                }

                builder.show()

            }


            productName.setRawInputType(InputType.TYPE_CLASS_TEXT)
            productDescription.setRawInputType(InputType.TYPE_CLASS_TEXT)
            productCategory.setRawInputType(InputType.TYPE_CLASS_TEXT)
            productInternalCode.setRawInputType(InputType.TYPE_CLASS_NUMBER)
            productProviderCode.setRawInputType(InputType.TYPE_CLASS_NUMBER)
            productPrice.setRawInputType(InputType.TYPE_CLASS_NUMBER)
            productStock.setRawInputType(InputType.TYPE_CLASS_NUMBER)

            Snackbar.make(v,"Ahora ya podes editar los campos", Snackbar.LENGTH_SHORT).show()

            updateButton.setText("Actualizar")


        }








        deleteButton.setOnClickListener{
            viewModel.deleteProduct(productDetails)

            val action = ProductDetailDirections.actionProductDetailToMainProductList()
            v.findNavController().navigate(action)
        }

    }




}