package exercise.bookstore.service.impl;

import errors.GenericError;
import errors.impl.MyError;
import exercise.bookstore.bean.Book;
import exercise.bookstore.service.ServiceBook;
import java.util.Arrays;
import java.util.List;
import scala.concurrent.Future;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public class ServiceBookMock extends ServiceBase implements ServiceBook<GenericError> {


    @Override
    public Future<Either<GenericError, Book>> getBook(final int bookId) {

        return createFuture(() -> findBook(bookId));
    }

    public Either<GenericError, Book> findBook(int bookId) {

        if (bookId < 0) {
            return new Left<>(new MyError("Book not found " + bookId));
        }

        if (bookId == 4) {

            throw new RuntimeException("Exception to get Book");

        }

        final String nameBook = "Book " + bookId;
        final String idAuthor = "author-book-" + bookId;

        final Long base = bookId * 1000L;
        final List<Long> chapters = Arrays.asList(
            base + 1L,
            base + 2L,
            base + 3L,
            base + 4L,
            base + 5L
        );

        final Book book = new Book(nameBook, idAuthor, chapters);

        return new Right<>(book);

    }

}
