package com.example.manage.data

data class Company(
    val name: String,
    val address: String,
    val contactNumber: String,
    val group: List<String>? = null
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
    val castedGroup: List<String>
)