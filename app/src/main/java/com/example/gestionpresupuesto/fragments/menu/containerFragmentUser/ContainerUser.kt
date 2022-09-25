package com.example.gestionpresupuesto.fragments.menu.containerFragmentUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.ContainerUserViewModel

class ContainerUser : Fragment() {

    companion object {
        fun newInstance() = ContainerUser()
    }

    private lateinit var viewModel: ContainerUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_container_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContainerUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}