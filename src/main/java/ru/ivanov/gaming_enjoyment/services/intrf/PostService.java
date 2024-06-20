package ru.ivanov.gaming_enjoyment.services.intrf;

import org.springframework.data.domain.Page;
import ru.ivanov.gaming_enjoyment.dto.PostDto;
import ru.ivanov.gaming_enjoyment.queries.PostPageQuery;

public interface PostService {

    Page<PostDto> getAllPosts(PostPageQuery query);
    PostDto createPost(PostDto postDto);
    void deletePost(Integer id);
}
