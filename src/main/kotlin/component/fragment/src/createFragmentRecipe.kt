package component.fragment.src

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import component.activity.res.layout.createActivityXml
import component.fragment.src.app_package.createFragmentKt
import component.viewmodel.createViewModelKt

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun RecipeExecutor.createFragmentRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    fragmentClass: String,
    layoutName: String,
    isDataBinding: Boolean,
    isViewModel: Boolean,
    isRouter: Boolean
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

//    val applicationPackage = projectData.applicationPackage
    val createFragment = createFragmentKt(
        packageName,
        fragmentClass,
        layoutName,
        isDataBinding,
        isViewModel,
        isRouter
    )
    // 保存Fragment
    save(
        createFragment,
        srcOut.resolve("ui/fragment/${fragmentClass}Fragment.${ktOrJavaExt}")
    )
    // 保存xml
    save(
        createActivityXml(isDataBinding),
        resOut.resolve("layout/${layoutName}.xml")
    )
    // 保存ViewModel
    if (isViewModel) {
        save(
            createViewModelKt(packageName, fragmentClass),
            srcOut.resolve("viewmodel/${fragmentClass}ViewModel.${ktOrJavaExt}")
        )
    }
}