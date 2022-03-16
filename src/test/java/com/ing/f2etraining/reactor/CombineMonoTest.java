package com.ing.f2etraining.reactor;

import com.ing.f2etraining.model.Person;
import org.junit.Test;
import reactor.core.publisher.Mono;
import util.reactor.MonoUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class CombineMonoTest {

    @Test
    public void combineSeveralMonoWithFlatMap() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));

        //when
        Mono<Integer> sumAges = meMono.flatMap(
                me -> friendMono.flatMap( //Here we are using flatMap on purpose, just to show what happens when you use the wrong operator
                        friend -> Mono.just(me.getAge() + friend.getAge())
                )
        );

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
        Mono<Mono<Integer>> sumAges = meMono.map( //Here we are using map on purpose, just to show what happens when you use the wrong operator
                me -> friendMono.map(
                        friend -> me.getAge() + friend.getAge()
                )
        );

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
        Mono<Integer> sumAges = meMono.flatMap(
                me -> friendMono.map(
                        friend -> me.getAge() + friend.getAge()
                )
        );

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
        Mono<Integer> sumAges = MonoUtils.map2(meMono, friendMono, (me, friend) -> me.getAge() + friend.getAge());

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineThreeMonoWithMonoUtils() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));
        Mono<Person> otherMono = Mono.just(new Person().setName("David").setAge(30));

        //when
        Mono<Integer> sumAges = MonoUtils.map3(
                meMono,
                friendMono,
                otherMono,
                (me, friend, other) -> me.getAge() + friend.getAge() + other.getAge()
        );

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(93);
    }

    @Test
    public void combineFourSingleWithMonoUtils() {
        //given
        Mono<Person> meSingle = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));
        Mono<Person> otherMono = Mono.just(new Person().setName("David").setAge(30));
        Mono<Person> anotherMono = Mono.just(new Person().setName("Nacho").setAge(21));

        //when
        Mono<Integer> sumAges = MonoUtils.map4(
                meSingle,
                friendMono,
                otherMono,
                anotherMono,
                (me, friend, other, another) -> me.getAge() + friend.getAge() + other.getAge() + another.getAge()
        );

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
        Mono<Person> friendMono = meMono.flatMap(me -> getFriend(me.getName()));
        Mono<Integer> sumAges = meMono.flatMap( me -> friendMono.map( friend -> me.getAge() + friend.getAge() ));

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
