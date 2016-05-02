package com.macoscope.unittesting.rxjava

import rx.Observable
import rx.functions.Func0
import rx.schedulers.Schedulers
import spock.lang.Specification
import spock.lang.Timeout

public class RxToBlockingSpec extends Specification {

    def 'io scheduler no delay'() {
        when:
            List<String> result = createObservableWithTextAndDelay("John", 0)
                    .subscribeOn(Schedulers.io())
                    .toList().toBlocking().single();
        then:
            //sleep 10
            result.contains("John")
    }


    def 'io scheduler with 1s delay'() {
        when:
            List<String> result = createObservableWithTextAndDelay("John", 1000)
                    .subscribeOn(Schedulers.io())
                    .toList().toBlocking().single();
        then:
            //sleep 1010
            result.contains("John")
    }

    @Timeout(10)
    def 'infinite io scheduler no delay'() {
        when:
            List<String> result = createObservableWithTextAndDelay("John", 0)
                    .repeat()
                    .subscribeOn(Schedulers.io())
                    .toList().toBlocking().single();
        then:
            //toBlocking().single() is waiting for sequence complete
            result.contains("John")
    }

    def 'error io scheduler with 1s delay'() {
        when:
            createObservableWithErrorAndDelay(new IllegalStateException(), 1000)
                    .subscribeOn(Schedulers.io())
                    .toList().toBlocking().single();
        then:
            thrown IllegalStateException
    }

    Observable<String> createObservableWithTextAndDelay(String text, long delay) {
        Observable.defer(new Func0<Observable>() {
            @Override
            Observable call() {
                sleep(delay)
                return Observable.just(text, text)
            }
        })
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
