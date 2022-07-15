<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	function fnLinkPage(pageNo){
		document.listForm.pageIndex.value = pageNo;
		document.listForm.action = "<c:url value='/cms/fileMng/fileMngList.do'/>";
	  	document.listForm.submit();	
	}
	
	
	function fnSearch(){
		
		document.listForm.action = "<c:url value='/cms/fileMng/fileMngList.do'/>";
	    document.listForm.submit();
	}


	function fn_detail(atchFileId){
		document.dtForm.action="<c:url value='/cms/fileMng/fileMngForm.do'/>"
		document.dtForm.atchFileId.value=atchFileId;
		document.dtForm.submit();
	}
	
	function fn_delete(){
		if(!confirm("삭제하시겠습니까?")){
			return;
		}
		var atchFileId = "";
		$("input[type=checkbox]:checked").not(":first").each(function(){
			atchFileId += $(this).val()+",";
		})
		
		document.dtForm.atchFileId.value = atchFileId;
		document.dtForm.action="<c:url value='/cms/fileMng/fileMngDelete.do'/>";
		document.dtForm.submit();
		
	}
	
	function fn_insert(){
		location.href="<c:url value='/cms/fileMng/fileMngForm.do'/>";
	}
</script>
<form name="dtForm" method="post">
	<input type="hidden" name="atchFileId" value=""/>
</form>
<form id="searchVO" name="listForm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex }'/>"/>
<div class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <ol class="breadcrumb m-0">
            <li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
            <li class="breadcrumb-item"><a href="javascript:void(0);">관리자</a></li>
            <li class="breadcrumb-item active">파일관리</li>
          </ol>
        </div>
        <h4 class="page-title">파일관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
         
          <div class="alert alert-light bg-light text-dark sch_wrap">
            <div class="input-group col-sm-12">
              <div class="input-group col-sm app-search">
              	
                	<input type="text" autocomplete="off" onkeypress="if(event.keyCode == 13){fnSearch();}" class="form-control" name="searchWrd" value="<c:out value='${searchVO.searchWrd }'/>">
                <span class="search-icon"></span>
                <div class="input-group-append">
                  <button class="btn btn-primary" type="button" onclick="fnSearch();">Search</button>
                </div>
              </div>
            </div>
          </div>
          <div class="dt-buttons col-sm-12 mb-3">
                 <button class="btn btn-danger mr-2" type="button" onclick="javascript:fn_delete();"><span>선택 삭제</span></button>
                <div class="float-right">
                  <button class="btn btn-success" type="button" onclick="javascript:fn_insert();"><span>등록</span></button>
                </div>
              </div>
          <div data-simplebar class="table-responsive">
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
                  	<th scope="col">등록일</th>
                </tr>
              </thead>
              <tbody>
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td>
	                        <div class="custom-control custom-checkbox">
	                          <input type="checkbox" class="custom-control-input" id="chk${status.index }" value="<c:out value='${result.atchFileId }'/>" >
	                          <label class="custom-control-label" for="chk${status.index }"></label>
	                        </div>
	                      </td>
						<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
					  <td><a href="javascript:void(0)" onclick="javascript:fn_detail('<c:out value='${result.atchFileId }'/>')" ><c:out value="${result.fileCn}"/></a></td>
					  <td><c:out value="${result.creatDt}"/></td>
					</tr>
				</c:forEach>
				<c:if test="${empty resultList }">
					<tr class="nodata">
						<td colspan="4">등록된 파일이 없습니다.</td>
					</tr>
				</c:if>
              </tbody>
              
            </table>
          </div> <!-- end table-responsive -->
          <div class="row mt-4">
			<div class="col-sm-12 col-md-5">
			  <div class="dataTables_length"><label>목록개수
				  <select class="custom-select mb-3" id="pageUnit" name="pageUnit" onchange="javascript:fnSearch();">
					<option value="10" <c:if test="${searchVO.pageUnit == 10 }">selected</c:if>>10</option>
					<option value="15" <c:if test="${searchVO.pageUnit == 15 }">selected</c:if>>15</option>
					<option value="20" <c:if test="${searchVO.pageUnit == 20 }">selected</c:if>>20</option>
					<option value="30" <c:if test="${searchVO.pageUnit == 30 }">selected</c:if>>30</option>
					<option value="40" <c:if test="${searchVO.pageUnit == 40 }">selected</c:if>>40</option>
					<option value="50" <c:if test="${searchVO.pageUnit == 50 }">selected</c:if>>50</option>
					<option value="100" <c:if test="${searchVO.pageUnit == 100 }">selected</c:if>>100</option>
				  </select></label></div>
			</div>
			<div class="col-sm-12 col-md-7">
			  <div class="dataTables_paginate paging_simple_numbers">
				<ul class="pagination pagination-rounded">
					<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fnLinkPage" />
				</ul>
			  </div>
			</div>
		  </div>
        </div> <!-- end card body-->
      </div> <!-- end card -->
    </div><!-- end col-->
  </div>
</div>
</form>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />
 