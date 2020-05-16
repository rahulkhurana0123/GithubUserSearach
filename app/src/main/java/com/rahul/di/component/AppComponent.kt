package com.rahul.di.component

import android.app.Application
import com.android.daggerandroid2.ActivityBuilderModule
import com.android.daggerandroid2.MainModule
import com.rahul.gitsearch.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Rahul khurana on 2020-05-16.
 */

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, ActivityBuilderModule::class,MainModule::class))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application( application: Application) : Builder

        fun build() : AppComponent

    }

}