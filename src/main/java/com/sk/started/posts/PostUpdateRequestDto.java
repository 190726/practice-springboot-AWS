package com.sk.started.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
