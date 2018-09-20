package exercise.bookstore.service.impl;

import errors.GenericError;
import errors.impl.MyError;
import exercise.bookstore.bean.Sales;
import exercise.bookstore.service.ServiceSales;
import scala.concurrent.Future;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public class ServiceSalesMock extends ServiceBase implements ServiceSales<GenericError> {


	@Override
	public Future<Either<GenericError, Sales>> getSales(int bookId) {
		
		return createFuture( () -> findSales( bookId ) );
	}

	public Either<GenericError, Sales> findSales( int bookId ) {

		if ( bookId > 999 ) {

			return new Left<>(new MyError("Sales not found " + bookId) );
		}

		final int salesId = 5000*bookId;

		final String printed = "Book("+ bookId +") printed:  " + salesId ;
		final String numberSales = "Book("+ bookId +") sold:  " + salesId ;

		final Sales sales = new Sales(printed , numberSales );

		return new Right<>( sales );

	}
}
