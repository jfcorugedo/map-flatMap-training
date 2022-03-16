package com.ing.f2etraining.reactor;

import com.ing.f2etraining.model.Person;
import org.junit.Test;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

public class CombineMonoTest {

    @Test
    public void combineSeveralMonoWithFlatMap() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO: You have to use flatMap */
        Mono<Integer> sumAges = null;

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralMonoWithMap() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO: You have to use Map */
        Mono<Mono<Integer>> sumAges = null;

        //then
        Integer result = sumAges.block().block();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralMonoWithFlatMapAndMap() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO: Use both flatMap and map */
        Mono<Integer> sumAges = null;

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineTwoMonoWithMonoUtils() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO: You can use util.reactor.MonoUtils to combine several Mono instances */
        Mono<Integer> sumAges = null;

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineThreeMonoWithSingleUtils() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));
        Mono<Person> otherMono = Mono.just(new Person().setName("David").setAge(30));

        //when
        /* TODO: Use class util.reactor.MonoUtils to combine several Mono objects */
        Mono<Integer> sumAges = null;

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(93);
    }

    @Test
    public void combineFourSingleWithSingleUtils() {
        //given
        Mono<Person> meSingle = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));
        Mono<Person> otherMono = Mono.just(new Person().setName("David").setAge(30));
        Mono<Person> anotherMono = Mono.just(new Person().setName("Nacho").setAge(21));

        //when
        /* TODO: Use class util.reactor.MonoUtils to combine several Mono objects */
        Mono<Integer> sumAges = null;

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(114);
    }

    @Test
    public void combineSeveralDependentFuturesWithFlatMapAndMap() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));

        //Hint: Use service getFriend(String name) to get the other person

        //when
        /* TODO */
        Mono<Integer> sumAges = null;

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(63);
    }

    /**
     * This method simulates a call to another services that will return the friend of the given person
     * @param name Name of the person whose friend we want to know
     * @return Person object representing the friend of the input name
     */
    private Mono<Person> getFriend(String name) {

        return Mono.just(new Person().setName("Luigi").setAge(28));
    }
}
