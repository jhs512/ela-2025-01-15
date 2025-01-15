package com.ll.domain.post.postDoc.controller;

import com.ll.domain.post.postDoc.document.PostDoc;
import com.ll.domain.post.postDoc.service.PostDocService;
import com.ll.global.app.AppConfig;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/postDocs")
@RequiredArgsConstructor
@Validated
public class ApiV1PostDocController {
    private final PostDocService postDocService;

    @GetMapping("/{id}")
    public PostDoc item(
            @PathVariable String id
    ) {
        PostDoc postDoc = postDocService.findById(id).get();

        return postDoc;
    }

    @GetMapping
    public Page<PostDoc> search(
            @RequestParam(defaultValue = "1") int page,
            @NotBlank String kw
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id.keyword"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));
        return postDocService.search(kw, pageable);
    }

    @GetMapping("/{id}/delete")
    public String delete(
            @PathVariable String id
    ) {
        PostDoc postDoc = postDocService.findById(id).get();
        postDocService.delete(postDoc);

        return "deleted";
    }

    @GetMapping("/{id}/modify")
    public String modify(
            @PathVariable String id,
            @NotBlank String title,
            @NotBlank String content
    ) {
        PostDoc postDoc = postDocService.findById(id).get();
        postDocService.modify(postDoc, title, content);

        return "modified";
    }

    @GetMapping("/write")
    public PostDoc write(
            @NotBlank String title,
            @NotBlank String content
    ) {
        return postDocService.write(title, content);
    }
}
