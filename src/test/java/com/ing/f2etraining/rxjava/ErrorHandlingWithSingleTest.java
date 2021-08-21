package com.ing.f2etraining.rxjava;

import com.ing.f2etraining.model.Person;
import io.reactivex.rxjava3.core.Single;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Practice with some methods inside RxJava to deal with errors:
 * - onErrorReturn
 * - onErrorResumeWith
 * - onErrorResumeNext
 */
public class ErrorHandlingWithSingleTest {

    @Test
    public void combineSeveralSingleWithFailureResult() {
        //given
        Single<Person> meSingle = Single.error(new Exception("Unexpected error"));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void combineSeveralSingleWithAnotherFailureResult() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.error(new Exception("Another unexpected error"));

        //when
        /* TODO */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void combineSeveralSingleUsingFailureRecovery() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.error(new Exception("Another unexpected error"));

        //when
        /* TODO: Use failureRecovery function */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(-2);
    }

    @Test
    public void combineSeveralSingleUsingFallback() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.error(new Exception("Another unexpected error"));

        //when
        /* TODO: Use fallback function */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(-3);
    }

    private Single<Person> getFriend(String name) {

        return Single.just(new Person().setName("Luigi").setAge(28));
    }

    private Single<Integer> failureRecovery(Throwable error) {
        return Single.just(-2);
    }

    private Single<Integer> fallback() {
        return Single.just(-3);
    }
}
