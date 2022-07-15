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
			data += '<td><input type="text" class="form-control form-control-user w-100" placeholder="허용IP" id="ip" value=""></td>';
			data += '<td><input type="text" class="form-control form-control-user w-100" placeholder="이용자명" id="name" value=""></td>';
			data += '<td><input type="checkbox" id="useAt" data-switch="bool" /><label for="useAt" data-on-label="사용" data-off-label="미사용"></label></td>';
			data += '<td></td>'
			data += '<td><button type="button" class="btn btn-primary" onclick="fnInsert()">등록</button>&nbsp;&nbsp;';
			data += '<button type="button" class="btn btn-danger" onclick="fnRemoveRow()">취소</button></td></tr>';
			
			$("#accessIpList").append(data).trigger("create");
		}
	}
	
	function fnRemoveRow()
	{
		$("#addRow").remove();
	}
	
	function fnInsert()
	{
		if ($("#ip").val() == "") {
			alert("허용IP를 입력하세요.");
			return false;
		}
		
		if ($("#name").val() == "") {
			alert("이용자명을 입력하세요.");
			return false;
		}
		
		if(confirm("등록 하시겠습니까?")){
			document.accessIPForm.name.value = $("#name").val();
			document.accessIPForm.ip.value= $("#ip").val();
			if($("input:checkbox[id='useAt']").is(":checked") == true)
				document.accessIPForm.useAt.value= 'Y'; 
			else
				document.accessIPForm.useAt.value= 'N';
			document.accessIPForm.action = "<c:url value='/cms/accessip/insertAccessIP.do'/>";
			document.accessIPForm.submit();
		}
	}
	
	function fnUpdate(idx)
	{
		if ($("#ip_"+idx).val() == "") {
			alert("허용IP를 입력하세요.");
			return false;
		}
		
		if ($("#name_"+idx).val() == "") {
			alert("이용자명을 입력하세요.");
			return false;
		}
		
		if(confirm("수정 하시겠습니까?")){
			document.accessIPForm.idx.value=idx;
			document.accessIPForm.name.value =$("#name_"+idx).val();
			document.accessIPForm.ip.value=$("#ip_"+idx).val();
			
			if($("input:checkbox[id='useAt_"+idx+"']").is(":checked") == true)
				document.accessIPForm.useAt.value= 'Y'; 
			else
				document.accessIPForm.useAt.value= 'N';
			
			document.accessIPForm.action = "<c:url value='/cms/accessip/updateAccessIP.do'/>";
			document.accessIPForm.submit();
		}
	}
	
	function fnDelete(idx)
	{
		if(confirm("삭제 하시겠습니까?")){
			document.accessIPForm.idx.value=idx;
			document.accessIPForm.action = "<c:url value='/cms/accessip/deleteAccessIP.do'/>";
			document.accessIPForm.submit();
		}
	}
	

</script>

<form method="post" id="accessIPForm" name="accessIPForm">
	<input type="hidden" name="idx">
	<input type="hidden" name="name">
	<input type="hidden" name="ip">
	<input type="hidden" name="useAt">
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
						<li class="breadcrumb-item active">접근 IP관리</li>
					</ol>
				</div>
				<h4 class="page-title">접근 IP관리</h4>
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
								<span>접근 IP등록</span>
							</button>
						</div>
					</div>
					<div data-simplebar class="table-responsive">
						<table class="table mb-0 table-hover table-middle">
							<thead class="thead-dark">
								<tr>
									<th scope="col">번호</th>
									<th scope="col">허용IP</th>
									<th scope="col">이용자명</th>
									<th scope="col">사용여부</th>
									<th scope="col">등록일</th>
									<th scope="col">수정</th>
								</tr>
							</thead>
							<tbody id="accessIpList">
								<c:forEach var="result" items="${ipList}" varStatus="status">
									<tr>
										<td>
											<c:out value="${(paginationInfo.totalRecordCount - status.index) - ( (paginationInfo.currentPageNo - 1)  *  paginationInfo.recordCountPerPage ) }" />
										</td>
										<td>
											<input type="text" class="form-control form-control-user w-100" placeholder="허용IP" id="ip_${result.idx}" value="${result.ip}">
										</td>
										<td>
											<input type="text" class="form-control form-control-user w-100" placeholder="이용자명" id="name_${result.idx}" value="${result.name}">
										</td>
										<td>
											<input type="checkbox" id="useAt_${result.idx }" <c:if test="${result.useAt eq 'Y' }">checked</c:if> data-switch="bool" />
											<label for="useAt_${result.idx }" data-on-label="사용" data-off-label="미사용"></label>
										</td>
										<td>
											<c:out value="${result.registDe }" />
										</td>
										<td>
											<button type="button" class="btn btn-primary" onclick="fnUpdate('${result.idx}')">수정</button>
											&nbsp;&nbsp;
											<button type="button" class="btn btn-danger" onclick="fnDelete('${result.idx}')">삭제</button>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${empty ipList}">
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
