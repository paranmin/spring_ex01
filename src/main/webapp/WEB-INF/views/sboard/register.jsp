<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Register Board</h3>
				</div>
				<div class="box-body">
					<form action="register" role="form" method="post">
						<div class="form-group">
							<label for="writer">Writer</label>
							<input type="text" name="writer" id="writer"  class="form-control" placeholder="Enter Writer"/>
						</div>
						<div class="form-group">
							<label for="title">Title</label>
							<input type="text" name="title" id="title"  class="form-control" placeholder="Enter Title"/>
						</div>
						<div class="form-group">
							<label for="content">Content</label>
							<textarea name="content" id="content" rows="5" cols="30" class="form-control"></textarea>
						</div>
						<div class="form-group">
							<input type="submit" value="Submit" class="btn btn-primary" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>


<%@ include file="../include/footer.jsp" %>