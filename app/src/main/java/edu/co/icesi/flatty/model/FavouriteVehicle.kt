package edu.co.icesi.flatty.model

class FavouriteVehicle {
    var model: String
    var licensePlate: String
    var vehicleType: VehicleType

    constructor(model: String, licensePlate: String, vehicleType: VehicleType){
        this.model = model
        this.licensePlate = licensePlate
        this.vehicleType = vehicleType
    }
}