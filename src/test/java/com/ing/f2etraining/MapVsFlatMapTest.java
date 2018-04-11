package com.ing.f2etraining;

import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.util.Timeout;
import com.ing.f2etraining.model.Person;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MapVsFlatMapTest {

    private static final ExecutionContext EXECUTOR = ExecutionContexts.fromExecutor(Executors.newSingleThreadExecutor());
    private static final Timeout TIMEOUT = new Timeout(Duration.create(5, "seconds"));

    @Test
    public void whatIsAMapOnLists() {
        //given
        List<Person> people = Arrays.asList(
                new Person().setName("Juan").setAge(35),
                new Person().setName("Miguel").setAge(34),
                new Person().setName("David").setAge(28)
        );

        //when
        /* TODO */
        List<String> names = null;

        //then
        assertThat(names).containsOnly("Juan", "David", "Miguel");
    }

    @Test
    public void whatIsAMapOnOptionals() {
        //given
        Optional<Person> personOptional = Optional.of(new Person().setName("Juan").setAge(35));

        //when
        /* TODO */
        Optional<String> nameOptional = null;

        //then
        assertThat(nameOptional).contains("Juan");
    }

    @Test
    public void whatIsAFlatMapOnLists() {
        //given
        List<Person> people = Arrays.asList(
                new Person().setName("Juan").setAge(35),
                new Person().setName("Miguel").setAge(34),
                new Person().setName("David").setAge(28)
        );

        //when
        /* TODO */
        List<String> names = null;

        //then
        assertThat(names).containsOnly("Juan", "David", "Miguel");
    }

    @Test
    public void whatIsAFlatMapOnOptionals() {
        //given
        Optional<Person> personOptional = Optional.of(new Person().setName("Juan").setAge(35));

        //when
        /* TODO */
        Optional<String> nameOptional = null;

        //then
        assertThat(nameOptional).contains("Juan");
    }

    @Test
    public void whatIsAMapOnFutures() throws Exception {
        //given
        Future<Person> personFuture = Futures.successful(new Person().setName("Juan").setAge(35));

        //when
        /* TODO */
        Future<String> nameF = null;

        //then
        String name = (String) Await.result(nameF, TIMEOUT.duration());
        assertThat(name).isEqualTo("Juan");
    }

    @Test
    public void whatIsAFlatMapOnFutures() throws Exception {
        //given
        Future<Person> personFuture = Futures.successful(new Person().setName("Juan").setAge(35));

        //when
        /* TODO */
        Future<String> nameF = null;

        //then
        String name = (String) Await.result(nameF, TIMEOUT.duration());
        assertThat(name).isEqualTo("Juan");
    }

    @Test
    public void combineSeveralFuturesWithFlatMap() throws Exception {
        //given
        Future<Person> meFuture = Futures.successful(new Person().setName("Juan").setAge(35));
        Future<Person> friendFuture = Futures.successful(new Person().setName("Miguel").setAge(28));

        //when
        /* TODO */
        Future<Integer> sumAgeF = null;

        //then
        Integer sumAge = (Integer) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge).isEqualTo(63);
    }

    @Test
    public void combineSeveralFuturesWithMap() throws Exception {
        //given
        Future<Person> meFuture = Futures.successful(new Person().setName("Juan").setAge(35));
        Future<Person> friendFuture = Futures.successful(new Person().setName("Miguel").setAge(28));

        //when
        /* TODO */
        Future<Future<Integer>> sumAgeFF = null;

        //then
        Future<Integer> sumAgeF = Await.result(sumAgeFF, TIMEOUT.duration());
        Integer sumAge = (Integer) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge).isEqualTo(63);
    }

    @Test
    public void combineSeveralFuturesWithFlatMapAndMap() throws Exception {
        //given
        Future<Person> meFuture = Futures.successful(new Person().setName("Juan").setAge(35));
        Future<Person> friendFuture = Futures.successful(new Person().setName("Miguel").setAge(28));

        //when
        /* TODO */
        Future<Integer> sumAgeF = null;

        //then
        Integer sumAge = (Integer) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge).isEqualTo(63);
    }

    @Test
    public void combineSeveralDependentFuturesWithFlatMapAndMap() throws Exception {
        //given
        Future<Person> meFuture = Futures.successful(new Person().setName("Juan").setAge(35));
        //Use service Future<Person> getFriend(String name)

        //when
        /* TODO */
        Future<Integer> sumAgeF = null;

        //then
        Integer sumAge = (Integer) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge).isEqualTo(63);
    }


    private Future<Person> getFriend(String name) {

        return Futures.successful(new Person().setName("Miguel").setAge(28));
    }
}
