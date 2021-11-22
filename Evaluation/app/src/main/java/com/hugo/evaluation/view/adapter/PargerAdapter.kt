package com.hugo.evaluation.view.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
//adaptador para recorrer los fragmentos
class PargerAdapter(
    val list: ArrayList<Fragment>,
    activity: AppCompatActivity
    //fragment: FragmentManager,
    //lyfecycle: Lifecycle
) : FragmentStateAdapter(activity) {

    //optener el numero de fracmentos
    override fun getItemCount(): Int {
        return list.size
    }

    //Crear el fracmento
    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}