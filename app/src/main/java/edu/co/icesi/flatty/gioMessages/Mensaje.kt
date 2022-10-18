package edu.co.icesi.flatty.gioMessages

class Mensaje {

    var text : String
    var typeShow : TypeShows

    constructor(text:String, typeShow: TypeShows) {
        this.text = text
        this.typeShow = typeShow
    }

}

enum class TypeShows(type:Int) {
    ENVIADO(1),
    RECIBIDO(2)
}