<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>

$( document ).ready( function () {
	if("${result.seq}" != "") {
		document.frm.act.value = 'update';
	}
});

function fn_egov_downFile(atchFileId, fileSn){
	window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
}

function fn_egov_deleteFile(atchFileId, fileSn, th) {
	if(confirm('삭제하시겠습니까?')){
		var frm = document.frm;
		
		frm.atchFileId.value = atchFileId;
		frm.fileSn.value = fileSn;
		
	    $.ajax({
	        url : '<c:url value="/cmm/fms/deleteFileInfs2.do"/>',
			data : $("#frm").serializeArray(),
			dataType : 'json',
			type : 'POST',
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
	        success : function(d) {  
	        	$("#bannerDiv .card").remove();
	        	
	        	$("#bannerDiv").append("<div class=\"mb-3\"><input type=\"file\" id=\"file_0\" name=\"file_0\" class=\"form-control\"></div>");
	        },
	        error : function(error) {
	           console.log(error)
	        }
	    });
	}
}

function fn_bannerList() {
	document.frm.action = '<c:url value="/cms/banner/bannerList.do"/>?purpose=<c:out value="${searchVO.purpose}"/>';
	document.frm.submit();
}

function fn_submit() {
	<c:if test="${searchVO.purpose eq '2' }">
		if (document.frm.engYn.value == "") {
			alert("언어 구분을 선택하세요.");
			return false;
		}
	</c:if>
	
	var fileCheck = $('#file_0').val();
	if (fileCheck == "") {
		alert("첨부파일을 등록하세요.");
		return false;
	}
	
	if (document.frm.title.value == "") {
		alert("기관명을 입력하세요.");
		return false;
	}
	
	<c:if test="${searchVO.purpose eq '1' }">
		if (document.frm.bannerDetail.value == "") {
			alert("설명을 입력하세요.");
			return false;
		}
	</c:if>
	
	if (document.frm.detail.value == "") {
		alert("주소를 입력하세요.");
		return false;
	}
	
	var seq = document.frm.seq.value;
	var url = '<c:url value="/cms/banner/bannerInsert.do"/>';
	
	if(document.frm.act.value == 'update'){
		url = '<c:url value="/cms/banner/bannerUpdate.do"/>';
	}
	
	var status = "N";
	if($("input:checkbox[id=switch1]").is(":checked") == true) {
		status = "Y";
	}
	
	/* if($('input:checkbox[id="purpose1"]').is(":checked") && $('input:checkbox[id="purpose1"]').is(":checked")){
		document.frm.purpose.value = '3';	
	}else if($('input:checkbox[id="purpose1"]').is(":checked") && !$('input:checkbox[id="purpose2"]').is(":checked")){
		document.frm.purpose.value = '1';
	}else if(!$('input:checkbox[id="purpose1"]').is(":checked") && $('input:checkbox[id="purpose2"]').is(":checked")){
		document.frm.purpose.value = '2';
	}else{
		document.frm.purpose.value = '0';
	} */
	
	document.frm.status.value = status;
	
	var loginChk = "N";
	if($("input:checkbox[id=switch2]").is(":checked") == true) {
		loginChk = "Y";
	}
	
	document.frm.loginChk.value = loginChk;
	
	var formData = new FormData($("#frm")[0]);
	formData.delete("status");
	formData.delete("loginChk");
	formData.append("status",status);
	formData.append("loginChk",loginChk);
	
	$.ajax({
		type : 'post',
		url : url,
		data : formData,
		enctype : "multipart/form-data",
		async : false,
		processData : false,
		contentType : false,
		success : function(d) {
			document.frm.action = '<c:url value="/cms/banner/bannerList.do"/>?purpose=<c:out value="${searchVO.purpose}"/>';
			document.frm.submit();
		},
		error : function(error) {

		}
	});
}

