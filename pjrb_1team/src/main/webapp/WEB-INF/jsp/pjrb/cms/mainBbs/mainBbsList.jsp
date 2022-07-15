<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>
$(document).ready(function() {
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


function fnAddRow()	{
	if($("#addRow").length ==0) {
		var data = '<tr id="addRow"><td></td>';
		data += '<td><select class="custom-select" name="type" id="type"><option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option></select></td>';
		data += '<td><input type="hidden" id="bbsId" /><input type="hidden" id="nttNo" /><input type="hidden" id="bbsTp" /><input type="text" class="form-control form-control-user w-100" placeholder="게시판명" id="bbsNm" value="" readonly /></td>';
		data += '<td><input type="text" class="form-control form-control-user w-100" placeholder="제목" id="title" value="" readonly /></td>';
		data += '<td><input type="text" style="text-align: center;" class="form-control form-control-user w-100" id="bbsRegDate" placeholder="게시물 등록일" readonly /></td>'
		data += '<td>'
		data += '<button class="btn btn-secondary" type="button" data-toggle="modal" data-target="#modal01" onclick="javascript:fn_mainBbsModal(\'1\',\'\');"><span>일반 게시판</span></button>';
		data += '&nbsp;&nbsp;&nbsp;&nbsp;';
		data += '<button class="btn btn-secondary" type="button" data-toggle="modal" data-target="#modal01" onclick="javascript:fn_mainBbsModal(\'2\',\'\');"><span>콘텐츠 게시판</span></button>';
		data += '</td>'
		data += '<td><input type="checkbox" id="useYn" data-switch="bool" /><label for="useYn" data-on-label="사용" data-off-label="미사용"></label></td>';
		data += '<td></td>'
		data += '<td></td>'
		data += '<td><button type="button" class="btn btn-primary" onclick="fnInsert()">저장</button>';
		data += '&nbsp;&nbsp;&nbsp;&nbsp;';
		data += '<button type="button" class="btn btn-danger" onclick="fnRemoveRow()">삭제</button></td></tr>';
		
		$("#mainBbsList").append(data).trigger("create");
	}
	$(".nodata").hide();
	$("#type").focus();
}

function fnRemoveRow() {
	$("#addRow").remove();
	$(".nodata").show();
}

function fn_mainBbsModal(flag, seq) {
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/mainBbs/mainBbsListPopup.do'/>",
		data:{
			"pageIndex": "1",
			"seq": seq,
			"searchFlag": flag
		},
		dataType:'html',
		async:false,
		success:function(returnData, status){
			
			if(status == "success") {
				$("#modal01").html(returnData);
			}else{ 
				alert("ERROR!");return;
			}
		}
	});
}

function fnInsert()	{
	if ($("#bbsNm").val() == "") {
		alert("게시판을 선택하세요.");
		return false;
	}
	
	if ($("#title").val() == "") {
		alert("게시판을 선택하세요.");
		return false;
	}
	
	if(confirm("저장 하시겠습니까?")){
		document.mainBbsForm.type.value = $("#type").val();
		document.mainBbsForm.bbsId.value = $("#bbsId").val();
		document.mainBbsForm.nttNo.value = $("#nttNo").val();
		document.mainBbsForm.bbsTp.value = $("#bbsTp").val();
		if('${searchVO.searchType}'!=''){
			document.mainBbsForm.searchType.value = $('#type').val();
		}
		if($("input:checkbox[id='useYn']").is(":checked") == true)
			document.mainBbsForm.useYn.value= 'Y'; 
		else
			document.mainBbsForm.useYn.value= 'N';
		document.mainBbsForm.action = "<c:url value='/cms/mainBbs/mainBbsInsert.do'/>";
		document.mainBbsForm.submit();
	}
}

function fnUpdate(seq)
{
	if ($("#bbsNm"+seq).val() == "") {
		alert("게시판을 선택하세요.");
		return false;
	}
	
	if(confirm("저장 하시겠습니까?")){
		document.mainBbsForm.seq.value = seq;
		document.mainBbsForm.type.value = $("#type"+seq).val();
		document.mainBbsForm.bbsId.value = $("#bbsId"+seq).val();
		document.mainBbsForm.nttNo.value = $("#nttNo"+seq).val();
		document.mainBbsForm.bbsTp.value = $("#bbsTp"+seq).val();
		if('${searchVO.searchType}'!=''){
			document.mainBbsForm.searchType.value = $('#type'+seq).val();
		}
		if($("input:checkbox[id='useYn"+seq+"']").is(":checked") == true)
			document.mainBbsForm.useYn.value= 'Y'; 
		else
			document.mainBbsForm.useYn.value= 'N';
		
		document.mainBbsForm.action = "<c:url value='/cms/mainBbs/mainBbsUpdate.do'/>";
		document.mainBbsForm.submit();
	}
}

