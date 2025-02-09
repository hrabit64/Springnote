package com.springnote.api.dto.comment.controller;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springnote.api.dto.comment.service.CommentCreateRequestServiceDto;
import com.springnote.api.utils.validation.bot.CheckCaptcha;
import com.springnote.api.utils.validation.bot.EnableHoneypot;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@EqualsAndHashCode
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentCreateRequestControllerDto {

    @Size(min = 2, max = 1000, message = "본문은 2자 이상, 500자 이하여야 합니다.")
    @NotEmpty(message = "본문을 입력해주세요.")
    private String content;

    @EnableHoneypot
    private Boolean imNotBot;


    @NotEmpty(message = "캡차 토큰을 입력해주세요.")
    private String captchaToken;


    public CommentCreateRequestServiceDto toServiceDto(Long postId, String ip) {
        return CommentCreateRequestServiceDto.builder()
                .postId(postId)
                .content(content)
                .ip(ip)
                .build();
    }
}
