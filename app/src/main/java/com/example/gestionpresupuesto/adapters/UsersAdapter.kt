package com.example.gestionpresupuesto.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.User

class UsersAdapter(
    var userList: MutableList<User>

) : RecyclerView.Adapter<UsersAdapter.MainHolder>() {

    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View
        init {
            this.view = v
        }

        fun setUserName(name: String) {
            val userName: TextView = view.findViewById(R.id.user_name)
            userName.text = name
        }

        fun setUserEmail(email: String) {
            val userEmail: TextView = view.findViewById(R.id.user_email)
            userEmail.text = email
        }

        fun setUserDNI(dni : String) {
            val userDNI: TextView = view.findViewById(R.id.user_dni)
            userDNI.text = dni

        }

        fun setIsAdmin(boolean : Boolean) {
            val isAdmin: TextView = view.findViewById(R.id.user_isAdmin)

            if(boolean) {
                isAdmin.text = "Usuario ADMINISTRADOR"
            } else {
                isAdmin.text = "Usuario INVITADO"

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return (MainHolder(view))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {

        holder.setUserName(userList[position].name)
        holder.setUserEmail(userList[position].email)
        holder.setUserDNI(userList[position].dni)
        holder.setIsAdmin(userList[position].admin)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}