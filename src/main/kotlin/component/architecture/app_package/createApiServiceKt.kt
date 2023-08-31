package component.architecture.app_package

import component.util.apiServiceName

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createApiServiceKt(packageName: String): String {
    val apiServiceName = packageName.apiServiceName()

    val sb = StringBuilder()
    sb.append(
        """
package $packageName.data.source.net;

public interface $apiServiceName {

}
    """
    )
    return sb.toString()
}