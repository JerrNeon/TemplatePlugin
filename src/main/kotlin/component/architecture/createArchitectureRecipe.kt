package component.architecture

import com.android.tools.idea.wizard.template.Language
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import component.architecture.app_package.*
import component.architecture.app_package.createIDBDataSourceKt
import component.util.apiServiceName
import component.util.constantName
import component.util.repositoryName
import java.io.File

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun RecipeExecutor.createArchitectureRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    language: Language,
) {
    val (_, srcOut, _) = moduleData
//    val ktOrJavaExt = projectData.language.extension
    val ktOrJavaExt = language.extension

//    val applicationPackage = projectData.applicationPackage
    val repositoryName = packageName.repositoryName()
    // 创建Constant文件
    save(
        createConstantKt(packageName),
        srcOut.resolve("app/${packageName.constantName()}.${ktOrJavaExt}")
    )
    // 创建bean目录
    createDirectory(File(srcOut, "data/bean"))
    // 保存Repository
    save(
        createRepositoryKt(packageName),
        srcOut.resolve("data/repository/${repositoryName}.${ktOrJavaExt}")
    )
    // 保存IDBDataSource
    save(
        createIDBDataSourceKt(packageName),
        srcOut.resolve("data/source/IDBDataSource.${ktOrJavaExt}")
    )
    // 保存ILocalDataSource
    save(
        createILocalDataSourceKt(packageName),
        srcOut.resolve("data/source/ILocalDataSource.${ktOrJavaExt}")
    )
    // 保存INetDataSource
    save(
        createINetDataSourceKt(packageName),
        srcOut.resolve("data/source/INetDataSource.${ktOrJavaExt}")
    )
    // 保存IDBDataSourceImpl
    save(
        createDBDataSourceImplKt(packageName),
        srcOut.resolve("data/source/db/DBDataSourceImpl.${ktOrJavaExt}")
    )
    // 保存ILocalDataSourceImpl
    save(
        createLocalDataSourceImplKt(packageName),
        srcOut.resolve("data/source/local/LocalDataSourceImpl.${ktOrJavaExt}")
    )
    // 保存INetDataSourceImpl
    save(
        createNetDataSourceImplKt(packageName),
        srcOut.resolve("data/source/net/NetDataSourceImpl.${ktOrJavaExt}")
    )
    // 保存ApiService
    save(
        createApiServiceKt(packageName),
        srcOut.resolve("data/source/net/${packageName.apiServiceName()}.${ktOrJavaExt}")
    )
    // 创建activity目录
    createDirectory(File(srcOut, "ui/activity"))
    // 创建fragment目录
    createDirectory(File(srcOut, "ui/fragment"))
    // 创建viewmodel目录
    createDirectory(File(srcOut, "viewmodel"))
}