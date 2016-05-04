package com.macoscope.unittesting.rxjava

import rx.Observable
import rx.functions.Action1
import rx.observers.TestSubscriber
import rx.schedulers.TestScheduler
import spock.lang.Specification
import spock.util.concurrent.BlockingVariable
import spock.util.concurrent.PollingConditions

import static java.util.concurrent.TimeUnit.MILLISECONDS

public class BlogSpec extends Specification {

    def 'store result in variable'() {
        setup:
            List<String> result = new ArrayList<>()
        when:
            Observable.just("Macoscope", "Android Apps")
                    .subscribe(new Action1<String>() {
                @Override
                void call(String s) {
                    result.add(s)
                }
            });
        then:
            result.containsAll("Macoscope", "Android Apps")
    }

    def 'store result in blocking variable'() {
        setup:
            BlockingVariable<String> result = new BlockingVariable<>(2000, MILLISECONDS)
        when:
            Observable.just("Macoscope")
                    .delay(1000, MILLISECONDS)
                    .subscribe(new Action1<String>() {
                @Override
                void call(String s) {
                    result.set(s)
                }
            });
        then:
            result.get() == "Macoscope"
    }

    def 'store result with pooling condition'() {
        setup:
            PollingConditions conditions = new PollingConditions(timeout: 2, initialDelay: 0, factor: 0.1)
            List<String> result = new ArrayList<>()
        when:
            Observable.just("Macoscope", "Android Apps")
                    .delay(1000, MILLISECONDS)
                    .subscribe(new Action1<String>() {
                @Override
                void call(String s) {
                    result.add(s)
                }
            });

        then:
            conditions.eventually {
                result.containsAll("Macoscope", "Android Apps")
            }
    }

    def 'convert to synchronous code'() {
        when:
            List<String> result = Observable.just("Macoscope", "Android Apps")
                    .delay(1000, MILLISECONDS)
                    .toList().toBlocking().single();
        then:
            result.containsAll("Macoscope", "Android Apps")
    }

    def 'test subscriber'() {
        setup:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
        when:
            Observable.just("Macoscope", "Android Apps")
                    .delay(1000, MILLISECONDS)
                    .subscribe(testSubscriber);
        then:
            testSubscriber.awaitTerminalEvent()
            testSubscriber.assertValues("Macoscope", "Android Apps")
    }

    def 'test subscriber with assertion failure'() {
        setup:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
        when:
            Observable.error(new IllegalStateException("custom error message"))
                    .delay(1000, MILLISECONDS)
                    .subscribe(testSubscriber);
        then:
            testSubscriber.awaitTerminalEvent()
            testSubscriber.assertNoErrors() //Comment line and check test result
            testSubscriber.assertValues("Macoscope", "Android Apps")
    }

    def 'test subscriber with Spock assertion failure'() {
        setup:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
        when:
            Observable.error(new IllegalStateException("custom error message"))
                    .delay(1000, MILLISECONDS)
                    .subscribe(testSubscriber);
        then:
            testSubscriber.awaitTerminalEvent()
            //testSubscriber.getOnErrorEvents().isEmpty() //Uncomment line and check test result
            testSubscriber.getOnNextEvents().containsAll("Macoscope", "Android Apps")
    }

    def 'test subscriber with error'() {
        setup:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
        when:
            Observable.error(new IllegalStateException("custom error message"))
                    .delay(1000, MILLISECONDS)
                    .subscribe(testSubscriber);
        then:
            testSubscriber.awaitTerminalEvent()
            Exception exception = testSubscriber.getOnErrorEvents().first()
            exception instanceof IllegalStateException
            exception.getMessage() == "custom error message"
    }

    def 'test subscriber with test scheduler'() {
        setup:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
            TestScheduler testScheduler = new TestScheduler()
        when:
            Observable.just("Macoscope", "Android Apps")
                    .delay(1000, MILLISECONDS, testScheduler)
                    .subscribe(testSubscriber);
        then:
            testScheduler.advanceTimeBy(1000, MILLISECONDS)
            testSubscriber.assertValues("Macoscope", "Android Apps")
    }
}
