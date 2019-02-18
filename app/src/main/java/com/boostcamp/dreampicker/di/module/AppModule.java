package com.boostcamp.dreampicker.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {
    @SuppressWarnings("unused")
    @Binds
    abstract Context bindContext(Application application);
}