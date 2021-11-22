package com.hugo.evaluation.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationBarView
import com.hugo.evaluation.R
import com.hugo.evaluation.databinding.ActivityHomeBinding
import com.hugo.evaluation.view.adapter.PargerAdapter
import com.hugo.evaluation.view.fragment.MapFragment
import com.hugo.evaluation.view.fragment.MovieFragment
import com.hugo.evaluation.view.fragment.PhotoFragment

class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigation()
    }


    private fun navigation() {
        val fragmentList = arrayListOf(
            MovieFragment(),
            MapFragment(),
            PhotoFragment()
        )
        binding.vp2Pager.adapter = PargerAdapter(
            fragmentList,
            this
        )//ViewPargerAdapter(fragmentList,supportFragmentManager)
        binding.bnvHome.setOnItemSelectedListener(this)
        binding.vp2Pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                println(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                println(position)
            }


            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    //requestPermissions()
                }
                binding.bnvHome.menu.getItem(position).isChecked = true
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.movieFragment -> {
                binding.vp2Pager.currentItem = 0
                return true
            }
            R.id.mapFragment -> {
                binding.vp2Pager.currentItem = 1
                return true
            }
            R.id.photoFragment -> {
                //requestPermissions()
                binding.vp2Pager.currentItem = 2
                return true
            }
        }
        return false
    }

}