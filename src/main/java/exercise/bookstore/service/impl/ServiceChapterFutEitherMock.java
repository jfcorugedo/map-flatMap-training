package exercise.bookstore.service.impl;


import errors.GenericError;
import errors.impl.MyError;
import exercise.bookstore.bean.Chapter;
import exercise.bookstore.service.ServiceChapter;
import scala.concurrent.Future;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public class ServiceChapterFutEitherMock extends ServiceBase implements ServiceChapter<GenericError> {
	
	@Override
	public Future<Either<GenericError, Chapter>> getChapter(long idChapter) {
		
		return createFuture( () -> findChapter( idChapter ) );
		
	}

	public Either<GenericError, Chapter> findChapter(long idChapter) {

		if ( Long.valueOf( idChapter ).equals(3005L) ) {

			return new Left<>( new MyError( "Chapter not found " + idChapter ) );

		}

		final Chapter chapter =  new Chapter( "Title chapter - " + idChapter );

		return new Right<>( chapter );
	}
}
