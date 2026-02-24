package edu.ucne.liamell_cruz_ap2_p2.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.liamell_cruz_ap2_p2.domain.repository.GastoRepository
import edu.ucne.liamell_cruz_ap2_p2.data.repository.GastoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindGastoRepository(
        impl: GastoRepositoryImpl
    ): GastoRepository
}
