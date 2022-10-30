package edu.co.icesi.flatty.model

class Resident {

    var id: String //hash
    var name: String
    var phone: String
    var numberApartment: String
    var age: String
    var email: String
    var password: String

    constructor(id: String, name: String, phone: String, numberApartment: String, age: String, email: String, password: String) {
        this.id = id
        this.name = name
        this.phone = phone
        this.numberApartment = numberApartment
        this.age = age
        this.email = email
        this.password = password
    }
}