package com.android.daggerandroid2

import com.rahul.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Created by Rahul khurana on 2020-05-03.
 */

@Module
abstract class ActivityBuilderModule{


    @ContributesAndroidInjector
   abstract fun contributeAndroidActivity () : MainActivity

}