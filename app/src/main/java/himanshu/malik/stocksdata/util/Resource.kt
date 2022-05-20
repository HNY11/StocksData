package himanshu.malik.stocksdata.util


/**
 * Author himanshu
 * Created on  19-May-2022
 *
 * generic class to handle states
 *
 * we will pass @param(data) here if the result will be success
 * and pass @param(message) here if we have an error case
 *
 * we could also create data classes here for Success, Error and Loading
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}