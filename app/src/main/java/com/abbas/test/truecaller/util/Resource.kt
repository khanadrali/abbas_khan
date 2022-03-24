package com.abbas.test.truecaller.util

sealed class Resource<T>(val data:T?=null,val errorMessage:String?=null) {
    class Success<T>(data: T?):Resource<T>(data=data)
    class Error(errorMessage: String?):Resource<String>(errorMessage=errorMessage)
    class Loading<T>():Resource<T>()
}