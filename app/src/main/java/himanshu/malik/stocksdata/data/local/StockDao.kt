package himanshu.malik.stocksdata.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * Author himanshu
 * Created on  18-May-2022
 */
@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockListings(
        stockListingEntities: List<StockListingEntity>
    )

    @Query("DELETE FROM stocklistingentity")
    suspend fun clearStockListings()

    @Query("""
            SELECT * 
            FROM stocklistingentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == symbol
        """)
    suspend fun searchStockListing(query: String): List<StockListingEntity>

}