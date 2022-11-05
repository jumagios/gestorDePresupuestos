package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.BudgetWithItemsDetailsViewModel


class BudgetWithItemsDetailsFragment : Fragment() {

    lateinit var v : View
    private lateinit var viewModel: BudgetWithItemsDetailsViewModel
    private lateinit var budgetNumber: TextView
    private lateinit var budgetDate: TextView
    private lateinit var budgetExpirationDate: TextView
    private lateinit var clientName: TextView
    private lateinit var clientCuit_dni: TextView
    private lateinit var clientDomicile: TextView
    private lateinit var clientBetweenStreet1: TextView
    private lateinit var clientBetweenStreet2: TextView
    private lateinit var clientApartment: TextView
    private lateinit var clientProvince: TextView
    private lateinit var clientLocality: TextView
    private lateinit var clientPhone: TextView
    private lateinit var budgetTotalGross: TextView
    private lateinit var clientAlternativePhone: TextView
    private lateinit var detailsProducts: TextView


    private lateinit var deleteButton: Button
    private lateinit var createPDFButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_budget_with_items_details, container, false)
        deleteButton = v.findViewById(R.id.delete_button)
        createPDFButton = v.findViewById(R.id.button_create_pdf)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BudgetWithItemsDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        var budgetDetails =
            BudgetWithItemsDetailsFragmentArgs.fromBundle(requireArguments()).budgetDetails


        budgetNumber = v.findViewById(R.id.budget_details_budgetnumber)
        budgetNumber.text = budgetDetails.budgetNumber

        budgetDate = v.findViewById(R.id.budget_details_creation_date)
        budgetDate.text = budgetDetails.budgetDate

        clientName = v.findViewById(R.id.budget_clientName)
        clientName.text = budgetDetails.clientName

        clientCuit_dni = v.findViewById(R.id.budget_cuit_dni)
        clientCuit_dni.text = budgetDetails.clientDNI_CUIT.toString()

        clientDomicile = v.findViewById(R.id.budget_clientDomicile)
        clientDomicile.text = budgetDetails.clientDomicile

        clientBetweenStreet1 = v.findViewById(R.id.budget_betweenStreet1)
        clientBetweenStreet1.text = budgetDetails.betweenStreet1

        clientBetweenStreet2 = v.findViewById(R.id.budget_betweenStreet2)
        clientBetweenStreet2.text = budgetDetails.betweenStreet2

        clientApartment = v.findViewById(R.id.budget_apartment)
        clientApartment.text = budgetDetails.apartment

        clientProvince = v.findViewById(R.id.budget_province)
        clientProvince.text = budgetDetails.province

        clientLocality = v.findViewById(R.id.budget_locality)
        clientLocality.text = budgetDetails.locality

        clientPhone = v.findViewById(R.id.budget_phone)
        clientPhone.setText(budgetDetails.phone)

        clientAlternativePhone = v.findViewById(R.id.budget_alternativePhone)
        clientAlternativePhone.setText(budgetDetails.alternativePhone)

        budgetExpirationDate = v.findViewById(R.id.budget_expirationDate)
        budgetExpirationDate.text = budgetDetails.expirationDate

        budgetTotalGross = v.findViewById(R.id.budget_totalGross)
        budgetTotalGross.text = budgetDetails.totalGross.toString()

        detailsProducts = v.findViewById(R.id.productList)
        detailsProducts.text = budgetDetails.productsItems.toString()
        detailsProducts.text = detailsProducts.text.substring(1, detailsProducts.text.length - 1)


        deleteButton.setOnClickListener{
            viewModel.deleteBudget(budgetDetails)

            var action = BudgetWithItemsDetailsFragmentDirections.actionBudgetWithItemsDetailsFragmentToMainBudgetList()
            v.findNavController().navigate(action)
        }

        createPDFButton.setOnClickListener() {

            viewModel.createPDF(this.requireContext(), budgetDetails)

        }
    }
}