<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:import url="/EgovPageLink.do?link=pjrb/temp/inc/header" />
<script>
$( document ).ready( function () {
	$("input[type=checkbox]").on("click",function(){
		var checkall = false //체크박스 전체 체크일경우 true
	
		if ($(this).attr("id") == "checkall") {
			checkall = $(this).is(":checked") ;
			$("input[id^=chk]").each(function(){
				$(this).prop("checked",checkall);
			});
			
		} else {
			checkall = $("input[id^=chk]").length == $("input[id^=chk]:checked").length ;
			$("#checkall").prop("checked", checkall );
			
		}
	})
});




function fn_addrWrite() {
//	document.frm.action = '<c:url value="/temp/email/addressWrite.do"/>';
	document.frm.submit();
}

function fn_addrModify(seq) {
	document.frm.seq.value = seq;

//	document.frm.action = '<c:url value="/temp/email/addressModify.do"/>';
	document.frm.submit();
}

function fn_addrList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	
	document.frm.action = '<c:url value="/temp/email/addressList.do"/>';
	document.frm.submit();
}

function fn_addrChkDel(){
	if($("input:checkbox[id^=chk]:checked").not("#checkall").length == 0){
		alert("선택된 항목이 없습니다.");
		return;
	}	
	
	if (confirm('삭제하시겠습니까')) {
		var seq = "";
		$("input:checkbox[id^=chk]:checked").each(function(index,element){
			seq += $(this).attr("seq") ;
			
			if($("input:checkbox[id^=chk]:checked").length-1 != index) 
				seq += "," ;			
		})
		var url = "<c:url value='/temp/email/delete.do'/>" ;
		
		var formData = new FormData($("#frm")[0]); /// XMLHttpRequest전송을위해 설계된 특수 객체 
		formData.append("seqList", seq);
		
		// Display the key/value pairs
		for (var pair of formData.entries()) {
		    console.log(pair[0]+ ', ' + pair[1]); 
		}
		
		$.ajax({
			type : 'post',
			url : url,
			data : formData,
			enctype : "multipart/form-data", // multipart/form-data(바이너리티 데이터, 파일업로드가 있는 양식요소에 사용, multipart는 폼 데이터가 여러부분으로 나뉘어 서버로 전송됨 )  / enctype을 별도로 지정하지 않을 경우 기본이 application/x-www-form-urlencoded (key=value&key=value 의 형태로 전달하는 기본 Content-type), application/json ({key:value} 형태의 데이터 형식)
			async : false,
			processData : false, // Jquery 내부적으로 쿼리 스트링을 만들어 data파라미터를 전송, 파일전송시엔 이를 피해야 함. 
			contentType: false, // default값이 application/x-www-form-urlencoded이므ㅡ로 multipart로 사용할경우 false 
			success : function(d) {
				document.frm.action = '<c:url value="/temp/email/addressList.do"/>';
				document.frm.submit();
			},
			error : function(error) {
				
			}
			
		})
		
	}
}

function fn_recordCountPerPage() {
	var recordCountPerPage = $("#recordCountPerPage option:selected").val();
	document.frm.pageIndex.value = 1;
	document.frm.recordCountPerPage.value = recordCountPerPage;
	
	document.frm.action = '<c:url value="/temp/email/addressList.do"/>';
	document.frm.submit(); 
}

</script>



<!-- Start Content -->
<div class="">
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
                    <select class="custom-select" name="searchCnd">
                      <option value="all" <c:if test='${searchVO.searchCnd == "" || searchVO.searchCnd == "all"}'> selected </c:if>>전체</option>
					  <option value="email" <c:if test='${searchVO.searchCnd eq "email"}'> selected </c:if>>메일</option>
					  <option value="name" <c:if test='${searchVO.searchCnd eq "name"}'> selected </c:if>>이름</option>
                    </select>
                  </div>


                  <div class="input-group col-sm app-search">
                    <input type="text" class="form-control" placeholder="검색어 입력" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeydown="javascript:if (event.keyCode == 13) { fn_addrList('1'); }">
                    <span class="search-icon"></span>
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button" onclick="javascript:fn_addrList('1');">Search</button>
                    </div>
                  </div>
                </div>
              </div>
              </form>
              
              <div class="dt-buttons col-sm-12 mb-3">
                <div class="float-right" style="vertical-align: bottom;">
                 <button class="btn btn-danger mr-2" type="button" onclick="javascript:fn_addrChkDel();"><span>선택 삭제</span></button>
                 <button class="btn btn-success" type="button" onclick="javascript:fn_addrWrite();"><span>팝업 등록</span></button>
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
                      <th scope="col">이름</th>
                      <th scope="col">이메일</th>
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
	                      <td><a href="javascript:void(0);" onclick="javascript:fn_addrModify('<c:out value="${result.seq }"/>');"><c:out value="${result.name }"/></a></td>
	                      <td><c:out value="${result.email }"/></a></td>
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
                        <option value="1" <c:if test="${searchVO.recordCountPerPage == 1 }">selected</c:if>>1</option>
                        <option value="2" <c:if test="${searchVO.recordCountPerPage == 2 }">selected</c:if>>2</option>
                        <option value="10" <c:if test="${searchVO.recordCountPerPage == 10 }">selected</c:if>>10</option>
                        <option value="30" <c:if test="${searchVO.recordCountPerPage == 30 }">selected</c:if>>30</option>
                        <option value="40" <c:if test="${searchVO.recordCountPerPage == 40 }">selected</c:if>>40</option>
                        <option value="50" <c:if test="${searchVO.recordCountPerPage == 50 }">selected</c:if>>50</option>
                        <option value="100" <c:if test="${searchVO.recordCountPerPage == 100 }">selected</c:if>>100</option>
                      </select></label></div>
                </div>
                <div class="col-sm-12 col-md-7">
                  <div class="dataTables_paginate paging_simple_numbers">
                    <ul class="pagination pagination-rounded">
                    	<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fn_addrList" />
                    </ul>
                  </div>
                </div>
              </div>
        </div> <!-- end card body-->
      </div> <!-- end card -->
    </div><!-- end col-->
  </div>
</div>
<c:import url="/EgovPageLink.do?link=pjrb/temp/inc/footer" />
