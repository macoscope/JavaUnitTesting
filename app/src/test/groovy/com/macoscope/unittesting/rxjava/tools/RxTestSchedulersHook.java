package com.macoscope.unittesting.rxjava.tools;

import rx.Scheduler;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

public class RxTestSchedulersHook extends RxJavaSchedulersHook {

    TestScheduler scheduler;

    @Override
    public Scheduler getComputationScheduler() {
        System.out.println("computation scheduler is replaced with immediate");
        return createTestScheduler();
    }

    @Override
    public Scheduler getIOScheduler() {
        System.out.println("io scheduler is replaced with immediate");
        return createTestScheduler();
    }

    @Override
    public Scheduler getNewThreadScheduler() {
        System.out.println("new thread scheduler is replaced with immediate");
        return createTestScheduler();
    }

    public TestScheduler createTestScheduler(){
        if(scheduler == null) {
            scheduler = Schedulers.test();
        }
        return scheduler;
    }

    /**
     * Call it after RxJavaPlugins.registerSchedulersHook
     */
    public TestScheduler getTestScheduler() {
        return createTestScheduler();
    }
}
