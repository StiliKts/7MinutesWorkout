package com.sevenminuteworkout

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SqliteOpenHelper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
) :
    SQLiteOpenHelper(
        context, DATABASE_NAME,
        factory, DATABASE_VERSION
    ) {


    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_HISTORY_TABLE = ("CREATE TABLE" +
                TABLE_HISTORY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_COMPLETED_DATE + "DATETIME"
                + " TEXT" + ")")
        db.execSQL(CREATE_HISTORY_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY)
        onCreate(db)
    }


    fun addDate(date: String) {
        val values =
            ContentValues()
        values.put(
            COLUMN_COMPLETED_DATE,
            date
        )
        val db =
            this.writableDatabase
        db.insert(TABLE_HISTORY, null, values)
        db.close()
    }


    fun getAllCompletedDatesList(): ArrayList<String> {
        val list = ArrayList<String>()
        val db =
            this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY", null)


        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_DATE)))
        }
        cursor.close()
        return list
    }

    fun checkTrainingcurrentDate(date:String):Boolean{

        val db =
                this.readableDatabase

        val cursor = db.rawQuery("SELECT DISTINCT $COLUMN_COMPLETED_DATE FROM $COLUMN_COMPLETED_DATE WHERE " +
                "$COLUMN_COMPLETED_DATE LIKE '%$date%'", null)

        cursor.close()

        return cursor.count ==1
    }



    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "SevenMinutesWorkout.db"
        private const val TABLE_HISTORY = "history"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_COMPLETED_DATE = "completed_date"
    }
}