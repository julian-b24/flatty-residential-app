package edu.co.icesi.flatty.quejas

import java.util.*

class Queja {
    var title : String
    var createdOn : Date
    var status : States

    constructor(title : String, createdOn : Date, status : States) {
        this.title = title
        this.createdOn = createdOn
        this.status = status
    }
}

enum class States(val status:String) {
    PENDIENTE("Pendiente"),
    FINALIZDO("Finalizado")
}