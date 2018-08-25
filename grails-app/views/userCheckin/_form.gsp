<%@ page import="good.thing.server.UserCheckin" %>



<div class="fieldcontain ${hasErrors(bean: userCheckinInstance, field: 'facebookPostId', 'error')} ">
	<label for="facebookPostId">
		<g:message code="userCheckin.facebookPostId.label" default="Facebook Post Id" />
		
	</label>
	<g:textField name="facebookPostId" value="${userCheckinInstance?.facebookPostId}"/>
</div>

