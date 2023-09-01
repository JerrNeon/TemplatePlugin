package component.activity.src.app_package

import component.util.dataBindingName
import component.util.humpToUnderline
import component.util.routerName
import component.util.viewModelName

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createActivityKt(
    packageName: String,
    activityClass: String,
    layoutName: String,
    isDataBinding: Boolean,
    isViewModel: Boolean,
    isRouter: Boolean
): String {
    val corePackageName = if (isDataBinding && isViewModel) {
        "com.zondy.lib.core.base.BaseActivity"
    } else if (isViewModel) {
        "com.zondy.lib.core.base.BaseVMActivity"
    } else {
        "com.zondy.lib.core.base.RootActivity"
    }
    val routerName = activityClass.routerName()
    val dataBingClassName = if (isDataBinding) layoutName.dataBindingName() else ""
    val dataBingPackName =
        if (isDataBinding) "import ${packageName}.databinding.$dataBingClassName;" else ""
    val viewModelClassName = if (isViewModel) activityClass.viewModelName() else ""
    val viewModelPackName =
        if (isViewModel) "import ${packageName}.viewmodel.$viewModelClassName;" else ""
    val coreName = if (isDataBinding && isViewModel) {
        "BaseActivity<$dataBingClassName, $viewModelClassName>"
    } else if (isViewModel) {
        "BaseVMActivity<$viewModelClassName>"
    } else {
        "RootActivity"
    }

    return buildString {
        append(
            """
package $packageName.ui.activity;

import android.os.Bundle;
        """
        )
        if (isRouter) {
            append(
                """
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zondy.biz.common.router.ARoutePath;"""
            )
        }
        append(
            """import $corePackageName;
import ${packageName}.R;
$dataBingPackName
$viewModelPackName 
        """
        )
        if (isRouter) {
            append(
                """
@Route(path = ARoutePath.$routerName)"""
            )
        }
        append(
            """public class ${activityClass}Activity extends $coreName {
   """
        )
        if (isDataBinding && isViewModel) {
            append(
                """
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.${layoutName};
    }
    """
            )
        } else {
            append(
                """
    public ${activityClass}Activity() {
        super(R.layout.${layoutName});
    }
    """
            )
        }
        append(
            """
    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }
    
    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }
    
    @Override
    public void loadData() {
        super.loadData();
    }
}
            """
        )
    }
}