package com.example.mapss.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.mapss.db.RunningDatabase
import com.example.mapss.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.mapss.other.Constants.KEY_NAME
import com.example.mapss.other.Constants.KEY_WIEGHT
import com.example.mapss.other.Constants.RUNNING_DATABASE_NAME
import com.example.mapss.other.Constants.SHARED_PREFERANCE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun  providingRunningDatabase(
        @ApplicationContext app:Context
    ) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db : RunningDatabase) = db.getRunDao()

    @Singleton
    @Provides
    fun provideSharedPreferance(@ApplicationContext app: Context) =
            app.getSharedPreferences(SHARED_PREFERANCE_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun ProvideName(sharedPref : SharedPreferences) = sharedPref.getString(KEY_NAME,"") ?:""

    @Singleton
    @Provides
    fun ProvideWeight(sharedPref : SharedPreferences) = sharedPref.getFloat(KEY_WIEGHT,80f)

    @Singleton
    @Provides
    fun ProvideFirstTimeToggle(sharedPref : SharedPreferences) = sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE,true)
}