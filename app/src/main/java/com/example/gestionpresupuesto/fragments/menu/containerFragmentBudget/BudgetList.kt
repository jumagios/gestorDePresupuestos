package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
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
    //private val stateBudget = resources.getStringArray(R.array.stateBudget)
    //private lateinit var spinner: Spinner
    lateinit var v: View
    private lateinit var button_approved: Button
    private lateinit var button_rejected: Button
    private lateinit var button_all: Button
    private lateinit var button_pending: Button
    lateinit var recBudgets: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var budgetAdapter: BudgetAdapter
    private lateinit var searchView: SearchView
    private lateinit var buttonAdd: FloatingActionButton
    private val viewModel: BudgetListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_main_budget_list, container, false)
        recBudgets = v.findViewById(R.id.rec_budgets)
        searchView = v.findViewById(R.id.searchViewBudget)
        buttonAdd = v.findViewById(R.id.floating_action_button_user)
        button_approved = v.findViewById(R.id.button_approved)
        button_rejected = v.findViewById(R.id.button_rejected)
        button_all = v.findViewById(R.id.button_all)
        button_pending = v.findViewById(R.id.button_pending)
        /** spinner = v.findViewById(R.id.spinner)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext, "Seleccion la lista de =" + stateBudget[position], Toast.LENGTH_SHORT).show()
            }

        }*/

        return v
    }


    override fun onStart() {

        var actualState = "all"

        super.onStart()

        viewModel.getAllBudgets()

        viewModel.budgetList.observe(viewLifecycleOwner, Observer { result ->

            searchView.setQuery("", false);
            var budgetList = result
            recBudgets.setHasFixedSize(true)
            linearLayoutManager = LinearLayoutManager(context)
            recBudgets.layoutManager = linearLayoutManager
            budgetAdapter = BudgetAdapter(budgetList, requireContext())

            recBudgets.adapter = budgetAdapter


            button_all.setOnClickListener {
                actualState = "all"
                budgetAdapter = BudgetAdapter(budgetList, requireContext())
                recBudgets.adapter = budgetAdapter
            }

            button_approved.setOnClickListener {
                actualState = "approved"
                viewModel.budgetFilter.value =
                    budgetList.filter { it.state == "approved" }.toMutableList()
                budgetAdapter =
                    BudgetAdapter(viewModel.budgetFilter.value!!, requireContext())
                recBudgets.adapter = budgetAdapter
            }


            button_rejected.setOnClickListener {
                actualState = "rejected"
                viewModel.budgetFilter.value =
                    budgetList.filter { it.state == "rejected" }.toMutableList()
                budgetAdapter =
                    BudgetAdapter(viewModel.budgetFilter.value!!, requireContext())
                recBudgets.adapter = budgetAdapter
            }

            button_pending.setOnClickListener {
                actualState = "pending"
                viewModel.budgetFilter.value =
                    budgetList.filter { it.state == "pending" }.toMutableList()

                var debug = viewModel.budgetFilter.value
                debug.toString()

                budgetAdapter = BudgetAdapter(viewModel.budgetFilter.value!!, requireContext())
                recBudgets.adapter = budgetAdapter
            }

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

        buttonAdd.setOnClickListener {
            val action = BudgetListDirections.actionMainBudgetListToBudgetForm()
            v.findNavController().navigate(action)
        }
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