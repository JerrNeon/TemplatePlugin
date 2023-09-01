package component.fragment.src

import com.android.tools.idea.wizard.template.Language
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import component.activity.res.layout.createActivityXml
import component.fragment.src.app_package.createFragmentKt
import component.router.*
import component.util.appendToJavaFile
import component.viewmodel.createViewModelKt
import java.io.File

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun RecipeExecutor.createFragmentRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    language: Language,
    fragmentClass: String,
    layoutName: String,
    isDataBinding: Boolean,
    isViewModel: Boolean,
    isRouter: Boolean
) {
    val (projectData, srcOut, resOut) = moduleData
//    val ktOrJavaExt = projectData.language.extension
    val ktOrJavaExt = language.extension

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
    if (isRouter) {
        val routerRootPath = "biz_common/src/main/java/com/zondy/biz/common/router"
        // 生成Router
        val routerFile = File(
            projectData.rootDir,
            "$routerRootPath/ARoutePath.${ktOrJavaExt}"
        )
        if (routerFile.exists()) {
            routerFile.appendToJavaFile(
                createRouterKt(
                    packageName,
                    fragmentClass,
                    false
                ),
                getRouterDeclare(packageName, fragmentClass)
            )
        } else {
            save(
                createRouterKt(packageName, fragmentClass, true),
                routerFile
            )
        }
        // 生成RouterService
        val routerServiceFile = File(
            projectData.rootDir,
            "$routerRootPath/service/RouterService.${ktOrJavaExt}"
        )
        if (routerServiceFile.exists()) {
            routerServiceFile.appendToJavaFile(
                createRouterServiceKt(
                    fragmentClass,
                    isCreate = false,
                    isActivity = false
                ),
                getRouterServiceDeclare(fragmentClass, isActivity = false)
            )
        } else {
            save(
                createRouterServiceKt(
                    fragmentClass,
                    isCreate = true,
                    isActivity = false
                ),
                routerServiceFile
            )
        }
        // 生成RouterServiceImpl
        val routerServiceImplFile = File(
            projectData.rootDir,
            "$routerRootPath/service/RouterServiceIml.${ktOrJavaExt}"
        )
        if (routerServiceImplFile.exists()) {
            routerServiceImplFile.appendToJavaFile(
                createRouterServiceImplKt(
                    packageName,
                    fragmentClass,
                    isCreate = false,
                    isActivity = false
                ),
                getRouterServiceImplDeclare(fragmentClass, isActivity = false)
            )
        } else {
            save(
                createRouterServiceImplKt(
                    packageName,
                    fragmentClass,
                    isCreate = true,
                    isActivity = false
                ),
                routerServiceImplFile
            )
        }
    }
}