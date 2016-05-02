package com.macoscope.unittesting.rxjava.tools;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaPluginsHelper;
import rx.plugins.RxJavaSchedulersHook;

public class RxJavaResetRule implements TestRule {


    private RxJavaSchedulersHook rxSchedulersHook;
    private RxJavaObservableExecutionHook rxExecutionHook;

    public RxJavaResetRule(RxJavaSchedulersHook rxSchedulersHook,
                           RxJavaObservableExecutionHook rxExecutionHook) {
        this.rxSchedulersHook = rxSchedulersHook;
        this.rxExecutionHook = rxExecutionHook;
    }

    public RxJavaResetRule() {
        this(RxJavaSchedulersHook.getDefaultInstance(), EmptyRxJavaObservableExecutionHook.getInstance());
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