package com.ll.domain.post.postDoc.service;

import com.github.f4b6a3.tsid.TsidCreator;
import com.ll.domain.post.postDoc.document.PostDoc;
import com.ll.domain.post.postDoc.repository.PostDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostDocService {
    private final PostDocRepository postDocRepository;

    public PostDoc write(String title, String content) {
        PostDoc postDoc = PostDoc.builder()
                .id(TsidCreator.getTsid().toString())
                .title(title)
                .content(content)
                .build();

        return postDocRepository.save(postDoc);
    }

    public void truncate() {
        postDocRepository.deleteAll();
    }
}
