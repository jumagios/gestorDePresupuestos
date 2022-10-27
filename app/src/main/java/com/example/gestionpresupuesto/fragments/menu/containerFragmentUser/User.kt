package com.example.gestionpresupuesto.fragments.menu.containerFragmentUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class User : Fragment() {

    companion object {
        fun newInstance() = User()
    }

    private lateinit var viewModel: UserViewModel
    private lateinit var v : View
    private lateinit var buttonAdd : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user_list, container, false)
        buttonAdd = v.findViewById(R.id.floating_action_button_user)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        buttonAdd.setOnClickListener{
            //val action = UserDirections.actionMainUserToUserCreator()
            //v.findNavController().navigate(action)
        }
    }

}