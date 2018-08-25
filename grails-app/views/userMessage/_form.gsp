<%@ page import="good.thing.server.UserMessage" %>



<div class="fieldcontain ${hasErrors(bean: userMessageInstance, field: 'goodThing', 'error')} required">
	<label for="goodThing">
		<g:message code="userMessage.goodThing.label" default="Good Thing" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="goodThing" name="goodThing.id" from="${good.thing.server.GoodThing.list()}" optionKey="id" required="" value="${userMessageInstance?.goodThing?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userMessageInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="userMessage.message.label" default="Message" />
		
	</label>
	<g:textField name="message" value="${userMessageInstance?.message}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userMessageInstance, field: 'userId', 'error')} ">
	<label for="userId">
		<g:message code="userMessage.userId.label" default="User Id" />
		
	</label>
	<g:textField name="userId" value="${userMessageInstance?.userId}"/>
</div>

