package com.nishantpardamwar.composepagingwithroom.di

import android.content.Context
import androidx.room.Room
import com.nishantpardamwar.composepagingwithroom.db.AppDatabase
import com.nishantpardamwar.composepagingwithroom.repository.Repo
import com.nishantpardamwar.composepagingwithroom.repository.RepoImpl
import com.nishantpardamwar.composepagingwithroom.repository.datastore.LocalDataStore
import com.nishantpardamwar.composepagingwithroom.repository.datastore.LocalDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "appdb").fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideLocalDataStore(db: AppDatabase): LocalDataStore {
        return LocalDataStoreImpl(db)
    }

    @Singleton
    @Provides
    fun provideRepo(localDataStore: LocalDataStore): Repo {
        return RepoImpl(localDataStore)
    }
}