package exercise.bookstore.service;

import exercise.bookstore.bean.Author;
import scala.concurrent.Future;
import scala.util.Either;

public interface ServiceAuthor<E> {
	
	Future<Either<E, Author>> getAuthor(String id);

}
