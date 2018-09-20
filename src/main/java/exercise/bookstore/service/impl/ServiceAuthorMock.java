package exercise.bookstore.service.impl;

import errors.GenericError;
import errors.impl.MyError;
import exercise.bookstore.bean.Author;
import exercise.bookstore.service.ServiceAuthor;
import scala.concurrent.Future;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public class ServiceAuthorMock extends ServiceBase implements ServiceAuthor<GenericError> {

	@Override
	public Future<Either<GenericError, Author>> getAuthor(String id) {		
		
		return createFuture( () -> findAuthor( id ) );
		
		
	}

	public Either<GenericError, Author> findAuthor(String id) {

		if ( "author-book-2".equals( id )  ){

			return new Left<>( new MyError( "Author not found " + id ));

		}

		if ( "author-book-5".equals( id )  ){

			throw new RuntimeException( "Exception to get Author" );

		}

		final Author author = new Author( "Name of " + id ) ;

		return new Right<>( author );
	}
	
  
}
