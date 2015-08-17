package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.dao.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QAuthorEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.to.SearchCriteria;

public class BookRepositoryImpl implements BookSearchCriteria {

	@PersistenceContext(name = "hsql")
	private EntityManager entityManager;

	@Override
	public List<BookEntity> findBySearchCriteria(SearchCriteria sc) {
		JPAQuery query = new JPAQuery(entityManager);
		QBookEntity bookEntity = QBookEntity.bookEntity;
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (sc.getTitle() != null) {
			booleanBuilder.and(bookEntity.title.startsWithIgnoreCase(sc.getTitle()));
		}
		if (sc.getAuthor() != null) {
			BooleanBuilder authorBooleanBuilder = new BooleanBuilder();
			authorBooleanBuilder.or(bookEntity.authors.any().firstName.startsWithIgnoreCase(sc.getAuthor()));
			authorBooleanBuilder.or(bookEntity.authors.any().lastName.startsWithIgnoreCase(sc.getAuthor()));
			booleanBuilder.and(authorBooleanBuilder);
		}
		if (sc.getLibraryName() != null) {
			booleanBuilder.and(bookEntity.library.name.startsWithIgnoreCase(sc.getLibraryName()));
		}
		return query.from(bookEntity).where(booleanBuilder).list(bookEntity);
	}

}
