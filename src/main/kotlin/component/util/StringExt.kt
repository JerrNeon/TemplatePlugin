package component.util

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun String.dataBindingName(): String {
    return this.underlineToHump() + "Binding"
}

fun String.viewModelName(): String {
    return "${this}ViewModel"
}

fun String.repositoryName(): String {
    return this.substring(this.lastIndexOf(".") + 1)
        .replaceFirstChar { char -> char.uppercaseChar() } + "Repository"
}

fun String.apiServiceName(): String {
    return this.substring(this.lastIndexOf(".") + 1)
        .replaceFirstChar { char -> char.uppercaseChar() } + "ApiService"
}

fun String.constantName(): String {
    return this.substring(this.lastIndexOf(".") + 1)
        .replaceFirstChar { char -> char.uppercaseChar() } + "Constant"
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