package com.example.gestionpresupuesto.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionpresupuesto.entities.Item


class SharedViewModel : ViewModel() {

    var itemLiveList = MutableLiveData<MutableList<Item>>()

    fun setItemList(itemList : MutableList<Item>)  {

        itemLiveList.value =  itemList


        }
    }
