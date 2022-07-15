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

		if ("${mResult.bbsTp}" == "4" && "${result.nttNo}" != '') {
			CKEDITOR.replace('ckeditor2', {
				filebrowserImageUploadUrl : '<c:url value="/ckeditor/ckeditorImageUpload.do"/>'
			});
		}
		
		$("#commentDiv").load("<c:url value='/cms/bbs/bbsCommnetList.do'/>",$("#commentFrm").serialize());
	});

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

	function fn_bbsList() {
		document.frm.action = '<c:url value="/cms/bbs/bbsList.do"/>';
		document.frm.submit();
	}

	function fn_bbsView() {
		document.frm.action = '<c:url value="/cms/bbs/bbsView.do"/>';
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
		var url = '<c:url value="/cms/bbs/bbsInsert.do"/>';

		if (nttNo != '' && nttNo != '0') {
			url = '<c:url value="/cms/bbs/bbsUpdate.do"/>';
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
						<%-- <div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label><i class="fad fa-check"></i>제목</label>
									<input type="text" class="form-control" value="<c:out value="${result.nttSj }"/>" id="nttSj" name="nttSj">
								</div>
							</div>
						</div> --%>
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
						<c:forEach var="ad" items="${addFieldValue }" varStatus="status">
							<input type="hidden" name="cmsBbsVOList[${status.index }].seq" value="<c:out value='${ad.seq }'/>"/>
							<c:if test="${ad.useYn eq 'Y'  and  ad.fieldStatusYn eq 'N' }">
								<div class="row">
					              <div class="col-lg-12">
					                <div class="form-group">
					                  <label><i class="fad fa-check"></i><c:out value="${ad.fieldNm }"/></label>
					                  <c:if test="${ad.fieldDtYn eq 'N' and ad.fieldTermYn eq 'N' }">
					                  	<input type="text" class="form-control addFieldInput"  dtTp="" fieldNm="<c:out value="${ad.fieldNm }"/>" name="cmsBbsVOList[${status.index }].addFieldCn" value="<c:out value='${ad.addFieldCn }'/>">
					                  </c:if>
					                  <c:if test="${ad.fieldDtYn eq 'Y' }">
					                  	<input type="text" class="form-control addFieldInput" readonly="readonly" seq="<c:out value='${ad.seq }'/>" id="dtVal_<c:out value='${status.index }'/>" dtTp="1" fieldNm="<c:out value="${ad.fieldNm }"/>" data-provide="datepicker" data-date-autoclose="true" required name="cmsBbsVOList[${status.index }].addFieldCn" value="<c:out value='${ad.addFieldCn }'/>">
					                  </c:if>
					                  <c:if test="${ad.fieldTermYn eq 'Y' }">
					                  	<input type="text" class="form-control addFieldInput" readonly="readonly" seq="<c:out value='${ad.seq }'/>" id="dtVal_<c:out value='${status.index }'/>" dtTp="2" fieldNm="<c:out value="${ad.fieldNm }"/>" data-toggle="date-picker" data-cancel-class="btn-warning"  name="cmsBbsVOList[${status.index }].addFieldCn" value="<c:out value='${ad.addFieldCn }'/>">
			                  		</c:if>
					                </div>
					              </div>
					            </div>
							</c:if>
							<c:if test="${ad.useYn eq 'N'  }">
								<input type="hidden" name="cmsBbsVOList[${status.index }].addFieldCn" value="<c:out value='${ad.addFieldCn }'/>"/>
							</c:if>
							<c:if test="${ad.fieldStatusYn eq 'Y'  }">
								<input type="hidden" name="cmsBbsVOList[${status.index }].addFieldCn" <c:if test="${ad.fieldStatusSeq eq '0' }">class="addFdFileStatus"</c:if> id="fieldStatusVal_<c:out value='${ad.fieldStatusSeq }'/>" value=""/>
							</c:if>
						</c:forEach>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label><i class="fad fa-check"></i>내용</label>
									<textarea class="textArea" placeholder="" id="ckeditor"><c:out value="${result.nttCn}" /></textarea>
								</div>
							</div>
						</div>
						<c:if test="${mResult.bbsTp eq '4' && not empty result }">
							<!-- q&a형 답글 -->
							<div class="row">
								<div class="col-lg-12">
									<div class="form-group">
										<label><i class="fad fa-check"></i>답변</label>
										<textarea class="textArea" placeholder="" id="ckeditor2"><c:out value="${result.replyCn}" /></textarea>
									</div>
								</div>
							</div>
						</c:if>
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