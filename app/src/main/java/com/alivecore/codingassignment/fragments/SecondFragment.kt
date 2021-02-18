package com.alivecore.codingassignment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.alivecore.codingassignment.MainActivity
import com.alivecore.codingassignment.R
import com.alivecore.codingassignment.interfaces.FragmentListener
import com.alivecore.codingassignment.interfaces.UpdateAgeListener
import kotlinx.android.synthetic.main.fragment_second.*


class SecondFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_back.setOnClickListener {
            (activity as FragmentListener).onBackClicked()
        }
        (activity as MainActivity).mainViewModel.age.observe(this, Observer {
            tv_age.text = it
        })
    }

    companion object {


        fun newInstance(): SecondFragment {
           return SecondFragment()
        }

    }





}