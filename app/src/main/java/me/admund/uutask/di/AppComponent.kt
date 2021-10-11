package me.admund.uutask.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import me.admund.uutask.MainActivity
import me.admund.uutask.UUApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, AndroidInjectionModule::class, AndroidModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: UUApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: UUApplication)
}
