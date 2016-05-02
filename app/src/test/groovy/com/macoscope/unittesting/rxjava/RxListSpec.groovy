package com.macoscope.unittesting.rxjava

import rx.Observable
import rx.functions.Action1
import rx.functions.Func0
import rx.schedulers.Schedulers
import spock.lang.Specification

//XXX storing results in list is not a recommended approach
public class RxListSpec extends Specification {

    def 'without schedulers no delay'() {
        given:
            List<String> result = new ArrayList<>()
        when:
            createObservableWithTextAndDelay("John", 0)
                    .subscribe(new AddToArrayAction<String>(result));
        then:
            result.contains("John")
    }

    def 'without schedulers with 1s delay'() {
        given:
            List<String> result = new ArrayList<>()
        when:
            createObservableWithTextAndDelay("John", 1000)
                    .subscribe(new AddToArrayAction<String>(result));
        then:
            result.contains("John")
    }

    def 'io scheduler no delay'() {
        given:
            List<String> result = new ArrayList<>()
        when:
            createObservableWithTextAndDelay("John", 0)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new AddToArrayAction<String>(result));
        then:
            sleep 10 //random failure without sleep due to thread switching
            result.contains("John")
    }

    def 'io scheduler with 1s delay'() {
        given:
            List<String> result = new ArrayList<>()
        when:

            createObservableWithTextAndDelay("John", 1000)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new AddToArrayAction<String>(result));
        then:
            sleep 1010 // have to wait till job ends
            result.contains("John")
    }

    def 'error io scheduler with 1s delay'() {
        given:
            List<String> result = new ArrayList<>()
            List<Throwable> resultError = new ArrayList<>()
        when:

            createObservableWithErrorAndDelay(new IllegalStateException(), 1000)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new AddToArrayAction<String>(result), new AddToArrayAction<Throwable>(resultError));
        then:
            sleep 1010 // have to wait till job ends
            result.isEmpty()
            resultError.first() instanceof IllegalStateException
    }

    private Observable createObservableWithTextAndDelay(String text, long delay) {
        Observable.defer(new Func0<Observable>() {
            @Override
            Observable call() {
                sleep(delay)
                return Observable.just(text)
            }
        })
    }

    private Observable createObservableWithErrorAndDelay(Throwable throwable, long delay) {
        Observable.defer(new Func0<Observable>() {
            @Override
            Observable call() {
                sleep(delay)
                return Observable.error(throwable)
            }
        })
    }

    static class AddToArrayAction<T> implements Action1<T> {

        List<T> result

        AddToArrayAction(List<T> result) {
            this.result = result
        }

        @Override
        void call(T s) {
            result.add(s)
        }
    }
}
