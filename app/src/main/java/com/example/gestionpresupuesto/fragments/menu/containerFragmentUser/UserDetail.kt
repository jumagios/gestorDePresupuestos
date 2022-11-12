package com.example.gestionpresupuesto.fragments.menu.containerFragmentUser

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.databinding.FragmentUserDetailBinding
import com.example.gestionpresupuesto.viewmodels.UserDetailViewModel
import com.google.android.material.snackbar.Snackbar

class user_detail : Fragment() {

    companion object {
        fun newInstance() = user_detail()
    }

    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel: UserDetailViewModel by viewModels()
    var userDetails = user_detailArgs.fromBundle(requireArguments()).userDetail2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.userDeleteButton.setOnClickListener{
            viewModel.deleteUser(userDetails)

            val action =user_detailDirections.actionUserDetail2ToUserList()
            binding.root.findNavController().navigate(action)
        }

    }


}