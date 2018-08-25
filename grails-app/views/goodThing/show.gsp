
<%@ page import="good.thing.server.GoodThing" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'goodThing.label', default: 'GoodThing')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-goodThing" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-goodThing" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list goodThing">
			
				<g:if test="${goodThingInstance?.longtitude}">
				<li class="fieldcontain">
					<span id="longtitude-label" class="property-label"><g:message code="goodThing.longtitude.label" default="Longtitude" /></span>
					
						<span class="property-value" aria-labelledby="longtitude-label"><g:fieldValue bean="${goodThingInstance}" field="longtitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.latitude}">
				<li class="fieldcontain">
					<span id="latitude-label" class="property-label"><g:message code="goodThing.latitude.label" default="Latitude" /></span>
					
						<span class="property-value" aria-labelledby="latitude-label"><g:fieldValue bean="${goodThingInstance}" field="latitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="goodThing.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${goodThingInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.businessTime}">
				<li class="fieldcontain">
					<span id="businessTime-label" class="property-label"><g:message code="goodThing.businessTime.label" default="Business Time" /></span>
					
						<span class="property-value" aria-labelledby="businessTime-label"><g:fieldValue bean="${goodThingInstance}" field="businessTime"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.canPost}">
				<li class="fieldcontain">
					<span id="canPost-label" class="property-label"><g:message code="goodThing.canPost.label" default="Can Post" /></span>
					
						<span class="property-value" aria-labelledby="canPost-label"><g:formatBoolean boolean="${goodThingInstance?.canPost}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.checkins}">
				<li class="fieldcontain">
					<span id="checkins-label" class="property-label"><g:message code="goodThing.checkins.label" default="Checkins" /></span>
					
						<g:each in="${goodThingInstance.checkins}" var="c">
						<span class="property-value" aria-labelledby="checkins-label"><g:link controller="userCheckin" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="goodThing.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label"><g:fieldValue bean="${goodThingInstance}" field="content"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="goodThing.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${goodThingInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.detailImageUrl}">
				<li class="fieldcontain">
					<span id="detailImageUrl-label" class="property-label"><g:message code="goodThing.detailImageUrl.label" default="Detail Image Url" /></span>
					
						<span class="property-value" aria-labelledby="detailImageUrl-label"><g:fieldValue bean="${goodThingInstance}" field="detailImageUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.goodThingType}">
				<li class="fieldcontain">
					<span id="goodThingType-label" class="property-label"><g:message code="goodThing.goodThingType.label" default="Good Thing Type" /></span>
					
						<span class="property-value" aria-labelledby="goodThingType-label"><g:link controller="goodThingType" action="show" id="${goodThingInstance?.goodThingType?.id}">${goodThingInstance?.goodThingType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.imageUrl}">
				<li class="fieldcontain">
					<span id="imageUrl-label" class="property-label"><g:message code="goodThing.imageUrl.label" default="Image Url" /></span>
					
						<span class="property-value" aria-labelledby="imageUrl-label"><g:fieldValue bean="${goodThingInstance}" field="imageUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.images}">
				<li class="fieldcontain">
					<span id="images-label" class="property-label"><g:message code="goodThing.images.label" default="Images" /></span>
					
						<span class="property-value" aria-labelledby="images-label"><g:fieldValue bean="${goodThingInstance}" field="images"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.isDeleted}">
				<li class="fieldcontain">
					<span id="isDeleted-label" class="property-label"><g:message code="goodThing.isDeleted.label" default="Is Deleted" /></span>
					
						<span class="property-value" aria-labelledby="isDeleted-label"><g:formatBoolean boolean="${goodThingInstance?.isDeleted}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="goodThing.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${goodThingInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>

				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="goodThing.like.label" default="Like" /></span>
						<span class="property-value" aria-labelledby="title-label">${goodThingInstance.likes.size()}</span>
					
				</li>
			
				<g:if test="${goodThingInstance?.listImageUrl}">
				<li class="fieldcontain">
					<span id="listImageUrl-label" class="property-label"><g:message code="goodThing.listImageUrl.label" default="List Image Url" /></span>
					
						<span class="property-value" aria-labelledby="listImageUrl-label"><g:fieldValue bean="${goodThingInstance}" field="listImageUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.memo}">
				<li class="fieldcontain">
					<span id="memo-label" class="property-label"><g:message code="goodThing.memo.label" default="Memo" /></span>
					
						<span class="property-value" aria-labelledby="memo-label"><g:fieldValue bean="${goodThingInstance}" field="memo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.story}">
				<li class="fieldcontain">
					<span id="story-label" class="property-label"><g:message code="goodThing.story.label" default="Story" /></span>
					
						<span class="property-value" aria-labelledby="story-label"><g:fieldValue bean="${goodThingInstance}" field="story"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="goodThing.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${goodThingInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${goodThingInstance?.userMessages}">
				<li class="fieldcontain">
					<span id="userMessages-label" class="property-label"><g:message code="goodThing.userMessages.label" default="User Messages" /></span>
					
						<g:each in="${goodThingInstance.userMessages}" var="u">
						<span class="property-value" aria-labelledby="userMessages-label"><g:link controller="userMessage" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>

				<g:if test="${goodThingInstance?.imageUrl}">
				<li class="fieldcontain">
					<span id="imageUrl-label" class="property-label"><g:message code="goodThing.imageUrl.label" default="Image Url" /></span>
						<span class="property-value" aria-labelledby="imageUrl-label">
							<img src="${goodThingInstance?.imageUrl}" />
						</span>
				</li>
				</g:if>

				<g:if test="${goodThingInstance?.listImageUrl}">
				<li class="fieldcontain">
					<span id="listImageUrl-label" class="property-label"><g:message code="goodThing.listImageUrl.label" default="List Image Url" /></span>
						<span class="property-value" aria-labelledby="listImageUrl-label">
							<img src="${goodThingInstance?.listImageUrl}" />
						</span>
				</li>
				</g:if>

				<g:if test="${goodThingInstance?.images}">
				<li class="fieldcontain">
					<span id="images-label" class="property-label"><g:message code="goodThing.images.label" default="images" /></span>
						<g:each in="${goodThingInstance.images}" var="u">
							<img src="${u}" />
						</g:each>
				</li>
				</g:if>

				<g:if test="${goodThingInstance?.detailImageUrl}">
				<li class="fieldcontain">
					<span id="detailImageUrl-label" class="property-label"><g:message code="goodThing.detailImageUrl.label" default="Detail Image Url" /></span>
						<span class="property-value" aria-labelledby="imageUrl-label">
							<img src="${goodThingInstance?.detailImageUrl}" />
						</span>
				</li>
				</g:if>
				<g:if test="${goodThingInstance?.images}">
				<li class="fieldcontain">
					<span id="images-label" class="property-label"><g:message code="goodThing.images.label" default="Images" /></span>
						<g:each in="${goodThingInstance.images}" var="u">
						<img src="${u}" />
						</g:each>
				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${goodThingInstance?.id}" />
					<g:link class="edit" action="edit" id="${goodThingInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
