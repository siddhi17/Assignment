package com.example.assignment.webservice

import com.example.assignment.responses.GetCategoryResponse
import com.example.assignment.base.Constants
import io.reactivex.Observable
import retrofit2.http.*

/*Define Api calling methods*/
interface ApiInterface {

    /*Get category list*/
    @GET(Constants.BASE_URL)
    fun getCategoryList(@Query("topic") category: String): Observable<GetCategoryResponse>

    /* Get Search list*/
    @GET(Constants.BASE_URL)
    fun getSearchList(@Query("search") search: String): Observable<GetCategoryResponse>
}