package component.architecture.app_package

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createNetDataSourceImplKt(packageName: String): String {
    val sb = StringBuilder()
    sb.append(
        """
package $packageName.data.source.net;

import $packageName.data.source.INetDataSource;

public class NetDataSourceImpl implements INetDataSource {

}
    """
    )
    return sb.toString()
}