<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />
<script type="text/javascript" src="<c:url value='/html/cms/ckeditor4/ckeditor.js'/>"></script>
<script>
	$(document).ready(function() {
		CKEDITOR.replace('ckeditor',{
			filebrowserImageUploadUrl : '<c:url value="/ckeditor/ckeditorImageUpload.do"/>'
		});
	});
	
	function fn_save(){
		
		if($("#pageNm").val() == ""){
			alert("페이지명을 입력해 주세요.");
			$("#pageNm").focus();
			return;
		}
		if(CKEDITOR.instances.ckeditor.getData() == ""){
			alert("페이지 내용을 입력해 주세요.");
			CKEDITOR.instances.ckeditor.focus();
			return;
		}
		/* if($("#useYn").is(":checked")){
			$("input[name=useYn]").val("Y");
		}else{
			$("input[name=useYn]").val("N");
		}
 */		
		if(!confirm("저장하시겠습니까?")){		
			return;
		}
		
		document.subForm.action="<c:url value='/cms/pageMng/pageMngSave.do'/>";
		document.subForm.submit();
		
	}
	
	function fn_delete(){
		if(!confirm("삭제하시겠습니까?")){
			return;
		}
		document.subForm.action="<c:url value='/cms/pageMng/pageMngDelete.do'/>";
		document.subForm.submit();
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
            <li class="breadcrumb-item"><a href="/cms/main.do"><i class="fal fa-home-alt"></i>대시보드</a></li>
            <li class="breadcrumb-item active">페이지 관리</li>
          </ol>
        </div>
        <h4 class="page-title">페이지 관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
          <form class="needs-validation" name="subForm" method="post">
          	<input type="hidden" name="pageId" value="<c:out value='${result.pageId }'/>"/>
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>페이지명</label>
                  <input type="text" class="form-control" id="pageNm" value="<c:out value='${result.pageNm }'/>" name="pageNm">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>페이지 내용</label>
                  <textarea class="textArea" name="pageContents" placeholder="" id="ckeditor"><c:out value="${result.pageContents}" /></textarea>
                </div>
              </div>
            </div>
            <input name="useYn"  type="hidden" value="Y"/>
            <%-- <div class="row">
              <div class="col-lg-3">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>사용여부</label>
                  <div>
                    <input type="checkbox" id="useYn"  data-switch="bool" value="Y" <c:if test="${result.useYn eq 'Y' }">checked</c:if>/>
                    <label for="useYn" data-on-label="사용" data-off-label="미사용"></label>
                    <input name="useYn"  type="hidden"/>
                  </div>
                </div>
              </div>
            </div> --%>
            <div class="btn-group bottom-btn float-right">
              <button type="button" class="btn btn-primary" onclick="javascript:fn_save();">저장</button>
              <c:if test="${not empty result }">
              	<button type="button" class="btn btn-danger" onclick="javascript:fn_delete();">삭제</button>
              </c:if>
              <button type="button" class="btn btn-secondary" onclick="location.href='/cms/pageMng/pageMngList.do'">목록</button>
            </div>
          </form>
        </div>
      </div> <!-- end card body-->
    </div> <!-- end card -->
  </div><!-- end col-->
</div>
</div>
<!-- /Start Content -->
<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />