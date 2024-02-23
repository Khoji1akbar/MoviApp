package com.example.moviapp.db

import com.example.moviapp.models.Movie

interface MyDbInter {

    fun add(movie: Movie)
    fun edit(movie: Movie)
    fun delete(movie: Movie)
    fun getAllMovie():ArrayList<Movie>

}