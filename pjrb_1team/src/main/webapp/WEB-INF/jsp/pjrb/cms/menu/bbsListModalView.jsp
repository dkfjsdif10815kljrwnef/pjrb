<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<script>
function fn_searchKeyPressed(event) {
	if (event.keyCode==13) {
		fnSearch();
	}
}


function fnSearch(){
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/menu/bbsListPopup.do' />",
		data:{
			//"checkId": $("#mberId").val()	
			"pageIndex": "1",
			"searchWrd": $("#searchWrd").val(),
			"bbsTp": $("#bbsTp").val()
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
		url:"<c:url value='/cms/menu/bbsListPopup.do' />",
		data:{
			//"checkId": $("#mberId").val()	
			"pageIndex": pageNo,
			"searchWrd": $("#searchWrd").val(),
			"bbsTp": $("#bbsTp").val()
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

function selectBBS(bbsId)
{
	var tp = "<c:out value='${menuType}'/>";
	if(tp == "1"){
		$("#menuUrl").val("/user/board.do?bbsId="+bbsId);		
	}else{
		
		$("#menuUrl").val("/userEng/board.do?bbsId="+bbsId);
	}
	
}


</script>

<form method="post" id="bbsfrm" name="bbsfrm" onsubmit="return false">
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>"/>
<input type="hidden" name="bbsId" value=""/>

	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">게시판 선택</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	          <div class="alert alert-light bg-light text-dark sch_wrap">
	            <div class="input-group col-sm-12">
	              <div class="input-group col-sm">
	                <select class="custom-select" name="bbsTp" id="bbsTp">
					  <option value="">게시판유형 전체</option>
					  <option value="1" <c:if test="${searchVO.bbsTp eq '1' }">selected</c:if>>리스트형</option>
					  <option value="2" <c:if test="${searchVO.bbsTp eq '2' }">selected</c:if>>이미지형</option>
					  <option value="3" <c:if test="${searchVO.bbsTp eq '3' }">selected</c:if>>영상형</option>
					  <option value="4" <c:if test="${searchVO.bbsTp eq '4' }">selected</c:if>>Q&A형</option>
					  <option value="5" <c:if test="${searchVO.bbsTp eq '5' }">selected</c:if>>비밀글</option>
					  <option value="5" <c:if test="${searchVO.bbsTp eq '6' }">selected</c:if>>구인/구직</option>
					</select>
	              </div>
	              <div class="input-group col-sm app-search">
	                <input type="text" class="form-control" placeholder="게시판명" id="searchWrd" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeypress="javascript:fn_searchKeyPressed(event);">
	                <span class="search-icon"></span>
	                <div class="input-group-append">
	                  <button type="button" class="btn btn-primary" onclick="javascript:fnSearch();">Search</button>
	                </div>
	              </div>
	            </div>
	          </div>
	          <div data-simplebar class="table-responsive">
	            <table class="table mb-0 table-hover">
	              <thead class="thead-dark">
	                <tr>
	                  <th scope="col">번호</th>
	                  <th scope="col">게시판명</th>
	                  <th scope="col">유형</th>
	                  <th scope="col">사용여부</th>
	                  <th scope="col">등록일</th>
	                  <th scope="col">최종수정일</th>
	                </tr>
	              </thead>
	              <tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr>
						  <td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
						  <td><a href="javascript:void(0);" onclick="javascript:selectBBS('<c:out value="${result.bbsId }"/>');" data-dismiss="modal"><c:out value="${result.bbsNm }"/></a></td>
						  <td>
							<c:if test="${result.bbsTp eq '1' }">리스트형</c:if>
							<c:if test="${result.bbsTp eq '2' }">이미지형</c:if>
							<c:if test="${result.bbsTp eq '3' }">영상형</c:if>
							<c:if test="${result.bbsTp eq '4' }">Q&A형</c:if>
							<c:if test="${result.bbsTp eq '5' }">비밀글</c:if>
							<c:if test="${result.bbsTp eq '6' }">구인/구직</c:if>
						  </td>
						  <td>
							<c:if test="${result.useYn eq 'Y' }"><div class="badge badge-success">사용</div></c:if>
							<c:if test="${result.useYn eq 'N' }"><div class="badge badge-danger">미사용</div></c:if>
						  </td>
						  <td><c:out value="${fn:substring(result.cdate,0,10) }"/></td>
						  <td><c:out value="${fn:substring(result.mdate,0,10) }"/></td>
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
