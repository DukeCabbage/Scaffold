package com.cabbage.scaffold.dagger.activity

import com.cabbage.scaffold.ui.demo.MainContract
import com.cabbage.scaffold.ui.demo.MainPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class ConfigPersistModule {

    @Binds
    abstract fun provideMainPresenter(presenter: MainPresenter): MainContract.Presenter
}