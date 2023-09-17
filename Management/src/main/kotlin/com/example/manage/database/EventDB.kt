package com.example.manage.database

import com.example.manage.data.Event
import java.io.*

class EventDB {
    companion object {
        val eventDB = HashMap<Int, Event>()

        val file = File("./managementfile/EventFile.dat")
        fun getEventFileDB() {
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
                    val groups = str.subList(3, str.size)
                    val data = Event(str[1], str[2], groups)
                    eventDB.put(str[0].toInt(), data)
                }
            }
        }

        fun updateEventFileDB() {
            var fileOut = BufferedWriter(FileWriter("./managementfile/EventFile.dat"))

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