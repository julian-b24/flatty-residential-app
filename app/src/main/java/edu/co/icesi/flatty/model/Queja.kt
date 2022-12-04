package edu.co.icesi.flatty.model

import java.util.*

data class Queja (
    var title : String = "",
    var description: String = "",
    var createdOn : Date = Date(),
    var status : States = States.PENDIENTE
)

enum class States(val status:String) {
    PENDIENTE("Pendiente"),
    FINALIZDO("Finalizado")
}