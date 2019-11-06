package com.example.sarwan.tawseel.entities.responses

data class GooglePlacesApiResponse( val results : ArrayList<Candidates> = ArrayList()) {
    data class Candidates( val formatted_address : String,  val geometry : GoogleGeometry,  val name : String, val id : String) {
        data class GoogleGeometry( val location : Location) {
            data class Location( val lat : Double,  val lng : Double)
        }
    }
}