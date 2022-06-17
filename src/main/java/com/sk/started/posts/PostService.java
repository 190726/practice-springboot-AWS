package com.sk.started.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostsRepository repository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return repository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Posts posts = postsFindById(id);
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );
        repository.delete(posts);
    }

    public PostResponseDto findById(Long id) {
        Posts posts = postsFindById(id);
        return new PostResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return repository.findAll().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    private Posts postsFindById(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id:"+id));
    }
}
