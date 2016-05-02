package com.macoscope.unittesting.rxjava

import com.macoscope.unittesting.rxjava.tools.RxImmediateSchedulersHook
import com.macoscope.unittesting.rxjava.tools.RxJavaHookRule
import org.junit.ClassRule
import rx.Observable
import rx.functions.Action1
import rx.functions.Func0
import rx.schedulers.Schedulers
import spock.lang.Shared
import spock.lang.Specification

public class RxReplaceImmediateSchedulerSpec extends Specification {

    @ClassRule
    @Shared
    RxJavaHookRule rxJavaResetRule = new RxJavaHookRule().withRxSchedulersHook(new RxImmediateSchedulersHook())

//    @Ignore("remove @Ignore and see results")
    def 'io scheduler with 1s delay'() {
        given:
            List<String> result = new ArrayList<>()
        when:
            createObservableWithTextAndDelay("John", 1000)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new AddToArrayAction<String>(result));
        then:
            //sleep 1010 //io scheduler is replaced with immediate, no need to wait for events
            result.contains("John")
    }

    Observable createObservableWithTextAndDelay(String text, long delay) {
        Observable.defer(new Func0<Observable>() {
            @Override
            Observable call() {
                sleep(1000)
                return Observable.just(text)
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
