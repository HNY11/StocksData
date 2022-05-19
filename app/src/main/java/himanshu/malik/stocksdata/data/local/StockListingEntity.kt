package himanshu.malik.stocksdata.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Author himanshu
 * Created on  18-May-2022
 *
 * Entity class to contain all stock companies data in the table.
 */
@Entity
class StockListingEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val symbol: String,
    val exchange: String,
)