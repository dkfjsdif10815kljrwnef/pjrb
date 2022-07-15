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
		}
	});
});

function fn_popupChkDel(){
	if($("input:checkbox[id^=chk]:checked").not("#checkall").length == 0){
		alert("선택된 항목이 없습니다.");
		return;
	}

	if (confirm('삭제하시겠습니까?')) {
		var seq = "";
		$("input:checkbox[id^=chk]:checked").each(function(index,element){
			seq += $(this).attr("seq");
			
			if($("input:checkbox[id^=chk]:checked").length-1 != index)
				seq += ",";
		});
		
		var url = '<c:url value="/cms/popup/popupChkDel.do"/>';
		
		var formData = new FormData($("#frm")[0]);
		formData.append("seqList",seq);
		
		$.ajax({
	        type : 'post',
	        url : url,
	        data : formData,
	        enctype : "multipart/form-data",
	        async: false,
	        processData : false,
	        contentType : false,
	        success : function(d) {  
	        	document.frm.action = '<c:url value="/cms/popup/popupList.do"/>';
				document.frm.submit();
	        },
	        error : function(error) {
	           
	        }
	    });
	}
}

function fn_popupStatusUpdate(id, inx){
	var status = "N";
	if($("input:checkbox[id=switch"+inx+"]").is(":checked") == true) {
		status = "Y";
	}
	
	var formData = new FormData($("#frm")[0]);
	formData.delete("seq");
	formData.append("seq",id);
	formData.append("status",status);
	
	$.ajax({
        type : 'post',
        url : '<c:url value="/cms/popup/popupStatusUpdate.do"/>',
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

function fn_popupList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	
	document.frm.action = '<c:url value="/cms/popup/popupList.do"/>';
	document.frm.submit();
}

function fn_popupWrite() {
	document.frm.action = '<c:url value="/cms/popup/popupWrite.do"/>';
	document.frm.submit();
}

function fn_popupModify(seq) {
	document.frm.seq.value = seq;

	document.frm.action = '<c:url value="/cms/popup/popupModify.do"/>';
	document.frm.submit();
}

function fn_recordCountPerPage(){
	var recordCountPerPage = $("#recordCountPerPage option:selected").val();
	document.frm.pageIndex.value = 1;
	document.frm.recordCountPerPage.value = recordCountPerPage;
	
	document.frm.action = '<c:url value="/cms/popup/popupList.do"/>';
	document.frm.submit();
}

function fn_searchKeyPressed(event) {
	if (event.keyCode==13) {
		fn_popupList('1');
	}
}

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
            <li class="breadcrumb-item">사이트</li>
            <li class="breadcrumb-item active">팝업 관리</li>
          </ol>
        </div>
        <h4 class="page-title">팝업 관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
              <form method="post" id="frm" name="frm">
			  <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
			  <input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>"/>
			  <input type="hidden" name="seq" value=""/>
			  
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
					  <option value="title" <c:if test='${searchVO.searchCnd eq "title"}'> selected </c:if>>사이트명</option>
					  <option value="detail" <c:if test='${searchVO.searchCnd eq "detail"}'> selected </c:if>>주소</option>
                    </select>
                  </div>
                  <div class="input-group col-sm app-search">
                    <input type="text" class="form-control" placeholder="검색어 입력" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeypress="javascript:fn_searchKeyPressed(event);">
                    <span class="search-icon"></span>
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button" onclick="javascript:fn_popupList('1');">Search</button>
                    </div>
                  </div>
                </div>
              </div>
              </form>
              
              <div class="dt-buttons col-sm-12 mb-3">
                <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excel('popup','');"><span>엑셀 다운로드</span></button>
                <div class="float-right" style="vertical-align: bottom;">
                 <button class="btn btn-danger mr-2" type="button" onclick="javascript:fn_popupChkDel();"><span>선택 삭제</span></button>
                  <button class="btn btn-success" type="button" onclick="javascript:fn_popupWrite();"><span>팝업 등록</span></button>
                </div>
              </div>
              <div data-simplebar class="table-responsive">
                <table class="table mb-0 table-hover table-middle">
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
                      <th scope="col">시작일시</th>
                      <th scope="col">종료일시</th>
                      <th scope="col">등록일</th>
                      <th scope="col">상태</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach var="result" items="${resultList}" varStatus="status">
	                    <tr>
	                      <td>
	                        <div class="custom-control custom-checkbox">
	                          <input type="checkbox" class="custom-control-input" id="chk${status.index }" seq="<c:out value='${result.seq }'/>">
	                          <label class="custom-control-label" for="chk${status.index }"></label>
	                        </div>
	                      </td>
	                      <td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
	                      <td><a href="javascript:void(0);" onclick="javascript:fn_popupModify('<c:out value="${result.seq }"/>');"><c:out value="${result.title }"/></a></td>
	                      <td><c:out value="${result.startDate }"/>&nbsp;<c:out value="${result.startTime }"/>:00</td>
	                      <td><c:out value="${result.endDate }"/>&nbsp;<c:out value="${result.endTime }"/>:00</td>
	                      <td><c:out value="${fn:substring(result.regDate,0,10) }"/></td>
	                      <td>
	                        <input type="checkbox" id="switch${status.index }" <c:if test="${result.status eq 'Y' }">checked</c:if> data-switch="bool" onclick="javascript:fn_popupStatusUpdate('<c:out value="${result.seq }"/>','<c:out value="${status.index }"/>');"/>
	                        <label for="switch${status.index }" data-on-label="사용" data-off-label="미사용"></label>
	                      </td>
	                    </tr>
	                 </c:forEach>
	                 <c:if test="${resultList == null || empty resultList }">
	                 	<tr class="nodata noprint">
                      		<td colspan="7">등록된 데이터가 없습니다.</td>
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
                    	<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fn_popupList" />
                    </ul>
                  </div>
                </div>
              </div>
        </div> <!-- end card body-->
      </div> <!-- end card -->
    </div><!-- end col-->
  </div>
</div>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />