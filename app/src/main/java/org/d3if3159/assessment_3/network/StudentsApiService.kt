package org.d3if3159.assessment_3.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import org.d3if3159.assessment_3.model.Student
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/marianasamosir/Assessment-3-Mobpro/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface StudentsApiService {
    @GET("student.json")
    suspend fun getStudent(): List<Student>
}

object StudentApi {
    val service: StudentsApiService by lazy {
        retrofit.create(StudentsApiService::class.java)
    }

    fun getStudentUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS }