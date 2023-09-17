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
    while (true) {
        println("+++++++++++++++++++++++++++++++++++++++++++++++++")
        println("전체 메뉴:    1. 회사 관리    2. 아이돌 관리   3. 행사관리")
        println("+++++++++++++++++++++++++++++++++++++++++++++++++")
        var line: String?
        line = ConsoleReader.consoleScanner()
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
        }
    }
}

fun main() {
    startManage()
}