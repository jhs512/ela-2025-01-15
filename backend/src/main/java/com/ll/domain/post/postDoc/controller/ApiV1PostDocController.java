package com.ll.domain.post.postDoc.controller;

import com.ll.domain.post.postDoc.document.PostDoc;
import com.ll.domain.post.postDoc.service.PostDocService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/postDocs")
@RequiredArgsConstructor
@Validated
public class ApiV1PostDocController {
    private final PostDocService postDocService;

    @GetMapping("/write")
    public PostDoc write(
            @NotBlank String title,
            @NotBlank String content
    ) {
        return postDocService.write(title, content);
    }
}
