<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>
$(document).ready(function (){
	$("input[type=checkbox]").on("click",function(){
		if($(this).attr("id") == "checkall"){
			if($(this).is(":checked")){
				$("input[id^=chk]").each(function(){
					$(this).prop("checked",true);
				});
			}else{
				$("input[id^=chk]").each(function(){
					$(this).prop("checked",false);
				});
			}
		}else{
			var allCnt = $("input[type=checkbox]").length;
			var chkCnt = $("input[type=checkbox]:checked").length;
			
			if(allCnt != chkCnt){
				$("#checkall").prop("checked",false);
			}
		}
	});
});

function fn_bbsChkDel(){
	if($("input:checkbox[id^=chk]:checked").not("#checkall").length == 0){
		alert("선택된 항목이 없습니다.");
		return;
	}

	if (confirm('삭제하시겠습니까?')) {
		var nttNo = "";
		$("input:checkbox[id^=chk]:checked").each(function(index,element){
			nttNo += $(this).attr("nttNo");
			
			if($("input:checkbox[id^=chk]:checked").length-1 != index)
				nttNo += ",";
		});
		
		var url = '<c:url value="/cms/bbs/bbsContentsChkDel.do"/>';
		
		var formData = new FormData($("#frm")[0]);
		formData.append("nttNo",nttNo);
		
		$.ajax({
	        type : 'post',
	        url : url,
	        data : formData,
	        async: false,
	        processData : false,
	        contentType : false,
	        success : function(d) {  
	        	document.frm.action = '<c:url value="/cms/bbs/bbsList.do"/>';
				document.frm.submit();
	        },
	        error : function(error) {
	           
	        }
	    });
	}
}
function fn_bbsList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	
	document.frm.action = '<c:url value="/cms/bbs/bbsList.do"/>';
	document.frm.submit();
}

function fn_bbsWrite() {
	document.frm.action = '<c:url value="/cms/bbs/bbsWrite.do"/>';
	document.frm.submit();
}

function fn_bbsModify(bbsId, nttNo) {
	document.frm.bbsId.value = bbsId;
	document.frm.nttNo.value = nttNo;

	document.frm.action = '<c:url value="/cms/bbs/bbsModify.do"/>';
	document.frm.submit();
}

function fn_recordCountPerPage(){
	var recordCountPerPage = $("#recordCountPerPage option:selected").val();
	document.frm.pageIndex.value = 1;
	document.frm.recordCountPerPage.value = recordCountPerPage;
	
	document.frm.action = '<c:url value="/cms/bbs/bbsList.do"/>';
	document.frm.submit();
}

function fn_searchKeyPressed(event) {
	if (event.keyCode==13) {
		fn_bbsList('1');
	}
}

function fn_bbsStatusUpdate(bbsId, nttNo, inx){
	var status = "N";
	if($("input:checkbox[id=switch"+inx+"]").is(":checked") == true) {
		status = "Y";
	}
	
	var formData = new FormData($("#frm")[0]);
	formData.delete("bbsId");
	formData.delete("nttNo");
	formData.append("bbsId",bbsId);
	formData.append("nttNo",nttNo);
	formData.append("useYn",status);
	$.ajax({
        type : 'post',
        url : '<c:url value="/cms/bbs/bbsUseAtUpdate.do"/>',
        data : formData,
        enctype : "multipart/form-data",
        async: false,
        processData : false,
        contentType : false,
        success : function(d) {  
        },
        error : function(error) {
        }
    });
}
	/*
	function fn_excel(){
		document.frm.action="<c:url value='/cms/bbs/bbsContentsListExcelDown.do'/>";
		document.frm.submit();
	}
	*/
</script>

