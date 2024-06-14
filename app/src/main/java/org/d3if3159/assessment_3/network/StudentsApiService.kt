package org.d3if3159.assessment_3.network

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3159.assessment_3.model.OpStatus
import org.d3if3159.assessment_3.model.Student
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://unspoken.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface StudentsApiService {
    @GET("student.php")
    suspend fun getStudent(
        @Header("Authorization") userId: String
    ): List<Student>

    @Multipart
    @POST("student.php")
    suspend fun postStudent(
        @Header("Authorization") userId: String,
        @Part("name") name: RequestBody,
        @Part("nim") nim: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("student.php/{id}")
    suspend fun deleteStudent(
        @Header("Authorization") userId: String,
        @Query("id") studentId: String
    ): OpStatus
}

object StudentApi {
    val service: StudentsApiService by lazy {
        retrofit.create(StudentsApiService::class.java)
    }

    fun getStudentUrl(imageId: String): String {
//        return "$BASE_URL$imageId.jpg"
        return "${BASE_URL}image.php?id=$imageId"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }