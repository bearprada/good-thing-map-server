
<%@ page import="good.thing.server.UserMessage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userMessage.label', default: 'UserMessage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-userMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-userMessage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'userMessage.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="userMessage.goodThing.label" default="Good Thing" /></th>
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'userMessage.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="message" title="${message(code: 'userMessage.message.label', default: 'Message')}" />
					
						<g:sortableColumn property="userId" title="${message(code: 'userMessage.userId.label', default: 'User Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userMessageInstanceList}" status="i" var="userMessageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userMessageInstance.id}">${fieldValue(bean: userMessageInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: userMessageInstance, field: "goodThing")}</td>
					
						<td><g:formatDate date="${userMessageInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: userMessageInstance, field: "message")}</td>
					
						<td>${fieldValue(bean: userMessageInstance, field: "userId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userMessageInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
