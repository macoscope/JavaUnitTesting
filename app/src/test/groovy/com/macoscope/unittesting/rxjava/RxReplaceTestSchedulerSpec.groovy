package com.macoscope.unittesting.rxjava

import com.macoscope.unittesting.rxjava.tools.RxJavaHookRule
import com.macoscope.unittesting.rxjava.tools.RxTestSchedulersHook
import org.junit.ClassRule
import rx.Observable
import rx.functions.Func0
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers
import rx.schedulers.TestScheduler
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

import static java.util.concurrent.TimeUnit.MILLISECONDS

@Ignore("replacing schedulers with test scheduler doesn't work")
public class RxReplaceTestSchedulerSpec extends Specification {

    @Shared
    RxTestSchedulersHook testSchedulersHook = new RxTestSchedulersHook()

    @ClassRule
    @Shared
    RxJavaHookRule rxJavaResetRule = new RxJavaHookRule().withRxSchedulersHook(testSchedulersHook)


    def 'test scheduler'() {
        given:
            TestSubscriber<String> testSubscriber = new TestSubscriber<>()
            TestScheduler testScheduler = testSchedulersHook.getTestScheduler()
        when:
            createObservableWithTextAndDelay("John", 1000)
                    .subscribeOn(Schedulers.io())
                    .subscribe(testSubscriber);
        then:
            testSubscriber.assertNoErrors()
            testScheduler.advanceTimeBy(1000, MILLISECONDS)
            testSubscriber.assertValue("John")
    }

    private Observable createObservableWithTextAndDelay(String text, long delay) {
        Observable.defer(new Func0<Observable>() {
            @Override
            Observable call() {
                return Observable.just(text)
            }
        }).delay(delay, MILLISECONDS)
    }
}
