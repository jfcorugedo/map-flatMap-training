package com.ing.f2etraining.rxjava;

import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.util.Timeout;
import com.ing.f2etraining.model.Person;
import errors.GenericError;
import errors.impl.MyError;
import io.reactivex.rxjava3.core.Single;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class CombineSinglesTest {

    private static final Timeout TIMEOUT = new Timeout(Duration.create(5, "seconds"));

    @Test
    public void combineSeveralSinglesWithFlatMap() throws Exception {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralFuturesWithMap() throws Exception {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralFuturesWithFlatMapAndMap() throws Exception {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralDependentFuturesWithFlatMapAndMap() throws Exception {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));

        //Hint: Use service Single<Person> getFriend(String name) to get the other person

        //when
        /* TODO */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralFuturesWithFailureResult() throws Exception {
        //given
        Single<Person> meFuture = Single.error(new Exception("Unexpected error"));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO */
        Future<Integer> sumAgeF = null;

        //then
        Integer sumAge = (Integer) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge).isEqualTo(-1);
    }

    @Test
    public void combineSeveralFuturesWithAnotherFailureResult() throws Exception {
        //given
        Future<Person> meFuture = Futures.successful(new Person().setName("Juan").setAge(35));
        Future<Person> friendFuture = Futures.failed(new Exception("Another unexpected error"));

        //when
        /* TODO */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(-1);
    }

    private Single<Person> getFriend(String name) {

        return Single.just(new Person().setName("Luigi").setAge(28));
    }
}
