package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct


import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.gestionpresupuesto.databinding.FragmentProductCreatorBinding
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.viewmodels.ProductCreatorViewModel
import com.google.firebase.Timestamp
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class ProductCreator : Fragment() {

    private lateinit var binding: FragmentProductCreatorBinding
    private var mStorageRef: StorageReference? = null
    private lateinit var firebaseImage: ImageView
    private val viewModel: ProductCreatorViewModel by viewModels()

    var storageRef = Firebase.storage.reference;

    var imagePickerActivityResult: ActivityResultLauncher<Intent> =
    // lambda expression to receive a result back, here we
        // receive single item(photo) on selection

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result != null) {
                // getting URI of selected Image
                val imageUri: Uri? = result.data?.data

                val fileName = imageUri?.pathSegments?.last()

                // extract the file name with extension
                val sd = getFileName(this.requireContext(), imageUri!!)

                // Upload Task with upload to directory 'file'
                // and name of the file remains same
                val uploadTask = storageRef.child("images/$sd").putFile(imageUri)

                // On success, download the file URL and display it

                val progressDialog = ProgressDialog(this.context)
                progressDialog.setMessage("Uploading File ...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                uploadTask.addOnSuccessListener {
                    // using glide library to display the image
                    storageRef.child("images/$sd").downloadUrl.addOnSuccessListener {

                        Glide.with(this).load(it).into(binding.firebaseImage)

                        viewModel.setImageURL(it.toString())
                        if (progressDialog.isShowing) progressDialog.dismiss()
                        com.google.android.material.snackbar.Snackbar.make(
                            binding.productCreator,
                            "Imagen cargada con exito",
                            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                        ).show()


                        Log.e("Firebase", "download passed")
                    }.addOnFailureListener {
                        if (progressDialog.isShowing) progressDialog.dismiss()
                        com.google.android.material.snackbar.Snackbar.make(
                            binding.productCreator,
                            "Error al carga la imagen",
                            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                        ).show()
                        Log.e("Firebase", "Failed in downloading")
                    }
                }.addOnFailureListener {
                    if (progressDialog.isShowing) progressDialog.dismiss()
                    com.google.android.material.snackbar.Snackbar.make(
                        binding.productCreator,
                        "Error al carga la imagen",
                        com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                    ).show()
                    Log.e("Firebase", "Image Upload fail")
                }
            }
        }


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

    override fun onStart() {
        super.onStart()

        binding.buttonUpload.setOnClickListener {

            val galleryIntent = Intent(Intent.ACTION_PICK)
            galleryIntent.type = "image/*"
            imagePickerActivityResult.launch(galleryIntent)

        }

        binding.acceptButton.setOnClickListener() {

            if (binding.productInternalCode.text.toString()
                    .isNullOrBlank() && binding.productProviderCode.text.toString()
                    .isNullOrBlank() &&
                binding.productname.text.toString()
                    .isNullOrBlank() && binding.productdescription.text.toString()
                    .isNullOrBlank() &&
                binding.productcategory.text.toString()
                    .isNullOrBlank() && binding.productPrice.text.toString().isNullOrBlank() &&
                binding.productStock.text.toString().isNullOrBlank()
            ) {

                com.google.android.material.snackbar.Snackbar.make(
                    binding.productCreator,
                    "Todos los campos deben tener valores",
                    com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                ).show()

            } else {

                createProduct()

            }

        }

        binding.cancelButton.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("¿Desea cancelar la creacion del producto?")
                .setCancelable(false)
                .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->

                    var action = ProductCreatorDirections.actionProductCreator2ToMainProductList()
                    binding.root.findNavController().navigate(action)


                })
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("")
            alert.show()
        }
    }


    private fun getFileName(context: Context, uri: Uri): String? {
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor.use {
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        // return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                }
            }
        }
        return uri.path?.lastIndexOf('/')?.let { uri.path?.substring(it) }
    }

    private fun createProduct() {

        var product = Product(
            "",
            binding.productInternalCode.text.toString(),
            binding.productProviderCode.text.toString(),
            binding.productname.text.toString(),
            binding.productdescription.text.toString(),
            binding.productcategory.text.toString(),
            binding.productPrice.text.toString().toDouble(),
            binding.productStock.text.toString().toInt(),
            Timestamp.now(),
            false,
            setImagenURL()
        )

        viewModel.createProduct(product, this)

    }

    private fun setImagenURL(): String {

        var imageURL = ""
        var defaultURL =
            "https://firebasestorage.googleapis.com/v0/b/gestion-de-presupuesto.appspot.com/o/images%2FProductImageNotFound.jpg?alt=media&token=d412003a-5dba-404e-b611-ed7f18dd839f"

        if (viewModel.imagenURL.value == null) {
            imageURL = defaultURL
        } else {
            imageURL = viewModel.imagenURL.value.toString()
        }

        return imageURL

    }

    fun showAlert() {

        com.google.android.material.snackbar.Snackbar.make(
            requireView(), "Producto creado con exito",
            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
        )
            .show()

        Handler(Looper.getMainLooper()).postDelayed({

            var action = ProductCreatorDirections.actionProductCreator2ToMainProductList()
            binding.root.findNavController().navigate(action)

        }, 1700)

    }

}
