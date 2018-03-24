package com.cabbage.scaffold.dagger.activity

import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.ui.demo.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}