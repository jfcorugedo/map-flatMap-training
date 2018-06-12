package com.ing.f2etraining;

import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.util.Timeout;
import com.ing.f2etraining.model.Person;
import errors.impl.MyError;
import monad.MonadFutEither;
import monad.impl.MonadFutEitherError;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import errors.GenericError;


import static monad.MonadFutEitherWrapper.wrap;
import static org.assertj.core.api.Assertions.assertThat;

public class WorkWithFuturesUsingMonadTransormerTest {

    private static final ExecutionContext EXECUTOR = ExecutionContexts.fromExecutor(Executors.newSingleThreadExecutor());
    private static final Timeout TIMEOUT = new Timeout(Duration.create(5, "seconds"));

    private static final MonadFutEither<GenericError> monadTransformer = new MonadFutEitherError(EXECUTOR);

    @Test
    public void mapWithMonadTranformer() throws Exception {
        //given
        Future<Either<GenericError, Person>> personFuture = Futures.successful(new Right<>(new Person().setName("Juan").setAge(35)));

        //when
        // REMEMBER: Future<String> nameF = personFuture.map( person -> person.right().get().getName() , EXECUTOR);

        Future<Either<GenericError,String>> nameF = null;

        //then
        Either<GenericError,String> name = (Either<GenericError,String>) Await.result(nameF, TIMEOUT.duration());
        assertThat(name.right().get()).isEqualTo("Juan");
    }

    @Test
    public void mapFailureWithMonadTranformer() throws Exception {
        //given
        Future<Either<GenericError, Person>> personFuture = Futures.failed(new ConnectException("Ahhhhhhhh!!!!"));

        //when
        Future<Either<GenericError, String>> nameF = null;

        //then
        Either<GenericError, String> name = (Either<GenericError, String>) Await.result(nameF, TIMEOUT.duration());
        assertThat(name.right().get()).isEqualTo("DEFAULT_VALUE");
    }

    @Test
    public void mapErrorWithMonadTranformer() throws Exception {
        //given
        Future<Either<GenericError, Person>> personFuture = Futures.successful(new Left<>(new MyError("User not found!")));

        //when
        Future<Either<GenericError, String>> nameF = null;


        //then
        Either<GenericError, String> name = (Either<GenericError, String>) Await.result(nameF, TIMEOUT.duration());
        assertThat(name.right().get()).isEqualTo("DEFAULT_VALUE");
    }

