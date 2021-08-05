package com.example.retrofit.endpoint

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {


     private  val retrofit = Retrofit.Builder()
         .baseUrl("https://parallelum.com.br")
         .addConverterFactory(GsonConverterFactory.create())
         .build()


    fun getServiceCarInstance() :  ServiceCar {
        return RetrofitBuilder.retrofit.create(ServiceCar::class.java)
    }

}