package com.macoscope.unittesting.rxjava.tools;

import rx.plugins.RxJavaObservableExecutionHook;


/**
 * Default no-op implementation of {@link RxJavaObservableExecutionHook}, RxJavaObservableExecutionHookDefault is
 * package private
 */
public class EmptyRxJavaObservableExecutionHook extends RxJavaObservableExecutionHook {

    private static EmptyRxJavaObservableExecutionHook INSTANCE = new EmptyRxJavaObservableExecutionHook();

    public static RxJavaObservableExecutionHook getInstance() {
        return INSTANCE;
    }

}
