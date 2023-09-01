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
    val routerDeclare = getRouterDeclare(packageName, componentClass)
    return buildString {
        if (isCreate) {
            append(
                """package com.zondy.biz.common.router;
                    
public class ARoutePath {

    $routerDeclare
}
                """
            )
        } else {
            append("""    $routerDeclare""")
        }
    }
}

fun getRouterDeclare(
    packageName: String,
    componentClass: String
): String {
    val routerName = componentClass.routerName()
    val routerPath = packageName.routerPath(componentClass)
    return "public static final String $routerName = \"$routerPath\";"
}