package ru.ivanov.gaming_enjoyment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.PostConverter;
import ru.ivanov.gaming_enjoyment.dto.PostDto;
import ru.ivanov.gaming_enjoyment.entities.Post;
import ru.ivanov.gaming_enjoyment.exceptions.EntityNotFoundException;
import ru.ivanov.gaming_enjoyment.queries.PostPageQuery;
import ru.ivanov.gaming_enjoyment.repositories.GroupRepository;
import ru.ivanov.gaming_enjoyment.repositories.PostRepository;
import ru.ivanov.gaming_enjoyment.repositories.UserRepository;
import ru.ivanov.gaming_enjoyment.services.intrf.PostService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    @Override
    public Page<PostDto> getAllPosts(PostPageQuery query) {
        Page<Post> postPage = postRepository.findPostsByUserId(query.getUserId(),
                PageRequest.of(query.getPage(), query.getSize()));
        return new PageImpl<PostDto>(postPage.getContent()
                .stream()
                .map(postConverter::convertToDto).toList(),
                postPage.getPageable(), postPage.getTotalElements());
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = postConverter.convertToEntity(postDto);
        if (postDto.getUser() != null) {
            post.setUser(userRepository.findById(postDto.getUser())
                    .orElseThrow(() -> new EntityNotFoundException("User with id " + postDto.getUser() + " not found")));
            post.setUsersNotViewed(post.getUser().getFriends());
        }
        if (postDto.getGroup() != null) {
            post.setGroup(groupRepository.findById(postDto.getGroup())
                    .orElseThrow(() -> new EntityNotFoundException("Group with id " + postDto.getGroup() + " not found")));
            post.setUsersNotViewed(post.getGroup().getUsers());
        }
        return postConverter.convertToDto(postRepository.save(post));
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}
