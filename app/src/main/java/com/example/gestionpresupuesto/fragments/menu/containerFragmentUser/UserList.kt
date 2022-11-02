package com.example.gestionpresupuesto.fragments.menu.containerFragmentUser


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.compose.material.Snackbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.adapters.UsersAdapter
import com.example.gestionpresupuesto.databinding.FragmentUserListBinding
import com.example.gestionpresupuesto.viewmodels.MainProductListViewModel
import com.example.gestionpresupuesto.viewmodels.UserListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class UserList : Fragment() {

    lateinit var v : View
    lateinit var recUsers : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var userAdapter : UsersAdapter
    private lateinit var searchView : SearchView
    private val userListViewModel: UserListViewModel by viewModels()
    private val viewModel: MainProductListViewModel by viewModels()

    private lateinit var binding: FragmentUserListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        searchView = binding.searchViewUser
        recUsers = binding.recUsers
        binding.floatingButtonUserList.hide()
        return binding.root
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onStart() {

        super.onStart()

        viewModel.getIsAdmin()

        viewModel.isAdmin.observe(viewLifecycleOwner, Observer { isAdmin ->

            if(viewModel.isAdmin.value == true) {

              binding.floatingButtonUserList.show()

            }

        userListViewModel.getAllUsers()

        userListViewModel.userList.observe(viewLifecycleOwner, Observer { result ->

            searchView.setQuery("",false)

            var userList = result
            recUsers.setHasFixedSize(true)
            linearLayoutManager = LinearLayoutManager(context)
            recUsers.layoutManager = linearLayoutManager
            userAdapter = UsersAdapter(userList)
            recUsers.adapter = userAdapter


            binding.floatingButtonUserList.setOnClickListener {

                binding.root.findNavController().navigate(UserListDirections.actionUserListToUserCreator())

            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query : String?): Boolean {
                    search(userList,query)
                    return true
                }
            })

            })
        })
 }

    private fun search(productList : MutableList<com.example.gestionpresupuesto.entities.User>, query: String?) {

        val temporalUserList = mutableListOf<com.example.gestionpresupuesto.entities.User>()
        val queryLowerCase = query!!.lowercase(Locale.getDefault())
        var cont = 0
        productList.forEach {
            if (it.name.lowercase().contains(queryLowerCase)) {
                temporalUserList.add(it)
            }

            var auxiliarAdapter = UsersAdapter(temporalUserList)

            cont += cont
            recUsers.setAdapter(auxiliarAdapter)

        }
    }
}





