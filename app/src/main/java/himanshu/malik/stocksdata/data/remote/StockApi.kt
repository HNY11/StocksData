package himanshu.malik.stocksdata.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Author himanshu
 * Created on  18-May-2022
 */
interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    companion object {
        const val API_KEY = "AD8KARFIKVS1N08M"
        const val BASE_URL = "https://alphavantage.co"
    }
}