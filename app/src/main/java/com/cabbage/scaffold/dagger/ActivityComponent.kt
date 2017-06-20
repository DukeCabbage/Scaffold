package com.cabbage.scaffold.dagger

import com.cabbage.scaffold.demo.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
           modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}