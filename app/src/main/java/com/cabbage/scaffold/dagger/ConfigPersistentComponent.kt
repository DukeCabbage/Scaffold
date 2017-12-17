package com.cabbage.scaffold.dagger

import dagger.Component

@ConfigPersistent
@Component(dependencies = [(AppComponent::class)])
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}