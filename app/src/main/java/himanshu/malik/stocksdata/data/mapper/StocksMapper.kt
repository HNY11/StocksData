package himanshu.malik.stocksdata.data.mapper

import himanshu.malik.stocksdata.data.local.StockListingEntity
import himanshu.malik.stocksdata.domain.StockListing


/**
 * Author himanshu
 * Created on  18-May-2022
 *
 * Mapper class contains extension functions
 * to convert the one class object to another
 */

fun StockListingEntity.toStockListing(): StockListing {
    return StockListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun StockListing.toStockListingEntity(): StockListingEntity {
    return StockListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}