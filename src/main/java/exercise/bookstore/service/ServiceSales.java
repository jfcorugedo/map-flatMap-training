package exercise.bookstore.service;

import exercise.bookstore.bean.Sales;
import scala.concurrent.Future;
import scala.util.Either;

public interface ServiceSales<E>  {
	
	Future<Either<E, Sales>> getSales(int bookId);

}
