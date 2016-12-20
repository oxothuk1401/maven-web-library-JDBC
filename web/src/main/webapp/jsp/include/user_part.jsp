<fmt:message var="showProfile" bundle="${loc}" key="locale.show.profile"/>
<fmt:message var="block" bundle="${loc}" key="locale.invalid.block"/>

<c:if test="${sessionScope.blacklist eq 'block'}">
    <span class="error">${block}</span>
</c:if>

