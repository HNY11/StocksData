package himanshu.malik.stocksdata.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * Author himanshu
 * Created on  18-May-2022
 */

@Database(
    entities = [StockListingEntity::class],
    version = 1
)
abstract class StockDatabase: RoomDatabase() {

    abstract val dao: StockDao

}