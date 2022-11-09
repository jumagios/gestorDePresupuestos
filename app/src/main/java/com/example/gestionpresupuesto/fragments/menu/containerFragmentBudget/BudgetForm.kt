package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.databinding.FragmentBugdetFormBinding
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.viewmodels.MainProductListViewModel
import com.example.gestionpresupuesto.viewmodels.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import java.text.ParseException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.ResolverStyle
import java.util.*

class BudgetForm : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val mainProductListViewModel: MainProductListViewModel by viewModels()
    private var _binding: FragmentBugdetFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBugdetFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {

        super.onStart()

        mainProductListViewModel.getAllProducts()

        mainProductListViewModel.productList.observe(viewLifecycleOwner, Observer { result ->

            sharedViewModel.setProductList(result)

            binding.siguienteButton.setOnClickListener {

                if (isValidDate(binding.inputExpirationDate.text.toString())) {

                    val date = LocalDate.now()
                    val date2 = LocalDate.parse(
                        binding.inputExpirationDate.text.toString(),
                        DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    )

                    if (date2.isBefore(date)) {

                        if(binding.inputExpirationDate.text.toString().isNullOrBlank()){

                            Snackbar.make(
                                binding.budgetCreator,
                                "Campos incompletos",
                                Snackbar.LENGTH_LONG
                            ).show()

                        } else {

                            Snackbar.make(
                                binding.budgetCreator,
                                "La fecha de vencimiento no puede ser anterior a la actual",
                                Snackbar.LENGTH_LONG
                            ).show()

                        }

                    } else {

                        createBudget()
                    }

                } else {

                    if(binding.inputExpirationDate.text.toString().isNullOrBlank()){

                        Snackbar.make(
                            binding.budgetCreator,
                            "Complete los campos",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.budgetCreator,
                            "Fecha invalida, EJ: 12-12-2022",
                            Snackbar.LENGTH_LONG
                        ).show()

                    }



                }
            }
        })

}


    private fun createBudget() {

        if (!binding.inputName.text.isNullOrBlank()
            && !binding.inputAdress.text.isNullOrBlank()
            && !binding.inputdniCuit.text.isNullOrBlank()
            && !binding.inputAdress2.text.isNullOrBlank()
            && !binding.inputAdress3.text.isNullOrBlank()
            && !binding.inputLocality.text.isNullOrBlank()
            && !binding.inputProvince.text.isNullOrBlank()
            && !binding.inputLocality.text.isNullOrBlank()
            && !binding.inputPhone.text.isNullOrBlank()
            && !binding.inputExpirationDate.text.isNullOrBlank()
            && !binding.inputExpirationDate.text.isNullOrBlank()
        ) {

            var parcialBudget = Budget(
                "",
                binding.inputName.text.toString(),
                binding.inputdniCuit.text.toString(),
                binding.inputAdress.text.toString(),
                binding.inputAdress2.text.toString(),
                binding.inputAdress3.text.toString(),
                binding.inputDepto.text.toString(),
                binding.inputLocality.text.toString(),
                binding.inputProvince.text.toString(),
                binding.inputPhone.text.toString(),
                binding.inputAlternativePhone.text.toString(),
                setLocalDate(),
                setLocalHour(),
                binding.inputExpirationDate.text.toString(),
                false,
                0.0,
                "pending",
                mutableListOf()
            )

            sharedViewModel.setBudgetToCreate(parcialBudget)
            var action = BudgetFormDirections.actionBudgetFormToBudgetCreator()
            binding.root.findNavController().navigate(action)

        } else {
            Snackbar.make(
                binding.budgetCreator,
                "Campos incompletos",
                Snackbar.LENGTH_LONG
            ).show()

        }
    }

    private fun setLocalDate(): String {

        var dateTime = LocalDateTime.now()
        var dateFormated = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        var date = dateFormated.format(dateTime)
        return date

    }

    private fun setLocalHour(): String {

        var dateTime = LocalDateTime.now()
        var hourFormated = DateTimeFormatter.ofPattern("HH:mm:ss")
        var hour = hourFormated.format(dateTime)
        return hour

    }


    fun isValidDate(dateStr: String): Boolean {
        try {
            LocalDate.parse(
                dateStr,
                DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT)
            )

        } catch (e: DateTimeParseException) {
            return false;
        }
        return true;
    }

}