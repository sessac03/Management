package com.example.manage.management.idol

import com.agent.manage.management.idol.IdolManageFun
import com.example.manage.util.ConsoleReader

fun showIdolMenu() {
    val idolManage = IdolManageFun()

    while (true) {
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        println("아이돌 관리 메뉴:    1. 아이돌 조회    2. 아이돌 등록    3. 아이돌 검색    4. 아이돌 수정    5. 아이돌 삭제    6. 뒤로가기")
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        var line: String?
        line = ConsoleReader.consoleScanner()
        val menu = line.trim().toInt()
        when (menu) {
            // 아이돌 조회
            1 -> {
                println("[조회 결과]")
                println("-----------------------------------")
                idolManage.getIdol()
                break
            }
            // 아이돌 등록
            2 -> {
                println("회사명,그룹명,멤버수,멤버 형식으로 입력해주세요.")
                idolManage.addIdol()
                break
            }
            // 아이돌 검색
            3 -> {
                print("그룹명을 입력해주세요: ")
                idolManage.searchIdol()
                break
            }

            4 -> {
                print("수정할 그룹명을 입력해주세요: ")
                idolManage.updateIdol()
                break
            }
            5 -> {
                print("삭제할 그룹명을 입력해주세요: ")
                idolManage.deleteIdol()
                break
            }
            6 -> break
        }
    }
}