package component.util

import com.intellij.openapi.application.ApplicationNamesInfo
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
val getHeaderComment: String
    get() {
        val userName = ApplicationNamesInfo.getInstance().fullProductName
        val currData = SimpleDateFormat("yyyy/MM/dd").format(Date(System.currentTimeMillis()))
        return """/**
 * Author：$userName Time：$currData
 * Class Comment：
 */"""
    }


fun String.dataBindingName(): String {
    return this.underlineToHump() + "Binding"
}

fun String.viewModelName(): String {
    return "${this}ViewModel"
}

fun String.moduleName(isFirstUppercase: Boolean = true): String {
    return this.substring(this.lastIndexOf(".") + 1)
        .replaceFirstChar { char ->
            if (isFirstUppercase)
                char.uppercaseChar()
            else char
        }
}

fun String.repositoryName(): String {
    return this.moduleName() + "Repository"
}

fun String.apiServiceName(): String {
    return this.moduleName() + "ApiService"
}

fun String.constantName(): String {
    return this.moduleName() + "Constant"
}

fun String.routerName(): String {
    return this.humpToUnderline().uppercase()
}

fun String.routerPath(componentClass: String): String {
    return "/${this.moduleName(false)}/${componentClass.replaceFirstChar { char -> char.lowercase() }}"
}

/**
 * 下划线转驼峰
 */
fun String.underlineToHump(): String {
    val layoutNameArray = this.split("_")
    val sb = StringBuilder()
    for (name in layoutNameArray) {
        sb.append(name.replaceFirstChar { char -> char.uppercaseChar() })
    }
    return sb.toString()
}

/**
 * 驼峰转下划线
 */
fun String.humpToUnderline(): String {
    return this.replace(Regex("[A-Z]")) { result ->
        "_${result.value}"
    }.replaceFirst("_", "").lowercase()
}