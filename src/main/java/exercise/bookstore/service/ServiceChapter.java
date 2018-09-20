package exercise.bookstore.service;

import exercise.bookstore.bean.Chapter;
import scala.concurrent.Future;
import scala.util.Either;

public interface ServiceChapter<E> {
	
	Future<Either<E, Chapter>> getChapter(long idChapter);

}
