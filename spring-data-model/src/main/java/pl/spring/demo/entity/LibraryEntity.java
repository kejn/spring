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
	private Set<BookEntity> bookSet = new HashSet<>();
	
	// 4 hibernate
	protected LibraryEntity() {
	}

}
