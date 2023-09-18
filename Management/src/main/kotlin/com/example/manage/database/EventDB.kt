package com.example.manage.database

import com.example.manage.data.Event
import kotlinx.coroutines.runBlocking
import java.io.*

private const val EVENT_DB = "./managementfile/EventFile.dat"

class EventDB {
    companion object {
        val eventDB = HashMap<Int, Event>()

        val file = File(EVENT_DB)
        fun getEventFileDB() = runBlocking {
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
                    val groups = str.subList(3, str.size)
                    val data = Event(str[1], str[2], groups)
                    eventDB.put(str[0].toInt(), data)
                }
            }
        }

        fun updateEventFileDB() = runBlocking {
            var fileOut = BufferedWriter(FileWriter(EVENT_DB))

            with(fileOut) {
                eventDB.forEach {
                    var str: String = ""
                    for (index in it.value.castedGroup!!.indices) {
                        str = str.plus(",")
                        str = str.plus(it.value.castedGroup!![index])
                    }
                    write("${it.key},${it.value.name},${it.value.date}${str}\n")
                    flush()
                }
                close()
            }
        }
    }
}