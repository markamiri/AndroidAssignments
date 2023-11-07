package com.example.androidassignments

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class ChatDatabaseHelper(ctx: Context?) :
    SQLiteOpenHelper(ctx, DATABASE_NAME, null, VERSION_NUM) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_MESSAGE TEXT)"
        db.execSQL(createTableQuery)

        Log.i("ChatDatabaseHelper", "onCreate() called: Database created")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

        // Log a message when onUpgrade is called
        Log.i("ChatDatabaseHelper", "onUpgrade() called: Database upgraded")
    }

    companion object {
        // Define static variables for database name and version
        private const val DATABASE_NAME = "Messages.db"
        private const val VERSION_NUM = 1

        // Define your table and column names as constants
        const val TABLE_NAME = "Messages"
        const val KEY_ID = "id"
        const val KEY_MESSAGE = "MESSAGE"
    }
}
