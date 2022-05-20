package himanshu.malik.stocksdata.data.repository

import himanshu.malik.stocksdata.data.csv.CSVParser
import himanshu.malik.stocksdata.data.csv.StockListingParser
import himanshu.malik.stocksdata.data.local.StockDao
import himanshu.malik.stocksdata.data.local.StockDatabase
import himanshu.malik.stocksdata.data.mapper.toStockListing
import himanshu.malik.stocksdata.data.mapper.toStockListingEntity
import himanshu.malik.stocksdata.data.remote.StockApi
import himanshu.malik.stocksdata.domain.StockListing
import himanshu.malik.stocksdata.domain.repository.StockRepository
import himanshu.malik.stocksdata.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Author himanshu
 * Created on  19-May-2022
 *
 * actual implementation for the #StockRepository
 */

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val stockListingParser: CSVParser<StockListing>
): StockRepository
 {
     private val dao = db.dao

     override suspend fun getStockListings(
         fetchFromRemote: Boolean,
         query: String
     ): Flow<Resource<List<StockListing>>> {
         return flow {
             emit(Resource.Loading(true))
             val localListing = dao.searchStockListing(query)
             emit(Resource.Success(
                 data = localListing.map { it.toStockListing() }
             ))

             val isDbEmpty = localListing.isEmpty() && query.isBlank()
             val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
             if (shouldJustLoadFromCache) {
                 emit(Resource.Loading(false))
                 return@flow
             }

             val remoteListings = try {
                 val response = api.getListings()
                  stockListingParser.parse(response.byteStream())
             }catch (e: IOException) {
                 e.printStackTrace()
                 emit(Resource.Error("IO Exception"))
                 null
             }catch (e: HttpException) {
                 e.printStackTrace()
                 emit(Resource.Error("Http Exception"))
                 null
             }

             remoteListings?.let { listing ->
                 // why need to clear data
                 // we can use replace strategy
                 dao.clearStockListings()
                 dao.insertStockListings(
                     listing.map { it.toStockListingEntity() }
                 )
                 emit(Resource.Success(
                     data = dao
                         .searchStockListing("")
                         .map { it.toStockListing() }
                 ))
                 emit(Resource.Loading(false))
             }

         }
     }

}