package exercise.bookstore.business.impl;


import errors.GenericError;
import exercise.bookstore.bean.Summary;
import exercise.bookstore.business.SummaryService;
import exercise.bookstore.service.ServiceAuthor;
import exercise.bookstore.service.ServiceBook;
import exercise.bookstore.service.ServiceChapter;
import exercise.bookstore.service.ServiceSales;
import monad.MonadFutEither;
import scala.concurrent.Future;
import scala.util.Either;


public class SummaryServiceImpl implements SummaryService<GenericError> {

	private final ServiceBook<GenericError> srvBook;
	private final ServiceSales<GenericError> srvSales;
	private final ServiceChapter<GenericError> srvChapter;
	private final ServiceAuthor<GenericError> srvAuthor;
	
	private final MonadFutEither<GenericError> monadTransformer;
	
	
	public SummaryServiceImpl(ServiceBook<GenericError> srvBook,
			ServiceSales<GenericError> srvSales,
			ServiceChapter<GenericError> srvChapter,
			ServiceAuthor<GenericError> srvAuthor,
			MonadFutEither<GenericError> monadTransformer) {
		super();
		this.srvBook = srvBook;
		this.srvSales = srvSales;
		this.srvChapter = srvChapter;
		this.srvAuthor = srvAuthor;
		this.monadTransformer = monadTransformer;
	}

	@Override
	public Future<Either<GenericError, Summary>> getSummary(Integer bookId) {


		//TODO: Code the logic to build a Future<Either<GenericError, Summary>>

		return null;
	}
}
