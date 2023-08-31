package component

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import component.activity.src.createActivityTemplate
import component.architecture.createArchitectureTemplate
import component.fragment.src.createFragmentTemplate

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
class WizardTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> {
        return listOf(
            createActivityTemplate,
            createFragmentTemplate,
            createArchitectureTemplate
        )
    }
}