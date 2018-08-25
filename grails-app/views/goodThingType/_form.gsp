<%@ page import="good.thing.server.GoodThingType" %>



<div class="fieldcontain ${hasErrors(bean: goodThingTypeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="goodThingType.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${goodThingTypeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: goodThingTypeInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="goodThingType.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="type" type="number" value="${goodThingTypeInstance.type}" required=""/>
</div>

