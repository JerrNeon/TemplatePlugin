package component.fragment.src.app_package

import component.util.dataBindingName
import component.util.routerName
import component.util.viewModelName

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createFragmentKt(
    packageName: String,
    fragmentClass: String,
    layoutName: String,
    isDataBinding: Boolean,
    isViewModel: Boolean,
    isRouter: Boolean
): String {
    val corePackageName = if (isDataBinding && isViewModel) {
        "com.zondy.lib.core.base.BaseFragment"
    } else if (isViewModel) {
        "com.zondy.lib.core.base.BaseVMFragment"
    } else {
        "com.zondy.lib.core.base.RootFragment"
    }
    val routerName = fragmentClass.routerName()
    val dataBingClassName = if (isDataBinding) layoutName.dataBindingName() else ""
    val dataBingPackName =
        if (isDataBinding) "import ${packageName}.databinding.$dataBingClassName;" else ""
    val viewModelClassName = if (isViewModel) fragmentClass.viewModelName() else ""
    val viewModelPackName =
        if (isViewModel) "import ${packageName}.viewmodel.$viewModelClassName;" else ""
    val coreName = if (isDataBinding && isViewModel) {
        "BaseFragment<$dataBingClassName, $viewModelClassName>"
    } else if (isViewModel) {
        "BaseVMFragment<$viewModelClassName>"
    } else {
        "RootFragment"
    }
    return buildString {
        append(
            """
package $packageName.ui.fragment;
      """
        )
        append(
            """
import android.os.Bundle;"""
        )
        if (isDataBinding && isViewModel) {
            append(
                """import android.view.LayoutInflater;
import android.view.ViewGroup;
            """
            )
        }

        append(
            """
import androidx.annotation.Nullable;
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
            """public class ${fragmentClass}Fragment extends $coreName {
   """
        )

        if (isDataBinding && isViewModel) {
            append(
                """
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.${layoutName};
    }
    """
            )
        } else {
            append(
                """
    public ${fragmentClass}Fragment() {
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