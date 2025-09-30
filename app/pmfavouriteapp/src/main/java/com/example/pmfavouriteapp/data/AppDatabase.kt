package com.example.pmfavouriteapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pmfavouriteapp.entities.ParliamentMember
import com.example.pmfavouriteapp.entities.User
import com.example.pmfavouriteapp.entities.UserFavouriteCrossRef

@Database(
    entities = [User::class, ParliamentMember::class, UserFavouriteCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun parliamentMemberDao(): ParliamentMemberDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // Return existing instance if it exists, otherwise create a new one
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
