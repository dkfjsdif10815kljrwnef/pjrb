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
		url:"<c:url value='/cms/mainBbs/mainBbsListPopup.do' />",
		data:{
			"pageIndex": "1",
			"searchCnd": $("#searchCnd").val(),
			"searchWrd": $("#searchWrd").val(),
			"searchFlag": $("#searchFlag").val(),
			"seq": $("#seq").val()
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

function fnLinkPage(pageNo){
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/mainBbs/mainBbsListPopup.do' />",
		data:{
			"pageIndex": pageNo,
			"searchCnd": $("#searchCnd").val(),
			"searchWrd": $("#searchWrd").val(),
			"searchFlag": $("#searchFlag").val(),
			"seq": $("#seq").val()
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

function selectBBS(bbsId,nttNo,bbsNm,title,bbsTp,bbsRegDate) {
	var seq = $('#seq').val();
	$("#bbsId"+seq).val(bbsId);
	$("#nttNo"+seq).val(nttNo);
	$("#bbsNm"+seq).val(bbsNm);
	$("#title"+seq).val(title);
	$("#bbsTp"+seq).val(bbsTp);
	$("#bbsRegDate"+seq).val(bbsRegDate);
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
</script>

<form method="post" id="bbsfrm" name="bbsfrm" onsubmit="return false">
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>"/>
<input type="hidden" name="bbsId" value=""/>
<input type="hidden" name="searchFlag" id="searchFlag" value="<c:out value='${searchVO.searchFlag}'/>"/>
<input type="hidden" name="seq" id="seq" value="<c:out value='${searchVO.seq}'/>"/>

	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">게시물 선택</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	          <div class="alert alert-light bg-light text-dark sch_wrap">
	            <div class="input-group col-sm-12">
	              <div class="input-group col-sm">
	                <select class="custom-select" name="searchCnd" id="searchCnd">
					  <option value="all" <c:if test='${searchVO.searchCnd == "" || searchVO.searchCnd == "all"}'>selected</c:if>>전체</option>
					  <option value="bbsNm" <c:if test="${searchVO.searchCnd eq 'bbsNm' }">selected</c:if>>게시판명</option>
					  <option value="title" <c:if test="${searchVO.searchCnd eq 'title' }">selected</c:if>>제목</option>
					</select>
	              </div>
	              <div class="input-group col-sm app-search">
	                <input type="text" class="form-control" placeholder="검색어 입력" id="searchWrd" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeypress="javascript:fn_searchKeyPressed(event);">
	                <span class="search-icon"></span>
	                <div class="input-group-append">
	                  <button type="button" class="btn btn-primary" onclick="javascript:fnSearch();">검색</button>
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
	                  <th scope="col">제목</th>
	                  <th scope="col">등록일</th>
	                </tr>
	              </thead>
	              <tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr>
						  <td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
						  <td><c:out value="${result.bbsNm }"/></td>
						  <td><a href="javascript:void(0);" onclick="javascript:selectBBS('<c:out value="${result.bbsId }"/>','<c:out value="${result.nttNo }"/>','<c:out value="${result.bbsNm }"/>','<c:out value="${result.title}"/>','<c:out value="${result.bbsTp}"/>','<c:out value="${fn:substring(result.regDate,0,10) }"/>');" data-dismiss="modal"><c:out value='${result.title}'/></a></td>
						  <td><c:out value="${fn:substring(result.regDate,0,10) }"/></td>
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
