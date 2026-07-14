package com.yallahmasyaf.app.di

import com.yallahmasyaf.app.data.datasource.MockCoastalDataSource
import com.yallahmasyaf.app.data.repository.CoastalRepositoryImpl
import com.yallahmasyaf.app.domain.repository.CoastalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMockCoastalDataSource(): MockCoastalDataSource {
        return MockCoastalDataSource()
    }

    @Provides
    @Singleton
    fun provideCoastalRepository(
        dataSource: MockCoastalDataSource
    ): CoastalRepository {
        return CoastalRepositoryImpl(dataSource)
    }
}
