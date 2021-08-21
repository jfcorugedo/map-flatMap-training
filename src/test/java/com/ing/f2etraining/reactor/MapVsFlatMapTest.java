package com.ing.f2etraining.reactor;

import com.ing.f2etraining.model.Person;
import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MapVsFlatMapTest {

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
    public void useMapOnMono() throws Exception {
        //given
        Mono<Person> personMono = Mono.just(new Person().setName("Juan").setAge(35));

        //when
        //Hint: You have to use map
        /* TODO */
        Mono<String> nameS = null;

        //then
        String name = nameS.block();
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
        Mono<Person> personMono = Mono.just(new Person().setName("Juan").setAge(35));

        //when
        //Hint: You have to use flatMap
        /* TODO */
        Mono<String> nameS = null;

        //then
        String name = nameS.block();
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
    public void workWithMonoInsideAnotherMono() throws Exception{
        //given
        Mono<Person> personMono = Mono.just(new Person().setName("Juan").setAge(35));

        //when
        //Hint: you need to call getSalary method to get the salary of this person. First try with map
        /* TODO */
        Mono<Double> salaryS = null;

        //then
        Double salary = salaryS.block();
        assertThat(salary).isEqualTo(100000d);
    }

    /**
     * Utility method that simulates a call to another service that will give you the salary of this employee
     * @param name Name of the employee you want to know the salary
     * @return The salary of this employee
     */
    private Mono<Double> getSalary(String name) {

        return Mono.just(100000d);
    }
}
