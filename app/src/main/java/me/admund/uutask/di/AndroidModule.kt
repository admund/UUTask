package me.admund.uutask.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.admund.uutask.ui.main.MainActivity

@Module
abstract class AndroidModule {
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun contributeActivityAndroidInjector(): MainActivity
}

@Module
class MainActivityModule
