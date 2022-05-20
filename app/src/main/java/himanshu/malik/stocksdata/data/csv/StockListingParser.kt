package himanshu.malik.stocksdata.data.csv

import com.opencsv.CSVReader
import himanshu.malik.stocksdata.domain.StockListing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Author himanshu
 * Created on  20-May-2022
 */
@Singleton
class StockListingParser @Inject constructor(): CSVParser<StockListing> {

    override suspend fun parse(stream: InputStream): List<StockListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange = line.getOrNull(2)

                    StockListing(
                        name = name ?: return@mapNotNull null,
                        symbol = symbol ?: return@mapNotNull null,
                        exchange = exchange ?: return@mapNotNull null
                    )
                }.also {
                    csvReader.close()
                }
        }
    }
}