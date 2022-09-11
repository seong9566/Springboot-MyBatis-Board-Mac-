<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/users/${users.id}/update" method="post">
		<div class="mb-3">
			<input type="email" class="form-control" placeholder="Enter email" name="email" >
		</div>
		<div class="mb-3">
			<input type="password" class="form-control" placeholder="Enter password" name="password" >
		</div>

		<button type="submit" class="btn btn-primary">회원 정보 수정 완료</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>

