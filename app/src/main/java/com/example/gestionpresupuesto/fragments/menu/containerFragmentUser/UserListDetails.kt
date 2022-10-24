package com.example.gestionpresupuesto.fragments.menu.containerFragmentUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.UserListDetailsViewModel

class UserListDetails : Fragment() {

    companion object {
        fun newInstance() = UserListDetails()
    }

    private lateinit var viewModel: UserListDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserListDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}