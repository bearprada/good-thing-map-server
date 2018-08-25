<%@ page import="good.thing.server.GoodThingConfig" %>



<div class="fieldcontain ${hasErrors(bean: goodThingConfigInstance, field: 'coverStory', 'error')} required">
	<label for="coverStory">
		<g:message code="goodThingConfig.coverStory.label" default="Cover Story" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="coverStory" name="coverStory.id" from="${good.thing.server.GoodThing.list()}" optionKey="id" required="" value="${goodThingConfigInstance?.coverStory?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingConfigInstance, field: 'responseNumber', 'error')} required">
	<label for="responseNumber">
		<g:message code="goodThingConfig.responseNumber.label" default="Response Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="responseNumber" type="number" value="${goodThingConfigInstance.responseNumber}" required=""/>
</div>

