package com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct

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

class ContainerProduct : Fragment() {

    lateinit var v: View
    lateinit var viewPager : ViewPager2
    lateinit var tabLayout : TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_container_product, container, false)

        tabLayout = v.findViewById(R.id.tab_layout_prod)

        viewPager = v.findViewById(R.id.view_pager_prod)

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
        override fun createFragment(position: Int): Fragment {

            return when (position) {
                0 -> MainProductList()
                1 -> ProductCreator()
                else -> MainProductList()
            }
        }


        override fun getItemCount(): Int {
            return TAB_COUNT
            TODO("Not yet implemented")
        }

        companion object {
            private const val TAB_COUNT = 2
        }


    }

}