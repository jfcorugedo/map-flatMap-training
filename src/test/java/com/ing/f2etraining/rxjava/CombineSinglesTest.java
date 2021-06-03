package com.ing.f2etraining.rxjava;

import com.ing.f2etraining.model.Person;
import io.reactivex.rxjava3.core.Single;
import org.junit.Test;

import static monad.rxjava.SingleUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CombineSinglesTest {

    @Test
    public void combineSeveralSinglesWithFlatMap() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        Single<Integer> sumAges = meSingle.flatMap(
                me -> friendSingle.flatMap(
                        friend -> Single.just(me.getAge() + friend.getAge())
                )
        );

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralFuturesWithMap() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        Single<Single<Integer>> sumAges = meSingle.map(
                me -> friendSingle.map(
                        friend -> me.getAge() + friend.getAge()
                )
        );

        //then
        Integer result = sumAges.blockingGet().blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralFuturesWithFlatMapAndMap() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        Single<Integer> sumAges = meSingle.flatMap(
                me -> friendSingle.map(
                        friend -> me.getAge() + friend.getAge()
                )
        );

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineTwoSingleWithSingleUtils() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        Single<Integer> sumAges = map2(meSingle, friendSingle, (me, friend) -> me.getAge() + friend.getAge());

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineThreeSingleWithSingleUtils() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));
        Single<Person> otherSingle = Single.just(new Person().setName("David").setAge(30));

        //when
        Single<Integer> sumAges = map3(
                meSingle,
                friendSingle,
                otherSingle,
                (me, frind, other) -> me.getAge() + frind.getAge() + other.getAge()
        );

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(93);
    }

    @Test
    public void combineFourSingleWithSingleUtils() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));
        Single<Person> otherSingle = Single.just(new Person().setName("David").setAge(30));
        Single<Person> anotherSingle = Single.just(new Person().setName("Nacho").setAge(21));

        //when
        Single<Integer> sumAges = map4(
                meSingle,
                friendSingle,
                otherSingle,
                anotherSingle,
                (me, friend, other, another) -> me.getAge() + friend.getAge() + other.getAge() + another.getAge()
        );

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(114);
    }

    @Test
    public void combineSeveralDependentFuturesWithFlatMapAndMap() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));

        //Hint: Use service Single<Person> getFriend(String name) to get the other person
        Single<Person> friendSingle = meSingle.flatMap(me -> getFriend(me.getName()));

        //when
        Single<Integer> sumAges = meSingle.flatMap(
                me -> friendSingle.map(
                        friend -> me.getAge() + friend.getAge()
                )
        );

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralFuturesWithFailureResult() {
        //given
        Single<Person> meSingle = Single.error(new Exception("Unexpected error"));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        Single<Integer> sumAges = meSingle.flatMap(
                me -> friendSingle.map(
                        friend -> me.getAge() + friend.getAge()
                )
        ).onErrorReturn(error -> -1);

        //then
        Integer sumAge = sumAges.blockingGet();
        assertThat(sumAge).isEqualTo(-1);
    }

    @Test
    public void combineSeveralFuturesWithAnotherFailureResult() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.error(new Exception("Another unexpected error"));

        //when
        Single<Integer> sumAges = meSingle.flatMap(
                me -> friendSingle.map(
                        friend -> me.getAge() + friend.getAge()
                )
        ).onErrorReturn(error -> -1);

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void combineSeveralFuturesUsingFailureRecovery() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.error(new Exception("Another unexpected error"));

        //when
        Single<Integer> sumAges = meSingle.flatMap(
                me -> friendSingle.map(
                        friend -> me.getAge() + friend.getAge()
                )
        ).onErrorResumeNext(this::failureRecovery);

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(-2);
    }

    @Test
    public void combineSeveralFuturesUsingFallback() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.error(new Exception("Another unexpected error"));

        //when
        Single<Integer> sumAges = meSingle.flatMap(
                me -> friendSingle.map(
                        friend -> me.getAge() + friend.getAge()
                )
        ).onErrorResumeWith(this.fallback());

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
