<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />
<script>
	function fn_AddRow(){
		var tmp = "<div class=\"mb-3\">";
			tmp +=	"<input type=\"file\" class=\"form-control\" name=\"file\" onchange=\"javascript:fn_checkFileSize(this);\">";
			tmp +="</div>"
		$("#addFile").append(tmp);
	}
	
	function fn_save(){
		if($("input[name=fileCn]").val() == ""){
			alert("제목을 입력해 주세요.");
			$("input[name=fileCn]").focus();
			return;
		}
		if(!confirm("저장하시겠습니까?")){
			return;
		}
		$("input[type=file]").each(function(i){
			if($(this).val() != ""){
				$(this).attr("name","file_"+i);
			}
		})
		
		document.subForm.action="<c:url value='/cms/fileMng/fileMngSave.do'/>";
		document.subForm.submit();
	}
	


	function fn_pjrb_deleteFile(atchFileId, fileSn) {
		if (confirm('삭제하시겠습니까?')) {
			$.ajax({
				url : "<c:url value='/common/ajaxFileDelete.do'/>",
				data : {"atchFileId":atchFileId,"fileSn":fileSn},
				dataType : 'json',
				type : 'POST',
				success : function(d) {
					location.reload();
				},
				error : function(error) {

				}
			});
		}
	}
	
	function fn_urlCopy(atchFileId, fileSn){
		  const t = document.createElement("textarea");
		  document.body.appendChild(t);
		  t.value = "<c:url value='/cmm/fms/FileDown.do?atchFileId=" + atchFileId + "&fileSn=" + fileSn + "'/>";
		  t.select();
		  document.execCommand('copy');
		  document.body.removeChild(t);
		  alert("다운로드 url 복사")
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
            <li class="breadcrumb-item"><a href="javascript:void(0)">관리자</a></li>
            <li class="breadcrumb-item active">파일관리</li>
          </ol>
        </div>
        <h4 class="page-title">파일관리</h4>
      </div>
    </div>
  </div>
  	<form name="subForm" method="post" enctype="multipart/form-data">
  		<input type="hidden" name="atchFileId" value="<c:out value='${result.atchFileId }'/>"/>
		<div class="row">
		    <div class="col-xl-12">
			      <div class="card">
			        <div class="card-body">
			        	<div class="dt-buttons col-sm-12 mb-3">
							<div class="text-right" style="vertical-align: bottom;">
								<button class="btn btn-success" type="button" onclick="javascript:fn_AddRow();">
									<span>파일추가</span>
								</button>
							</div>
						</div>
						<div class="row">
			              <div class="col-lg-12">
			                <div class="form-group">
			                  <label><i class="fad fa-check"></i>제목</label>
			                  	<input type="text" class="form-control" name="fileCn" value="<c:out value='${result.fileCn }'/>" >
			                </div>
			              </div>
			            </div>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<div id="fileInput">
										<c:forEach var="fileVO" items="${resultList}" varStatus="status">
											<div class="card mb-0 shadow-none border mb-3">
												<div class="p-3">
													<div class="row align-items-center">
														<div class="col-auto pl-3">
															<div class="avatar-sm">
																<span class="avatar-title rounded"><i class="fad fa-file"></i></span>
															</div>
														</div>
														<div class="col pl-0">
															<a href="javascript:void(0);" class="text-muted" onclick="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')"><c:out value="${fileVO.orignlFileNm}" /></a>
														</div>
														<div class="col-auto">
															<a href="javascript:void(0);" class="btn btn-link btn-lg text-muted" onclick="javascript:fn_urlCopy('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')" title="복사"><i class="fad fad fa-copy"></i></a>
														</div>
														<div class="col-auto">
															<a href="javascript:void(0);" class="btn btn-link btn-lg text-muted" onclick="javascript:fn_pjrb_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')" title="삭제"><i class="fad fa-trash-alt"></i></a>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
										<div id="addFile">
											<div class="mb-3">
												<input type="file" class="form-control" name="file" onchange="javascript:fn_checkFileSize(this);">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="btn-group bottom-btn float-right">
							<button type="button" class="btn btn-primary" onclick="javascript:fn_save()">저장</button>
			              <button type="button" class="btn btn-secondary" onclick="location.href='/cms/fileMng/fileMngList.do'">취소</button>
			            </div>		
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />