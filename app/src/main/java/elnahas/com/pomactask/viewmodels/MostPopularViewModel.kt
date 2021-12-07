package elnahas.com.pomactask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import elnahas.com.pomactask.data.model.NyTimesModel
import elnahas.com.pomactask.data.repository.MostPopularRepository
import elnahas.com.pomactask.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class MostPopularViewModel @Inject constructor(
    private val repository: MostPopularRepository
) : ViewModel() {

    private val _mostPopularState = MutableStateFlow<Resource<NyTimesModel>>(Resource.Empty)
    val mostPopularState: StateFlow<Resource<NyTimesModel>> = _mostPopularState

    init {
        getMostPopular()
    }

    fun getMostPopular() {

        viewModelScope.launch {

            repository.getMostPopular()
                .onStart {
                    _mostPopularState.value = Resource.Loading(true)
                }
                .catch {
                    it.message?.let { message ->
                        _mostPopularState.value = Resource.Error(message)
                    }
                }
                .collect { news ->
                    _mostPopularState.value = Resource.Success(news)
                }
        }

    }

}