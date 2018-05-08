<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Modify Board</h3>
				</div>
				<div class="box-body">
					<form action="modifyPage" role="form" method="post">
						<input type="hidden" name="bno" value="${boardVO.bno }" />
						<input type="hidden" name="page" value="${criteria.page }" />
						<div class="form-group">
							<label for="writer">Writer</label>
							<input type="text" name="writer" id="writer"  class="form-control" value="${boardVO.writer }" />
						</div>
						<div class="form-group">
							<label for="title">Title</label>
							<input type="text" name="title" id="title"  class="form-control" value="${boardVO.title }" />
						</div>
						<div class="form-group">
							<label for="content">Content</label>
							<textarea name="content" id="content" rows="5" cols="30" class="form-control">${boardVO.content }</textarea>
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-primary" id="btnGoList">GO LIST</button>
							<input type="submit" value="Submit" class="btn btn-success" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>

<script>
$(function() {
	$("#btnGoList").on("click", function() {
		var page = $("input[name='page']").val();
		location.href = "${pageContext.request.contextPath}/board/listPage?page="+page;
		return false;
	});
});
</script>

<%@ include file="../include/footer.jsp" %>