package com.example.manage.management.event

import com.example.manage.data.Event
import com.example.manage.data.IdolGroup
import com.example.manage.database.EventDB.Companion.eventDB
import com.example.manage.database.EventDB.Companion.updateEventFileDB
import com.example.manage.database.IdolDB
import com.example.manage.database.IdolDB.Companion.idolDB
import com.example.manage.util.ConsoleReader
import com.example.manage.util.RandomIdGenerator
import kotlin.math.abs
import kotlin.random.Random

class EventManageFun {
    fun getEvents() {
        if (eventDB.size == 0) {
            println("등록된 행사가 없습니다.")
        } else {
            eventDB.forEach { event ->
                println("행사명: ${event.value.name}")
                println("행사 날짜: ${event.value.date}")
                println("출연 아이돌: ${event.value.castedGroup}")
                println("-----------------------------------")
            }
        }
    }

    fun addEvent() {
        val line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            val eventInfo = line.split(',')
            val groupList = eventInfo.subList(2, eventInfo.size)
            val data = Event(eventInfo[0], eventInfo[1], groupList)
            val dupEvent = eventDB.filter {
                it.value.name == data.name
            }
            if (dupEvent.isEmpty()) {
                addAndUpdateEventToIdolDB(groupList, eventInfo[0])
                eventDB.put(RandomIdGenerator.randomId, data)
//            println("AddEvent 결과: ${eventDB}")
                updateEventFileDB()
                println("등록이 완료되었습니다.")
            } else {
                println("이미 존재하는 행사명입니다.")
            }
        }
    }

    fun searchEvent() {
        val eventName: String?
        eventName = ConsoleReader.consoleScanner()
        if (!eventName.isNullOrEmpty()) {
            println("[$eventName] 검색 결과")
            val result = eventDB.filter {
                it.value.name == eventName
            }
            if (result.isEmpty()) {
                println("존재하지 않는 행사입니다.")
            } else {
                result.forEach {
                    println("행사명: ${it.value.name}")
                    println("행사 날짜: ${it.value.date}")
                    println("출연 아이돌: ${it.value.castedGroup}")
                }
            }
        }
    }

    fun updateEvent() {
        val eventName: String?
        eventName = ConsoleReader.consoleScanner()
        if (!eventName.isNullOrEmpty()) {
            var eventKey = -1
            for (event in eventDB) {
                if (eventName == event.value.name) {
                    eventKey = event.key
                    break
                }
            }
            if (eventKey != -1) {
                println("수정할 정보를 입력해주세요.(형식: 행사명,행사 날짜(0000-00-00),캐스팅된 아이돌)")
                val newData = ConsoleReader.consoleScanner()
                if (!newData.isNullOrEmpty()) {
                    val eventInfo = newData.split(",")
                    val groupList = eventInfo.subList(2, eventInfo.size)
                    val data = Event(eventInfo[0], eventInfo[1], groupList)
                    addAndUpdateEventToIdolDB(groupList, eventInfo[0])
                    eventDB.replace(eventKey, data)
                    println("수정이 완료되었습니다.")
                }
            } else {
                println("존재하지 않는 행사입니다.")
            }
        }
        updateEventFileDB()
    }

    fun deleteEvent() {
        var eventName: String?
        eventName = ConsoleReader.consoleScanner()
        if (!eventName.isNullOrEmpty()) {
            var eventKey = -1
            for (event in eventDB) {
                if (eventName == event.value.name) {
                    eventKey = event.key
                    deleteEventToIdolDB(eventName)
                    break
                }
            }
            if (eventKey != -1) {
                eventDB.remove(eventKey)
                println("삭제 완료!")
            } else {
                println("존재하지 않는 행사입니다.")
            }
        }
        updateEventFileDB()
    }

    fun addAndUpdateEventToIdolDB(groupList: List<String>, eventName: String) {
        for (group in groupList) {
            for (idol in idolDB) {
                if (group == idol.value.name) {
                    var eventList = mutableListOf<String>()
                    if (idol.value.events != null) {
                        eventList = idol.value.events!!.toMutableList()
                        eventList.add(eventName)
                    } else {
                        eventList.add(eventName)
                    }
                    val data =
                        IdolGroup(idol.value.company, idol.value.name, idol.value.count, idol.value.members, eventList)
                    idolDB.replace(idol.key, data)
                }
            }
        }
        IdolDB.updateIdolFileDB()
    }

    fun deleteEventToIdolDB(eventName: String) {
        for (idol in idolDB) {
            for (event in idol.value.events as List<String>) {
                if (eventName == event) {
                    var eventList = idol.value.events!!.toMutableList()
                    eventList.remove(event)
                    val data =
                        IdolGroup(idol.value.company, idol.value.name, idol.value.count, idol.value.members, eventList)
                    idolDB.replace(idol.key, data)
                }
            }
        }
        IdolDB.updateIdolFileDB()
    }

}