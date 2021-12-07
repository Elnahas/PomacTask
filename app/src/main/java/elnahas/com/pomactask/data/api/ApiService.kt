package elnahas.com.pomactask.data.api

import elnahas.com.pomactask.data.model.NyTimesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("7.json")
    suspend fun getMostPopular(@Query("api-key") apiKey: String = "TJl8a2EAzziEGuwY0iGYGsBuIUseMDA7"): NyTimesModel
}