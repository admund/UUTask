package me.admund.uutask.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import me.admund.uutask.UUApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class,
    RepositoriesModule::class,
    AndroidInjectionModule::class,
    AndroidModule::class
])
interface AppComponent {
    fun inject(app: UUApplication)
}
