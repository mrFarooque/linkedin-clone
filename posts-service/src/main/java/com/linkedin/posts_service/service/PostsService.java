package com.linkedin.posts_service.service;

import com.linkedin.posts_service.dto.PostCreateRequestDto;
import com.linkedin.posts_service.dto.PostDto;
import com.linkedin.posts_service.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface PostsService {

    PostDto createPost(PostCreateRequestDto postDto);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostsOfUser(Long userId);
}
