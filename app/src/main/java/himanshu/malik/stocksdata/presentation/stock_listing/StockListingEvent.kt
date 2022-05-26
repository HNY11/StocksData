package himanshu.malik.stocksdata.presentation.stock_listing


/**
 * Author himanshu
 * Created on  20-May-2022
 *
 * sealed class contain all the user events of the stock listing page
 * we create this, so we don't have to create multiple functions for each event in #StockListingViewModel
 */
sealed class StockListingEvent {
    object Refresh: StockListingEvent()
    data class OnSearchQueryChange(val query: String): StockListingEvent()
}