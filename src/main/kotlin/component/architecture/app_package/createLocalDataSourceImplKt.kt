package component.architecture.app_package

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createLocalDataSourceImplKt(packageName: String): String {
    val sb = StringBuilder()
    sb.append(
        """
package $packageName.data.source.local;

import $packageName.data.source.ILocalDataSource;

public class LocalDataSourceImpl implements ILocalDataSource {

}
    """
    )
    return sb.toString()
}