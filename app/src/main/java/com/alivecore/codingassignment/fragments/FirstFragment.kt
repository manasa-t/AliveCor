package com.alivecore.codingassignment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.alivecore.codingassignment.MainActivity
import com.alivecore.codingassignment.MainViewModel
import com.alivecore.codingassignment.R
import com.alivecore.codingassignment.interfaces.FragmentListener
import kotlinx.android.synthetic.main.fragment_.*


private const val ARG_FNAME = "first_name"
private const val ARG_LNAME = "last_name"


class FirstFragment : Fragment() {

    private var fName: String? = null
    private var lName: String? = null

    var years = arrayListOf<Int>()
    var months = arrayListOf<Int>()
    var days = arrayListOf<Int>()

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fName = it.getString(ARG_FNAME)
            lName = it.getString(ARG_LNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String): FirstFragment {
            var args = Bundle()
            args. putString(ARG_FNAME, param1)
            args. putString(ARG_LNAME, param2)
            var ff = FirstFragment()
            ff.arguments = args
           return ff
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).mainViewModel
        tv_firstName.text = getString(R.string.f_name)+" "+fName
        tv_lastName.text = getString(R.string.l_name)+" "+lName
        initSpinners()
        initListeners()

        btn_next.setOnClickListener {
            (activity as FragmentListener).onNextClicked()
        }


    }

    fun initSpinners() {

        for (y in 1900..2021) {
            years.add(y)
        }
        for (m in 1..12) {
            months.add(m)
        }
        for (d in 1..31) {
            days.add(d)
        }
        Log.d("first month"," first month "+months.get(0))
        years.reverse()
        var adapterY =
            ArrayAdapter<Int>(activity!!, android.R.layout.simple_spinner_dropdown_item, years)
        var adapterM = ArrayAdapter<Int>(
            activity!!,
            android.R.layout.simple_spinner_dropdown_item,
            months
        )
        var adapterD =
            ArrayAdapter<Int>(activity!!, android.R.layout.simple_spinner_dropdown_item, days)
        adapterY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_year.adapter = adapterY
        spinner_month.adapter = adapterM
        spinner_day.adapter = adapterD

        spinner_day.setSelection(days.indexOf(viewModel.dobDay))
        spinner_month.setSelection(months.indexOf(viewModel.dobMonth))
        spinner_year.setSelection(years.indexOf(viewModel.dobYear))

    }

    fun initListeners() {
        spinner_year.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("year","year selected "+years.get(position))
               viewModel.yearSelected( years.get(position))
            }

        }

        spinner_month.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("month","month selected "+months.get(position))
                viewModel.monthSelected( months.get(position))
            }

        }

        spinner_day.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.daySelected(days.get(position))
            }

        }
    }

}