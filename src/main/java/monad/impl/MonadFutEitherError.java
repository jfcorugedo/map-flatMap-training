package monad.impl;


import java.util.function.Function;

import akka.dispatch.Futures;
import errors.impl.MyError;
import monad.MonadFutEither;
import monad.MonadFutEitherWrapper;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.util.Either;

import scala.util.Left;
import scala.util.Right;
import util.Java8;
import errors.GenericError;
import static monad.MonadFutEitherWrapper.wrap;

public class MonadFutEitherError implements MonadFutEither<GenericError> {
	
	private final ExecutionContext ec;
	
	
	public MonadFutEitherError(ExecutionContext ec) {
		super();
		this.ec = ec;
	}

	@Override
	public <T> Future<Either<GenericError, T>> pure(T value) {
		
		return Futures.successful(new Right<>(value));
	}

	@Override
	public <A, T> Future<Either<GenericError, T>> flatMap(
			Future<Either<GenericError, A>> from,
			Function<A, Future<Either<GenericError, T>>> f) {
		
		return from
				.flatMap( a -> a.isRight() ? f.apply(a.right().get()) : raiseError(a.left().get()), ec)
				.recoverWith(Java8.recoverF(t -> raiseError(new MyError(t.getMessage()))), ec);
	}

	@Override
	public <T> Future<Either<GenericError, T>> raiseError(GenericError error) {
		
		return Futures.successful(new Left<>(error));
	}

	@Override
	public <T> Future<Either<GenericError, T>> handleErrorWith(
			Future<Either<GenericError, T>> from,
			Function<GenericError, Future<Either<GenericError, T>>> f) {
		
		return from
				.flatMap(t -> t.isRight() ? pure(t.right().get()) : f.apply(t.left().get()) , ec)
				.recoverWith(Java8.recoverF( t-> raiseError(new MyError(t.getMessage()))), ec);
	}

	@Override
    public <T> MonadFutEitherWrapper<GenericError, T> dslFrom(Future<Either<GenericError, T>> future) {
	    return wrap(future, this);
    }
}