function fnDelete(seq, searchType) {
	if(confirm("삭제 하시겠습니까?")){
		document.mainBbsForm.seq.value = seq;
		if('${searchVO.searchType}'!=''){
			document.mainBbsForm.searchType.value = $('#type'+seq).val();
		}
		document.mainBbsForm.action = "<c:url value='/cms/mainBbs/mainBbsDelete.do'/>";
		document.mainBbsForm.submit();
	}
}

function fn_mainBbsDetail (bbsId, nttNo) {
	document.mainBbsForm.bbsId.value = bbsId;
	document.mainBbsForm.nttNo.value = nttNo;
	document.mainBbsForm.action = "<c:url value='/cms/bbs/bbsModify.do'/>";
	document.mainBbsForm.submit();
}

function fn_searchKeyPressed(event) {
	if (event.keyCode==13) {
		fnMainBbsList();
	}
}

function fnMainBbsList() {
	document.frm.action = '<c:url value="/cms/mainBbs/mainBbsList.do"/>';
	document.frm.submit();
}

function fnType(type){
	document.frm.searchType.value = type;
	document.frm.action = "<c:url value='/cms/mainBbs/mainBbsList.do'/>";
    document.frm.submit();
}
</script>

<form method="post" id="mainBbsForm" name="mainBbsForm">
	<input type="hidden" name="seq">
	<input type="hidden" name="type">
	<input type="hidden" name="bbsId">
	<input type="hidden" name="nttNo">
	<input type="hidden" name="bbsTp">
	<input type="hidden" name="useYn">
	<input type="hidden" name="searchType">
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
						<li class="breadcrumb-item"><a href="javascript:void(0)">사이트</a></li>
						<li class="breadcrumb-item active">메인 게시판 관리</li>
					</ol>
				</div>
				<h4 class="page-title">메인 게시판 관리</h4>
			</div>
		</div>
	</div>
	<!-- end page title -->

	<div class="row">
		<div class="col-xl-12">
			<div class="card">
				<div class="card-body">
					<form method="post" id="frm" name="frm">
					<input type="hidden" name="searchType" value="<c:out value="${searchVO.searchType}"/>">
						
						<div class="alert alert-light bg-light text-dark sch_wrap">
							<div class="input-group col-sm-12">
								<div class="input-group col-sm">
									<select class="custom-select" name="searchStatus">
										<option value="" <c:if test='${searchVO.searchStatus == null || searchVO.searchStatus == ""}'> selected </c:if>>사용여부 전체</option>
										<option value="Y" <c:if test='${searchVO.searchStatus eq "Y"}'> selected </c:if>>사용</option>
										<option value="N" <c:if test='${searchVO.searchStatus eq "N"}'> selected </c:if>>미사용</option>
									</select>
								</div>
								<div class="input-group col-sm">
									<select class="custom-select" name="searchCnd">
										<option value="all" <c:if test='${searchVO.searchCnd == "" || searchVO.searchCnd == "all"}'> selected </c:if>>전체</option>
										<option value="bbsNm" <c:if test='${searchVO.searchCnd eq "bbsNm"}'> selected </c:if>>게시판명</option>
										<option value="title" <c:if test='${searchVO.searchCnd eq "title"}'> selected </c:if>>제목</option>
									</select>
								</div>
								<div class="input-group col-sm app-search">
									<input type="text" class="form-control" placeholder="검색어 입력" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeypress="javascript:fn_searchKeyPressed(event);">
									<span class="search-icon"></span>
									<div class="input-group-append">
										<button class="btn btn-primary" type="button" onclick="javascript:fnMainBbsList();">검색</button>
									</div>
								</div>
							</div>
						</div>
					</form>
					<div style="display: inline-block;">
						<div class="dt-buttons col-sm-12 mb-3">
							<ul class="nav nav-tabs nav-bordered mb-2">
								<li class="nav-item">
					                <a class="nav-link <c:if test='${searchVO.searchType eq null || empty searchVO.searchType }'>active</c:if>" href="#" onclick="fnType(''); return false;">전체</a>
					            </li>
					            <li class="nav-item">
					                <a class="nav-link <c:if test='${searchVO.searchType eq "A"}'>active</c:if>" href="#" onclick="fnType('A'); return false;">A</a>
					            </li>
					            <li class="nav-item">
					            	<a class="nav-link <c:if test='${searchVO.searchType eq "B" }'>active</c:if>" href="#" onclick="fnType('B'); return false;">B</a>
					        	</li>
					        	<li class="nav-item">
					            	<a class="nav-link <c:if test='${searchVO.searchType eq "C" }'>active</c:if>" href="#" onclick="fnType('C'); return false;">C</a>
					        	</li>
					        	<li class="nav-item">
					            	<a class="nav-link <c:if test='${searchVO.searchType eq "D" }'>active</c:if>" href="#" onclick="fnType('D'); return false;">D</a>
					        	</li>
					        </ul>
						</div>
					</div>
					<div class="text-right" style="display: inline-block; float: right;">
						<button class="btn btn-success" type="button" onclick="fnAddRow();"><span>게시물 추가</span></button>
					</div>
					<div data-simplebar class="table-responsive">
						<table class="table mb-0 table-hover table-middle">
							<thead class="thead-dark">
								<tr>
									<th scope="col">번호</th>
									<th scope="col">구분</th>
									<th scope="col">게시판명</th>
									<th scope="col">제목</th>
									<th scope="col">게시물 등록일</th>
									<th scope="col">게시판 선택</th>
									<th scope="col">사용여부</th>
									<th scope="col">등록일</th>
									<th scope="col"></th>
									<th scope="col">수정</th>
								</tr>
							</thead>
							<tbody id="mainBbsList">
								<c:forEach var="result" items="${resultList}" varStatus="status">
									<tr>
										<td><c:out value="${(paginationInfo.totalRecordCount - status.index) - ( (paginationInfo.currentPageNo - 1)  *  paginationInfo.recordCountPerPage ) }" /></td>
										<td>
											<select class="custom-select" id="type<c:out value="${result.seq}"/>">
												<option value="A" <c:if test="${result.type eq 'A' }">selected</c:if>>A</option>
												<option value="B" <c:if test="${result.type eq 'B' }">selected</c:if>>B</option>
												<option value="C" <c:if test="${result.type eq 'C' }">selected</c:if>>C</option>
												<option value="D" <c:if test="${result.type eq 'D' }">selected</c:if>>D</option>
											</select>
										</td>
										<td>
											<input type="hidden" id="bbsId<c:out value="${result.seq}"/>" value="<c:out value="${result.bbsId}"/>" />
											<input type="hidden" id="nttNo<c:out value="${result.seq}"/>" value="<c:out value="${result.nttNo}"/>" />
											<input type="hidden" id="bbsTp<c:out value="${result.seq}"/>" value="<c:out value="${result.bbsTp}"/>" />
											<input type="text" class="form-control form-control-user w-100" placeholder="게시판명" id="bbsNm<c:out value="${result.seq}"/>" value="<c:out value="${result.bbsNm}"/>" readonly />
										</td>
										<td><input type="text" class="form-control form-control-user w-100" placeholder="제목" id="title<c:out value="${result.seq}"/>" value="<c:out value="${result.title}"/>" readonly /></td>
										<td><input type="text" style="text-align: center;" class="form-control form-control-user w-100" placeholder="게시물 등록일" id="bbsRegDate<c:out value="${result.seq}"/>" value="<c:out value="${fn:substring(result.bbsRegDate,0,10) }"/>" readonly /></td>
										<td>
											<button class="btn btn-secondary" type="button" data-toggle="modal" data-target="#modal01" onclick="javascript:fn_mainBbsModal('1','<c:out value="${result.seq}"/>');"><span>일반 게시판</span></button>
											&nbsp;&nbsp;
											<button class="btn btn-secondary" type="button" data-toggle="modal" data-target="#modal01" onclick="javascript:fn_mainBbsModal('2','<c:out value="${result.seq}"/>');"><span>콘텐츠 게시판</span></button>
										</td>
										<td>
											<input type="checkbox" id="useYn<c:out value="${result.seq}"/>" <c:if test="${result.useYn eq 'Y' }">checked</c:if> data-switch="bool"/>
	                        				<label for="useYn<c:out value="${result.seq}"/>" data-on-label="사용" data-off-label="미사용"></label>
										</td>
										<td><c:out value="${fn:substring(result.regDate,0,10) }"/></td>
										<td><button class="btn btn-success" type="button" onclick="javascript:fn_mainBbsDetail('<c:out value="${result.bbsId}"/>','<c:out value="${result.nttNo}"/>');"><span>게시물 내용보기</span></button></td>
										<td>
											<button type="button" class="btn btn-primary" onclick="fnUpdate('<c:out value="${result.seq}"/>')">저장</button>
											&nbsp;&nbsp;
											<button type="button" class="btn btn-danger" onclick="fnDelete('<c:out value="${result.seq}"/>')">삭제</button>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${empty resultList}">
									<tr class="nodata">
										<td colspan="10">등록된 데이터가 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- end table-responsive -->
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 모달창 -->
<div class="modal fade" id="modal01" tabindex="-1" aria-labelledby="콘텐츠 선택" aria-hidden="true"></div>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />
