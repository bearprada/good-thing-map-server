
<%@ page import="good.thing.server.GoodThingConfig" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'goodThingConfig.label', default: 'GoodThingConfig')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-goodThingConfig" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-goodThingConfig" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="goodThingConfig.coverStory.label" default="Cover Story" /></th>
					
						<g:sortableColumn property="responseNumber" title="${message(code: 'goodThingConfig.responseNumber.label', default: 'Response Number')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${goodThingConfigInstanceList}" status="i" var="goodThingConfigInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${goodThingConfigInstance.id}">${fieldValue(bean: goodThingConfigInstance, field: "coverStory")}</g:link></td>
					
						<td>${fieldValue(bean: goodThingConfigInstance, field: "responseNumber")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${goodThingConfigInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
