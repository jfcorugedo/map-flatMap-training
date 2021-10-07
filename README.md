# map-flatMap-training
Some useful tests to understand map and flatMap operations over functors and monads

## Introduction

This project contains several test classes to practice with some of the most common reactive types.

Inside the folder `rxjava` you will find some exercise using [RxJava](https://github.com/ReactiveX/RxJava) reactive types.

Test classes inside `ractor` folder contains exercises to work with Reactive Types of [Project Reactor](https://projectreactor.io/).

You can also work with Scala futures using exercises inside `scala` folder.

There is another test class (`WorkWithFuturesUsingMonadTransormerTest`) to get used to the same operations but instead of using raw Scala futures, using the MonadTransformer object.

Finally, there is a practical exercise to use the monad transformer to combine several futures to get the final result: `SummaryServiceTest` and `SummaryServiceImpl`.

# How to start
To start solving the tests, just download master branch and execute tests (using your IDE or mvn test).

If you have trouble solving a test and you're about to give up, you have all the tests solved inside the branch `solved`.

Good luck!!
