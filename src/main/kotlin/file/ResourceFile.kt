package file

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class ResourceFile(fileName: String) {

    private val inputStream: InputStream = object {}.javaClass.classLoader.getResourceAsStream(fileName)
    fun lines(): List<String> {
        return BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.lines().toList()
        }
    }

}