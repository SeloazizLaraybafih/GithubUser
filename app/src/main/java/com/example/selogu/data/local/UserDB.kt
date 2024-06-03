package com.example.selogu.data.local

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavUser::class],
    version = 2
)
abstract class UserDB:RoomDatabase() {
    companion object {
        var INSTANCE: UserDB? = null

        fun getDB(context: Context): UserDB? {
            if(INSTANCE==null)  {
                synchronized(UserDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, UserDB::class.java, "user_db").build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun favUserDao(): FavUserDao
}

