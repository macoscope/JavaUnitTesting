package com.macoscope.unittesting.rxjava.tools;

import rx.plugins.DebugHook;
import rx.plugins.DebugNotification;
import rx.plugins.DebugNotificationListener;

import static rx.plugins.DebugNotification.quote;

public class RxExecutionHook extends DebugHook<String> {

    public RxExecutionHook() {
        super(new DebugLogListener());
    }

    static class DebugLogListener extends DebugNotificationListener<String> {

        @Override
        public Object onNext(DebugNotification n) {
            System.out.println("onNext " + getKindString(n) + ", value: " + quote(n.getValue()));
            return super.onNext(n);
        }

        @Override
        public String start(DebugNotification n) {
            String string = getKindString(n);
            System.out.println("start " + string);
            return string;
        }

        private String getKindString(DebugNotification n) {
            return n.getKind().toString();
        }

        @Override
        public void complete(String context) {
            System.out.println("complete " + context);
        }

        @Override
        public void error(String context, Throwable e) {
            System.out.println("error " + context + " " + e.toString());
        }
    }
}
