<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>XML RSS Feed</title>
</head>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<body>
    <div class="view_single_wrapper">
        <div class="feed_info_wrapper">
            <ul>
              <li><b>Name:</b><a>${feedInfo.name}</a></li>
              <li><b>URL:</b><a href="${feedInfo.url}">${feedInfo.url}</a></li>
              <li><b>Update time:</b><a>${feedInfo.updated}</a></li>
              <li><b>Article count:</b><a>${feedInfo.articleCount}</a></li>
            </ul>
        </div>
        <table class="feeds_list" id="feeds">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Link</th>
                    <th>Published</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${feedItems}" var="feedItem">
                    <tr>
                        <td>${feedItem.title}</td>
                        <td><a href="${feedItem.link}">${feedItem.link}</a></td>
                        <td>${feedItem.published}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <a class="menu_item" href="/view">→ Back to feeds</a>
</body>
</html>