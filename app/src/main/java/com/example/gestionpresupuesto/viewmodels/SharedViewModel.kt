package com.example.gestionpresupuesto.viewmodels
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class SharedViewModel : ViewModel() {

    private var productList = MutableLiveData<MutableList<Product>>()

    private var budgetToCreate = MutableLiveData<Budget>()

    var itemLiveList = MutableLiveData<MutableList<Item>>()

    var saveState = mutableListOf<Item>()

    private var itemList = mutableListOf<Item>()


    fun getItemList() : MutableList<Item> {

        return this.itemList
    }

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

    fun getSate() : MutableList<Item> {

        return this.saveState

    }

 }
