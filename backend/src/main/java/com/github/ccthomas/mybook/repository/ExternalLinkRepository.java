package com.github.ccthomas.mybook.repository;

import com.github.ccthomas.mybook.models.book.ExternalLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalLinkRepository extends JpaRepository<ExternalLink, Long> {

}
