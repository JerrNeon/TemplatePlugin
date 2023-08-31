package component.activity.src

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import component.activity.res.layout.createActivityXml
import component.activity.src.app_package.createActivityKt
import component.viewmodel.createViewModelKt

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */

fun RecipeExecutor.createActivityRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    activityClass: String,
    layoutName: String,
    isDataBinding: Boolean,
    isViewModel: Boolean,
    isRouter: Boolean
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
//    generateManifest(
//    moduleData: com.android.tools.idea.wizard.template.ModuleTemplateData,
//    activityClass: kotlin.String, packageName: kotlin.String,
//    isLauncher: kotlin.Boolean,
//    hasNoActionBar: kotlin.Boolean,
//    activityThemeName: kotlin.String /* = compiled code */,
//    isNewModule: kotlin.Boolean /* = compiled code */,
//    isLibrary: kotlin.Boolean /* = compiled code */,
//    manifestOut: java.io.File /* = compiled code */,
//    baseFeatureResOut: java.io.File /* = compiled code */,
//    generateActivityTitle: kotlin.Boolean,
//    isResizeable: kotlin.Boolean /* = compiled code */): kotlin.Unit { /* compiled code */
//    }
    generateManifest(
        moduleData = moduleData,
        activityClass = "ui.activity.${activityClass}Activity",
        packageName = packageName,
        isLauncher = false,
        hasNoActionBar = false,
        isNewModule = false,
        isLibrary = false,
//        manifestOut = ,
//        baseFeatureResOut = ,
        generateActivityTitle = false,
        isResizeable = false,
    )

//    val applicationPackage = projectData.applicationPackage
    val createActivity = createActivityKt(
        packageName,
        activityClass,
        layoutName,
        isDataBinding,
        isViewModel,
        isRouter
    )
    // 保存Activity
    save(
        createActivity,
        srcOut.resolve("ui/activity/${activityClass}Activity.${ktOrJavaExt}")
    )
    // 保存xml
    save(
        createActivityXml(isDataBinding),
        resOut.resolve("layout/${layoutName}.xml")
    )
    // 保存ViewModel
    if (isViewModel) {
        save(
            createViewModelKt(packageName, activityClass),
            srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}")
        )
    }
}