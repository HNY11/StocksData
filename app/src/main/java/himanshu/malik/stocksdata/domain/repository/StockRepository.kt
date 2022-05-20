package himanshu.malik.stocksdata.domain.repository

import himanshu.malik.stocksdata.domain.StockListing
import himanshu.malik.stocksdata.util.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Author himanshu
 * Created on  19-May-2022
 *
 * Repo. must be in the domain layer because presentation layer will access it and we don't want
 * the presentation layer to access the data layer
 *
 * We use the flow to update the current state like loading, complete, error with the help of Resource.
 *
 */
interface StockRepository {

    /**
    *  fetchFromRemote - get the data from remote(API) if true and from local database if false
    * */
    suspend fun getStockListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<StockListing>>>

}