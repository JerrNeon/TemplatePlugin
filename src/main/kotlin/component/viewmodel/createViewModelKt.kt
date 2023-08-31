package component.viewmodel

import component.util.repositoryName
import component.util.viewModelName

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createViewModelKt(
    packageName: String,
    activityClass: String
): String {
    val viewModelName = activityClass.viewModelName()
    val repositoryName = packageName.repositoryName()
    val sb = StringBuilder()
    sb.append(
        """
package $packageName.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zondy.lib.core.architecture.BaseViewModel;
import $packageName.data.repository.$repositoryName;

public class $viewModelName extends BaseViewModel<$repositoryName> {
   
    public $viewModelName(@NonNull Application application) {
        super(application, $repositoryName.getInstance());
    }
}
    """
    )
    return sb.toString()
}