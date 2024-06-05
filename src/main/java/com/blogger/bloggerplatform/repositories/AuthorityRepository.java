package com.blogger.bloggerplatform.repositories;

import com.blogger.bloggerplatform.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
