package pl.spring.demo.mapper;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.LibraryTo;

public class LibraryMapper {

	public static LibraryTo map(LibraryEntity libraryEntity) {
		if (libraryEntity != null) {
			return new LibraryTo(libraryEntity.getId(), libraryEntity.getName(), map2BooksTo(libraryEntity.getBooks()));
		}
		return null;
	}

	public static LibraryEntity map(LibraryTo libraryTo) {
		if (libraryTo != null) {
			return new LibraryEntity(libraryTo.getId(), libraryTo.getName(), map2BooksEntity(libraryTo.getBooks()));
		}
		return null;
	}

	private static Set<BookEntity> map2BooksEntity(Collection<BookTo> books) {
		if (!books.isEmpty()) {
			return books.stream().map(bookTo -> BookMapper.map(bookTo)).collect(Collectors.toSet());
		}
		return null;
	}

	private static Set<BookTo> map2BooksTo(Collection<BookEntity> books) {
		if (!books.isEmpty()) {
			return books.stream().map(bookEntity -> BookMapper.map(bookEntity)).collect(Collectors.toSet());
		}
		return null;
	}
}
