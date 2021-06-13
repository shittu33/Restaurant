package org.codejudge.application.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

const val BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/"
const val ALL_RESTAURANTS =
    "json?location=47.6204,-122.3491&radius=2500&type=restaurant&key=AIzaSyDkGIvqAXuuOE5TUoDedazelbPdKtQxb1E";
const val SEARCH_RESTAURANTS =
    "json?location=47.6204,-122.3491&radius=2500" +
            "&type=restaurant&key=AIzaSyDkGIvqAXuuOE5TUoDedazelbPdKtQxb1E";
//&keyword=:{keyword}
interface ApiService {

    @GET(ALL_RESTAURANTS)
    fun getAllRestaurants(): Call<Restaurants>

    @GET(SEARCH_RESTAURANTS)
    fun getSearchedRes(@Query("keyword")/*@Path("keyword")*/ keyword: String): Call<Restaurants>

}

object ApiDao {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService: ApiService = getRetrofit().create(ApiService::class.java);

    fun getAllRestaurants(): Call<Restaurants> = apiService.getAllRestaurants()

    fun getSearchedRes(keyword: String): Call<Restaurants> =
        apiService.getSearchedRes(keyword)

}