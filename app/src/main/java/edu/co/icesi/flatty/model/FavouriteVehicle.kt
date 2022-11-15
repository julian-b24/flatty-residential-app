package edu.co.icesi.flatty.model

data class FavouriteVehicle (

    var id: String = "",
    var model: String = "",
    var licensePlate: String = "",
    var vehicleType: VehicleType = VehicleType.CAR
)