package component.util

import java.io.File

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun File.crateDir() {
    if (!exists()) {
        mkdirs()
    }
}