package elnahas.com.pomactask.data.repository

import elnahas.com.pomactask.data.api.ApiService
import elnahas.com.pomactask.data.model.NyTimesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MostPopularRepository @Inject constructor(val api: ApiService) {

    fun getMostPopular(): Flow<NyTimesModel> {

        return flow {
            val result = api.getMostPopular()
            emit(result)
        }.flowOn(Dispatchers.IO)

    }

}