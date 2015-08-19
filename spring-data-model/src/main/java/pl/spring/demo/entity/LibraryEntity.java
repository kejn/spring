package pl.spring.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "LIBRARY")
public class LibraryEntity {

	@Id
	private Long id;
	
	@Column(nullable = false, length = 50)
    private String name;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="library")
	private Set<BookEntity> books = new HashSet<>();
	
	// 4 hibernate
	protected LibraryEntity() {
	}
	
	public LibraryEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public LibraryEntity(Long id, String name, Set<BookEntity> books) {
		this.id = id;
		this.name = name;
		this.books = books;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(Set<BookEntity> bookSet) {
		this.books = bookSet;
	}
}
