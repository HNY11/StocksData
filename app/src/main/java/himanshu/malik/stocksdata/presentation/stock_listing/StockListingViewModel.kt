package himanshu.malik.stocksdata.presentation.stock_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import himanshu.malik.stocksdata.domain.repository.StockRepository
import himanshu.malik.stocksdata.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Author himanshu
 * Created on  20-May-2022
 */
@HiltViewModel
class StockListingViewModel @Inject constructor(
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(StockListingsState())

    // coroutine job to cancel the job in between
    private var searchJob: Job? = null

    init {
        getStockListings()
    }

    fun onEvent(event: StockListingEvent) {
        when (event) {
            is StockListingEvent.Refresh -> {
                getStockListings(fetchFromRemote = true)
            }

            is StockListingEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getStockListings()
                }
            }
        }
    }

    private fun getStockListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getStockListings(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                state = state.copy(
                                    stocks = it
                                )
                            }

                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Error -> Unit
                    }
                }
        }
    }


}