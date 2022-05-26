package himanshu.malik.stocksdata.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import himanshu.malik.stocksdata.data.local.StockDatabase
import himanshu.malik.stocksdata.data.remote.StockApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


/**
 * Author himanshu
 * Created on  25-May-2022
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application) : StockDatabase {
        return Room.databaseBuilder(
            app,
            StockDatabase::class.java,
            "stock_db.db"
        ).build()
    }

}