package com.example.webapi.Retrofit

import io.reactivex.Observable
import retrofit2.http.*


interface WebAPI {
        //TODO() ссылОчки и что кидать в пост-запрос

        @GET("GetTable")
        //fun getTable(): Observable<String>
        fun getTable(): Observable<List<TablePOJO>>

       /* @GET("1GetTable.php")
        fun getTable(): Observable<String>*/

        @POST("SendTable")
        fun sendTable(@Body list:List<TablePOJO>):Observable<String>
}