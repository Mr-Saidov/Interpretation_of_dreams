package java.com.interpretationofdreams.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.com.interpretationofdreams.data.local.AppDatabase
import java.com.interpretationofdreams.repository.Repository
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalAppDataSource

    @LocalAppDataSource
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        AppDatabase.dbName
    ).createFromAsset(AppDatabase.dbName).fallbackToDestructiveMigration().build()
}

@Module
@InstallIn(ApplicationComponent::class)
object AppRepositoryModule {

    @Singleton
    @Provides
    fun provideAppRepository(@AppModule.LocalAppDataSource localTasksDataSource: AppDatabase) =
        Repository(localTasksDataSource)
}
