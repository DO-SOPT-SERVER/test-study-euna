package com.example.seminar.repository;

import com.example.seminar.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.member.name in :memberName")
    List<Post> findAllByMemberNameIn(List<String> memberName);
}
