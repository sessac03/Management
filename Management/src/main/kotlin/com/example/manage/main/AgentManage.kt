package com.example.manage.main

import com.example.manage.database.CompanyDB.Companion.getCompanyFileDB
import com.example.manage.database.EventDB.Companion.getEventFileDB
import com.example.manage.database.IdolDB.Companion.getIdolFileDB
import com.example.manage.management.company.showCompanyMenu
import com.example.manage.management.event.showEventMenu
import com.example.manage.management.idol.showIdolMenu
import com.example.manage.util.ConsoleReader

fun startManage() {
    getCompanyFileDB()
    getIdolFileDB()
    getEventFileDB()
    do {
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        println("전체 메뉴:    1. 회사 관리    2. 아이돌 관리   3. 행사관리    4. 종료 또는 엔터를 치세요.")
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (line == "") {
            println("프로그램이 종료되었습니다.")
            break
        } else {
            try {
                val menu = line.trim().toInt()
                when (menu) {
                    // 회사 관리
                    1 -> {
                        showCompanyMenu()
                    }
                    // 아이돌 관리
                    2 -> {
                        showIdolMenu()
                    }
                    // 행사 관리
                    3 -> {
                        showEventMenu()
                    }
                    // 프로그램 종료
                    4 -> {
                        println("프로그램이 종료되었습니다.")
                        break
                    }

                    else -> {
                        println("유효하지 않은 메뉴입니다.")
                    }
                }
            } catch (nfe: NumberFormatException) {
                println("숫자로 입력해주세요.")
            }
        }
    } while (true)
}

fun main() {
    startManage()
}