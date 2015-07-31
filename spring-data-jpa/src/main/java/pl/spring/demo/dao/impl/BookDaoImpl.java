package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookMapper;
import pl.spring.demo.to.BookTo;

@Service
public class BookDaoImpl implements BookDao {

    private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

    @Autowired
    private Sequence sequence;

    public BookDaoImpl() {
    	addTestBooks();
    }

    @Override
    public List<BookEntity> findAll() {
        return new ArrayList<>(ALL_BOOKS);
    }

    @Override
    public List<BookEntity> findBookByTitle(String title) {
        return null;
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
        return null;
    }

    @Override
    @NullableId
    public BookEntity save(BookEntity book) {
    	if (book.getId() == null) {
    		book.setId(sequence.nextValue(ALL_BOOKS));
    	}
        ALL_BOOKS.add(book);
        return book;
    }
    
    public void setSequence(Sequence sequence) {
    	this.sequence = sequence;
    }

    public Sequence getSequence() {
    	return this.sequence;
    }
    
    private void addTestBooks() {
    	BookMapper bookMapper = new BookMapper();
        ALL_BOOKS.add(bookMapper.toBookEntity(new BookTo(1L, "Romeo i Julia", "Wiliam Szekspir")));
        ALL_BOOKS.add(bookMapper.toBookEntity(new BookTo(2L, "Opium w rosole", "Hanna Ożogowska")));
        ALL_BOOKS.add(bookMapper.toBookEntity(new BookTo(3L, "Przygody Odyseusza", "Jan Parandowski")));
        ALL_BOOKS.add(bookMapper.toBookEntity(new BookTo(4L, "Awantura w Niekłaju", "Edmund Niziurski")));
        ALL_BOOKS.add(bookMapper.toBookEntity(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki")));
        ALL_BOOKS.add(bookMapper.toBookEntity(new BookTo(6L, "Zemsta", "Aleksander Fredro")));
    }
}
