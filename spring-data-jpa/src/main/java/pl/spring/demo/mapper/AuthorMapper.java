package pl.spring.demo.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.to.AuthorTo;

public class AuthorMapper {

    public static AuthorTo map(AuthorEntity authorEntity) {
        if (authorEntity != null) {
            return new AuthorTo(authorEntity.getId(), authorEntity.getFirstName(), authorEntity.getLastName());
        }
        return null;
    }

	public static AuthorEntity map(AuthorTo authorTo) {
        if (authorTo != null) {
            return new AuthorEntity(authorTo.getId(), authorTo.getFirstName(), authorTo.getLastName());
        }
        return null;
    }

	public static Set<AuthorTo> map2To(Set<AuthorEntity> authorEntities) {
        return authorEntities.stream().map(AuthorMapper::map).collect(Collectors.toSet());
    }

    public static Set<AuthorEntity> map2Entity(Set<AuthorTo> authorEntities) {
        return authorEntities.stream().map(AuthorMapper::map).collect(Collectors.toSet());
    }
}
