package himanshu.malik.stocksdata.data.csv

import java.io.InputStream


/**
 * Author himanshu
 * Created on  20-May-2022
 */
interface CSVParser<T> {
    suspend fun parse(stream: InputStream): List<T>
}