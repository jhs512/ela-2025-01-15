package com.ll.domain.post.postDoc.service;

import com.github.f4b6a3.tsid.TsidCreator;
import com.ll.domain.post.postDoc.document.PostDoc;
import com.ll.domain.post.postDoc.repository.PostDocRepository;
import com.ll.global.app.AppConfig;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PostDocService {
    private final PostDocRepository postDocRepository;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public String genNextId() {
        return AppConfig.isNotProd() ? atomicInteger.incrementAndGet() + "" : TsidCreator.getTsid().toString();
    }

    public PostDoc write(String title, String content) {
        PostDoc postDoc = PostDoc.builder()
                .id(genNextId())
                .title(title)
                .content(content)
                .build();

        return postDocRepository.save(postDoc);
    }

    public void truncate() {
        postDocRepository.deleteAll();
    }

    public void delete(PostDoc postDoc) {
        postDocRepository.delete(postDoc);
    }

    public Optional<PostDoc> findById(String id) {
        return postDocRepository.findById(id);
    }

    public void modify(PostDoc postDoc, String title, String content) {
        postDoc.setTitle(title);
        postDoc.setContent(content);

        postDocRepository.save(postDoc);
    }

    public Page<PostDoc> search(@NotBlank String kw, Pageable pageable) {
        return postDocRepository.findByTitleContaining(kw, pageable);
    }
}
