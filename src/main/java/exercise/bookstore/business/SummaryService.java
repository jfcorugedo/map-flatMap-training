package exercise.bookstore.business;


import exercise.bookstore.bean.Summary;
import scala.concurrent.Future;
import scala.util.Either;

public interface SummaryService<E> {
	
	Future<Either<E, Summary>> getSummary(Integer bookId);

}
