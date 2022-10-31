package edu.co.icesi.flatty.model

class Guard {
    var id: String //hash
    var name: String
    var phone: String
    var age: String
    var email: String
    var password: String

    constructor(id: String, name: String, phone: String, age: String, email: String, password: String) {
        this.id = id
        this.name = name
        this.phone = phone
        this.age = age
        this.email = email
        this.password = password
    }

}