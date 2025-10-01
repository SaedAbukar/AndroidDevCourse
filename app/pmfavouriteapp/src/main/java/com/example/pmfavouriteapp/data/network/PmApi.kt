package com.example.pmfavouriteapp.data.network
import com.example.pmfavouriteapp.entities.ParliamentMember
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object PmApi {
    const val BASE_URL = "https://users.metropolia.fi/~peterh/"


    interface Service {
        @GET("seating.json")
        suspend fun getSeating(): List<ParliamentMember>
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: Service by lazy { retrofit.create(Service::class.java) }
}
