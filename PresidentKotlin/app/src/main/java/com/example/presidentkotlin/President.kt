package com.example.presidentkotlin

/**
 * Created by HimelR on 08-Feb-18.
 */



/**
 * Created by HimelR on 23-Jan-18.
 */

class President {


    var id: Int = 0
    var name: String? = null
    var startYear: Int = 0
    var endYear: Int = 0
    var img: String? = null


    constructor(name: String, startYear: Int, endYear: Int, img: String) {
        this.name = name
        this.startYear = startYear
        this.endYear = endYear
        this.img = img


    }

    override fun toString(): String {
        return name.toString()
    }

}



