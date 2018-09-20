package exercise.bookstore.service.impl;

import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import java.util.concurrent.Callable;
import scala.concurrent.Future;
import scala.util.Either;

public abstract class ServiceBase {

	public ServiceBase() {
		super();
	}

	protected <E, T> Future<Either<E, T>> createFuture(Callable <Either<E, T>> cont ) {
		return Futures.future(
				cont::call,
				ExecutionContexts.global() 
			);
	}
}