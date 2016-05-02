package com.macoscope.unittesting.rxjava.tools;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaPluginsHelper;
import rx.plugins.RxJavaSchedulersHook;

public class RxJavaHookRule implements TestRule {

    private RxJavaSchedulersHook rxSchedulersHook;
    private RxJavaObservableExecutionHook rxExecutionHook;

    public RxJavaHookRule() {
        this(RxJavaSchedulersHook.getDefaultInstance(), EmptyRxJavaObservableExecutionHook.getInstance());
    }

    public RxJavaHookRule(RxJavaSchedulersHook rxSchedulersHook,
                          RxJavaObservableExecutionHook rxExecutionHook) {
        this.rxSchedulersHook = rxSchedulersHook;
        this.rxExecutionHook = rxExecutionHook;
    }

    public RxJavaHookRule withRxSchedulersHook(RxJavaSchedulersHook rxSchedulersHook) {
        this.rxSchedulersHook = rxSchedulersHook;
        return this;
    }

    public RxJavaHookRule withRxExecutionHook(RxJavaObservableExecutionHook rxExecutionHook) {
        this.rxExecutionHook = rxExecutionHook;
        return this;
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("Registering RxJava Hooks\n");

                RxJavaPlugins rxJavaPlugins = RxJavaPlugins.getInstance();
                RxJavaPluginsHelper.reset(rxJavaPlugins);
                rxJavaPlugins.registerSchedulersHook(rxSchedulersHook);
                rxJavaPlugins.registerObservableExecutionHook(rxExecutionHook);

                base.evaluate();

                RxJavaPluginsHelper.reset(rxJavaPlugins);
            }
        };
    }
}