function fn_purpose(th){
	
	if($(th).is(":checked") == true){
		$('input:checkbox[id="purpose1"]').prop("checked", true);
		$('input:checkbox[id="purpose2"]').prop("checked", true);
	}else{
		$('input:checkbox[id="purpose1"]').prop("checked", false);
		$('input:checkbox[id="purpose2"]').prop("checked", false);
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
			<c:if test="${searchVO.purpose eq '1' }">
				<li class="breadcrumb-item active">배너 관리</li>
			</c:if>
			<c:if test="${searchVO.purpose eq '2' }">
				<li class="breadcrumb-item active">관련사이트 관리</li>
			</c:if>
          </ol>
        </div>
        <c:if test="${searchVO.purpose eq '1' }">
			<h4 class="page-title">배너 관리</h4>
		</c:if>
		<c:if test="${searchVO.purpose eq '2' }">
			<h4 class="page-title">관련사이트 관리</h4>
		</c:if>
      </div>
    </div>
  </div>
  <!-- end page title -->
  
  <form class="needs-validation" novalidate id="frm" name="frm" method="post" enctype="multipart/form-data">
  <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
  <input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>"/>
  <input type="hidden" name="searchStatus" value="<c:out value='${searchVO.searchStatus}'/>"/>
  <input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
  <input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
  <input type="hidden" name="act" value="write" />
  <input type="hidden" name="seq" value="<c:out value='${result.seq}'/>" />
  <input type="hidden" name="atchFileId" value="<c:out value='${result.atchFileId}' />">
  <input type="hidden" name="fileSn" value="">
  <input type="hidden" name="purpose" value="<c:out value='${searchVO.purpose}' />">
  
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
        	<%-- <div class="row">
				<div class="col-lg-12">
					<div class="form-group">
						<label class="d-inline mr-3">배너 및 관련사이트 옵션</label>
						<div class="custom-control custom-checkbox custom-control-inline mr-3">
							<input type="checkbox" id="chkAll" <c:if test="${result.purpose eq '3' }">checked</c:if> class="custom-control-input" onclick="javascript:fn_purpose(this);"/>
							<label class="custom-control-label" for="chkAll"><span>전체선택</span></label>
						</div>
						<div class="custom-control custom-checkbox custom-control-inline mr-3">
							<input type="checkbox" id="purpose1" <c:if test="${result.purpose eq '1' || result.purpose eq '3'}">checked</c:if> class="custom-control-input" />
							<label class="custom-control-label" for="purpose1"><span>배너</span></label>
						</div>
						<div class="custom-control custom-checkbox custom-control-inline mr-3">
							<input type="checkbox" id="purpose2" <c:if test="${result.purpose eq '2' || result.purpose eq '3'}">checked</c:if> class="custom-control-input" />
							<label class="custom-control-label" for="purpose2"><span>관련사이트</span></label>
						</div>
					</div>
				</div>
			</div> --%>
			<c:if test="${searchVO.purpose eq '2' }">
				<div class="col-lg-12">
					<div class="form-group">
						<label class="d-inline mr-3"><i class="fad fa-check"></i>언어</label>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" value="N" name="engYn" id="engYn1" <c:if test="${result.engYn eq 'N' || result.engYn eq null }">checked</c:if>>
							<label class="custom-control-label" for="engYn1"><span>국문</span></label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" value="Y" name="engYn" id="engYn2" <c:if test="${result.engYn eq 'Y' }">checked</c:if>>
							<label class="custom-control-label" for="engYn2"><span>영문</span></label>
						</div>
					</div>
				</div>
			</c:if>
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <c:if test="${result.seq == null || result.seq == '' }">
	        	  	<c:set var="param_flag" value="write" />
	        	  </c:if>
	        	  <c:if test="${result.seq != null && result.seq != '' }">
	        	  	<c:set var="param_flag" value="update" />
	        	  </c:if>
                  <label><i class="fad fa-check"></i>이미지</label>
               	  <c:import url="/common/bbsSelectFile.do" charEncoding="utf-8">         
		        	  <c:param name="param_atchFileId" value='${result.atchFileId}' />
		        	  <c:param name="param_gubun" value="banner" />
		        	  <c:param name="param_flag" value="${param_flag }" />
		          </c:import>
                </div>
              </div>
            </div>
            <c:if test="${searchVO.purpose eq '1' }">
	            <div class="row">
	              <div class="col-lg-4">
	                <div class="form-group">
	                  <label><i class="fad fa-check"></i>기관명</label>
	                  <input type="text" class="form-control" value="<c:out value="${result.title }"/>" required name="title">
	                </div>
	              </div>
	              <div class="col-lg-8">
	                <div class="form-group">
	                  <label><i class="fad fa-check"></i>설명</label>
	                  <input type="text" class="form-control" value="<c:out value="${result.bannerDetail }"/>" required name="bannerDetail">
	                </div>
	              </div>
	            </div>
	            <div class="row">
	              <div class="col-lg-12">
	                <div class="form-group">
	                  <label><i class="fad fa-check"></i>주소</label>
	                  <input type="text" class="form-control" placeholder="http://" value="<c:out value="${result.detail }"/>" required name="detail">
	                </div>
	              </div>
	            </div>
            </c:if>
            <c:if test="${searchVO.purpose eq '2' }">
	            <div class="row">
	              <%-- <div class="col-lg-4">
	                <div class="form-group">
	                  <label><i class="fad fa-check"></i>구분</label>
	                  <input type="text" class="form-control" value="<c:out value="${result.type }"/>" required name="type">
	                </div>
	              </div> --%>
	              <div class="col-lg-4">
	                <div class="form-group">
	                  <label><i class="fad fa-check"></i>기관명</label>
	                  <input type="text" class="form-control" value="<c:out value="${result.title }"/>" required name="title">
	                </div>
	              </div>
	              <div class="col-lg-8">
	                <div class="form-group">
	                  <label><i class="fad fa-check"></i>주소</label>
	                  <input type="text" class="form-control" placeholder="http://" value="<c:out value="${result.detail }"/>" required name="detail">
	                </div>
	              </div>
	            </div>
	        </c:if>
            <div class="row">
              <div class="col-lg-1">
                <div class="form-group">
	                <label><i class="fad fa-check"></i>사용여부</label>
	                <div>
	                    <input type="checkbox" id="switch1" <c:if test="${result.status eq 'Y' }">checked</c:if> data-switch="bool" value="Y" name="status" />
	                    <label for="switch1" data-on-label="사용" data-off-label="미사용"></label>
	                </div>
	            </div>
              </div>
              <div class="col-lg-1">
                <div class="form-group">
	                <label><i class="fad fa-check"></i>로그인 체크</label>
	                <div>
	                    <input type="checkbox" id="switch2" <c:if test="${result.loginChk eq 'Y' }">checked</c:if> data-switch="bool" value="Y" name="loginChk" />
	                    <label for="switch2" data-on-label="사용" data-off-label="미사용"></label>
	                </div>
	            </div>
	          </div>
            </div>
            <div class="btn-group bottom-btn float-right">
              <button type="button" class="btn btn-primary" onclick="javascript:fn_submit();">저장</button>
              <button type="button" class="btn btn-danger" onclick="javascript:fn_bannerList();">취소</button>
            </div>
        </div>
      </div> <!-- end card body-->
    </div> <!-- end card -->
  </div><!-- end col-->
  
  </form>
  
</div>
</div>
<!-- /Start Content -->

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />