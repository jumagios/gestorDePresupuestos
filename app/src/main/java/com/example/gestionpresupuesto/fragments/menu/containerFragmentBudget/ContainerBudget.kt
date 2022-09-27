package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.gestionpresupuesto.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ContainerBudget : Fragment() {

   private lateinit var v: View
   private lateinit var viewPager : ViewPager2
   private lateinit var tabLayout : TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_container_budget, container, false)

        tabLayout = v.findViewById(R.id.tab_layout_bud)

        viewPager = v.findViewById(R.id.view_pager_bud)




        return v
    }

    override fun onStart() {
        super.onStart()

        viewPager.setAdapter(ViewPagerAdapter(requireActivity()))

        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.text = "Listar"
                1 -> tab.text = "Crear"
                else -> tab.text = "undefined"
            }

        }).attach()

    }


    class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int) : Fragment {

            return when(position){
                0 -> Budget()
                1 -> BudgetCreator()
                else -> Budget()
            }
        }



        override fun getItemCount(): Int {
            return TAB_COUNT

        }

        companion object {
            private const val TAB_COUNT = 2
        }




    }



}