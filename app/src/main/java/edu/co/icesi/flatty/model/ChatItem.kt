package edu.co.icesi.flatty.model

import android.widget.ImageView

class ChatItem {
    var name: String
    var message: String
    var lastMessageHour: String

    constructor(name: String, message:String, lastMessageHour: String){
        this.name = name
        this.message = message
        this.lastMessageHour = lastMessageHour
    }
}