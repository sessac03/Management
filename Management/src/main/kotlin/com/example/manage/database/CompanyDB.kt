package com.example.manage.database

import com.example.manage.data.Company
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.*

class CompanyDB {
    companion object {
        val companyDB = HashMap<Int, Company>()

        val file = File("./managementfile/CompanyFile.dat")
        fun getCompanyFileDB() = runBlocking {
            withContext(Dispatchers.IO) {
                BufferedReader(FileReader(file)).use { br ->
                    br.lines().forEach {
                        val str = it.split(",")
                        var groups = listOf<String>()
                        if (str.size > 3) {
                            groups = str.subList(4, str.size)
                        }
                        val data = Company(str[1], str[2], str[3], groups)
                        companyDB.put(str[0].toInt(), data)
                    }
                }
            }
        }

        fun updateCompanyFileDB() = runBlocking {
            withContext(Dispatchers.IO) {
                var fileOut = BufferedWriter(FileWriter("./managementfile/CompanyFile.dat"))
                with(fileOut) {
                    for (company in companyDB) {
                        var groupStr = ""
                        if (company.value.group != null) {
                            groupStr += ","
                            for (index in company.value.group!!.indices) {
                                if (index != 0) {
                                    groupStr += ","
                                }
                                groupStr += company.value.group!![index]
                            }
                        }
                        write("${company.key},${company.value.name},${company.value.address},${company.value.contactNumber}${groupStr}\n")
                        flush()
                    }
                    close()
                }
            }
        }
    }
}