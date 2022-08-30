<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>XML RSS Feed</title>
</head>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<body>
<body>
 <div class="border_wrapper add_box">
        <h3>XML RSS Feed</h2>
        <a class="provide_msg">Please find the list of all available feeds</a>
        <div class ="view_table_wrapper">
            <table>
                <thead>
                    <tr>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${feeds}" var="feed">
                        <tr>
                            <td ><a class="view_table_item" href="/view/${feed.id}">${feed.title}<a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <a class="menu_item" href="/">→ Back to menu</a>
 </div>
</body>
</body>
</html>