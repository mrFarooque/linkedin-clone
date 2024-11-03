package com.linkedin.posts_service.service;

public interface PostLikeService {
     void likePost(Long postId, Long userId);

     void unlikePost(Long postId, Long userId);
}
