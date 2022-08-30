<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>XML RSS Feed</title>
</head>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<body>
    <div class="border_wrapper add_box">
        <h3>Add XML RSS Feed</h2>
        <a class="provide_msg">Please provide new XML RSS Feed information</a>
          <c:if test="${newFeedAdded}">
                    <div class="success_msg_wrapper"><a class="success_msg">Successfully added new RSS feed:</a><b>${savedFeed.name}</b></div>
          </c:if>
           <c:if test="${malformedUrl}">
                     <div id="url_err" class="error_msg">Save failed - provided <b>URL</b> is invalid</div>
           </c:if>
          <c:url var="add_feed_url" value="/add"/>

          <f:form class="add_form" action="${add_feed_url}" method="POST" modelAttribute="newFeed">
              <f:label class="add_label" path="url">XML RSS Feed URL: </f:label>
              <f:input class="add_input" type="text" id="url" path="url"/>
              <br>
              <f:label class="add_label" path="name">XML RSS Feed Name: </f:label>
              <f:input class="add_input" type="text" id="name" path="name"/>
              <br>
              <input id="submitBtn" type="submit" value="Add feed"/></br>
          </f:form>
        <a class="menu_item" href="/">→ Back to menu</a>
    </div>
</body>
</html>