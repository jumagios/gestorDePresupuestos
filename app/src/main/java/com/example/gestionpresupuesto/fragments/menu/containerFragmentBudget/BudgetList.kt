package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.adapters.BudgetAdapter
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.viewmodels.BudgetListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class BudgetList : Fragment() {

    companion object {
        fun newInstance() = BudgetList()
    }

    lateinit var v: View

    private lateinit var spinner: Spinner
    lateinit var recBudgets: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var budgetAdapter: BudgetAdapter
    private lateinit var searchView: SearchView
    private lateinit var buttonAdd: FloatingActionButton
    private val viewModel: BudgetListViewModel by viewModels()

    val states = arrayOf(
        "Todos", "Aprobados", "Pendientes", "Rechazados"
    )

    var budgetList = mutableListOf<Budget>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_main_budget_list, container, false)
        recBudgets = v.findViewById(R.id.rec_budgets)
        searchView = v.findViewById(R.id.searchViewBudget)
        buttonAdd = v.findViewById(R.id.floating_action_button_user)
        spinner = v.findViewById(R.id.budget_list_spinner)

        return v
    }


    override fun onStart() {

        super.onStart()

        var actualState = "Todos"

        initSprinner(states)

        viewModel.getAllBudgets()

        viewModel.budgetList.observe(viewLifecycleOwner, Observer { result ->

            searchView.setQuery("", false);
            budgetList = result
            recBudgets.setHasFixedSize(true)
            linearLayoutManager = LinearLayoutManager(context)
            recBudgets.layoutManager = linearLayoutManager


            budgetList = budgetList.filter { it.erased == false }.toMutableList()

            budgetAdapter = BudgetAdapter(budgetList, requireContext())

            recBudgets.adapter = budgetAdapter



            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (actualState == "all") {
                        search(budgetList, query)
                        return true

                    } else if (actualState == "approved") {

                        search(viewModel.budgetFilter.value!!, query)
                        return true

                    } else if (actualState == "rejected") {

                        search(viewModel.budgetFilter.value!!, query)
                        return true

                    } else if (actualState == "pending") {

                        search(viewModel.budgetFilter.value!!, query)
                        return true

                    }

                    return true
                }


            })
        })

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

               if(states[position].equals("Todos")) {
                    actualState = "all"
                    budgetAdapter = BudgetAdapter(budgetList, requireContext())
                    recBudgets.adapter = budgetAdapter

                } else if(states[position].equals("Aprobados")){
                   actualState = "approved"
                   viewModel.budgetFilter.value =
                       budgetList.filter { it.state == "approved" }.toMutableList()
                   budgetAdapter =
                       BudgetAdapter(viewModel.budgetFilter.value!!, requireContext())
                   recBudgets.adapter = budgetAdapter

               }else if(states[position].equals("Rechazados")){
                   actualState = "rejected"
                   viewModel.budgetFilter.value =
                       budgetList.filter { it.state == "rejected" }.toMutableList()
                   budgetAdapter =
                       BudgetAdapter(viewModel.budgetFilter.value!!, requireContext())
                   recBudgets.adapter = budgetAdapter

               }else if(states[position].equals("Pendientes")){
                   actualState = "pending"
                   viewModel.budgetFilter.value =
                       budgetList.filter { it.state == "pending" }.toMutableList()
                   budgetAdapter =
                       BudgetAdapter(viewModel.budgetFilter.value!!, requireContext())
                   recBudgets.adapter = budgetAdapter
               }

            }
        }

        buttonAdd.setOnClickListener {
            val action = BudgetListDirections.actionMainBudgetListToBudgetForm()
            v.findNavController().navigate(action)
        }
    }

    private fun initSprinner(states: Array<String>) {

        val arrayAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, states)
        spinner.adapter = arrayAdapter
    }


    @SuppressLint("SuspiciousIndentation")
    private fun search(budgetList: MutableList<Budget>, query: String?) {

        val temporalBudgetList = mutableListOf<Budget>()
        val queryLowerCase = query!!.lowercase(Locale.getDefault())

        budgetList.forEach {
            if (it.clientDomicile.lowercase().contains(queryLowerCase)) {
                temporalBudgetList.add(it)
            }
            var auxiliarAdapter =
                BudgetAdapter(temporalBudgetList, requireContext())
            recBudgets.setAdapter(auxiliarAdapter)

        }
    }

}