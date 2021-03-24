package com.example.assignment.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.assignment.responses.GetCategoryResponse
import com.example.assignment.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BooksViewModel : BaseViewModel(){

    private lateinit var context: Context
    private lateinit var disposable: Disposable
    var mProgess = MutableLiveData<Boolean>()

    /*book variables*/

    var category: String = ""
    var search: String = ""

    /*Responses*/
    var getCategoryListResponse = MutableLiveData<GetCategoryResponse>()
    var getSearchListResponse = MutableLiveData<GetCategoryResponse>()

    /*errors*/
    var errorGetCategoryList = MutableLiveData<Throwable>()
    var errorGetSearchList = MutableLiveData<Throwable>()

    /*Call Search list api*/

    fun hitGetSearchListApi() {
        disposable = apiInterface.getSearchList(
            search = search
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                mProgess.value = true
            }.doOnTerminate {
                mProgess.value = false
            }
            .subscribe({
                onSuccessGetSearchList(it)
            },
                {
                    onErrorGetSearchList(it)
                })
    }

    private fun onSuccessGetSearchList(it: GetCategoryResponse) {

        getSearchListResponse.value = it
    }

    private fun onErrorGetSearchList(it: Throwable) {
        errorGetSearchList.value = it
    }


    /*call category list api*/

    fun hitGetCategoryListApi() {
        disposable = apiInterface.getCategoryList(
            category = category
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                mProgess.value = true
            }.doOnTerminate {
                mProgess.value = false
            }
            .subscribe({
                onSuccessGetCategoryList(it)
            },
                {
                    onErrorGetCategoryList(it)
                })
    }

    private fun onSuccessGetCategoryList(it: GetCategoryResponse) {

        getCategoryListResponse.value = it
    }

    private fun onErrorGetCategoryList(it: Throwable) {
        errorGetCategoryList.value = it
    }

}