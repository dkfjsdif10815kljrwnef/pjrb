<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>

function moveUp(el){
	var $div = $(el).parent().parent().parent(); // 클릭한 버튼이 속한 tr 요소
	$div.prev().before($div); // 현재 tr 의 이전 tr 앞에 선택한 tr 넣기
}

function moveDown(el){
	var $div = $(el).parent().parent().parent(); // 클릭한 버튼이 속한 tr 요소
	$div.next().after($div); // 현재 tr 의 다음 tr 뒤에 선택한 tr 넣기
}

function fn_submit(){
	var frm = document.frm;
	
	frm.action = '<c:url value="/cms/menu/menuSortUpdate.do"/>';
	frm.submit();
}

</script>

<form method="post" name="frm">
	<input type="hidden" name="menuType" value='<c:out value="${menuType }"/>'>
	<%-- <table class="tableline nohover">
		<colgroup>
			<col>
			<col class="width100">
		</colgroup>
		<thead>
			<tr>
				<th>메뉴명</th>
				<th>이동</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td>
						<c:out value="${result.menuTitle }"/>
						<input type="hidden" name="menuId" value='<c:out value="${result.menuId }"/>'>
					</td>
					<td>
						<a href="javascript:void(0);" onclick="javascript:moveUp(this)" title="위로"><i class="far fa-caret-circle-up"></i></a>&nbsp;
						<a href="javascript:void(0);" onclick="javascript:moveDown(this)" title="아래로"><i class="far fa-caret-circle-down"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>		
	</table> --%>
	<div class="row">
      <div class="col-lg-3">
        <div class="form-group">
          <label>메뉴명</label>
        </div>
      </div>
      <div class="col-lg-3">
        <div class="form-group">
          <label>이동</label>
        </div>
      </div>
    </div>
    <div>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	    <div class="row">
	      <div class="col-lg-3">
	        <div class="form-group">
	          <input type="text" class="form-control" value="${result.menuTitle }" readonly>
	          <input type="hidden" name="menuId" value='<c:out value="${result.menuId }"/>'>
	        </div>
	      </div>
	      <div class="col-lg-3">
	        <div class="form-group">
	          	<a href="javascript:void(0);" onclick="javascript:moveUp(this);" title="위로"><i class="fad fa-caret-circle-up" style="font-size: 2.5em;"></i></a>&nbsp;
				<a href="javascript:void(0);" onclick="javascript:moveDown(this);" title="아래로"><i class="fad fa-caret-circle-down" style="font-size: 2.5em;"></i></a>
	        </div>
	      </div>
	    </div>
    </c:forEach>
    </div>
</form>
<div class="space10"></div>
<div class="board_write_btngroup alignR">
	<button type="button" class="btn btn-primary" onclick="javascript:fn_submit();">저장</button>
</div>