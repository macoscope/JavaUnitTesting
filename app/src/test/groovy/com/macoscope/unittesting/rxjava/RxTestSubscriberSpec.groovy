package com.macoscope.unittesting.rxjava

import rx.Observable
import rx.Scheduler
import rx.functions.Func0
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers
import rx.schedulers.TestScheduler
import spock.lang.Ignore
import spock.lang.Specification

import static java.util.concurrent.TimeUnit.MILLISECONDS

public class RxTestSubscriberSpec extends Specification {

    def 'get on next events'() {
        given:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
        when:
            createObservableWithTextAndDelay("John", 1000)
                    .subscribeOn(Schedulers.io())
                    .subscribe(testSubscriber);
        then:
            testSubscriber.awaitTerminalEvent()
            List<String> result = testSubscriber.getOnNextEvents()
            result.contains("John")
    }

    def 'assert value'() {
        given:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
        when:
            createObservableWithTextAndDelay("John", 1000)
                    .subscribeOn(Schedulers.io())
                    .subscribe(testSubscriber);
        then:
            testSubscriber.awaitTerminalEvent()
            testSubscriber.assertValue("John")
    }

    def 'assert error'() {
        given:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
        when:
            createObservableWithErrorAndDelay(new IllegalStateException(), 1000)
                    .subscribeOn(Schedulers.io())
                    .subscribe(testSubscriber);
        then:
            testSubscriber.awaitTerminalEvent()
            testSubscriber.assertError IllegalStateException
    }
    @Ignore("remove @Ignore and see results")
    def 'error - assertion message'() {
        given:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
        when:
            createObservableWithErrorAndDelay(new IllegalStateException(), 1000)
                    .subscribeOn(Schedulers.io())
                    .subscribe(testSubscriber);
        then:
            testSubscriber.awaitTerminalEvent()
//            testSubscriber.assertNoErrors()
            testSubscriber.assertValue("John")
    }

    def 'test scheduler'() {
        given:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
            TestScheduler testScheduler = Schedulers.test()
        when:
            createObservableWithTextAndDelay("John", 1000, testScheduler)
                    .subscribeOn(Schedulers.io())
                    .subscribe(testSubscriber);
        then:
            testScheduler.advanceTimeBy(1000, MILLISECONDS)
            testSubscriber.assertValue("John")
    }


    Observable createObservableWithTextAndDelay(String text, long delay) {
        createObservableWithTextAndDelay(text, delay, Schedulers.computation())
    }

    Observable createObservableWithTextAndDelay(String text, long delay, Scheduler scheduler) {
        Observable.defer(new Func0<Observable>() {
            @Override
            Observable call() {
                return Observable.just(text)
            }
        }).delay(delay, MILLISECONDS, scheduler)
    }

    Observable createObservableWithErrorAndDelay(Throwable throwable, long delay) {
        Observable.defer(new Func0<Observable>() {
            @Override
            Observable call() {
                sleep(delay)
                return Observable.error(throwable)
            }
        })
    }
}
