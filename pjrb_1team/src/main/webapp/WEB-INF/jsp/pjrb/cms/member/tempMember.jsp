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

function fn_memberChkDel(){
	if($("input:checkbox[id^=chk]:checked").not("#checkall").length == 0){
		alert("선택된 항목이 없습니다.");
		return;
	}

	if (confirm('삭제하시겠습니까?')) {
		var emplyrId = "";
		var type = "";
		$("input:checkbox[id^=chk]:checked").each(function(index,element){
			emplyrId += $(this).attr("emplyrId");
			type += $(this).attr("memberType");
			
			if($("input:checkbox[id^=chk]:checked").length-1 != index){
				emplyrId += ",";
				type += ",";
			}
		});
		
		var url = '<c:url value="/cms/member/memberChkDel.do"/>';
		
		var formData = new FormData($("#frm")[0]);
		formData.append("emplyrIdList",emplyrId);
		formData.append("typeList",type);
		
		$.ajax({
	        type : 'post',
	        url : url,
	        data : formData,
	        enctype : "multipart/form-data",
	        async: false,
	        processData : false,
	        contentType : false,
	        success : function(d) {  
	        	document.frm.action = '<c:url value="/cms/member/memberList.do"/>';
				document.frm.submit();
	        },
	        error : function(error) {
	           
	        }
	    });
	}
}

