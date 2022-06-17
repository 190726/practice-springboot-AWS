package com.sk.started.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository repository;

    @After
    public void cleanup(){
        repository.deleteAll();
    }

    @Test
    public void save_게시글저장불러오기(){
        //given
        String title = "테스트용 제목";
        String content = "테스트용 본문";

        repository.save(Posts.builder()
                .title(title).content(content).author("lee").build());

        //when
        List<Posts> postsList = repository.findAll();

        //then
        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2022, 6, 16, 0,0,0);
        repository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );
        
        //when
        List<Posts> all = repository.findAll();

        //then
        Posts posts = all.get(0);

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
