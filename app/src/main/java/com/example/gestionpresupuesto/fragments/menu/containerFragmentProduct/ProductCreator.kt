package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.databinding.FragmentProductCreatorBinding
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.ProductRepository
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestionpresupuesto.adapters.MainProductListAdapter
import com.example.gestionpresupuesto.viewmodels.ProductCreatorViewModel
import com.google.android.material.dialog.MaterialDialogs
import com.google.firebase.Timestamp
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

class ProductCreator : Fragment() {
    private lateinit var binding: FragmentProductCreatorBinding
    private lateinit var ImageUri : Uri
    private var mStorageRef: StorageReference? = null
    private lateinit var firebaseImage : ImageView
    private lateinit var repository: ProductRepository
    private lateinit var v : View

    companion object {
        fun newInstance() = ProductCreator()
    }

    private lateinit var viewModel: ProductCreatorViewModel
    private lateinit var productCreator: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        v = inflater.inflate(R.layout.fragment_product_creator, container, false)
        mStorageRef = FirebaseStorage.getInstance().getReference()
        binding = FragmentProductCreatorBinding.inflate(layoutInflater)
        productCreator = v.findViewById(R.id.productCreator)
        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductCreatorViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        /**binding.buttonUpload.setOnClickListener{
            selectImage()
        }*/

        binding.acceptButton.setOnClickListener {
           // uploadImage()
            createProduct(binding)
            goToProductCreate()
        }

        binding.cancelButton.setOnClickListener {
            goToProductCreate()
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

            firebaseImage.setImageURI(null)
            Toast.makeText(this.context, "Successfuly uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()
        }.addOnFailureListener{
            if(progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this.context, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
    private fun selectImage() {
        //val intent : Intent
        //intent.type = "images/*"
        //intent.action = Intent.ACTION_GET_CONTENT

        //startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            ImageUri = data?.data!!
            firebaseImage.setImageURI(ImageUri)

        }
    }
    private fun goToProductCreate() {
        refreshFragment(context)
    }

    private fun refreshFragment(context: Context?) {
        context?.let {
            val fragmentManager = (context as? AppCompatActivity)?.supportFragmentManager
            fragmentManager?.let {
                val currentFragment = fragmentManager.findFragmentById(R.id.container)
                currentFragment?.let {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.detach(it)
                    fragmentTransaction.attach(it)
                    fragmentTransaction.commit()
                }
            }
        }
    }

    private fun createProduct(binding: FragmentProductCreatorBinding) {
        var product = Product("",binding.productInternalCode.editText.toString(),binding.productProviderCode.editText.toString(),
            binding.productname.editText.toString(),binding.productdescription.editText.toString(),binding.productcategory.editText.toString(),
            binding.productPrice.editText.toString().toDouble(),binding.productStock.editText.toString().toInt(),Timestamp.now(),false,
            binding.firebaseImage.toString())
        viewModel.createProduct(product)
    }

}