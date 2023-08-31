package component.architecture.app_package

import component.util.constantName

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createConstantKt(packageName: String): String {
    val constantName = packageName.constantName()

    val sb = StringBuilder()
    sb.append(
        """
package $packageName.app;

public class $constantName {

}
    """
    )
    return sb.toString()
}