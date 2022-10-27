package com.example.gestionpresupuesto.fragments.menu.containerFragmentUser

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.databinding.FragmentProductCreatorBinding
import com.example.gestionpresupuesto.databinding.FragmentUserCreatorBinding
import com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct.ProductCreator
import com.example.gestionpresupuesto.repository.ProductRepository
import com.example.gestionpresupuesto.repository.UserRepository
import com.example.gestionpresupuesto.viewmodels.ProductCreatorViewModel
import com.example.gestionpresupuesto.viewmodels.UserCreatorViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UserCreator : Fragment() {
    private lateinit var binding: FragmentUserCreatorBinding
    private lateinit var repository: UserRepository
    private lateinit var v : View
    private var mStorageRef: StorageReference? = null

    companion object {
        fun newInstance() = UserCreator()
    }

    private lateinit var viewModel: UserCreatorViewModel
    private lateinit var userCreator: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user_creator, container, false)
        mStorageRef = FirebaseStorage.getInstance().getReference()
        binding = FragmentUserCreatorBinding.inflate(layoutInflater)
        userCreator = v.findViewById(R.id.userCreator)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserCreatorViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        binding.userAcceptButton.setOnClickListener {
            //createProduct(binding)
            goToUserCreate()
        }

        binding.userCancelButton.setOnClickListener {
            goToUserCreate()
        }

    }

    private fun goToUserCreate() {
        refreshFragment(context)
    }

    private fun refreshFragment(context: Context?) {
        context?.let {
            val fragmentManager = (context as? AppCompatActivity)?.supportFragmentManager
            fragmentManager?.let {
                val currentFragment = fragmentManager.findFragmentById(com.google.android.material.R.id.container)
                currentFragment?.let {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.detach(it)
                    fragmentTransaction.attach(it)
                    fragmentTransaction.commit()
                }
            }
        }
    }


}