package component.activity.src

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import component.util.humpToUnderline
import java.io.File

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
val createActivityTemplate
    get() = template {
        name = "Zondy Activity"
        description = "继承自BaseActivity的Activity"
        minApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val activityClass = stringParameter {
            name = "Activity Name"
            default = "Main"
            help = "只输入名字，不要包含Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(activityClass.value.humpToUnderline()) }
        }

        val isDataBinding = booleanParameter {
            name = "is dataBinding"
            default = true
            help = "若勾选，自动添加DataBing配置"
        }

        val isViewModel = booleanParameter {
            name = "is viewModel"
            default = true
            help = "若勾选，自动添加ViewModel配置"
        }

        val isRouter = booleanParameter {
            name = "is router"
            default = true
            help = "若勾选，自动添加ARouter配置"
        }

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
            TextFieldWidget(activityClass),
            TextFieldWidget(layoutName),
            CheckBoxWidget(isDataBinding),
            CheckBoxWidget(isViewModel),
            CheckBoxWidget(isRouter),
            PackageNameWidget(packageName),
            EnumWidget(language),
        )

        recipe = { data: TemplateData ->
            createActivityRecipe(
                data as ModuleTemplateData,
                packageName.value,
                language.value,
                activityClass.value,
                layoutName.value,
                isDataBinding.value,
                isViewModel.value,
                isRouter.value
            )
        }

        thumb { File("images/template_zondy_activity.png") }
    }