package component.router

import component.util.routerName

/**
 * Author：Stevie.Chen Time：2023/8/31
 * Class Comment：
 */
@Suppress("UNUSED_PARAMETER")
fun createRouterServiceImplKt(
    packageName: String,
    componentClass: String,
    isCreate: Boolean,
    isActivity: Boolean
): String {
    val routerName = componentClass.routerName()
    val funcBody = if (isActivity) {
        """
    @Override
    public void navigationTo${componentClass}Activity() {
        ARouter.getInstance().build(ARoutePath.$routerName)
                .navigation();    
    }
    """
    } else {
        """
    @Override
    public Fragment get${componentClass}Fragment() {
        return (Fragment) ARouter.getInstance().build(ARoutePath.$routerName)
                .navigation();    
    }    
"""
    }
    return buildString {
        if (isCreate) {
            append(
                """
package com.zondy.biz.common.router.service;
                """
            )
            if (!isActivity) {
                append(
                    """
import androidx.fragment.app.Fragment;
import android.content.Context;
                """
                )
            }
            append(
                """
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zondy.biz.common.router.ARoutePath;

@Route(path = ARouterConfig.Provider.ROUTER_PATH_SERVICE_ROUTER_ALL)
public class RouterServiceIml implements RouterService {

    @Override
    public void init(Context context) {
    }
$funcBody    
}
                """
            )
        } else {
            append(funcBody)
        }
    }
}

fun getRouterServiceImplDeclare(
    componentClass: String,
    isActivity: Boolean
): String {
    return if (isActivity) "navigationTo${componentClass}Activity"
    else "get${componentClass}Fragment"
}