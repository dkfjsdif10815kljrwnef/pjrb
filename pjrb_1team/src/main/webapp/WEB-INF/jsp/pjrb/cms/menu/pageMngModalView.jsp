<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function() {
	
});
function fnSelectManager(id, depart, name, phone) {
	$("#emplyrId").val(id);
	$("#mngDepart").val(depart);
	$("#mngName").val(name);
	$("#mngPhone").val(phone);
}

function fnSearch(){
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/menu/pageManagerListPopup.do' />",
		data:{
			//"checkId": $("#mberId").val()	
			"pageIndex": "1",
			"searchWrd": $("#searchWrd").val(),
			"searchCnd": $("#searchCnd").val()
		},
		//dataType:'json',
		dataType:'html',
		async:false,
		success:function(returnData, status){
			
			if(status == "success") {
				//alert(returnData);
				$("#modal01").html(returnData);
			}else{ 
				alert("ERROR!");return;
			}
		}
	});
}
function fnLinkPage(pageNo){
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/menu/pageManagerListPopup.do' />",
		data:{
			//"checkId": $("#mberId").val()	
			"pageIndex": pageNo,
			"searchWrd": $("#searchWrd").val(),
			"searchCnd": $("#searchCnd").val()
		},
		//dataType:'json',
		dataType:'html',
		async:false,
		success:function(returnData, status){
			
			if(status == "success") {
				//alert(returnData);
				$("#modal01").html(returnData);
			}else{ 
				alert("ERROR!");return;
			}
		}
	});
}

</script>
<style>
.error {color:#FA0606;}
</style>


<form method="post" name="listPopupForm" method="post" onsubmit="return false;">
<input type="hidden" name="pageIndex"/>

	<div class="modal-dialog modal-lg">
	   <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">담당자 선택</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	          <div class="alert alert-light bg-light text-dark sch_wrap">
	            <div class="input-group col-sm-12">
	              <div class="input-group col-sm">
	                <select class="custom-select" id="searchCnd" name="searchCnd">
	                  <option value="all" <c:if test='${searchVO.searchCnd == "" || searchVO.searchCnd == "all"}'> selected </c:if>>전체</option>
					  <option value="emplyrId" <c:if test='${searchVO.searchCnd eq "emplyrId"}'> selected </c:if>>아이디</option>
					  <option value="userNm" <c:if test='${searchVO.searchCnd eq "userNm"}'> selected </c:if>>이름</option>
					  <%-- <option value="department" <c:if test='${searchVO.searchCnd eq "department"}'> selected </c:if>>소속</option> --%>
	                </select>
	              </div>
	              <div class="input-group col-sm app-search">
	                <input type="text" name="searchWrd" value="<c:out value='${searchVO.searchWrd }'/>" class="form-control" placeholder="검색어 입력" id="searchWrd">
	                <span class="search-icon"></span>
	                <div class="input-group-append">
	                  <button class="btn btn-primary" type="submit" onclick="fnSearch();">Search</button>
	                </div>
	              </div>
	            </div>
	          </div>
	          <div data-simplebar class="table-responsive">
	            <table class="table mb-0 table-hover">
	              <thead class="thead-dark">
	                <tr>
	                  <th scope="col">번호</th>
	                  <th scope="col">담당부서</th>
	                  <th scope="col">담당자</th>
	                  <th scope="col">연락처</th>
	                  <th scope="col">등록일</th>
	                  <th scope="col">수정일</th>
	                </tr>
	              </thead>
	              <tbody>
	                              
	                <c:forEach var="result" items="${resultList}" varStatus="status">
	           			<tr>
		                   <td><c:out value="${(paginationInfo.totalRecordCount - status.index) - ( (paginationInfo.currentPageNo - 1)  *  paginationInfo.recordCountPerPage ) }"/></td>
		                   <td><a href="javascript:void(0);" onclick="fnSelectManager('${result.emplyrId }','${result.department}','${result.userNm}','${result.phone}');" data-dismiss="modal"><c:out value="${result.department}"/></a></td>
		                   <td><c:out value="${result.userNm}"/></td>
		                   <td><c:out value="${result.phone}"/></td> 
		                   <td><c:out value="${fn:substring(result.regDate,0,10) }"/></td>
		                   <td><c:out value="${fn:substring(result.modDate,0,10) }"/></td>
	                 	</tr>
	              	</c:forEach>
	              	<c:if test="${empty resultList}">
	       				 <tr class="nodata">
		                   <td colspan="6">등록된 데이터가 없습니다.</td>
		                 </tr>
	              	</c:if>
	                
	              </tbody>
	            </table>
	          </div> <!-- end table-responsive -->
	          <div class="row mt-4">
	            <div class="col-sm-12">
	              <div class="dataTables_paginate paging_simple_numbers">
	                <ul class="pagination pagination-rounded">
	                	<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fnLinkPage" />
	                </ul>
	              </div>
	            </div>
	          </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
	    </div>
    </div>
</form>