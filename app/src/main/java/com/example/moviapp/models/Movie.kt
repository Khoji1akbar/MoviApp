package com.example.moviapp.models
import java.io.Serializable

class Movie : Serializable {

    var id: Int? = null
    var name: String? = null
    var authors: String? = null
    var date: String? = null
    var info: String? = null

    constructor(id: Int?, name: String?, authors: String?, date: String?, info: String?) {
        this.id = id
        this.name = name
        this.authors = authors
        this.date = date
        this.info = info
    }

    constructor(name: String?, authors: String?, date: String?) {
        this.name = name
        this.authors = authors
        this.date = date
    }

    constructor(name: String?, authors: String?, date: String?, info: String?) {
        this.name = name
        this.authors = authors
        this.date = date
        this.info = info
    }


}