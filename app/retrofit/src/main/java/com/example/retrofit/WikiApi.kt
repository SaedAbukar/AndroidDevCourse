package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object WikiApi {
    const val BASE_URL = "https://en.wikipedia.org/w/api.php/"

    data class SearchInfo(val totalhits: Int)
    data class WikiQuery(val searchinfo: SearchInfo)
    data class WikiResponse(val query: WikiQuery)

    interface Service {
        @Headers("User-Agent: MyApp/1.0")
        @GET("?action=query&format=json&list=search")
        suspend fun getHits(@Query("srsearch") name: String): WikiResponse
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: Service by lazy { retrofit.create(Service::class.java) }
}
