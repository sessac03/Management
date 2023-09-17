package com.example.manage.util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.*

class ConsoleReader {
    companion object {
        private lateinit var consoleInput: BufferedReader
        private lateinit var scanner: Scanner

        // 예전 방식
        fun consoleLineInput(): String {
            if (!this::consoleInput.isInitialized) {
                consoleInput = BufferedReader(InputStreamReader(System.`in`, Charset.defaultCharset()))
            }
            return consoleInput.readLine()
        }

        // 나름 최근
        fun consoleScanner(): String {
            if (!this::scanner.isInitialized) {
                scanner = Scanner(System.`in`)
            }
            return scanner.nextLine()
        }
    }
}