    @Test
    public void combineSeveralFuturesWithMonadTransformer() throws Exception {
        //given
        Future<Either<GenericError, Person>> meFuture = Futures.successful(new Right<>(new Person().setName("Juan").setAge(35)));
        Future<Either<GenericError, Person>> friendFuture = Futures.successful(new Right<>(new Person().setName("Miguel").setAge(28)));

        //when
        /* REMEMBER:
        Future<Integer> sumAgeF = meFuture.flatMap(
                me -> friendFuture.flatMap(
                        friend -> Futures.successful(me.getAge() + friend.getAge())

                        ,EXECUTOR)
                ,EXECUTOR);
         */
        Future<Either<GenericError, Integer>> sumAgeF = null;



        //then
        Either<GenericError, Integer> sumAge = (Either<GenericError, Integer>) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge.right().get()).isEqualTo(63);
    }

    @Test
    public void combineFailedFutureWithAnotherFutureWithMonadTransformer() throws Exception {
        //given
        Future<Either<GenericError, Person>> meFuture = Futures.failed(new ConnectException("Ahhhhhhhhhh!!!!!"));
        Future<Either<GenericError, Person>> friendFuture = Futures.successful(new Right<>(new Person().setName("Miguel").setAge(28)));

        //when
        Future<Either<GenericError, Integer>> sumAgeF = null;


        //then
        Either<GenericError, Integer> sumAge = (Either<GenericError, Integer>) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge.right().get()).isEqualTo(-1);
    }

    @Test
    public void combineSeveralDependentFuturesWithMonadTransformer() throws Exception {
        //given
        Future<Either<GenericError,Person>> meFuture = Futures.successful(new Right<>(new Person().setName("Juan").setAge(35)));

        //NOTE: Use service Future<Person> getFriend(String name)

        //when
        Future<Either<GenericError, Integer>> sumAgeF = null;


        //then
        Either<GenericError, Integer> sumAge = (Either<GenericError, Integer>) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge.right().get()).isEqualTo(63);
    }


    @Test
    public void combineFailedFutureWithMonadTransformer() throws Exception {
        //given
        Future<Either<GenericError,Person>> meFuture = Futures.successful(new Right<>(new Person().setName("Juan").setAge(35)));

        //NOTE: Use service Future<Person> getFailedFriend(String name)

        //when
        Future<Either<GenericError, Integer>> sumAgeF = null;


        //then
        Either<GenericError, Integer> sumAge = (Either<GenericError, Integer>) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge.right().get()).isEqualTo(-1);
    }

    @Test
    public void combineThreeFutureWithMonadTransformer() throws Exception {
        //given
        Future<Either<GenericError,Person>> meFuture = Futures.successful(new Right<>(new Person().setName("Juan").setAge(35)));
        Future<Either<GenericError,Person>> friend1Future = Futures.successful(new Right<>(new Person().setName("Miguel").setAge(28)));
        Future<Either<GenericError,Person>> friend2Future = Futures.successful(new Right<>(new Person().setName("Jose Luis").setAge(49)));

        //when
        Future<Either<GenericError, Integer>> sumAgeF = null;


        //then
        Either<GenericError, Integer> sumAge = (Either<GenericError, Integer>) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge.right().get()).isEqualTo(112);
    }

    @Test
    public void combineFourFutureWithMonadTransformer() throws Exception {
        //given
        Future<Either<GenericError,Person>> meFutureFuture = Futures.successful(new Right<>(new Person().setName("Juan").setAge(35)));
        Future<Either<GenericError,Person>> myBestFriendFuture = Futures.successful(new Right<>(new Person().setName("Miguel").setAge(28)));
        Future<Either<GenericError,Person>> anotherFriendFuture = Futures.successful(new Right<>(new Person().setName("Eva").setAge(49)));
        Future<Either<GenericError,Person>> friendFuture = Futures.successful(new Right<>(new Person().setName("Ivan").setAge(20)));

        //when
        Future<Either<GenericError, Integer>> sumAgeF = null;


        //then
        Either<GenericError, Integer> sumAge = (Either<GenericError, Integer>) Await.result(sumAgeF, TIMEOUT.duration());
        assertThat(sumAge.right().get()).isEqualTo(132);
    }

    @Test
    public void combineListOfFuturesWithMonadTransformer() throws Exception {
        //given
        List<Future<Either<GenericError,Person>>> friends = getFriends();

        //when
        Future<Either<GenericError, List<String>>> namesF = null;


        //then
        Either<GenericError, List<String>> names = (Either<GenericError, List<String>>) Await.result(namesF, TIMEOUT.duration());
        assertThat(names.right().get()).contains("Juan", "Miguel", "Eva");
    }

    private List<Future<Either<GenericError,Person>>> getFriends() {

        return Arrays.asList(
                Futures.successful(new Right<>(new Person().setName("Juan").setAge(35))),
                Futures.successful(new Right<>(new Person().setName("Miguel").setAge(28))),
                Futures.successful(new Right<>(new Person().setName("Eva").setAge(49)))
        );
    }


    private Future<Either<GenericError,Person>> getFriend(String name) {

        return Futures.successful(new Right<>(new Person().setName("Miguel").setAge(28)));
    }

    private Future<Either<GenericError,Person>> getFailedFriend(String name) {

        return Futures.failed(new ConnectException("Connection error!!!!!"));
    }
}
