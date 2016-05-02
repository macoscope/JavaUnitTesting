package com.macoscope.unittesting.rxjava.tools;

import rx.Scheduler;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

public class RxImmediateSchedulersHook extends RxJavaSchedulersHook {
    @Override
    public Scheduler getComputationScheduler() {
        System.out.println("computation scheduler is replaced with immediate");
        return Schedulers.immediate();
    }

    @Override
    public Scheduler getIOScheduler() {
        System.out.println("io scheduler is replaced with immediate");
        return Schedulers.immediate();
    }

    @Override
    public Scheduler getNewThreadScheduler() {
        System.out.println("new thread scheduler is replaced with immediate");
        return Schedulers.immediate();
    }
}
