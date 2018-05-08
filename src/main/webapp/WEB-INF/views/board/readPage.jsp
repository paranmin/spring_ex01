<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Read Board</h3>
				</div>
				<div class="box-body">
					<form method="get" id="readForm">
						<input type="hidden" name="bno" value="${boardVO.bno }" />
						<input type="hidden" name="page" value="${criteria.page }" />
					</form>
					<div class="form-group">
						<label for="writer">Writer</label>
						<input type="text" name="writer" id="writer"  class="form-control" value="${boardVO.writer }" readOnly />
					</div>
					<div class="form-group">
						<label for="title">Title</label>
						<input type="text" name="title" id="title"  class="form-control" value="${boardVO.title }" readOnly />
					</div>
					<div class="form-group">
						<label for="content">Content</label>
						<textarea name="content" id="content" rows="5" cols="30" class="form-control" readOnly >${boardVO.content }</textarea>
					</div>
				</div>
				<div class="box-footer">
					<button type="submit" class="btn btn-warning" id="btnModify">MODIFY</button>
					<button type="submit" class="btn btn-danger" id="btnDelete">DELETE</button>
					<button type="submit" class="btn btn-primary" id="btnGoList">GO LIST</button>
				</div>
			</div>
		</div>
	</div>
</section>

<script>
$(function() {
	$("#btnGoList").on("click", function() {
		$("#readForm").attr("action", "listPage").submit();
	});
	$("#btnDelete").on("click", function() {
		if (confirm("정말 삭제하시겠습니까?")) {
			$("#readForm").attr("action", "removePage").submit();
		}
	});
	$("#btnModify").on("click", function() {
		$("#readForm").attr("action", "modifyPage").submit();
	});
});
</script>

<%@ include file="../include/footer.jsp" %>