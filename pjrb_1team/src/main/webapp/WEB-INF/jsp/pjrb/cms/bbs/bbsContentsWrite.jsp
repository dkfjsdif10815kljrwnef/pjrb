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
		CKEDITOR.replace('ckeditor', {
			filebrowserImageUploadUrl : '<c:url value="/ckeditor/ckeditorImageUpload.do"/>'
		});
		
		$("input[name='videoFileType']").on("click",function(){
			fn_radio($(this));
		});
		
		fn_radio($("input[name='videoFileType']:checked"));
		$("#commentDiv").load("<c:url value='/cms/bbs/bbsCommnetList.do'/>",$("#commentFrm").serialize());
	});
	
	function fn_radio(radio){
		if(radio.val() == 1){
			$("#divVideoFile").show();
			$("#videoUrl").attr("disabled","disabled");
			$("#videoFile").attr("disabled",false);
			$("#divVideoUrl").hide();
		}else{
			$("#divVideoFile").hide();
			$("#videoFile").attr("disabled","disabled");
			$("#videoUrl").attr("disabled",false);
			$("#divVideoUrl").show();
		}
	}
	function fn_egov_deleteFile(atchFileId, fileSn, th) {
		if (confirm('삭제하시겠습니까?')) {
			$.ajax({
				url : "<c:url value='/common/ajaxFileDelete.do'/>",
				data : {"atchFileId":atchFileId,"fileSn":fileSn},
				dataType : 'json',
				type : 'POST',
				success : function(d) {
					//$("#fileInput .card").remove();
					$(th).parent().parent().parent().parent().remove();
					var cnt = $("input[type=file]").length + 1;
					if (cnt == 1) {
						$("#fileInput").append("<div class=\"mb-3\"><input type=\"file\" name=\"file_1\" class=\"form-control\"></div>");
					} else {
						$("#fileInput").append("<div class=\"mb-3\"><input type=\"file\" name=\"file_"+cnt+"\" class=\"form-control\" onchange=\"javascript:fn_checkFileSize(this);\"></div>");
					}
				},
				error : function(error) {

				}
			});
		}
	}
	
	function fn_egov_deleteThumbFile(atchFileId, fileSn, th) {
		if (confirm('삭제하시겠습니까?')) {
			$.ajax({
				url : "<c:url value='/common/ajaxFileDelete.do'/>",
				data : {"atchFileId":atchFileId,"fileSn":fileSn},
				dataType : 'json',
				type : 'POST',
				success : function(d) {
					//$("#fileInput .card").remove();
					$(th).parent().parent().parent().parent().remove();
					
					$("#thumbnailDiv").append("<div class=\"mb-3\"><input type=\"file\" name=\"fileThumbnail\" class=\"form-control\" onchange=\"javascript:fn_checkThumbnail(this);\"></div>");
				},
				error : function(error) {

				}
			});
		}
	}
	
	function fn_egov_deleteVideoFile(atchFileId, fileSn, th) {
		if (confirm('삭제하시겠습니까?')) {
			$.ajax({
				url : "<c:url value='/common/ajaxFileDelete.do'/>",
				data : {"atchFileId":atchFileId,"fileSn":fileSn},
				dataType : 'json',
				type : 'POST',
				success : function(d) {
					//$("#fileInput .card").remove();
					$(th).parent().parent().parent().parent().remove();
					
					$("#divVideoFile").append("<div class=\"mb-3\"><input type=\"file\" name=\"fileThumbnail\" class=\"form-control\" onchange=\"javascript:fn_checkThumbnail(this);\"></div>");
				},
				error : function(error) {

				}
			});
		}
	}

	function fn_bbsList() {
		document.frm.action = '<c:url value="/cms/bbs/bbsList.do"/>';
		document.frm.submit();
	}

	function fn_submit() {
		/* if($("#nttSj").val() == ""){
			alert("제목을 입력해 주세요.");
			$("#nttSj").focus();
			return;
		} */
		
		var isValid = true;
		$(".addFieldInput").each(function(){
			if($(this).val() == ""){
				alert($(this).attr("fieldNm")+"을 입력해 주세요.");
				$(this).focus();
				isValid = false;
				return false;
				
			}

			if($(this).attr("dtTp") == "1"){
				var seq= $(this).attr("seq");
				$("#fieldStatusVal_"+seq).val($(this).val());
			}
			if($(this).attr("dtTp") == "2"){
				var seq= $(this).attr("seq");
				$("#fieldStatusVal_"+seq).val($(this).val());
			}
			
		});
		
		var isFile = "N";
		if($("input[type=file]").length > 0){
			$("input[type=file]").each(function(){
				if($(this).val() != ""){
					isFile = "Y";
					return false;
				}
			})
		}
		if($(".fa-file").length > 0){
			isFile = "Y";
		}
		
		if($(".addFdFileStatus").length > 0){
			$(".addFdFileStatus").each(function(){
				$(this).val(isFile);
			})
		}
		
		if(!isValid){
			return;
		}
		
		if(CKEDITOR.instances.ckeditor.getData() == ""){
			alert("내용을 입력해 주세요.");
			CKEDITOR.instances.ckeditor.focus();
			return;
		}
		if(!confirm("저장하시겠습니까?")){
			return;
		}
		var nttNo = document.frm.nttNo.value;
		var url = '<c:url value="/cms/bbs/bbsContentsInsert.do"/>';

		if (nttNo != '' && nttNo != '0') {
			url = '<c:url value="/cms/bbs/bbsContentsUpdate.do"/>';
		}

		var formData = new FormData($("#frm")[0]);
		formData.append("nttCn", CKEDITOR.instances.ckeditor.getData());

		if ("${mResult.bbsTp}" == "4" && "${result.nttNo}" != '') {
			formData.append("replyCn", CKEDITOR.instances.ckeditor2.getData());
		}

		$.ajax({
			type : 'post',
			url : url,
			data : formData,
			enctype : "multipart/form-data",
			async : false,
			processData : false,
			contentType : false,
			success : function(d) {
				document.frm.action = '<c:url value="/cms/bbs/bbsList.do"/>';
				document.frm.submit();
			},
			error : function(error) {

			}
		});
	}
	
	function fn_checkFileSize(obj){		
		if(obj.files[0] != undefined){			
			if(${mResult.atchFileSize}*1 < obj.files[0].size*1){
				alert('파일용량을 초과하였습니다. 다른파일을 선택해 주세요.');
				$(obj).val('');
				return;
			}
			var ext = obj.files[0].name
			ext = ext.slice(ext.lastIndexOf(".") + 1).toLowerCase();
			if($.inArray(ext, ['gif','png','jpg','jpeg','doc','docx','xls','xlsx','hwp','pdf','zip','txt']) == -1) {
				alert('등록 할수 없는 파일입니다.');
				$(obj).val('');
				return;
			}
		}
	}
	
	function fn_checkVideoSize(obj){		
		if(obj.files[0] != undefined){			
			if(524288000*1 < obj.files[0].size*1){
				alert('파일용량을 초과하였습니다. 다른파일을 선택해 주세요.');
				$(obj).val('');
				return;
			}
			var ext = obj.files[0].name
			ext = ext.slice(ext.lastIndexOf(".") + 1).toLowerCase();
			if($.inArray(ext, ['mp4','flv']) == -1) {
				alert('등록 할수 없는 파일입니다.');
				$(obj).val('');
				return;
			}
		}
	}
	
	function fn_checkThumbnail(obj){
		var ext = obj.files[0].name
		ext = ext.slice(ext.lastIndexOf(".") + 1).toLowerCase();
		if($.inArray(ext, ['gif','png','jpg']) == -1) {
			alert('등록 할수 없는 파일입니다.');
			$(obj).val('');
			return;
		}
	}
	
	function fn_bbsCommentList(pageNo) {
		$("#cmPageIndex").val(pageNo); 
		$("#commentDiv").load("<c:url value='/cms/bbs/bbsCommnetList.do'/>",$("#commentFrm").serialize());
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
			            <li class="breadcrumb-item"><a href="javascript:void(0)">게시물 관리</a></li>
			            <li class="breadcrumb-item active"><c:out value="${mResult.bbsNm }"/></li>
			          </ol>
				</div>
				<h4 class="page-title"><c:out value="${mResult.bbsNm }"/></h4>
			</div>
		</div>
	</div>
	<!-- end page title -->
	<div class="row">
		<div class="col-xl-12">
			<div class="card">
				<div class="card-body">
					<form class="needs-validation" novalidate id="frm" name="frm" method="post">
						<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
						<input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>" />
						<input type="hidden" name="bbsId" value="<c:out value='${searchVO.bbsId}'/>" />
						<input type="hidden" name="nttNo" value="<c:out value='${searchVO.nttNo}'/>" />
						<input type="hidden" name="atchFileId" value="<c:out value='${result.atchFileId}' />">
						<input type="hidden" name="thumbAtchFileId" value="<c:out value='${result.thumbAtchFileId}' />">
						<input type="hidden" name="videoFileId" value="<c:out value='${result.videoFileId}' />">
<%-- 						<input type="hidden" name="videoFileType" id="videoFileType"  value="${result.videoFileType}"> --%>
						<input type="hidden" name="fileSn" value="">
						<input type="hidden" name="useYn" value="Y">
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<%-- <label class="d-inline mr-3">게시물 옵션</label>
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" class="custom-control-input" value="Y" name="useAt" id="useAt1" <c:if test="${result.useAt eq 'Y' || result.useAt eq null }">checked</c:if>>
										<label class="custom-control-label" for="useAt1"><span>발행</span></label>
									</div>
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" class="custom-control-input" value="N" name="useAt" id="useAt2" <c:if test="${result.useAt eq 'N' }">checked</c:if>>
										<label class="custom-control-label" for="useAt2"><span>임시저장</span></label>
									</div> --%>
									<c:if test="${mResult.noticeYn eq 'Y' }">
										<div class="custom-control custom-radio custom-control-inline">
											<input type="radio" class="custom-control-input" value="1" name="ntcYn" id="ntcYn" <c:if test="${result.ntcYn eq '1' }">checked</c:if>>
											<label class="custom-control-label" for="ntcYn"><span>공지글</span></label>
										</div>
										<div class="custom-control custom-radio custom-control-inline">
											<input type="radio" class="custom-control-input" value="0" name="ntcYn" id="ntcYn2" <c:if test="${empty result.ntcYn or result.ntcYn eq '0' }">checked</c:if>>
											<label class="custom-control-label" for="ntcYn2"><span>일반글</span></label>
										</div>
									</c:if>
									<c:if test="${mResult.noticeYn ne 'Y' }">
										<input type="hidden" name="ntcYn" value="0">
									</c:if>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label><i class="fad fa-check"></i>제목</label>
									<input type="text" class="form-control" value="<c:out value="${result.nttSj }"/>" id="nttSj" name="nttSj">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label><i class="fad fa-check"></i>작성자</label>
									<input type="text" class="form-control" value="관리자" readonly="readonly" name="frstRegisterId">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label>등록일</label>
									<input type="text" class="form-control" readonly="readonly" data-provide="datepicker" data-date-autoclose="true" required data-date-format="yyyy-mm-dd" value="<c:out value="${fn:substring(result.regDate,0,10) }"/>" id="regDate" name="regDate">
								</div>
							</div>
						</div>
						<%-- <c:if test="${mResult.ntceYn eq 'Y' }">
							<div class="row">
								<div class="col-lg-6">
									<div class="form-group">
										<label>시작일</label>
										<input type="text" autocomplete=”off” class="form-control" data-provide="datepicker" data-date-autoclose="true" required data-date-format="yyyy-mm-dd" value="<c:out value="${result.ntceBgnde }"/>" name="ntceBgnde">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="form-group">
										<label>종료일</label>
										<input type="text" autocomplete=”off” class="form-control" data-provide="datepicker" data-date-autoclose="true" required data-date-format="yyyy-mm-dd" value="<c:out value="${result.ntceEndde }"/>" name="ntceEndde">
									</div>
								</div>
							</div>
						</c:if> --%>
						<div class="row">
								<div class="col-lg-12">
									<div class="form-group">
										<label>썸네일</label> 
										<c:import url="/common/bbsSelectFile.do" charEncoding="utf-8">
											<c:param name="param_atchFileId" value='${result.thumbAtchFileId}' />
											<c:param name="param_gubun" value="thumbnail" />
											<c:param name="param_flag" value="write" />
										</c:import>
									</div>
								</div>
						</div>
						<c:if test="${mResult.bbsTp eq 3 }">
							<div class="row">
								<div class="col-lg-12 form-group">
									<label>동영상</label>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-2">
									<div class="form-group">
											<div class="custom-control custom-radio custom-control-inline">
												<input type="radio" id="fileType1" name="videoFileType" class="custom-control-input" value="1"  <c:if test="${empty result or result.videoFileType eq '1' }">checked</c:if>>
												<label class="custom-control-label" for="fileType1" style="top: 5px"><span>첨부파일</span></label>
											</div>
									</div>
								</div>
								<div class="col-lg-10">
									<div class="form-group">
											<div class="custom-control custom-radio custom-control-inline">
												<input type="radio" id="fileType2" name="videoFileType" class="custom-control-input" value="2" <c:if test="${result.videoFileType eq '2'}">checked</c:if>>
												<label class="custom-control-label" for="fileType2" style="top: 5px"><span>URL</span></label>
											</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<div class="form-group">
										<c:import url="/common/bbsSelectFile.do" charEncoding="utf-8">
											<c:param name="param_atchFileId" value='${result.videoFileId}' />
											<c:param name="param_gubun" value="video" />
											<c:param name="param_flag" value="write" />
										</c:import>
									</div>
								</div>
							</div>
						</c:if>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label><i class="fad fa-check"></i>내용</label>
									<textarea class="textArea" placeholder="" id="ckeditor"><c:out value="${result.nttCn}" /></textarea>
								</div>
							</div>
						</div>
						<c:if test="${mResult.atchFileNum > 0 }">
							<div class="row">
								<div class="col-lg-12">
									<div class="form-group">
										<label>첨부파일</label>
										<c:import url="/common/bbsSelectFile.do" charEncoding="utf-8">
											<c:param name="param_atchFileId" value='${result.atchFileId}' />
											<c:param name="param_gubun" value="board" />
											<c:param name="param_flag" value="write" />
										</c:import>
									</div>
								</div>
							</div>
						</c:if>
						<div class="btn-group bottom-btn float-right">
							<button type="button" class="btn btn-primary" onclick="javascript:fn_submit()">저장</button>
							<button type="button" class="btn btn-secondary" onclick="javascript:fn_bbsList()">취소</button>
						</div>
					</form>
					</div>
					<c:if test="${mResult.cmdYn eq 'Y' }" >
						<form id="commentFrm">
							<input type="hidden" name="bbsId" value="${result.bbsId }">
							<input type="hidden" name="nttNo" value="${result.nttNo }">
							<input type="hidden" id="cmPageIndex" name="pageIndex" value="1"/>
						</form>
						<div id="commentDiv">
						</div>
					</c:if>
				</div>
			</div>
			<!-- end card body-->
		</div>
		<!-- end card -->
	</div>
	<!-- end col-->
</div>
</div>
<!-- /Start Content --> 

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />