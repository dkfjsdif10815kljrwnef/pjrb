<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/temp/inc/header" />

<%-- <script type="text/javascript" src="<c:url value='/html/cms/ckeditor4/ckeditor.js'/>"></script> --%>

<script>
function fn_addrList() {
	document.frm.action = '<c:url value="/temp/email/addressList.do"/>';
	document.frm.submit();
}

function fn_submit(){
	
	var fileCheck = $("input:file[id^=file]").length > 0;
	if (fileCheck == false) {
		alert("첨부파일을 등록하세요.");
		return false;
	}
	
	var url = '<c:url value="/temp/fileupload.do"/>';
	
	var status = "N";
	if($("input:checkbox[id=switch1]").is(":checked") == true) {
		status = "Y";
	}
	
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
			document.frm.action = '<c:url value="/temp/email/addressList.do"/>';
			document.frm.submit();
		},
		error : function(error) {

		}
	});

}
</script>

<!-- Start Content -->
<div class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <h4 class="page-title">주소록 관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  
  <form class="needs-validation" novalidate id="frm" name="frm" method="post">
  <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
  <input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>"/>
  <input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
  <input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
  <input type="hidden" name="act" value="write" />
  <input type="hidden" name="seq" value="<c:out value='${result.seq}'/>" />
  <input type="hidden" name="atchFileId" value="<c:out value='${result.atchFileId}' />">
  <input type="hidden" name="fileSn" value="">
  
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
        
          	<div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>이름</label>
                  <input type="text" class="form-control" value="<c:out value="${result.name }"/>" required name="name">
                </div>
              </div>
            </div>
            
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>이메일</label>
                  <input type="text" class="form-control" value="<c:out value="${result.email }"/>" required name="email">
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>전화번호</label>
                  <input type="text" class="form-control" value="<c:out value="${result.phoneNumber }"/>" required name="phoneNumber">
                </div>
              </div>
            </div>

            <div class="row">
				<div class="col-lg-12">
					<div class="form-group">
						<label><i class="fad fa-check"></i>참고이미지 등록</label>
						<c:if test="${result.seq == null || result.seq == '' }">
			        		<c:set var="param_flag" value="write" />
			        	</c:if>
			        	<c:if test="${result.seq != null && result.seq != '' }">
			        	  	<c:set var="param_flag" value="update" />
			        	</c:if>
						<c:import url="/temp/download/bbsSelectFile.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value='${result.atchFileId}' />
							<c:param name="param_gubun" value="banner" />
		        	  		<c:param name="param_flag" value="${param_flag }" />
						</c:import>
					</div>
				</div>
			</div>
			
            <div class="btn-group bottom-btn float-right">
              <button type="button" class="btn btn-primary" onclick="javascript:fn_submit();">저장</button>
              <button type="button" class="btn btn-danger" onclick="javascript:fn_addrList();">취소</button>
            </div>
        </div>
      </div> <!-- end card body-->
    </div> <!-- end card -->
  </div><!-- end col-->
  
  </form>
  
</div>
</div>
<!-- /Start Content -->

<c:import url="/EgovPageLink.do?link=pjrb/temp/inc/footer" />