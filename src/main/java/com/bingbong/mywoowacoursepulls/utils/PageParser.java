package com.bingbong.mywoowacoursepulls.utils;

/*
 * 아래 글을 참고하여 작성했습니다.
 * https://github.com/eclipse/egit-github/blob/master/org.eclipse.egit.github.core/src/org/eclipse/egit/github/core/client/PageLinks.java#L43-75
 *
 * */

public class PageParser {

    private static final String LINKS_DELIMITER = ",";
    private static final String LINK_DELIMITER = ";";
    private static final String KEY_VALUE_DELIMITER = "=";
    private static final String META_REL = "rel";
    private static final String META_LAST = "\"last\"";
    private static final String PARAM_DELIMITER = "&";
    private static final String PARAMS_DELIMITER = "\\?";
    private static final String DEFAULT_PARSE_KEY = "page";
    private static final int DEFAULT_ACCESS_INDEX = 2;

    public static int getLastPageIndex(String pageLink) {
        final String[] links = pageLink.split(LINKS_DELIMITER);

        for (String link : links) {
            String[] linkSegment = link.split(LINK_DELIMITER);
            if (isAccessibleIndex(linkSegment)) {
                continue;
            }
            if (isRelValue(linkSegment[1])) {
                continue;
            }
            String htmlUrl = linkSegment[0].trim();
            if (isHtmlUrl(htmlUrl)) {
                continue;
            }

            htmlUrl = htmlUrl.substring(1, htmlUrl.length() - 1);
            String[] htmlUrlSegment = htmlUrl.split(PARAMS_DELIMITER);
            if (isAccessibleIndex(htmlUrlSegment)) {
                continue;
            }

            String[] params = htmlUrlSegment[1].split(PARAM_DELIMITER);

            for (String param : params) {
                String[] paramSegment = param.split(KEY_VALUE_DELIMITER);

                if (isAccessibleIndex(paramSegment)) {
                    continue;
                }

                String paramSegmentKey = paramSegment[0];
                String paramSegmentValue = paramSegment[1];

                if (!paramSegmentKey.equals(DEFAULT_PARSE_KEY)) {
                    continue;
                }

                return Integer.parseInt(paramSegmentValue);
            }
        }

        return -1;
    }

    private static boolean isHtmlUrl(String htmlUrl) {
        return !htmlUrl.startsWith("<") || !htmlUrl.endsWith(">");
    }

    private static boolean isRelValue(String rel) {
        String[] relSegment = rel.trim()
            .split(KEY_VALUE_DELIMITER);

        if (isAccessibleIndex(relSegment)) {
            return true;
        }

        String relSegmentKey = relSegment[0];
        String relSegmentValue = relSegment[1];

        return !relSegmentKey.equals(META_REL) || !relSegmentValue.equals(META_LAST);
    }

    private static boolean isAccessibleIndex(String[] segment) {
        return segment.length < DEFAULT_ACCESS_INDEX;
    }
}
