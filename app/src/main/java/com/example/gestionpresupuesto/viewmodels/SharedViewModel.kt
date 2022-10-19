package com.example.gestionpresupuesto.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.entities.Product

class SharedViewModel : ViewModel() {

    private var productList = MutableLiveData<MutableList<Product>>()

    private var budgetToCreate = MutableLiveData<Budget>()

    var saveState = mutableListOf<Item>()

    fun setBudgetToCreate(budgetToCreate : Budget) {

        this.budgetToCreate.value = budgetToCreate

    }

    fun getBudgetToCreate() : MutableLiveData<Budget> {

        return this.budgetToCreate

    }

    fun setProductList(productList: MutableList<Product>) {

        this.productList.value = productList

    }

    fun getProductList() : MutableList<Product>? {

        return this.productList.value

    }

    fun getState() : MutableList<Item> {

        return this.saveState

    }

 }
