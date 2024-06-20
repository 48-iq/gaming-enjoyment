package ru.ivanov.gaming_enjoyment.converters;

import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.PostDto;
import ru.ivanov.gaming_enjoyment.entities.Post;

@Component
public class PostConverter {

    public PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setUser(post.getUser().getId());
        postDto.setGroup(post.getGroup().getId());
        postDto.setTitle(post.getTitle());
        postDto.setImage(post.getImage());
        postDto.setText(post.getText());
        return postDto;
    }

    public Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setImage(postDto.getImage());
        post.setText(postDto.getText());
        return post;
    }
}
