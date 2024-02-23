package com.example.moviapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.moviapp.models.Movie

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION_CODE),
    MyDbInter {

    companion object {

        const val DB_NAME = "my_movie"
        const val TABLE_NAME = "movie"
        const val VERSION_CODE = 1

        const val ID = "id"
        const val NAME = "name"
        const val AUTHORS = "authors"
        const val DATE = "date"
        const val INFO = "info"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME ($ID integer not null primary key autoincrement unique," +
                    "$NAME text not null," +
                    "$AUTHORS text not null," +
                    "$DATE text not null," +
                    "$INFO text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun add(movie: Movie) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME, movie.name)
        contentValue.put(AUTHORS, movie.authors)
        contentValue.put(DATE, movie.date)
        contentValue.put(INFO, movie.info)
        database.insert(TABLE_NAME, null, contentValue)
        database.close()
    }

    override fun edit(movie: Movie) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME, movie.name)
        contentValue.put(AUTHORS, movie.authors)
        contentValue.put(DATE, movie.date)
        contentValue.put(INFO, movie.info)
        database.update(TABLE_NAME,contentValue,"$ID = ?" , arrayOf(movie.id.toString()))
        database.close()
    }

    override fun delete(movie: Movie) {
        val database = this.writableDatabase

        database.delete(TABLE_NAME, "$ID = ?", arrayOf(movie.id.toString()))

        database.close()
    }

    override fun getAllMovie(): ArrayList<Movie> {
        val list = ArrayList<Movie>()

        val query = "select * from $TABLE_NAME"
        val cursor = this.readableDatabase.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {

                val movie = Movie(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
                list.add(movie)

            } while (cursor.moveToNext())
        }
        return list
    }
}