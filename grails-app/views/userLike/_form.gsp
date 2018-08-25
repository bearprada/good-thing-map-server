<%@ page import="good.thing.server.UserLike" %>



<div class="fieldcontain ${hasErrors(bean: userLikeInstance, field: 'goodThing', 'error')} required">
	<label for="goodThing">
		<g:message code="userLike.goodThing.label" default="Good Thing" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="goodThing" name="goodThing.id" from="${good.thing.server.GoodThing.list()}" optionKey="id" required="" value="${userLikeInstance?.goodThing?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userLikeInstance, field: 'userId', 'error')} ">
	<label for="userId">
		<g:message code="userLike.userId.label" default="User Id" />
		
	</label>
	<g:textField name="userId" value="${userLikeInstance?.userId}"/>
</div>

