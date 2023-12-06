package file

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class ResourceFile(fileName: String) {

    private val inputStream: InputStream = object {}.javaClass.classLoader.getResourceAsStream(fileName)
    val lines: List<String> =
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.lines().toList()
        }

    val text: String = lines.toString()

}