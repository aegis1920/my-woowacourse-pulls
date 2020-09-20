package com.bingbong.mywoowacoursepulls.domain;

import java.util.List;
import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface GithubApiFunction<T1, R> {

    ResponseEntity<List<R>> apply(T1 t1);
}
