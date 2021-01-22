package com.example.recyclerview

import java.lang.reflect.Constructor
import java.time.Year

class Example {
    var id : String? = null
    var name: String? = null
    var year: Int? = null

    constructor() {

    }

    constructor(id:String?, name:String?, year: Int?){
        this.id = id
        this.name = name
        this.year = year
    }


}


