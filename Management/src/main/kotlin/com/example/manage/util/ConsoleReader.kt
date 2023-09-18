package com.example.manage.util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.*

class ConsoleReader {
    companion object {
        private lateinit var scanner: Scanner

        fun consoleScanner(): String {
            if (!this::scanner.isInitialized) {
                scanner = Scanner(System.`in`)
            }
            return scanner.nextLine()
        }
    }
}