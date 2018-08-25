<%@ page import="good.thing.server.GoodThing" %>


<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="goodThing.title.label" default="Title" />
	</label>
	<g:textField name="title" value="${goodThingInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="goodThing.address.label" default="Address" />
	</label>
	<g:textField name="address" value="${goodThingInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'businessTime', 'error')} ">
	<label for="businessTime">
		<g:message code="goodThing.businessTime.label" default="Business Time" />
	</label>
	<g:textField name="businessTime" value="${goodThingInstance?.businessTime}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'canPost', 'error')} ">
	<label for="canPost">
		<g:message code="goodThing.canPost.label" default="Can Post" />
	</label>
	<g:checkBox name="canPost" value="${goodThingInstance?.canPost}" />
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="goodThing.content.label" default="Content" />
	</label>
	<g:textField name="content" value="${goodThingInstance?.content}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'goodThingType', 'error')} required">
	<label for="goodThingType">
		<g:message code="goodThing.goodThingType.label" default="Good Thing Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="goodThingType" name="goodThingType.id" from="${good.thing.server.GoodThingType.list()}" optionKey="id" required="" value="${goodThingInstance?.goodThingType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'isDeleted', 'error')} ">
	<label for="isDeleted">
		<g:message code="goodThing.isDeleted.label" default="Is Deleted" />
	</label>
	<g:checkBox name="isDeleted" value="${goodThingInstance?.isDeleted}" />
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'memo', 'error')} ">
	<label for="memo">
		<g:message code="goodThing.memo.label" default="Memo" />
	</label>
	<g:textField name="memo" value="${goodThingInstance?.memo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'story', 'error')} ">
	<label for="story">
		<g:message code="goodThing.story.label" default="Story" />		
	</label>
	<g:textField name="story" value="${goodThingInstance?.story}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'userMessages', 'error')} ">
	<label for="userMessages">
		<g:message code="goodThing.userMessages.label" default="User Messages" />
	</label>
	<ul class="one-to-many">
		<g:each in="${goodThingInstance?.userMessages?}" var="u">
			<li><g:link controller="userMessage" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
		</g:each>
	</ul>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'likes', 'error')} ">
	<label for="likes">
		<g:message code="goodThing.likes.label" default="Likes" />
	</label>
	${goodThingInstance?.likes?.size()}
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'checkins', 'error')} ">
	<label for="checkins">
		<g:message code="goodThing.checkins.label" default="Checkins" />
	</label>
	${goodThingInstance?.checkins?.size()}
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'images', 'error')} ">
	<label for="images">
		<g:message code="goodThing.images.label" default="Images" />
	</label>
	<ul class="one-to-many">
		<g:each in="${goodThingInstance?.images?}" var="u">
			<li>${u?.encodeAsHTML()}</li>
		</g:each>
	</ul>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'longtitude', 'error')} required">
	<label for="longtitude">
		<g:message code="goodThing.longtitude.label" default="Longtitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="longtitude" value="${goodThingInstance.longtitude}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'latitude', 'error')} required">
	<label for="latitude">
		<g:message code="goodThing.latitude.label" default="Latitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="latitude" value="${goodThingInstance.latitude}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'detailImageUrl', 'error')} ">
	<label for="detailImageUrl">
		<g:message code="goodThing.detailImageUrl.label" default="Detail Image Url" />
	</label>
	<img src="${goodThingInstance?.detailImageUrl}" /><input type="file" name="detail_image_url" />
</div>
<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'imageUrl', 'error')} ">
	<label for="imageUrl">
		<g:message code="goodThing.imageUrl.label" default="Image Url" />
	</label>
	<img src="${goodThingInstance?.imageUrl}" /><input type="file" name="image_url" />
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingInstance, field: 'listImageUrl', 'error')} ">
	<label for="listImageUrl">
		<g:message code="goodThing.listImageUrl.label" default="List Image Url" />
	</label>
	<img src="${goodThingInstance?.listImageUrl}" /><input type="file" name="list_image_url" />
</div>