package component.architecture.app_package

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createILocalDataSourceKt(packageName: String): String {
    val sb = StringBuilder()
    sb.append(
        """
package $packageName.data.source;

public interface ILocalDataSource {

}
    """
    )
    return sb.toString()
}