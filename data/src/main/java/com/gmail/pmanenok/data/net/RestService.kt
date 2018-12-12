package com.gmail.pmanenok.data.net

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestService(private val apiUrl: String) {
    /*private val restApi: RestApi
    private val restParser: RestErrorParser

    init {

        val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
        okHttpBuilder
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        //val gson = Gson()
        val gson = GsonBuilder()
            //.registerTypeAdapter(StudentResponse::class.java, )
            .create()

        restParser = RestErrorParser(gson)

        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpBuilder.build())
            .build()

        restApi = retrofit.create(RestApi::class.java)
    }*/

    /*fun getStudents(): Observable<List<NoteResponse>> {
//        Log.e("aaa", "RestService getStudents")
        return restApi.getStudents(30).compose(restParser.parseError())
    }

    fun getStudentById(id: String): Observable<StudentResponse> {
        //      Log.e("aaa", "RestService getStudentById")
        return restApi.getStudentById(id).compose(restParser.parseError())
    }

    fun updateStudent(student: StudentRequest): Observable<StudentResponse> {
        //    Log.e("aaa", "RestService updateStudent")
        return restApi.updateStudent(student).compose(restParser.parseError())
    }

    fun saveStudent(student: StudentRequest): Observable<StudentResponse> {
        //  Log.e("aaa", "RestService saveStudent")
        return restApi.saveStudent(student).compose(restParser.parseError())
    }

    fun deleteStudent(id: String): Observable<String> {
        //Log.e("aaa", "RestService deleteStudent $id")
        return restApi.deleteStudent(id).compose(restParser.parseError())
    }

    fun login(login: LoginRequest): Observable<Token> {
        return restApi.login(login).compose(restParser.parseError())
    }*/
}