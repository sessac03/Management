package com.example.manage.management.company

import com.example.manage.data.Company
import com.example.manage.database.CompanyDB.Companion.companyDB
import com.example.manage.database.CompanyDB.Companion.updateCompanyFileDB
import com.example.manage.database.IdolDB.Companion.idolDB
import com.example.manage.database.IdolDB.Companion.updateIdolFileDB
import com.example.manage.util.ConsoleReader
import com.example.manage.util.RandomIdGenerator

class CompanyManageFun {
    fun getCompanies() {
        if (companyDB.size == 0) {
            println("등록된 회사가 없습니다.")
        } else {
            for (company in companyDB) {
                println("이름: ${company.value.name}")
                println("주소: ${company.value.address}")
                println("연락처: ${company.value.contactNumber}")
                print("아이돌 리스트: ")
                company.value.group?.forEach {
                    print("${it} ")
                }
                println()
                println("-----------------------------------")
            }
        }
    }

    fun addCompany() {
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            val str = line.split(',')
            var flag = false
            for (item in companyDB) {
                if (item.value.name == str[0]) {
                    flag = true
                    break
                }
            }
            if (flag) {
                println("이미 존재하는 회사명입니다.")
            } else {
                val id = RandomIdGenerator.randomId
                val data = Company(str[0], str[1], str[2], emptyList())
                companyDB.put(id, data)
                println("회사 등록이 완료되었습니다.")
//                println("AddCompany 결과: $companyDB")
                updateCompanyFileDB()
            }
        }
    }

    fun searchCompany() {
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        if (!companyName.isNullOrEmpty()) {
            var flag = false
            for (company in companyDB) {
                if (companyName.equals(company.value.name)) {
                    flag = true
                    println("[$companyName] 검색결과")
                    println("이름\t\t\t: ${company.value.name}")
                    println("주소\t\t\t: ${company.value.address}")
                    println("연락처\t\t: ${company.value.contactNumber}")
                    print("아이돌 리스트\t: ")
                    company.value.group?.forEach {
                        print("${it} ")
                    }
                    println()
                }
            }
            println()
            if (flag == false) {
                println("존재하지 않는 회사입니다.")
            }
        }
    }

    fun updateCompany() {
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        if (!companyName.isNullOrEmpty()) {
            var flag = false
            var companyKey = 0
            var groups: List<String>? = emptyList()
            for (company in companyDB) {
                if (companyName.equals(company.value.name)) {
                    flag = true
                    companyKey = company.key
                    groups = company.value.group
                    break
                }
            }
            if (flag == false) {
                println("존재하지 않는 회사입니다.")
                return
            }
            println("회사명,주소,전화번호 형식으로 입력해주세요.")
            val newData = ConsoleReader.consoleScanner()
            if (!newData.isNullOrEmpty()) {
                val str = newData.split(",")
                if (str[0] == companyName) {
                    val data = Company(str[0], str[1], str[2], groups)
                    companyDB.replace(companyKey, data)
                    println("수정이 완료되었습니다.")
//                println("AddCompany 결과: $companyDB")
                } else {
                    println("회사 이름이 동일하지 않습니다.")
                }
            }
        }
        updateCompanyFileDB()
    }

    fun deleteCompany() {
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        if (!companyName.isNullOrEmpty()) {
            var flag = false
            for (company in companyDB) {
                if (companyName.equals(company.value.name)) {
                    flag = true
                    companyDB.remove(company.key)
                    for(idol in idolDB){
                        if(company.value.name == idol.value.company){
                            idol.value.company = "소속사 없음"
                        }
                    }
                    break
                }
            }
            if (flag) {
                println("삭제 완료!")
            } else {
                println("존재하지 않는 회사입니다.")
            }
        }
        updateCompanyFileDB()
        updateIdolFileDB()
    }
}