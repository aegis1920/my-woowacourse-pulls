package com.bingbong.mywoowacoursepulls.exception;

public class NotFoundPullRequestException extends IllegalArgumentException {

    public NotFoundPullRequestException(String message) {
        super(message);
    }
}
