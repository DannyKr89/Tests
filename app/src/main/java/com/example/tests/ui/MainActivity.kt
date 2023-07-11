package com.example.tests.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tests.databinding.ActivityMainBinding
import com.example.tests.ui.list.SearchListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.mainContainer.id, SearchListFragment.newInstance())
                .commit()
        }
    }
}