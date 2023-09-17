package com.example.manage.data

data class CompanyInfo(
    val name: String,
    val address: String,
    val contactNumber: String,
    val group: List<IdolGroup>? = null
)

data class IdolGroup(
    val company: String?,
    val name: String,
    val count: Int,
    val members:List<String>? = null,
    val events: List<String>? = null
)

data class Event(
    val name:String,
    val date: String,
    val castedGroup: List<String>?
)

/*
1,코엑스,2023-09-24,르세라핌,소시
2,킨텍스,2023-09-30,소대,시대
1389975548,하하하,2023-10-45,BTS,몬스타엑스
 */
