package com.rahul.gitsearch

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.rahul.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlin.properties.Delegates

/**
 * Created by rahul khurana on 15.05.2020.
 */

class App  : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build();
    }

    companion object {
        private val TAG = App::class.java.simpleName
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}