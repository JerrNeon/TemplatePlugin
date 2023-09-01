package component.fragment.src

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import component.util.humpToUnderline
import java.io.File

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
val createFragmentTemplate
    get() = template {
        name = "Zondy Fragment"
        description = "继承自BaseFragment的Fragment"
        minApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val fragmentClass = stringParameter {
            name = "Fragment Name"
            default = "Main"
            help = "只输入名字，不要包含Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "fragment_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(fragmentClass.value.humpToUnderline()) }
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
            TextFieldWidget(fragmentClass),
            TextFieldWidget(layoutName),
            CheckBoxWidget(isDataBinding),
            CheckBoxWidget(isViewModel),
            CheckBoxWidget(isRouter),
            PackageNameWidget(packageName),
            EnumWidget(language)
        )

        recipe = { data: TemplateData ->
            createFragmentRecipe(
                data as ModuleTemplateData,
                packageName.value,
                language.value,
                fragmentClass.value,
                layoutName.value,
                isDataBinding.value,
                isViewModel.value,
                isRouter.value
            )
        }

        thumb { File("images/template_zondy_fragment.png") }
    }