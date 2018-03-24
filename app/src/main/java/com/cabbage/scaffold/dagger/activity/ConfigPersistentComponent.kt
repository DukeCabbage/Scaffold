package com.cabbage.scaffold.dagger.activity

import com.cabbage.scaffold.dagger.ConfigPersistent
import com.cabbage.scaffold.dagger.app.AppComponent
import dagger.Component

@ConfigPersistent
@Component(dependencies = [(AppComponent::class)], modules = [ConfigPersistModule::class])
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}