package com.example.assignment.base

import androidx.lifecycle.ViewModel
import com.example.assignment.webservice.ApiInterface
import com.example.assignment.webservice.RetrofitUtil

/*Base view model to define common methods*/
abstract class BaseViewModel : ViewModel() {

    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.createBaseApiService()
    }
}