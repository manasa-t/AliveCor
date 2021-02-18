package com.alivecore.codingassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.alivecore.codingassignment.fragments.FirstFragment
import com.alivecore.codingassignment.fragments.SecondFragment
import com.alivecore.codingassignment.interfaces.FragmentListener
import com.alivecore.codingassignment.interfaces.UpdateAgeListener

class MainActivity : AppCompatActivity(), FragmentListener {

    private val firstName = "AAA"
    private val lastName = "BBB"

    lateinit var firstFragment: Fragment
    lateinit var secondFragment: Fragment

    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = ViewModelFactory()
       mainViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        firstFragment = FirstFragment.newInstance(firstName, lastName)
        secondFragment = SecondFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, firstFragment, "first")
            .commitNow()
    }


    override fun onNextClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, secondFragment, "second")
            .commit()
    }

    override fun onBackClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, firstFragment, " first")
            .commit()
    }


}