<!-- Start Content -->
<div class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <ol class="breadcrumb m-0">
            <li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
            <li class="breadcrumb-item"><a href="javascript:void(0)">게시판</a></li>
            <li class="breadcrumb-item active"><c:out value="${mResult.bbsNm }"/></li>
          </ol>
        </div>
        <h4 class="page-title"><c:out value="${mResult.bbsNm }"/></h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
              <div class="alert alert-light bg-light text-dark sch_wrap">
              	<form method="post" id="frm" name="frm" onsubmit="return false">
					<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
					<input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>"/>
					<input type="hidden" name="bbsId" value="<c:out value='${searchVO.bbsId}'/>"/>
					<input type="hidden" name="nttNo" value="0"/>
					<input type="hidden" name="excelFlag" value="bbsCon"/>
	                <div class="input-group col-sm-12">
	                  <div class="input-group col-sm">
	                  <select class="custom-select" name="searchCnd">
	                      <option value="all" <c:if test='${searchVO.searchCnd == "" || searchVO.searchCnd == "all"}'> selected </c:if>>전체</option>
						  <option value="nttSj" <c:if test='${searchVO.searchCnd eq "nttSj"}'> selected </c:if>>제목</option>
						  <option value="nttCn" <c:if test='${searchVO.searchCnd eq "nttCn"}'> selected </c:if>>내용</option>
	                  </select>  
	                  </div>
	                  <div class="input-group col-sm app-search">
	                    <input type="text" class="form-control" placeholder="검색어 입력" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeypress="javascript:fn_searchKeyPressed(event);">
	                    <span class="search-icon"></span>
	                    <div class="input-group-append">
	                      <button class="btn btn-primary" onclick="javascript:fn_bbsList('1');">Search</button>
	                    </div>
	                  </div>
	                </div>
                </form>
              </div>
              <div class="dt-buttons col-sm-12 mb-3">
                <!-- <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excel();"><span>엑셀 다운로드</span></button> -->
                <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excel('bbsCon','');"><span>엑셀 다운로드</span></button>
                <div class="float-right">
                  <button class="btn btn-danger mr-2" type="button" onclick="javascript:fn_bbsChkDel();"><span>선택 삭제</span></button>
                  <button class="btn btn-success" type="button" onclick="javascript:fn_bbsWrite();"><span>게시물 등록</span></button>
                </div>
              </div>
              <div data-simplebar class="table-responsive">
              	<c:set var="colCnt" value="9"/>
                <table class="table mb-0 table-hover">
                  <thead class="thead-dark">
                    <tr>
                      <th scope="col">
                        <div class="custom-control custom-checkbox">
                          <input type="checkbox" class="custom-control-input checkall" id="checkall">
                          <label class="custom-control-label" for="checkall"></label>
                        </div>
                      </th>
                      <th scope="col">번호</th>
                      <th scope="col">제목</th>
                      <th scope="col">조회수</th>
                      <th scope="col">작성일</th>
                      <c:set var="colLength" value = "5"/>
                  </thead>
                  <tbody>
                  	<c:set var="titleAtag" value=""/>
                  	<c:forEach var="result" items="${resultList}" varStatus="status">
                  		<c:set var="titleAtag" value="Y"/>
	                    <tr>
	                      <td>
	                        <div class="custom-control custom-checkbox">
	                          <input type="checkbox" class="custom-control-input" id="chk${status.index }" nttNo="<c:out value='${result.nttNo }'/>">
	                          <label class="custom-control-label" for="chk${status.index }"></label>
	                        </div>
	                      </td>
	                      <%-- <td class="text-left"><a href="javascript:void(0);" onclick="javascript:fn_bbsModify('<c:out value="${result.bbsId }"/>', '<c:out value="${result.nttNo }"/>');"><c:out value="${result.nttSj }"/></a></td> --%>
	                       <%-- <td><c:if test="${result.atchFileId != null && result.atchFileId ne ''}"><i class="fad fa-file"></i></c:if></td> --%>
					 	  <td>
							<c:choose>
		                      	<c:when test="${mResult.noticeYn eq 'Y' }">
		                      		<c:choose>
		                      			<c:when test="${result.ntcYn eq '1' }"><span class="badge badge-primary badge-pill">공지</span></c:when>
		                      			<c:otherwise>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</c:otherwise>
		                      		</c:choose>
		                      	</c:when>
		                      	<c:otherwise>
		                      		${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }
		                      	</c:otherwise>
	                      	</c:choose>
						  </td>
					 	  <td><a href="javascript:void(0);" onclick="javascript:fn_bbsModify('<c:out value="${result.bbsId }"/>', '<c:out value="${result.nttNo }"/>');"> <c:out value="${result.nttSj}"/> </a> </td>
					 	  <td><c:out value="${result.rdcnt}"/></td>
	                      <td><c:out value="${fn:substring(result.regDate,0,10) }"/></td>
	                      <%-- <td>
	                      	<c:if test="${result.useAt eq 'Y' }"><div class="badge badge-success">발행</div></c:if>
	                      	<c:if test="${result.useAt eq 'N' }"><div class="badge badge-danger">임시저장</div></c:if>
	                      </td> 
	                      <td>
	                        <input type="checkbox" id="switch${status.index }" <c:if test="${result.useAt eq 'Y' }">checked</c:if> data-switch="bool" onclick="javascript:fn_bbsStatusUpdate('<c:out value="${result.bbsId }"/>','<c:out value="${result.nttNo }"/>','<c:out value="${status.index }"/>');"/>
	                        <label for="switch${status.index }" data-on-label="사용" data-off-label="미사용"></label>
	                      </td>--%>
	                    </tr>
                    </c:forEach>
                    <c:if test="${resultList == null || empty resultList }">
	                    <tr class="nodata">
	                      <td colspan="<c:out value='${colLength }'/>">등록된 데이터가 없습니다.</td>
	                    </tr>
                    </c:if>
                  </tbody>
                </table>
              </div> <!-- end table-responsive -->
              <div class="row mt-4">
                <div class="col-sm-12 col-md-5">
                  <div class="dataTables_length"><label>목록개수
                      <select class="custom-select mb-3" id="recordCountPerPage" onchange="javascript:fn_recordCountPerPage();">
                        <option value="10" <c:if test="${searchVO.recordCountPerPage == 10 }">selected</c:if>>10</option>
                        <option value="15" <c:if test="${searchVO.recordCountPerPage == 15 }">selected</c:if>>15</option>
                        <option value="20" <c:if test="${searchVO.recordCountPerPage == 20 }">selected</c:if>>20</option>
                        <option value="30" <c:if test="${searchVO.recordCountPerPage == 30 }">selected</c:if>>30</option>
                        <option value="40" <c:if test="${searchVO.recordCountPerPage == 40 }">selected</c:if>>40</option>
                        <option value="50" <c:if test="${searchVO.recordCountPerPage == 50 }">selected</c:if>>50</option>
                        <option value="100" <c:if test="${searchVO.recordCountPerPage == 100 }">selected</c:if>>100</option>
                      </select></label></div>
                </div>
                <div class="col-sm-12 col-md-7">
                  <div class="dataTables_paginate paging_simple_numbers">
                    <ul class="pagination pagination-rounded">
                    	<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fn_bbsList" />
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