package com.alivecore.codingassignment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.joda.time.DateTime
import java.time.LocalDate
import java.time.Period

import java.time.Period.between

import java.util.*

class MainViewModel: ViewModel() {

     var dobYear: Int = 0
     var dobMonth: Int = 0
     var dobDay: Int = 0

    var today = LocalDate.now()

    var age =  MutableLiveData<String>()

    init {
        dobYear = today.year
        dobMonth = today.monthValue
        dobDay = today.dayOfMonth
    }

    fun calculateAge() {
        Log.d("age ","from "+dobMonth+" to now "+LocalDate.now().monthValue)
        var dob = LocalDate.of(dobYear,dobMonth,dobDay)
        var diffYears = Period.between(dob,today).years
        var diffMonths = Period.between(dob,today).months
        var diffDays = Period.between(dob,today).days

        age.postValue( "$diffYears years , $diffMonths months , $diffDays days." )
    }

    fun daySelected(day: Int){
        dobDay = day
        calculateAge()
    }

    fun monthSelected(month: Int){
        dobMonth = month
        calculateAge()
    }

    fun yearSelected(year:Int){
        dobYear = year
        calculateAge()
    }
}