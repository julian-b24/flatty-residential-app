package edu.co.icesi.flatty.models

class Resident {

    var id: String //hash
    var name: String
    var phone: String
    var numberApartment: String
    var age: String
    var email: String

    constructor(id: String, name: String, phone: String, numberApartment: String, age: String, email: String) {
        this.id = id
        this.name = name
        this.phone = phone
        this.numberApartment = numberApartment
        this.age = age
        this.email = email
    }
}