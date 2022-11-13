package com.example.gestionpresupuesto.fragments.menu.containerFragmentUser


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.gestionpresupuesto.databinding.FragmentUserDetailBinding
import com.example.gestionpresupuesto.viewmodels.UserDetailViewModel

class user_detail : Fragment() {

    companion object {
        fun newInstance() = user_detail()
    }

    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onStart() {

        super.onStart()

        var userDetails = user_detailArgs.fromBundle(requireArguments()).userDetail2

        setRadioButtonState(userDetails)
        setUserImage()

        binding.userDetailMail.text = userDetails.email


        binding.userAdminButton.setOnClickListener{

            binding.userSellerButton.isChecked = false
            binding.userDetailDeleted.isChecked = false
            userDetails.admin = true
            viewModel.updateAdminState(userDetails)

        }

        binding.userSellerButton.setOnClickListener{

            binding.userAdminButton.isChecked = false
            binding.userDetailDeleted.isChecked = false
            userDetails.admin = false
            viewModel.updateAdminState(userDetails)

        }


        binding.userDetailDeleted.setOnClickListener{

            binding.userSellerButton.isChecked = false
            binding.userAdminButton.isChecked = false
            binding.userDetailDeleted.isChecked = true
            viewModel.deleteUser(userDetails)

        }
    }

    private fun setUserImage() {

        Glide.with(binding.userDetailImagen).load("https://firebasestorage.googleapis.com/v0/b/gestion-de-presupuesto.appspot.com/o/images%2Fuser.webp?alt=media&token=7de2722d-6e41-4c25-a5b5-5ff4f4205126").override(300,300).into(binding.userDetailImagen)

    }

    private fun setRadioButtonState(userDetails : com.example.gestionpresupuesto.entities.User) {

        if(userDetails.admin && !userDetails.erased) {

            binding.userAdminButton.isChecked = true


        } else if (!userDetails.admin && !userDetails.erased) {

            binding.userSellerButton.isChecked = true

        } else  {
            binding.userAdminButton.isChecked = false
            binding.userSellerButton.isChecked = false
            binding.userDetailDeleted.isChecked = true

        }
    }
}