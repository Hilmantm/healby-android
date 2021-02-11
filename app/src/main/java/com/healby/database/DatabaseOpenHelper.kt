package com.healby.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.healby.utilities.Constant.Companion.DATABASE_NAME
import com.healby.utilities.Constant.Companion.DATABASE_VERSION
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class DatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(
    ctx, DATABASE_NAME, null, DATABASE_VERSION
) {

    companion object {
        private var INSTANCE: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if(INSTANCE == null) {
                INSTANCE = DatabaseOpenHelper(ctx)
            }
            return INSTANCE as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)