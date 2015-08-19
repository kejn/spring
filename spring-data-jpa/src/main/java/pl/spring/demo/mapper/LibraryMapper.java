package pl.spring.demo.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.to.LibraryTo;

public class LibraryMapper {

	public static LibraryTo map(LibraryEntity libraryEntity) {
		if (libraryEntity != null) {
			return new LibraryTo(libraryEntity.getId(), libraryEntity.getName(),
					BookMapper.map2To(libraryEntity.getBooks()));
		}
		return null;
	}

	public static LibraryEntity map(LibraryTo libraryTo) {
		if (libraryTo != null) {
			return new LibraryEntity(libraryTo.getId(), libraryTo.getName(),
					BookMapper.map2Entity(libraryTo.getBooks()));
		}
		return null;
	}

	public static List<LibraryEntity> map2Entity(Collection<LibraryTo> libraries) {
		return libraries.stream().map(libraryTo -> LibraryMapper.map(libraryTo)).collect(Collectors.toList());
	}

	public static List<LibraryTo> map2To(Collection<LibraryEntity> library) {
		return library.stream().map(libraryEntity -> LibraryMapper.map(libraryEntity)).collect(Collectors.toList());
	}
}
