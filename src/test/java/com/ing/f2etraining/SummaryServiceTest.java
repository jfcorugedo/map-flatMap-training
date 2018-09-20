package com.ing.f2etraining;

import akka.dispatch.ExecutionContexts;
import errors.GenericError;
import exercise.bookstore.bean.Author;
import exercise.bookstore.bean.Book;
import exercise.bookstore.bean.Chapter;
import exercise.bookstore.bean.Sales;
import exercise.bookstore.bean.Summary;
import exercise.bookstore.business.SummaryService;
import exercise.bookstore.business.impl.SummaryServiceImpl;
import exercise.bookstore.service.ServiceAuthor;
import exercise.bookstore.service.ServiceBook;
import exercise.bookstore.service.ServiceChapter;
import exercise.bookstore.service.ServiceSales;
import exercise.bookstore.service.impl.ServiceAuthorMock;
import exercise.bookstore.service.impl.ServiceBookMock;
import exercise.bookstore.service.impl.ServiceChapterFutEitherMock;
import exercise.bookstore.service.impl.ServiceSalesMock;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import monad.MonadFutEither;
import monad.impl.MonadFutEitherError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.util.Either;
import static org.assertj.core.api.Assertions.assertThat;

public class SummaryServiceTest {

  private SummaryService<GenericError> srvSummary;

  private final ServiceBook<GenericError> srvBook = new ServiceBookMock();
  private final ServiceSales<GenericError> srvSales = new ServiceSalesMock();
  private final ServiceChapter<GenericError> srvChapter = new ServiceChapterFutEitherMock();
  private final ServiceAuthor<GenericError> srvAuthor = new ServiceAuthorMock();
  private final MonadFutEither<GenericError> monadTransformer = 
      new MonadFutEitherError(
          ExecutionContexts.fromExecutor(
            Executors.newSingleThreadExecutor()
          )
      );
  
  private ServiceBookMock srvBookCheck = new ServiceBookMock();
  private ServiceChapterFutEitherMock srvChapterCheck = new ServiceChapterFutEitherMock();
  private ServiceSalesMock srvSalesCheck = new ServiceSalesMock();
  private ServiceAuthorMock srvAuthorCheck = new ServiceAuthorMock();
  
  @Before
  public void setUp() {
      
    srvSummary = new SummaryServiceImpl(
                                    srvBook, 
                                    srvSales,
                                    srvChapter, 
                                    srvAuthor, 
                                    monadTransformer);
  }
  
  @After
  public void tearDown() {
  }

  @Test
  public void happyPath() throws Exception {
    
    // Given
    final Integer bookId = 1;
    
    final Book expectedBook = srvBookCheck.findBook(bookId).right().get();

    final List<Chapter> expectedChapters = expectedBook.getChapters().stream()
        .map(s -> srvChapterCheck.findChapter(s).right().get())
        .collect(Collectors.toList());
    
    final Sales expectedSales = srvSalesCheck.findSales(bookId).right().get();
    
    final Author expectedAuthor = srvAuthorCheck.findAuthor( expectedBook.getIdAuthor() ).right().get();

    // When
    final Future<Either<GenericError, Summary>> summaryFu = srvSummary
        .getSummary(bookId);
    
    final Either<GenericError, Summary> res = Await.result(summaryFu, Duration.apply(100, TimeUnit.SECONDS));

    // Then
    final Summary summary = res.right().get();


    assertThat( summary.getBook() ).isEqualTo( expectedBook );
    assertThat( summary.getSales() ).isEqualTo( Optional.of(expectedSales) );
    assertThat( summary.getAuthor() ).isEqualTo( expectedAuthor );
    assertThat( summary.getChapter() ).isEqualTo( expectedChapters );

  }
  


  @Test
  public void happyPathWOSales() throws Exception {
    
    // Given
    final Integer bookId = 1000;
    
    final Book expectedBook = srvBookCheck.findBook(bookId).right().get();

    final List<Chapter> expectedChapters = expectedBook.getChapters().stream()
        .map(s -> srvChapterCheck.findChapter(s).right().get())
        .collect(Collectors.toList());
    
    final Author expectedAuthor = srvAuthorCheck.findAuthor( expectedBook.getIdAuthor() ).right().get();

    // When
    final Future<Either<GenericError, Summary>> summaryFu = srvSummary
        .getSummary(bookId);
    
    final Either<GenericError, Summary> res = Await.result(summaryFu, Duration.apply(100, TimeUnit.MILLISECONDS));

    // Then
    final Summary summary = res.right().get();

    assertThat( summary.getBook() ).isEqualTo( expectedBook );
    assertThat( summary.getSales().isPresent() ).isFalse();
    assertThat( summary.getAuthor() ).isEqualTo( expectedAuthor );
    assertThat( summary.getChapter() ).isEqualTo( expectedChapters );

  }
  
  @Test
  public void GenericErrorBook() throws Exception {
    
    testGenericErrorGeneric( -1 );
    
  }
  
  @Test
  public void GenericErrorAuthor() throws Exception {
    
    testGenericErrorGeneric( 2 );
        
    
  }
  
  @Test
  public void GenericErrorChapter() throws Exception {
    
    testGenericErrorGeneric( 3 );
        
    
  }
  
  @Test
  public void exceptionBook() throws Exception {
    
    testGenericErrorGeneric( 4 ); 
        
    
  }
  
  @Test
  public void exceptionAuthor() throws Exception {
    
    testGenericErrorGeneric( 5 ); 
        
    
  }
  
  private void testGenericErrorGeneric( final Integer bookId ) throws Exception {
    
    // When
    final Future<Either<GenericError, Summary>> summaryFu = srvSummary
        .getSummary(bookId);
    
    
    final Either<GenericError, Summary> res = Await.result(summaryFu, Duration.apply(100, TimeUnit.MILLISECONDS));
    
    // Then
    assertThat( res.left().get().getDescription() ).isEqualTo( "It is impossible to get book summary" );
    
    
  }

}
