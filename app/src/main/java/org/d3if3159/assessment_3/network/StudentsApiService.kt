package org.d3if3159.assessment_3.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/marianasamosir/Assessment-3-Mobpro/static-api/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface StudentsApiService {
    @GET("student.json")
    suspend fun getStudent(): String
}

object StudentApi {
    val service: StudentsApiService by lazy {
        retrofit.create(StudentsApiService::class.java)
    }
}