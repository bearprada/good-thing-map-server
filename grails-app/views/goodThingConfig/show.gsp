
<%@ page import="good.thing.server.GoodThingConfig" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'goodThingConfig.label', default: 'GoodThingConfig')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-goodThingConfig" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-goodThingConfig" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list goodThingConfig">
			
				<g:if test="${goodThingConfigInstance?.coverStory}">
				<li class="fieldcontain">
					<span id="coverStory-label" class="property-label"><g:message code="goodThingConfig.coverStory.label" default="Cover Story" /></span>
					
						<span class="property-value" aria-labelledby="coverStory-label"><g:link controller="goodThing" action="show" id="${goodThingConfigInstance?.coverStory?.id}">${goodThingConfigInstance?.coverStory?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingConfigInstance?.responseNumber}">
				<li class="fieldcontain">
					<span id="responseNumber-label" class="property-label"><g:message code="goodThingConfig.responseNumber.label" default="Response Number" /></span>
					
						<span class="property-value" aria-labelledby="responseNumber-label"><g:fieldValue bean="${goodThingConfigInstance}" field="responseNumber"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:goodThingConfigInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${goodThingConfigInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
