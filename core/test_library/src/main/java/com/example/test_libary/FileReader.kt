package com.example.test_libary

object FileReader {
    fun readFile(filePath: String): String? =
        this::class.java.classLoader?.getResourceAsStream(filePath)
            ?.bufferedReader()?.use { it.readText() }
}
