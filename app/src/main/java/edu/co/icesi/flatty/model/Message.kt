package edu.co.icesi.flatty.model

import java.sql.Timestamp


data class Message(
    var id:String = "",
    var author:String = "",
    var date: Long = 0,
    var text:String = ""
)