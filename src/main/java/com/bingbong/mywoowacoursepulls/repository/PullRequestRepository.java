package com.bingbong.mywoowacoursepulls.repository;

import com.bingbong.mywoowacoursepulls.domain.PullRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PullRequestRepository extends JpaRepository<PullRequest, Long> {

    List<PullRequest> findAllByTitleContaining(String nickname);
}
