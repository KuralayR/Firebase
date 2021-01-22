package com.example.test

class Profile {
    var id : Int? = null
    var name: String? = null
    var year : Int? = null

    constructor(){

    }

    constructor(id: Int?, name: String?, year: Int?){
        this.id = id
        this.name = name
        this.year = year
    }

}