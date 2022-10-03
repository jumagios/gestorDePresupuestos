package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget


import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.adapters.BudgetAdapter
import com.example.gestionpresupuesto.viewmodels.BudgetListViewModel
import com.example.gestionpresupuesto.entities.Budget
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*


class BudgetList : Fragment() {

    companion object {
        fun newInstance() = BudgetList()
    }

    lateinit var v : View
    lateinit var recBudgets : RecyclerView
    private lateinit var viewModel: BudgetListViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var budgetAdapter : BudgetAdapter
    private lateinit var searchView : SearchView
    private lateinit var temporalBudgetList : MutableList<Budget>
    private lateinit var buttonAdd : FloatingActionButton



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_main_budget_list, container, false)
        recBudgets = v.findViewById(R.id.rec_budgets)
        searchView = v.findViewById(R.id.searchViewBudget)
        buttonAdd = v.findViewById(R.id.floating_action_button_user)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BudgetListViewModel::class.java)

    }

    override fun onStart() {

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

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query : String?): Boolean {
                    search(budgetList,query)
                    return true
                }

            })
        })

        buttonAdd.setOnClickListener {
            val action = BudgetListDirections.actionMainBudgetListToProductCreator()
            v.findNavController().navigate(action)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun search(budgetList : MutableList<Budget>, query: String?) {

        val temporalBudgetList = mutableListOf<Budget>()
        val queryLowerCase = query!!.lowercase(Locale.getDefault())

        budgetList.forEach {
            if (it.clientDomicile.lowercase().contains(queryLowerCase)) {
                temporalBudgetList.add(it)
            }
                 var auxiliarAdapter = BudgetAdapter(temporalBudgetList, requireContext())
                     recBudgets.setAdapter(auxiliarAdapter)

                 }
        }

    fun onItemClick ( position : Int )  {
        Snackbar.make(v,position.toString(), Snackbar.LENGTH_SHORT).show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BudgetListViewModel::class.java)
        // TODO: Use the ViewModel
    }
}