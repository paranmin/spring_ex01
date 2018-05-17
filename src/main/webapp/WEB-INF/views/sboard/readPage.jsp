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
						<input type="hidden" name="searchType" value="${criteria.searchType }" />
						<input type="hidden" name="keyword" value="${criteria.keyword }" />
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
	
	<div class="row">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">ADD NEW REPLY</h3>
				</div>
				<div class="box-body">
					<label for="newReplyWriter">Writer</label>
					<input type="text" id="newReplyWriter" placeholder="User ID" class="form-control" />
					
					<label for="newReplyText">Reply Text</label>
					<input type="text" id="newReplyText" placeholder="Reply Text" class="form-control" />
				</div>
				<div class="box-footer">
					<button class="btn btn-primary" id="replyAddBtn">Add Reply</button>
				</div>
			</div>
			<ul class="timeline">
				<li class="time-label" id="repliesDiv">
					<span class="bg-green btn">Replies List [<span id="replyCnt">${boardVO.replycnt}</span>]</span>
				</li>
			</ul>
			<div class="text-center">
				<ul id="pagination" class="pagination pagination-sm no-margin"></ul>
			</div>
		</div>
	</div>
</section>

<!-- Modal -->
<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Reply Modify</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<label for="modifyReplyText">Reply Text</label>
				<input type="text" id="modifyReplyText" placeholder="Reply Text" class="form-control" data-rno=""/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary btnMdlModify">Save changes</button>
			</div>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath }/resources/js/handlebars-v4.0.10.js"></script>
<script id="template" type="text/x-handlebars-template">
{{#each.}}
<li class="replyLi" data-rno={{rno}}>
	<i class="fa fa-comments bg-blue"></i>
	<div class="timeline-item">
		<span class="time">
			<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
		</span>
		<h3 class="timeline-header"><strong>{{rno}}</strong> - {{replyer}}</h3>
		<div class="timeline-body">{{replytext}}</div>
		<div class="timeline-footer">
			<a href="#" class="btn btn-primary btn-xs btnModify" data-toggle="modal" data-target="#modifyModal">Modify</a>
			<a href="#" class="btn btn-danger btn-xs btnDelete">Delete</a>
		</div>
	</div>
</li>
{{/each}}
</script>
<script>
$(function() {
	Handlebars.registerHelper("prettifyDate", function(value) {
		var dateObj = new Date(value),
			year = dateObj.getFullYear(),
			month = dateObj.getMonth() + 1,
			date = dateObj.getDate();
		
		return year + "/" + month + "/" + date;
	});
	var source = $("#template").html(),
		tFunc = Handlebars.compile(source),
		bnoVal = ${boardVO.bno},
		page = ${criteria.page};

	$(document).on("click", ".btnModify", function() {
		var $parent = $(this).parent().parent().parent(),
			rno = $parent.data("rno"),
			replytext = $parent.find(".timeline-body").text();
		$("#modifyReplyText").val(replytext);
		$("#modifyReplyText").data("rno", rno);
	});
	$(document).on("click", ".btnMdlModify", function() {
		var $replytext = $("#modifyReplyText"),
			replyVal = $replytext.val(),
			replyRno = $replytext.data("rno"),
			sendData = {replytext:replyVal};

		$.ajax({
			url: "${pageContext.request.contextPath}/replies/"+replyRno,
			type: "put",
			headers: {"content-Type" : "application/json"},
			data: JSON.stringify(sendData),
			dataType: "text",
			success: function(result) {
				if (result == "success") {
					$('#modifyModal').modal('hide')
					$.getListData(bnoVal, page);
				}
			}
		});
	});
	$(document).on("click", "#repliesDiv", function() {
		$.getListData(bnoVal, page);
	});
	$('#modifyModal').on('hidden.bs.modal', function (e) {
		$("#modifyReplyText").val("");
	});
	$(document).on("click", ".pagination a", function(e) {
		e.preventDefault();
		var bnoVal = $("#bno").val().trim(),
			page = $(this).text();

		$("#repliesDiv").trigger("click");
	});
	$("#replyAddBtn").on("click", function() {
		var replyerVal = $("#newReplyWriter").val().trim(),
			replytextVal = $("#newReplyText").val().trim(),
			sendData = {bno:bnoVal, replyer:replyerVal, replytext:replytextVal};

		$.ajax({
			url: "${pageContext.request.contextPath}/replies",
			type: "post",
			headers: {"content-Type" : "application/json"},
			data: JSON.stringify(sendData),
			dataType: "text",
			success: function(result) {
				if (result == "success") {
					$.formEmpty();
					$.getListData(bnoVal, page);
					$.replyCnt(1);
				}
			}
		});
	});
	$(document).on("click", ".btnDelete", function() {
		if (!confirm("삭제하시겠습니까?")) {
			return false;
		}
		var $parent = $(this).parent().parent().parent(),
			rno = $parent.data("rno");

		$.ajax({
			url: "${pageContext.request.contextPath}/replies/"+rno,
			type: "delete",
			dataType: "text",
			success: function(result) {
				if (result == "success") {
					$parent.remove();
					$.replyCnt(-1);
				}
			}
		});
		return false;
	});
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
	$.getListData = function(bno, page) {
		$.ajax({
			url: "${pageContext.request.contextPath}/replies/"+bno+"/"+page,
			type: "get",
			dateType: "json",
			success: function(result) {
				$.getList(result.list);
				var str = "";
				if (result.pageMaker.prev) {
					str += "<li><a href='#'> << </a></li>";
				}
				
				for (var i = result.pageMaker.startPage; i <= result.pageMaker.endPage; i++) {
					str += "<li><a href='#'> " + i +" </a></li>";
				}
				if (result.pageMaker.next) {
					str += "<li><a href='#'> >> </a></li>";
				}
				
				$(".pagination").html(str);
			}
		});
		
	};
	$.replyCnt = function(count) {
		var replyCnt = $("#replyCnt").text().trim(),
			cnt = parseInt(replyCnt);
		$("#replyCnt").text(cnt+count);
	}
	$.getList = function(data) {
		var str = tFunc(data),
			$li = $('<li class="time-label" id="repliesDiv">'),
			replyCnt = $("#replyCnt").text().trim();
		$li.append('<span class="bg-green btn">Replies List [<span id="replyCnt">'+replyCnt+'</span>]</span>');

		$(".timeline").empty();
		$(".timeline").append($li);
		$(".timeline").append(str);
	}
	$.formEmpty = function() {
		$("#newReplyWriter").val("");
		$("#newReplyText").val("");
	};
	
	$.getListData(bnoVal, page);
});

</script>

<%@ include file="../include/footer.jsp" %>