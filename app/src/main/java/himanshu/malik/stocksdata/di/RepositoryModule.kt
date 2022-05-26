package himanshu.malik.stocksdata.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import himanshu.malik.stocksdata.data.csv.CSVParser
import himanshu.malik.stocksdata.data.csv.StockListingParser
import himanshu.malik.stocksdata.data.repository.StockRepositoryImpl
import himanshu.malik.stocksdata.domain.StockListing
import himanshu.malik.stocksdata.domain.repository.StockRepository
import javax.inject.Singleton


/**
 * Author himanshu
 * Created on  26-May-2022
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockListingsParser(
        stockListingParser: StockListingParser
    ): CSVParser<StockListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

}