package org.d3if3159.assessment_3.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3159.assessment_3.model.OpStatus
import org.d3if3159.assessment_3.model.Student
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

private const val BASE_URL = "https://raw.githubusercontent.com/marianasamosir/Assessment-3-Mobpro/static-api/" // tar ini jg diganti

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface StudentsApiService {
    @GET("student.json") //tar ini diganti
    suspend fun getStudent(): List<Student>

    @Multipart
//    @POST()
    suspend fun postStudent(
        @Header("Authorization") userId: String,
        @Part("name") name: RequestBody,
        @Part("nim") nim: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus
}

object StudentApi {
    val service: StudentsApiService by lazy {
        retrofit.create(StudentsApiService::class.java)
    }

    fun getStudentUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }