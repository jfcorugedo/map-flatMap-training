package com.ing.f2etraining.rxjava;

import com.ing.f2etraining.model.Person;
import io.reactivex.rxjava3.core.Single;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CombineSinglesTest {

    @Test
    public void combineSeveralSinglesWithFlatMap() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO: You have to use flatMap */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralSingleWithMap() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO: You have to use map */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    @Test
    public void combineSeveralSingleWithFlatMapAndMap() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));
        Single<Person> friendSingle = Single.just(new Person().setName("Luigi").setAge(28));

        //when
        /* TODO: Use both flatMap and map */
        Single<Integer> sumAges = null;

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
        /* TODO: Use class util.rxjava.SingleUtils to combine several Single objects */
        Single<Integer> sumAges = null;

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
        /* TODO: Use class util.rxjava.SingleUtils to combine three Single objects */
        Single<Integer> sumAges = null;

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
        /* TODO: Use class util.rxjava.SingleUtils to combine three Single objects */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(114);
    }

    @Test
    public void combineSeveralDependentSingleWithFlatMapAndMap() {
        //given
        Single<Person> meSingle = Single.just(new Person().setName("Juan").setAge(35));

        //Hint: Use method getFriend(String name) to get the other person

        //when
        /* TODO */
        Single<Integer> sumAges = null;

        //then
        Integer result = sumAges.blockingGet();
        assertThat(result).isEqualTo(63);
    }

    /**
     * This method simulates a call to another services that will return the friend of the given person
     * @param name Name of the person whose friend we want to know
     * @return Person object representing the friend of the input name
     */
    private Single<Person> getFriend(String name) {

        return Single.just(new Person().setName("Luigi").setAge(28));
    }
}
