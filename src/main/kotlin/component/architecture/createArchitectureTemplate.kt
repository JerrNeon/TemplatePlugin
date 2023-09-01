package component.architecture

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import component.fragment.src.createFragmentRecipe
import java.io.File

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
val createArchitectureTemplate
    get() = template {
        name = "Zondy Architecture"
        description = "模块架构生成"
        minApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val packageName = stringParameter {
            name = "Package name"
            visible = { !isNewModule }
            default = "com.zondy.module.main"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { packageName }
        }

        val language = enumParameter<Language> {
            name = "Source Language"
            help = "请选择语言"
            default = Language.Java
        }

        widgets(
            PackageNameWidget(packageName),
            EnumWidget(language)
        )

        recipe = { data: TemplateData ->
            createArchitectureRecipe(
                data as ModuleTemplateData,
                packageName.value,
                language.value
            )
        }

        thumb { File("images/template_zondy_architecture.png") }
    }