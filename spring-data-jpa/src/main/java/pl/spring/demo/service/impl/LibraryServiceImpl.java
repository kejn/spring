package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.mapper.LibraryMapper;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.LibraryService;
import pl.spring.demo.to.LibraryTo;

@Service
@Transactional(readOnly = true)
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;
    
	@Override
	public List<LibraryTo> findAllLibraries() {
		return LibraryMapper.map2To(libraryRepository.findAll());
	}

	@Override
	@Transactional(readOnly = false)
	public LibraryTo saveLibrary(LibraryTo library) {
		LibraryEntity entity = LibraryMapper.map(library);
        entity = libraryRepository.save(entity);
        return LibraryMapper.map(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteLibrary(Long id) {
		libraryRepository.delete(id);
	}

}
