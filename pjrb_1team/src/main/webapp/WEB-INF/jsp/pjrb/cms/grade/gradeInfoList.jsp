<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>
$(document).ready(function() 
{
	fnInit();
	// task alert
	setTimeout("fnAlert()", 500); // 500ms(0.5초) 후 alert창호출
	
});

function fnAlert(){
	<c:if test="${!empty resultMsg}">alert('${resultMsg}');</c:if>
}

/*********************************************************
 * 초기화
 ******************************************************** */

 function fnInit(){
	// 첫 입력란에 포커스..
	//document.articleForm.searchCnd.focus();
}


	function fnAddRow()
	{
		if($("#addRow").length ==0)
		{
			var data = '<tr id="addRow"><td></td>';
			data += '<td><input type="text" class="form-control form-control-user w-100" placeholder="메뉴" id="menuKey" value=""></td>';
			data += '<td><input type="text" class="form-control form-control-user w-100" placeholder="권한 명" id="menuNm" value=""></td>';
			data += '<td><button type="button" class="btn btn-primary" onclick="fnInsert()">등록</button>&nbsp;&nbsp;';
			data += '<button type="button" class="btn btn-danger" onclick="fnRemoveRow()">취소</button></td></tr>';
			
			$("#gradeInfoList").append(data).trigger("create");
		}
	}
	
	function fnRemoveRow()
	{
		$("#addRow").remove();
	}
	
	function fnInsert()
	{
		if ($("#menuKey").val() == "") {
			alert("메뉴를 입력하세요.");
			return false;
		}
		
		if ($("#nenuNm").val() == "") {
			alert("권한 명을 입력하세요.");
			return false;
		}
		
		if(confirm("등록 하시겠습니까?")){
			document.gradeInfoForm.menuKey.value =$("#menuKey").val();
			document.gradeInfoForm.menuNm.value=$("#menuNm").val();
			document.gradeInfoForm.action = "<c:url value='/cms/grade/gradeInfoInsert.do'/>";
			document.gradeInfoForm.submit();
		}
	}
	
	function fnUpdate(seq)
	{
		if ($("#menuKey_"+seq).val() == "") {
			alert("메뉴를 입력하세요.");
			return false;
		}
		
		if ($("#menuNm_"+seq).val() == "") {
			alert("권한 명을 입력하세요.");
			return false;
		}
		
		if(confirm("수정 하시겠습니까?")){
			document.gradeInfoForm.seq.value=seq;
			document.gradeInfoForm.menuKey.value =$("#menuKey_"+seq).val();
			document.gradeInfoForm.menuNm.value=$("#menuNm_"+seq).val();
			document.gradeInfoForm.action = "<c:url value='/cms/grade/gradeInfoUpdate.do'/>";
			document.gradeInfoForm.submit();
		}
	}
	
	function fnDelete(seq)
	{
		if(confirm("삭제 하시겠습니까?")){
			document.gradeInfoForm.seq.value=seq;
			document.gradeInfoForm.action = "<c:url value='/cms/grade/gradeInfoDelete.do'/>";
			document.gradeInfoForm.submit();
		}
	}
	

</script>

<form method="post" id="gradeInfoForm" name="gradeInfoForm">
	<input type="hidden" name="menuKey">
	<input type="hidden" name="menuNm">
	<input type="hidden" name="seq">
</form>

<!-- Start Content -->
<div class="container-fluid">
	<!-- start page title -->
	<div class="row">
		<div class="col-12">
			<div class="page-title-box">
				<div class="page-title-right">
					<ol class="breadcrumb m-0">
						<li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
						<li class="breadcrumb-item"><a href="javascript:void(0)">관리자</a></li>
						<li class="breadcrumb-item active">권한 설정</li>
					</ol>
				</div>
				<h4 class="page-title">권한 설정</h4>
			</div>
		</div>
	</div>
	<!-- end page title -->

	<div class="row">
		<div class="col-xl-12">
			<div class="card">
				<div class="card-body">
					<div class="dt-buttons col-sm-12 mb-3">
						<div class="text-right" style="vertical-align: bottom;">
							<button class="btn btn-success" type="button" onclick="fnAddRow();">
								<span>권한 등록</span>
							</button>
						</div>
					</div>
					<div data-simplebar class="table-responsive">
						<table class="table mb-0 table-hover table-middle">
							<thead class="thead-dark">
								<tr>
									<th scope="col">번호</th>
									<th scope="col">권한 메뉴</th>
									<th scope="col">권한 명</th>
									<th scope="col">수정</th>
								</tr>
							</thead>
							<tbody id="gradeInfoList">
								<c:forEach var="result" items="${resultList}" varStatus="status">
									<tr>
										<td scope="row"><c:out value="${status.count }"/></td>
										<td>
											<input type="text" class="form-control form-control-user w-100" placeholder="메뉴" id="menuKey_${result.seq}" value="${result.menuKey}">
										</td>
										<td>
											<input type="text" class="form-control form-control-user w-100" placeholder="권한 명" id="menuNm_${result.seq}" value="${result.menuNm}">
										</td>
										<td>
											<button type="button" class="btn btn-primary" onclick="fnUpdate('${result.seq}')">수정</button>
											&nbsp;&nbsp;
											<button type="button" class="btn btn-danger" onclick="fnDelete('${result.seq}')">삭제</button>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${empty resultList}">
									<tr class="nodata">
										<td colspan="6">등록된 데이터가 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- end table-responsive -->
					<div class="row mt-4">
						<div class="col-sm-12 col-md-5"></div>
						<div class="col-sm-12 col-md-7">
							<div class="dataTables_paginate paging_simple_numbers">
								<ul class="pagination pagination-rounded">
									<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fnLinkPage" />
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />
