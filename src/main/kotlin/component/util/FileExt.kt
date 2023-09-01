package component.util

import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.Thumb.NoThumb.path
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption


/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun File.crateDir() {
    if (!exists()) {
        mkdirs()
    }
}

fun File.append(str: String) {
    var writer: OutputStreamWriter? = null
    var fos: FileOutputStream? = null
    try {
        if (!parentFile.exists()) {
            parentFile.mkdirs()
        }
        fos = FileOutputStream(this, true)
        writer = OutputStreamWriter(fos, Charsets.UTF_8)
        writer.flush()
        writer.write(str)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        if (writer != null) {
            try {
                writer.flush()
                writer.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (fos != null) {
            try {
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

/**
 * 追加字符串到Java文件的最后面，在}前面
 */
fun File.appendToSecondLineFromTheBottom(str: String) {
    var reader: BufferedReader? = null
    var fileReader: FileReader? = null
    var writer: BufferedWriter? = null
    var fileWriter: FileWriter? = null
    try {
        reader = BufferedReader(FileReader(this, Charsets.UTF_8).also { fileReader = it })
        val tempFile = File(this.parentFile, this.nameWithoutExtension + "Temp." + this.extension)
        writer = BufferedWriter(FileWriter(tempFile, Charsets.UTF_8).also { fileWriter = it })
        val lines = reader.readLines()
        var emptyLine = 0
        for (i in lines.size - 1 downTo 0) {
            if (lines[i].isNotBlank()) break
            emptyLine++
        }
        val realLines = lines.subList(0, lines.size - emptyLine).toMutableList()
        if (realLines[realLines.size - 2].isBlank()) {
            realLines.add(realLines.size - 2, str)
        } else {
            realLines.add(realLines.size - 1, str)
        }
        for (s in realLines) {
            writer.appendLine(s)
        }
        writer.flush()
        writer.close()
        writer = null
        reader.close()
        reader = null
        delete()
        tempFile.renameTo(this)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        if (reader != null) {
            try {
                reader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (fileReader != null) {
            try {
                fileReader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (writer != null) {
            try {
                writer.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (fileWriter != null) {
            try {
                fileWriter?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

/**
 * 追加字符串到Java文件的最后面，在}前面
 */
fun File.appendToJavaFile(str: String, key: String) {
    try {
        val path = Paths.get(absolutePath)
        val lines = Files.readAllLines(path)
        var isExit = false
        for (i in lines.size - 1 downTo 0) {
            if (lines[i].contains(key)) {
                isExit = true
                break
            }
        }
        if (isExit)
            return
        var emptyLine = 0
        for (i in lines.size - 1 downTo 0) {
            if (lines[i].isNotBlank()) break
            emptyLine++
        }
        val realLines = lines.subList(0, lines.size - emptyLine)
        if (realLines[realLines.size - 2].isBlank()) {
            realLines.add(realLines.size - 2, str)
        } else {
            realLines.add(realLines.size - 1, str)
        }
        Files.write(path, realLines)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}