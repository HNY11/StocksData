package himanshu.malik.stocksdata.presentation.stock_listing

import himanshu.malik.stocksdata.domain.StockListing


/**
 * Author himanshu
 * Created on  20-May-2022
 *
 *
 */
data class StockListingsState(
    val stocks: List<StockListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
) {
}