function fn_memberStatusUpdate(id, inx, type){
	var status = "N";
	if($("input:checkbox[id=status"+inx+"]").is(":checked") == true) {
		status = "Y";
	}
	
	var formData = new FormData($("#frm")[0]);
	formData.delete("emplyrId");
	formData.delete("type");
	formData.append("emplyrId",id);
	formData.append("type",type);
	formData.append("status",status);
	
	$.ajax({
        type : 'post',
        url : '<c:url value="/cms/member/memberStatusUpdate.do"/>',
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

function fn_memberAllowYnUpdate(id, inx){
	var allowYn = "N";
	if($("input:checkbox[id=allowYn"+inx+"]").is(":checked") == true) {
		allowYn = "Y";
	}
	
	var formData = new FormData($("#frm")[0]);
	formData.delete("emplyrId");
	formData.append("emplyrId",id);
	formData.append("allowYn",allowYn);
	
	$.ajax({
        type : 'post',
        url : '<c:url value="/cms/member/memberAllowYnUpdate.do"/>',
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

function fn_gradeChange(id, th){
	var grade = $(th).find('option:selected').val();
	var formData = new FormData($("#frm")[0]);
	formData.delete("emplyrId");
	formData.append("emplyrId",id);
	formData.append("grade",grade);
	
	$.ajax({
        type : 'post',
        url : '<c:url value="/cms/member/memberGradeUpdate.do"/>',
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

function fn_memberList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	
	document.frm.action = '<c:url value="/cms/member/memberList.do"/>';
	document.frm.submit();
}

function fn_memberWrite() {
	document.frm.action = '<c:url value="/cms/member/memberWrite.do"/>';
	document.frm.submit();
}

function fn_memberModify(emplyrId, type) {
	document.frm.emplyrId.value = emplyrId;
	document.frm.type.value = type;

	document.frm.action = '<c:url value="/cms/member/memberModify.do"/>';
	document.frm.submit();
}

function fn_recordCountPerPage(){
	var recordCountPerPage = $("#recordCountPerPage option:selected").val();
	document.frm.pageIndex.value = 1;
	document.frm.recordCountPerPage.value = recordCountPerPage;
	
	document.frm.action = '<c:url value="/cms/member/memberList.do"/>';
	document.frm.submit();
}

function fn_searchKeyPressed(event) {
	if (event.keyCode==13) {
		fn_memberList('1');
	}
}

function fn_searchType(searchType){
	document.frm.searchType.value = searchType;
	fn_memberList('1');
}

</script>

  
<div class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <ol class="breadcrumb m-0">
            <li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
            <li class="breadcrumb-item active">회원관리</li>
          </ol>
        </div>
        <h4 class="page-title">회원관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
		  <ul class="nav nav-tabs nav-bordered mb-4">
            <li class="nav-item">
              <a href="javascript:void(0);" onclick="javascript:fn_searchType('');" class="nav-link active">전체</a>
            </li>
            <li class="nav-item">
              <a href="javascript:void(0);" onclick="javascript:fn_searchType('admin');" class="nav-link">관리자</a>
            </li>
            <li class="nav-item">
              <a href="javascript:void(0);" onclick="javascript:fn_searchType('general');" class="nav-link">일반회원</a>
            </li>
            <li class="nav-item">
              <a href="javascript:void(0);" onclick="javascript:fn_searchType('orgnzt');" class="nav-link">기관회원</a>
            </li>
          </ul> <!-- end nav-->
			
          <form method="post" id="frm" name="frm">
			  <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
			  <input type="hidden" name="recordCountPerPage" value="${searchVO.recordCountPerPage}"/>
			  <input type="hidden" name="emplyrId" value=""/>
			  <input type="hidden" name="searchType" value="<c:out value='${searchVO.searchType}'/>"/>
			  <input type="hidden" name="type" value="<c:out value='${searchVO.searchType}'/>"/>
			  
              <div class="alert alert-light bg-light text-dark sch_wrap">
                <div class="input-group col-sm-12">
                  <div class="input-group col-sm">
                    <select class="custom-select" name="searchCnd">
                      <option value="all" selected >전체</option>
					  <option value="emplyrId"selected>아이디</option>
					  <option value="userNm"  selected>이름</option>
					  <%-- <option value="department" <c:if test='${searchVO.searchCnd eq "department"}'> selected </c:if>>소속</option> --%>
                    </select>
                  </div>
                  <div class="input-group col-sm app-search">
                    <input type="text" class="form-control" placeholder="검색어 입력" name="searchWrd" value="" onkeypress="javascript:fn_searchKeyPressed(event);">
                    <span class="search-icon"></span>
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button" onclick="javascript:fn_memberList('1');">Search</button>
                    </div>
                  </div>
                </div>
              </div>
              </form>

              <div class="dt-buttons col-sm-12 mb-3">
                <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excel('member','');">
                	<span>엑셀 다운로드</span>
                </button>
                <div class="float-right" style="vertical-align: bottom;">
                 <button class="btn btn-danger mr-2" type="button" onclick="javascript:fn_memberChkDel();"><span>선택 삭제</span></button>
                  <button class="btn btn-success" type="button" onclick="javascript:fn_memberWrite();"><span>회원 등록</span></button>
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
                      <th scope="col">구분</th>
                      <th scope="col">아이디</th>
                      <th scope="col">이름</th>
                      <th scope="col">연락처</th>
                      <th scope="col">가입일시</th>
                      <th scope="col">상태</th>                      
                      <c:if test="${searchVO.searchType eq 'orgnzt' }">
                      	<th scope="col">승인여부</th>
                      </c:if>
                      <c:if test="${searchVO.searchType eq 'admin' }">
                      	<th scope="col">권한</th>
                      </c:if>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach var="result" items="${resultList}" varStatus="status">
	                    <tr>
	                      <td>
	                        <div class="custom-control custom-checkbox">
	                          <input type="checkbox" class="custom-control-input chk" id="chk${status.index}" emplyrId="<c:out value='${result.emplyrId}'/>" memberType="<c:out value='${result.type}'/>">
	                          <label class="custom-control-label" for="chk${status.index}"></label>
	                        </div>
	                      </td>
	                      
	                      <td>번호</td>
	                    
                      	 <td>
                      	 	<c:if test="${result.type eq 'admin'}"><div class="badge badge-outline-warning">관리자</div></c:if>
                      	 	<c:if test="${result.type eq 'general'}"><div class="badge badge-outline-primary">일반회원</div></c:if>
                      	 	<c:if test="${result.type eq 'orgnzt'}"><div class="badge badge-outline-success">기관회원</div></c:if>
                      	 </td>
	                      
	                      <td><c:out value="${result.emplyrId }"/></td>
	                      <td><a href="javascript:void(0);" onclick="javascript:fn_memberModify('<c:out value="${result.emplyrId }"/>','<c:out value="${result.type }"/>');"><c:out value="${result.userNm }"/></a></td>
	                      <td><c:out value="${result.phone }"/></td>
	                      <td><c:out value="${fn:substring(result.regDate,0,10) }"/></td>
	                      <td>
	                        <input type="checkbox" id="status${status.index }" <c:if test="${result.status eq 'Y' }">checked</c:if> data-switch="bool" onclick="javascript:fn_memberStatusUpdate('<c:out value="${result.emplyrId }"/>','<c:out value="${status.index }"/>','<c:out value="${result.type }"/>');"/>
	                        <label for="status${status.index }" data-on-label="사용" data-off-label="미사용"></label>
	                      </td>
	                      <c:if test="${searchVO.searchType eq 'orgnzt' }">
		                      <td> 승인여부
		                        <input type="checkbox" id="allowYn"checked data-switch="bool" onclick="javascript:fn_memberAllowYnUpdate('','');"/>
		                        <label for="allowYn" data-on-label="사용" data-off-label="미사용"></label>
		                      </td>
		                  </c:if>
		                  <c:if test="${searchVO.searchType eq 'admin' }">
		                      <td> 권한선택
		                       <select class="form-control" id="grade" onchange="javascript:fn_gradeChange('', this)">
		                        	<option value="">권한 선택</option>
		                        		<option value="" >selected
		                        		</option>
		                        </select>
		                      </td>
		                  </c:if>
	                    </tr>
	                 </c:forEach>
	                 <c:if test="${resultList == null}">
	                 	<tr class="nodata noprint">
                      		<td colspan="8">등록된 데이터가 없습니다.</td>
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
                    	<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fn_memberList" />
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