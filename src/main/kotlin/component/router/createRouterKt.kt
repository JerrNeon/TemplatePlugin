package component.router

import component.util.routerName
import component.util.routerPath

/**
 * Author：Stevie.Chen Time：2023/8/31
 * Class Comment：
 */
fun createRouterKt(
    packageName: String,
    componentClass: String,
    isCreate: Boolean
): String {
    val routerName = componentClass.routerName()
    val routerPath = packageName.routerPath(componentClass)
    return buildString {
        if (isCreate) {
            append(
                """package com.zondy.biz.common.router;
                    
public class ARoutePath {

    public static final String $routerName = "$routerPath";
}
                """
            )
        } else {
            append("""    public static final String $routerName = "$routerPath";""")
        }
    }
}

fun getRouterDeclare(
    componentClass: String
): String {
    return componentClass.routerName()
}