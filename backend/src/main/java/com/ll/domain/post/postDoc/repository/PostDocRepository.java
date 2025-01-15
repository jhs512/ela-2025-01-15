package com.ll.domain.post.postDoc.repository;

import com.ll.domain.post.postDoc.document.PostDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostDocRepository extends ElasticsearchRepository<PostDoc, String> {
    Page<PostDoc> findByTitleContaining(String title, Pageable pageable);
}
