
<%@ page import="good.thing.server.UserLike" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userLike.label', default: 'UserLike')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-userLike" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-userLike" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'userLike.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="userLike.goodThing.label" default="Good Thing" /></th>
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'userLike.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="userId" title="${message(code: 'userLike.userId.label', default: 'User Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userLikeInstanceList}" status="i" var="userLikeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userLikeInstance.id}">${fieldValue(bean: userLikeInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: userLikeInstance, field: "goodThing")}</td>
					
						<td><g:formatDate date="${userLikeInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: userLikeInstance, field: "userId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userLikeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
