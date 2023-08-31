package component.architecture.app_package

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createDBDataSourceImplKt(packageName: String): String {
    val sb = StringBuilder()
    sb.append(
        """
package $packageName.data.source.db;

import $packageName.data.source.IDBDataSource;

public class DBDataSourceImpl implements IDBDataSource {

}
    """
    )
    return sb.toString()
}