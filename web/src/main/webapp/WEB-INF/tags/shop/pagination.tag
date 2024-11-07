<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="pagination">
	<c:if test="${not empty categoryId}">
		<c:forEach items="${pages}" var="pageNumber">
			<c:url value="category" var="pageHref" scope="request">
				<c:param name="id" value="${categoryId}" />
				<c:param name="page" value="${pageNumber}" />
			</c:url>
			<c:if test="${activePage == pageNumber}">
				<a class="active" href="${pageHref}">${pageNumber}</a>
			</c:if>
			<c:if test="${activePage != pageNumber}">
				<a href="${pageHref}">${pageNumber}</a>
			</c:if>
		</c:forEach>
	</c:if>
	<c:if test="${not empty searchQuery}">
		<c:forEach items="${pages}" var="pageNumber">
			<c:url value="search" var="pageHref" scope="request">
				<c:param name="searchQuery" value="${searchQuery}" />
				<c:param name="page" value="${pageNumber}" />
			</c:url>
			<c:if test="${activePage == pageNumber}">
				<a class="active" href="${pageHref}">${pageNumber}</a>
			</c:if>
			<c:if test="${activePage != pageNumber}">
				<a href="${pageHref}">${pageNumber}</a>
			</c:if>
		</c:forEach>
	</c:if>
</div>

<style>
.pagination a {
    font-size: 20px; /* Adjust this value as needed */
    padding: 5px 10px;
    text-decoration: none;
    color: #333;
}

.pagination a.active {
    font-weight: bold;
    color: #007bff;
}
</style>
