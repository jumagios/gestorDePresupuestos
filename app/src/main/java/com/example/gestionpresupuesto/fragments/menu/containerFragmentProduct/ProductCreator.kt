package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.material.Snackbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.gestionpresupuesto.databinding.FragmentProductCreatorBinding
import com.example.gestionpresupuesto.entities.Product
import com.bumptech.glide.Glide
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetCreatorDirections
import com.example.gestionpresupuesto.viewmodels.ProductCreatorViewModel
import com.google.firebase.Timestamp
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import gun0912.tedimagepicker.builder.TedImagePicker
import java.text.SimpleDateFormat
import java.util.*

 class ProductCreator : Fragment() {
    private lateinit var binding: FragmentProductCreatorBinding
    private lateinit var ImageUri : Uri
    private var mStorageRef: StorageReference? = null
    private lateinit var firebaseImage : ImageView
    private lateinit var viewModel: ProductCreatorViewModel
    private var imagen : String = ""

    companion object {
        fun newInstance() = ProductCreator()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        mStorageRef = FirebaseStorage.getInstance().getReference()
        binding = FragmentProductCreatorBinding.inflate(layoutInflater)
        return binding.root
    }

    //seguir de aca

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductCreatorViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        binding.buttonUpload.setOnClickListener{
            selectImage()
        }

        binding.acceptButton.setOnClickListener {


            if( binding.productInternalCode.text.toString().isNullOrBlank() && binding.productProviderCode.text.toString().isNullOrBlank() &&
                binding.productname.text.toString().isNullOrBlank() && binding.productdescription.text.toString().isNullOrBlank() &&
                binding.productcategory.text.toString().isNullOrBlank() && binding.productPrice.text.toString().isNullOrBlank() &&
                binding.productStock.text.toString().isNullOrBlank()) {

                com.google.android.material.snackbar.Snackbar.make(binding.productCreator, "Todos los campos deben tener valores", com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show()

            } else {



                uploadImage()



            }

        }

        binding.cancelButton.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("¿Desea cancelar la creacion del producto?")
                .setCancelable(false)
                .setPositiveButton("Aceptar", DialogInterface.OnClickListener {
                        dialog, id ->

                        var action = ProductCreatorDirections.actionProductCreator2ToMainProductList()
                        binding.root.findNavController().navigate(action)


                })
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("")
            alert.show()
        }
    }


    private fun uploadImage() {
        val progressDialog = ProgressDialog(this.context)
        progressDialog.setMessage("Uploading File ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(ImageUri).addOnSuccessListener {

           // firebaseImage.setImageURI(null)
            Toast.makeText(this.context, "Successfuly uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()
            imagen = storageReference.downloadUrl.toString()

            createProduct(binding)

        }.addOnFailureListener {
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this.context, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectImage() {
        TedImagePicker.with(this.requireContext())
            .start { uri -> showSingleImage(uri) }
    }

    private fun showSingleImage(uri: Uri) {
        binding.firebaseImage.visibility = View.VISIBLE
        binding.containerSelectedPhotos.visibility = View.GONE
        Glide.with(this).load(uri).into(binding.firebaseImage)
        ImageUri = uri
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            ImageUri = data?.data!!
            firebaseImage.setImageURI(ImageUri)

        }
    }

    private fun createProduct(binding: FragmentProductCreatorBinding) {
            var product = Product("",binding.productInternalCode.text.toString(),binding.productProviderCode.text.toString(),
            binding.productname.text.toString(),binding.productdescription.text.toString(),binding.productcategory.text.toString(),
            binding.productPrice.text.toString().toDouble(),binding.productStock.text.toString().toInt(),Timestamp.now(),false,
            imagen)
            viewModel.createProduct(product, this)
    }

     fun showAlert(){

         com.google.android.material.snackbar.
         Snackbar.make(requireView(), "Producto creado con exito",
             com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
                  .show()

         Handler(Looper.getMainLooper()).postDelayed({

             var action = ProductCreatorDirections.actionProductCreator2ToMainProductList()
             binding.root.findNavController().navigate(action)

         }, 1700)


     }

}