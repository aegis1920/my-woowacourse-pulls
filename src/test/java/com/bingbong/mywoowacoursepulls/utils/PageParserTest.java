package com.bingbong.mywoowacoursepulls.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PageParserTest {

    @DisplayName("마지막 페이지 인덱스 파싱")
    @ParameterizedTest
    @CsvSource(delimiter = '₩', value = {
        "<https://api.github.com/repositories/245939275/pulls?state=all&per_page=2&page=2>; rel=\"next\", <https://api.github.com/repositories/245939275/pulls?state=all&per_page=2&page=81>; rel=\"last\" ₩ 81",
        "<https://api.github.com/organizations/45747236/repos?page=2>; rel=\"next\", <https://api.github.com/organizations/45747236/repos?page=3>; rel=\"last\" ₩ 3",
        "<https://api.github.com/organizations/45747236/repos?page=2>; rel=\"next\", <https://api.github.com/organizations/45747236/repos?page=>; rel=\"last\" ₩ -1",
        "<https://api.github.com/organizations/45747236/repos?page=2>; rel=\"next\", <https://api.github.com/organizations/45747236/repos?page=>; rel=\"lst\" ₩ -1",
        "<https://api.github.com/organizations/45747236/repos?page=2>; rel=\"next\", <https://api.github.com/organizations/45747236/repos>; rel=\"lst\" ₩ -1"
    })
    void getLastPageIndex(String input, int expected) {
        int lastPageIndex = PageParser.getLastPageIndex(input);
        assertThat(lastPageIndex).isEqualTo(expected);
    }
}
