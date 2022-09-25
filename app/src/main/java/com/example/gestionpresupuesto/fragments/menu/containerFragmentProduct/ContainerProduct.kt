package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.ContainerProductViewModel

class ContainerProduct : Fragment() {

    companion object {
        fun newInstance() = ContainerProduct()
    }

    private lateinit var viewModel: ContainerProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_container_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContainerProductViewModel::class.java)
        // TODO: Use the ViewModel
    }

}