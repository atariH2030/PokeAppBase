package br.com.rickson.pokeappbase

import android.app.Application
import br.com.rickson.pokeappbase.di.dataModule
import br.com.rickson.pokeappbase.di.domainModule
import br.com.rickson.pokeappbase.di.networkModule
import br.com.rickson.pokeappbase.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokemonApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PokemonApplication)
            // Agora o Koin conhece todas as camadas do nosso app!
            modules(
                networkModule,
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}