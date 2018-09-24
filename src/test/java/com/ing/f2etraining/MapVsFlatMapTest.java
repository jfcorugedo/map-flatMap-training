package com.ing.f2etraining;

import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.util.Timeout;
import com.ing.f2etraining.model.Person;
import errors.impl.MyError;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;
import util.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import errors.GenericError;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MapVsFlatMapTest {

    private static final ExecutionContext EXECUTOR = ExecutionContexts.fromExecutor(Executors.newSingleThreadExecutor());
    private static final Timeout TIMEOUT = new Timeout(Duration.create(5, "seconds"));

    @Test
    public void useMapOnList() {
        //given
        List<Person> people = Arrays.asList(
                new Person().setName("Juan").setAge(35),
                new Person().setName("Miguel").setAge(34),
                new Person().setName("David").setAge(28)
        );

        //when
        //Hint: You have to use map
        /* TODO */
        List<String> names = null;

        //then
        assertThat(names).containsOnly("Juan", "David", "Miguel");
    }

    @Test
    public void useMapOnOptional() {
        //given
        Optional<Person> personOptional = Optional.of(new Person().setName("Juan").setAge(35));

        //when
        //Hint: You have to use map
        /* TODO */
        Optional<String> nameOptional = null;

        //then
        assertThat(nameOptional).contains("Juan");
    }

    @Test
    public void useMapOnFutures() throws Exception {
        //given
        Future<Person> personFuture = Futures.successful(new Person().setName("Juan").setAge(35));

        //when
        //Hint: You have to use map
        /* TODO */
        Future<String> nameF = null;

        //then
        String name = (String) Await.result(nameF, TIMEOUT.duration());
        assertThat(name).isEqualTo("Juan");
    }

    @Test
    public void useFlatMapOnList() {
        //given
        List<Person> people = Arrays.asList(
                new Person().setName("Juan").setAge(35),
                new Person().setName("Miguel").setAge(34),
                new Person().setName("David").setAge(28)
        );

        //when
        //Hint: You have to use flatMap
        /* TODO */
        List<String> names = null;

        //then
        assertThat(names).containsOnly("Juan", "David", "Miguel");
    }

    @Test
    public void useFlatMapOnOptional() {
        //given
        Optional<Person> personOptional = Optional.of(new Person().setName("Juan").setAge(35));

        //when
        //Hint: You have to use flatMap
        /* TODO */
        Optional<String> nameOptional = null;

        //then
        assertThat(nameOptional).contains("Juan");
    }

    @Test
    public void useFlatMapOnFuture() throws Exception {
        //given
        Future<Person> personFuture = Futures.successful(new Person().setName("Juan").setAge(35));

        //when
        //Hint: You have to use flatMap
        /* TODO */
        Future<String> nameF = null;

        //then
        String name = (String) Await.result(nameF, TIMEOUT.duration());
        assertThat(name).isEqualTo("Juan");
    }

    @Test
    public void workWithListsInsideList() {
        //given
        List<Person> people = Arrays.asList(
            new Person().setName("Juan").setAge(35).addSkill("Java").addSkill("Go"),
            new Person().setName("Miguel").setAge(34).addSkill("C++").addSkill("Python"),
            new Person().setName("David").setAge(28).addSkill("Scala")
        );

        //when
        //Hint: Try to use map first
        /* TODO */
        List<String> skills = null;

        //then
        assertThat(skills).containsOnly("Java", "Go", "C++", "Python", "Scala");
    }

    @Test
    public void workWithFutureInsideAotherFuture() throws Exception{
        //given
        Future<Person> personFuture = Futures.successful(new Person().setName("Juan").setAge(35));

        //when
        //Hint: you need to use getSalary method to get the salary of this person. First try with map
        /* TODO */
        Future<Double> salaryF = null;

        //then
        Double salary = Await.result(salaryF, TIMEOUT.duration());
        assertThat(salary).isEqualTo(100000d);
    }

    private Future<Double> getSalary(String name) {

        return Futures.successful(100000d);
    }
}
