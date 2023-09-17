package com.example.manage.database

import com.example.manage.data.IdolGroup
import kotlinx.coroutines.runBlocking
import java.io.*

class IdolDB {
    companion object {
        val idolDB = HashMap<Int, IdolGroup>()

        val file = File("./managementfile/IdolFile.dat")
        fun getIdolFileDB() = runBlocking {
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
                    var members = listOf<String>()
                    if (str.size > 3) {
                        members = str.subList(4, str[3].toInt() + 4)
                    }
                    var events = listOf<String>()
                    if (str.size > str[3].toInt() + 4) {
                        events = str.subList(str[3].toInt() + 4, str.size)
                    }
                    val data = IdolGroup(str[1], str[2], str[3].toInt(), members, events)
                    idolDB.put(str[0].toInt(), data)
                }
            }
            //TODO NULL 처리
        }

        fun updateIdolFileDB() = runBlocking {
//            println("IodlDB updateIodlFileDB $idolDB")
            var fileOut = BufferedWriter(FileWriter("./managementfile/IdolFile.dat"))
            with(fileOut) {
                for (idol in idolDB) {
                    var memberStr = ""
                    for (index in idol.value.members!!.indices) {
                        if (index != 0) {
                            memberStr += ","
                        }
                        memberStr += idol.value.members!![index]
                    }
                    var eventStr = ""
                    if (idol.value.events != null) {
                        eventStr += ","
                        for (index in idol.value.events!!.indices) {
                            if (index != 0) {
                                eventStr += ","
                            }
                            eventStr += idol.value.events!![index]
                        }
                    }
                    write("${idol.key},${idol.value.company},${idol.value.name},${idol.value.count},${memberStr}${eventStr}\n")
                    flush()
                }
                close()
            }
        }
    }
}