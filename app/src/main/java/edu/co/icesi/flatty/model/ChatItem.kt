package edu.co.icesi.flatty.model

data class ChatItem(var friendId: String = "",
    var idLastMessage: String = "",
    var name: String = "",
    var message: String = "",
    var lastMessageHour: Long = 0,
    var profilePhoto : String = ""
)