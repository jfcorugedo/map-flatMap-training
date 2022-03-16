package com.ing.f2etraining.reactor;

import com.ing.f2etraining.model.Person;
import org.junit.Test;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Practice with some methods inside RxJava to deal with errors:
 * - onErrorReturn
 * - onErrorResumeWith
 * - onErrorResumeNext
 */
public class ErrorHandlingWithMonoTest {

    @Test
    public void combineSeveralMonoWithFailureResult() {
        //given
        Mono<Person> meMono = Mono.error(new Exception("Unexpected error"));
        Mono<Person> friendMono = Mono.just(new Person().setName("Luigi").setAge(28));

        //when
        /* Take a look at the official documentation: https://projectreactor.io/docs/core/release/reference/#error.handling */
        Mono<Integer> sumAges = meMono.flatMap(
                me -> friendMono.map(
                        friend -> me.getAge() + friend.getAge()
                )
        ).onErrorReturn(-1);

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void combineSeveralMonoWithAnotherFailureResult() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.error(new Exception("Another unexpected error"));

        //when
        /* Take a look at the official documentation: https://projectreactor.io/docs/core/release/reference/#error.handling */
        Mono<Integer> sumAges = meMono.flatMap(
                me -> friendMono.map(
                        friend -> me.getAge() + friend.getAge()
                )
        ).onErrorReturn(-1);

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void combineSeveralMonoUsingFailureRecovery() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.error(new Exception("Another unexpected error"));

        //when
        /* Take a look at the official documentation: https://projectreactor.io/docs/core/release/reference/#error.handling */
        Mono<Integer> sumAges = meMono.flatMap(
                me -> friendMono.map(
                        friend -> me.getAge() + friend.getAge()
                )
        ).onErrorResume( error -> failureRecovery(error));

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(-2);
    }

    @Test
    public void combineSeveralMonoUsingFallback() {
        //given
        Mono<Person> meMono = Mono.just(new Person().setName("Juan").setAge(35));
        Mono<Person> friendMono = Mono.error(new Exception("Another unexpected error"));

        //when
        /* Take a look at the official documentation: https://projectreactor.io/docs/core/release/reference/#error.handling */
        Mono<Integer> sumAges = meMono.flatMap(
                me -> friendMono.map(
                        friend -> me.getAge() + friend.getAge()
                )
        ).onErrorResume(error -> Mono.just(computeDefaultValue()));

        //then
        Integer result = sumAges.block();
        assertThat(result).isEqualTo(-3);
    }

    private Mono<Integer> failureRecovery(Throwable error) {
        return Mono.just(-2);
    }

    private Integer computeDefaultValue() {
        return -3;
    }
}
