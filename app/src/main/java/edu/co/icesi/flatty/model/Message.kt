package edu.co.icesi.flatty.model

import java.sql.Timestamp


data class Message(
    var id:String = "",
    var author:String = "",
    var date: Timestamp = Timestamp.valueOf(""),
    var text:String = ""
)