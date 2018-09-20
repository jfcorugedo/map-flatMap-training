package exercise.bookstore.service;

import exercise.bookstore.bean.Book;
import scala.concurrent.Future;
import scala.util.Either;

public interface ServiceBook<E> {
	
	Future<Either<E, Book>> getBook(int bookId);

}
