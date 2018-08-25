
<%@ page import="good.thing.server.GoodThing" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'goodThing.label', default: 'GoodThing')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-goodThing" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li>
					<g:form method="post" >
						<g:textField name="q" value=""/>
						<g:actionSubmit class="save" action="query" value="${message(code: 'default.button.search.label', default: 'Search')}" />
					</g:form>
				</li>
			</ul>
		</div>
		<div id="list-goodThing" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="id" title="${message(code: 'goodThing.id.label', default: 'Id')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'goodThing.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'goodThing.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="businessTime" title="${message(code: 'goodThing.businessTime.label', default: 'Business Time')}" />

						<g:sortableColumn property="content" title="${message(code: 'goodThing.content.label', default: 'Content')}" />

						<g:sortableColumn property="id" title="${message(code: 'goodThing.likes.label', default: 'Likes')}" />
					
						<g:sortableColumn property="id" title="${message(code: 'goodThing.posts.label', default: 'Posts')}" />

						<g:sortableColumn property="lastUpdated" title="${message(code: 'goodThing.lastUpdated.label', default: 'Date')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${goodThingInstanceList}" status="i" var="goodThingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${goodThingInstance.id}">${fieldValue(bean: goodThingInstance, field: "id")}</g:link></td>
					
						<td>${fieldValue(bean: goodThingInstance, field: "title")}</td>
					
						<td>${fieldValue(bean: goodThingInstance, field: "address")}</td>
					
						<td>${fieldValue(bean: goodThingInstance, field: "businessTime")}</td>
					
						<td>${fieldValue(bean: goodThingInstance, field: "content")}</td>
						
						<td><g:formatNumber number="${goodThingInstance.likes.size()}" /></td>

						<td><g:formatNumber number="${goodThingInstance.userMessages.size()}" /></td>

						<td>${fieldValue(bean: goodThingInstance, field: "lastUpdated")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${goodThingInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
