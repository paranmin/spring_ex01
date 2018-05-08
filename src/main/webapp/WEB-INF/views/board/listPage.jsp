<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">ListAll Board</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th class="text-center" style="width:10px;">BNO</th>
							<th class="text-center">TITLE</th>
							<th class="text-center" style="width:150px;">WRITER</th>
							<th class="text-center" style="width:150px;">REGDATE</th>
							<th class="text-center" style="width:40px;">VIEWCNT</th>
						</tr>
						<c:forEach var="item" items="${list}">
							<tr>
								<td class="text-center">${item.bno }</td>
								<td><a href="readPage?bno=${item.bno}&page=${pageMaker.criteria.page}">${item.title }</a></td>
								<td class="text-center">${item.writer }</td>
								<td class="text-center"><fmt:formatDate value="${item.regdate}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td class="text-center">${item.viewcnt }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="box-footer">
					<div class="text-right">
						<button type="submit" class="btn btn-success" id="btnRegister">REGISTER</button>
					</div>
					<div class="text-center">
						<ul class="pagination justify-content-center">
							<c:if test="${pageMaker.prev }">
								<li class="page-item">
									<a class="page-link" href="listPage?page=${pageMaker.startPage-1}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Prev</span>
									</a>
								</li>
							</c:if>
							
							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
								<li class="page-item${pageMaker.criteria.page == idx ? ' active': '' }"><a class="page-link" href="listPage?page=${idx}">${idx}</a></li>
							</c:forEach>
							<c:if test="${pageMaker.next }">
								<li class="page-item">
									<a class="page-link" href="listPage?page=${pageMaker.endPage+1}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
									</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script>
$(function() {
	$("#btnRegister").on("click", function() {
		location.href = "${pageContext.request.contextPath}/board/register";
	});
});
</script>

<%@ include file="../include/footer.jsp" %>