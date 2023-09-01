package component.router

/**
 * Author：Stevie.Chen Time：2023/8/31
 * Class Comment：
 */
fun createRouterServiceKt(
    componentClass: String,
    isCreate: Boolean,
    isActivity: Boolean
): String {
    val funcName =
        if (isActivity) "void navigationTo${componentClass}Activity();" else "Fragment get${componentClass}Fragment();"
    return buildString {
        if (isCreate) {
            append(
                """
package com.zondy.biz.common.router.service;
                """
            )
            if (!isActivity)
                append(
                    """
import androidx.fragment.app.Fragment;
                """
                )
            append(
                """
import com.alibaba.android.arouter.facade.template.IProvider;
                    
public interface RouterService extends IProvider {

    $funcName
    
}
                """
            )
        } else {
            append(
                """
    $funcName
                """
            )
        }
    }
}

fun getRouterServiceDeclare(
    componentClass: String,
    isActivity: Boolean
): String {
    return if (isActivity) "navigationTo${componentClass}Activity" else "get${componentClass}Fragment"
}