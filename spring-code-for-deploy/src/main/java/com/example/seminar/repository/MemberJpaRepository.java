package com.example.seminar.repository;

import com.example.seminar.common.exception.MemberException;
import com.example.seminar.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new MemberException("존재하지 않는 회원입니다."));
    }
}
