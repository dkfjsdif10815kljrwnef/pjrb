<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<%-- <script type="text/javascript" src="<c:url value='/html/cms/ckeditor4/ckeditor.js'/>"></script> --%>

<script>
	/* $(document).ready(function() {
		CKEDITOR.replace('ckeditor',{
			filebrowserImageUploadUrl : '<c:url value="/ckeditor/ckeditorImageUpload.do"/>'
		});
	}); */
	
	$("document").ready(function(){
		
		$("input[type=checkbox]").on("click",function(){
			var id = $(this).attr("id").split("_");
			id = id[1];
			if($(this).attr("id").indexOf("use") > -1){
				if(!$(this).is(":checked")){
					$("#fieldTerm_"+id).prop("checked",false);
					$("#fieldNm_"+id).val('');
					$("#fieldDt_"+id).prop("checked",false);
					$("#fieldStatus_"+id).prop("checked",false);
					$("#fieldSelectSearch_"+id).prop("checked",false);
					$(this).parent().parent().parent().attr("class","col-lg-6");
					$(".fielsStatusSeqDiv_"+id).remove();
					
				}
			}
			
			if($(this).attr("id").indexOf("fieldDt") > -1){
				$("#fieldStatus_"+id).prop("checked",false);
				$(this).parent().parent().parent().attr("class","col-lg-6");
				$(".fielsStatusSeqDiv_"+id).remove();
				if($(this).is(":checked")){
					if($("#fieldTerm_"+id).is(":checked")){
						$("#fieldTerm_"+id).prop("checked",false);
					}
				}
				fn_fieldStatusSet();
			}
			
			if($(this).attr("id").indexOf("fieldTerm") > -1){
				$("#fieldStatus_"+id).prop("checked",false);
				$(this).parent().parent().parent().attr("class","col-lg-6");
				$(".fielsStatusSeqDiv_"+id).remove();
				if($(this).is(":checked")){
					if($("#fieldDt_"+id).is(":checked")){
						$("#fieldDt_"+id).prop("checked",false);
					}
				}
				fn_fieldStatusSet();
			}
			
			if($(this).attr("id").indexOf("fieldStatus") > -1){
				$("#fieldDt_"+id).prop("checked",false);
				$("#fieldTerm_"+id).prop("checked",false);
				$("#fieldSelectSearch_"+id).prop("checked",false);
			}
			
			if($(this).attr("id").indexOf("fieldSelectSearch") > -1){
				$(this).parent().parent().parent().attr("class","col-lg-6");
				$(".fielsStatusSeqDiv_"+id).remove();
				$("#fieldStatus_"+id).prop("checked",false);
			}
		});
		
		$(".useYnChk").on("click",function(){
			
			fn_addSelectFiled();
			fn_fieldStatusSet();
			
		});
		
		
		$(".fieldStatusChk").on("click",function(){
			var seq = $(this).attr("fieldSeq");
			var idx = ($(this).attr("fieldSeq")*1) -1
			var tmp = "";
			
			if($(this).is(":checked")){
				$(this).parent().parent().parent().addClass("col-lg-4");
				$(this).parent().parent().parent().removeClass("col-lg-6");
				tmp += "<div class='col-lg-2 fielsStatusSeqDiv_"+seq+"'>";
				tmp += 		"<div class='form-group'>"
				tmp += 			"<label>상태 기준값 선택</label>"
				tmp += 			"<select class='form-control mb-2 fieldStatusSeqSelect' name='cmsBbsMstVOList["+idx+"].fieldStatusSeq'>";
				tmp += 				"<option value='0'>첨부파일</option>";
				$(".dt").each(function(){
					if($(this).is(":checked") && $("#useYn_"+seq).is(":checked")){
						tmp += 	"<option value='"+$(this).attr("fieldSeq")+"'>여분필드"+$(this).attr("fieldSeq")+"</option>"	
					}
				}); 
				tmp += 			"</select>"
				tmp += 		"</div>";
				tmp += "</div>";
				$(this).parent().parent().parent().after(tmp);
			}else{
				$(this).parent().parent().parent().addClass("col-lg-6");
				$(this).parent().parent().parent().removeClass("col-lg-4");
				$(".fielsStatusSeqDiv_"+seq).remove();
			}
			
			fn_fieldStatusSet();
		});
		
		$("input[name='bbsTp']").on("click",function(){
			
			fn_radio($(this));
			
		});
		
		fn_radio($("input[name='bbsTp']:checked"));
		fn_addSelectFiled();
		
		$("#switchCmd").on("click",function(){
			if($(this).is(":checked")==true){
				$("#cmdYn").val("Y");
			}else{
				$("#cmdYn").val("N")
			}
			
		});
		
	});
	
	function fn_radio(radio){
		
		$(".headListRow").show();
		$("#divAddFiled_1").show();
		
		if(radio.val()=="4"){
			$("#divCmdYn").hide();
		}else{
			$("#divCmdYn").show();
		}
		
		if(radio.val()!="1"){
			for(var i=2; i<11; i++){
				$("#divAddFiled_"+i).hide();
				$(".fieldStatusSeqSelect").hide();
				$("input[name='cmsBbsMstVOList["+(i-1)+"].useYn']").prop("checked",false);
				$("input[name='cmsBbsMstVOList["+(i-1)+"].fieldDtYn']").prop("checked",false);
				$("input[name='cmsBbsMstVOList["+(i-1)+"].fieldTermYn']").prop("checked",false);
				$("input[name='cmsBbsMstVOList["+(i-1)+"].fieldStatusYn']").prop("checked",false);
				$("input[name='cmsBbsMstVOList["+(i-1)+"].fieldSelectSearchYn']").prop("checked",false)
				$("input[name='cmsBbsMstVOList["+(i-1)+"].fieldNm']").val("");
				$("input[name='cmsBbsMstVOList["+(i-1)+"].fieldStatusSeqSelect']").val("");
				if(i<6){
					$("select[name='cmsBbsMstVOList2["+(i-1)+"].seq']").prop("disabled","disabled");
				}
				$(".fielsStatusSeqDiv_"+(i)).remove();
			}
			
			$(".fieldNmDiv").addClass("col-lg-6");
			$(".fieldNmDiv").removeClass("col-lg-4");
			
			if(radio.val()=="2"||radio.val()=="3"){
				$(".headListRow").hide();
				$("#divAddFiled_1").hide();
			}
			
			
		}else{
			for(var i=2; i<11; i++){
				$("#divAddFiled_"+i).show();
				$(".fieldStatusSeqSelect").show();
				if(i<6){
					$("select[name='cmsBbsMstVOList2["+(i-1)+"].seq']").attr("disabled",false);
				}
			}
		}
	}
	
	function fn_addSelectFiled(){
		var temp = "<option value='0' >사용안함</option>";
		var selectValArr = new Array();
		$(".fieldSelect").each(function(){
			selectValArr.push($(this).val());
		});
		$(".useYnChk").each(function(){
			if($(this).is(":checked")){
				var v = $(this).attr("id").replace("useYn_","");
				temp += "<option value='"+v+"'>여분필드"+v+"</option>"
			}
		});
		$(".fieldSelect").empty();
		$(".fieldSelect").append(temp);
		$(".fieldSelect").each(function(i){
			$(this).val(selectValArr[i]);
			if($(this).val() == null){
				$(this).val('0');
			}
		});
	}
	
	function fn_mngList() {
		document.frm.bbsTp.value = "";
		document.frm.action = '<c:url value="/cms/bbsmng/mngList.do"/>';
		document.frm.submit();
	}

	function fn_submit() {
		var bbsTp = $("input[name='bbsTp']:checked").val();
		if($("input[name=bbsNm]").val() == ""){
			alert("게시판명을 입력해 주세요.");
			$("input[name=bbsNm]").focus();
			return;
		}
		
		var isVaild = true;
		if(bbsTp==1||bbsTp==4){
			$(".useYnChk").each(function(){
				if($(this).is(":checked")){
					var v = $(this).attr("id").replace("useYn_","");
					if($("#fieldNm_"+v).val() == ""){
						alert("여분필드명을 입력해 주세요.");
						$("#fieldNm_"+v).focus();
						isVaild = false;
						return false;
					}
				}
			});
		}
		if(!isVaild){
			return;
		}
		
		if(!confirm("저장하시겠습니까?")){
			return;
		}
		
		var bbsId = document.frm.bbsId.value;
		var url = '<c:url value="/cms/bbsmng/mngInsert.do"/>';

		if (bbsId != '') {
			url = '<c:url value="/cms/bbsmng/mngUpdate.do"/>';
		}

		$("#useYn").val("Y");

		status = "N";
		if ($("input:checkbox[id=switch2]").is(":checked") == true) {
			status = "Y";
		}
		$("#noticeYn").val(status);
		
		if(bbsTp==4){
			$("#cmdYn").val("N");
		}
		
		if(bbsTp !=1){
			for(var i=1; i<5; i++){
				$("select[name='cmsBbsMstVOList2["+(i)+"].seq']").attr("disabled",false);
				$("select[name='cmsBbsMstVOList2["+(i)+"].seq'] option:eq(0)").attr("selected",true);
			}
		}
		
		document.frm.action = url;
		document.frm.submit();
	}

	function fn_delete() {
		if (confirm('삭제하시겠습니까?')) {
			var bbsId = document.frm.bbsId.value;
			var url = '<c:url value="/cms/bbsmng/mngDelete.do"/>';

			document.frm.action = url;
			document.frm.submit();
		}
	}
	
	function fn_fieldStatusSet(){
		var temp = "<option value=''>상태 기준값 선택</option><option value='0'>첨부파일</option>";
		var selectValArr = new Array();
		$(".fieldStatusSeqSelect").each(function(){
			selectValArr.push($(this).val());
		});
		$(".dt").each(function(){
			var id = $(this).attr("id").split("_");
			if($(this).is(":checked") && $("#useYn_"+id[1]).is(":checked")){
				var v = $(this).attr("fieldSeq");
				temp += "<option value='"+v+"'>여분필드"+v+"</option>"
			}
		});
		$(".fieldStatusSeqSelect").empty();
		$(".fieldStatusSeqSelect").append(temp);
		$(".fieldStatusSeqSelect").each(function(i){
			$(this).val(selectValArr[i]);
			if($(this).val() == null){
				$(this).val('');
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
				<div class="page-title-right">
				
					<ol class="breadcrumb m-0">
						<li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i>대시보드</a></li>
						<li class="breadcrumb-item active">게시판 관리</li>
					</ol>
				</div>
				<h4 class="page-title">
					게시판 관리
				</h4>
			</div>
		</div>
	</div>
	<!-- end page title -->

	<form class="needs-validation" novalidate id="frm" name="frm" method="post">
		<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
		<input type="hidden" name="pageUnit" value="<c:out value='${searchVO.pageUnit}'/>" />
		<input type="hidden" name="bbsId" value="<c:out value='${searchVO.bbsId}'/>" />
		<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>" />
		<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>" />

		<input type="hidden" id="useYn" name="useYn" value="<c:out value='${result.useYn}'/>" />
		<input type="hidden" id="noticeYn" name="noticeYn" value="<c:out value='${result.noticeYn}'/>" />
		<input type="hidden" id="cmdYn" name="cmdYn" value="<c:out value='${result.cmdYn}'/>" />


		<div class="row">
			<div class="col-xl-12">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label> <i class="fad fa-check"></i>게시판명
									</label>
									<input type="text" class="form-control" required value="<c:out value="${result.bbsNm}"/>" name="bbsNm">
								</div>
							</div>
						</div>
						<div class="row">
						<c:if test="${not empty result }">
							<input type="hidden" name="bbsTp" value="${result.bbsTp }">
						</c:if>
							<div class="col-lg-3">
								<div class="form-group">
									<label class="d-block"><i class="fad fa-check"></i>유형</label> <img src="${pageContext.request.contextPath}/images/cms/bbstype01.png" class="mr-2" />
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="customRadio1" name="bbsTp" class="custom-control-input" value="1" <c:if test="${empty result or result.bbsTp eq '1' }">checked</c:if>
										 <c:if test="${not empty result }"> disabled</c:if>
										>
										<label class="custom-control-label" for="customRadio1" style="top: 5px"><span>리스트</span></label>
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="form-group">
									<label class="d-block">&nbsp;</label> <img src="${pageContext.request.contextPath}/images/cms/bbstype02.png" class="mr-2" />
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="customRadio2" name="bbsTp" class="custom-control-input" value="2" <c:if test="${result.bbsTp eq '2' }">checked</c:if> <c:if test="${not empty result }">disabled</c:if>>
										<label class="custom-control-label" for="customRadio2" style="top: 5px"><span>이미지</span></label>
									</div>
								</div>
							</div>
							<%-- <div class="col-lg-2">
								<div class="form-group">
									<label class="d-block">&nbsp;</label> <img src="${pageContext.request.contextPath}/images/cms/bbstype03.png" class="mr-2" />
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="customRadio7" name="bbsTp" class="custom-control-input" value="7" <c:if test="${result.bbsTp eq '7' }">checked</c:if>>
										<label class="custom-control-label" for="customRadio7" style="top: 5px"><span>포스터형</span></label>
									</div>
								</div>
							</div> --%>
							<div class="col-lg-3">
								<div class="form-group">
									<label class="d-block">&nbsp;</label> <img src="${pageContext.request.contextPath}/images/cms/bbstype03.png" class="mr-2" />
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="customRadio3" name="bbsTp" class="custom-control-input" value="3" <c:if test="${result.bbsTp eq '3' }">checked</c:if> <c:if test="${not empty result }">disabled</c:if>>
										<label class="custom-control-label" for="customRadio3" style="top: 5px"><span>동영상</span></label>
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="form-group">
									<label class="d-block">&nbsp;</label> <img src="${pageContext.request.contextPath}/images/cms/bbstype04.png" class="mr-2" />
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="customRadio4" name="bbsTp" class="custom-control-input" value="4" <c:if test="${result.bbsTp eq '4' }">checked</c:if> <c:if test="${not empty result }">disabled</c:if>>
										<label class="custom-control-label" for="customRadio4" style="top: 5px"><span>묻고답하기</span></label>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label>게시판 옵션</label>
									<div>
										<div class="custom-control custom-checkbox custom-control-inline mr-3">
											<input type="checkbox" id="switch2" <c:if test="${result.noticeYn eq 'Y' }">checked</c:if> class="custom-control-input" />
											<label class="custom-control-label" for="switch2"><span>공지사용</span></label>
										</div>
										<div id="divCmdYn" class="custom-control custom-checkbox custom-control-inline mr-3" <c:if test="${result.bbsTp eq '4'}">style='display=none;'</c:if>>
											<input type="checkbox" id="switchCmd" <c:if test="${result.cmdYn eq 'Y' }">checked</c:if> class="custom-control-input" />
											<label class="custom-control-label" for="switchCmd"><span>댓글사용</span></label>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label>첨부파일 업로드 수</label>
									<select class="form-control" name="atchFileNum">
										<option value="0" <c:if test="${result.atchFileNum == 0 }">selected</c:if>>사용안함</option>
										<option value="1" <c:if test="${result.atchFileNum == 1 }">selected</c:if>>1개</option>
										<option value="2" <c:if test="${result.atchFileNum == 2 }">selected</c:if>>2개</option>
										<option value="3" <c:if test="${result.atchFileNum == 3 }">selected</c:if>>3개</option>
										<option value="4" <c:if test="${result.atchFileNum == 4 }">selected</c:if>>4개</option>
										<option value="5" <c:if test="${result.atchFileNum == 5 }">selected</c:if>>5개</option>
										<option value="6" <c:if test="${result.atchFileNum == 6 }">selected</c:if>>6개</option>
										<option value="7" <c:if test="${result.atchFileNum == 7 }">selected</c:if>>7개</option>
										<option value="8" <c:if test="${result.atchFileNum == 8 }">selected</c:if>>8개</option>
										<option value="9" <c:if test="${result.atchFileNum == 9 }">selected</c:if>>9개</option>
										<option value="10" <c:if test="${result.atchFileNum == 10 }">selected</c:if>>10개</option>
									</select>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label>첨부파일 크기 제한</label>
									<select class="form-control" name="atchFileSize">
					                    <option value="10485760" <c:if test="${result.atchFileSize eq '10485760' }">selected</c:if>>10MB</option>
					                    <option value="52428800" <c:if test="${result.atchFileSize eq '52428800' }">selected</c:if>>50MB</option>
					                    <option value="104857600" <c:if test="${result.atchFileSize eq '104857600' }">selected</c:if>>100MB</option>
					                    <option value="157286400" <c:if test="${result.atchFileSize eq '157286400' }">selected</c:if>>150MB</option>
					                    <option value="314572800" <c:if test="${result.atchFileSize eq '314572800' }">selected</c:if>>300MB</option>
					                    <option value="524288000" <c:if test="${result.atchFileSize eq '524288000' }">selected</c:if>>500MB</option>
					                  </select>
								</div>
							</div>
						</div>
						
						<c:if test="${empty addFieldList }">
							<c:forEach begin = "1" end="10" step="1" varStatus="status">
								<c:if test="${status.index % 2 == 1 }">
									<div class="row">
								</c:if>
									<div class="col-lg-6 fieldNmDiv">
										<div class="form-group" id="divAddFiled_${status.index }">
											<input type="hidden" name="cmsBbsMstVOList[${status.index-1 }].seq" value="${status.index}"/>
											<label> <c:if test="${status.index eq 1}"><i class="fad fa-check"></i></c:if>여분필드명 <c:out value="${status.index }"/></label>
											<input type="text" class="form-control mb-2" required  name="cmsBbsMstVOList[${status.index-1 }].fieldNm" id="fieldNm_${status.index}" >
										
											<div class="custom-control custom-checkbox custom-control-inline mr-3">
												<input type="checkbox" id= "fieldDt_${status.index }" name="cmsBbsMstVOList[${status.index-1 }].fieldDtYn" fieldSeq="${status.index}" value="Y" class="custom-control-input dt" />
												<label class="custom-control-label" for="fieldDt_${status.index }"><span>날짜1</span></label>
											</div>
											
											<div class="custom-control custom-checkbox custom-control-inline mr-3">
												<input type="checkbox" id= "fieldTerm_${status.index }" name="cmsBbsMstVOList[${status.index-1 }].fieldTermYn" value="Y" fieldSeq="${status.index}" class="custom-control-input dt" />
												<label class="custom-control-label" for="fieldTerm_${status.index }"><span>날짜2(기간)</span></label>
											</div>
											<div class="custom-control custom-checkbox custom-control-inline mr-3">
												<input type="checkbox" id= "fieldStatus_${status.index }" name="cmsBbsMstVOList[${status.index-1 }].fieldStatusYn" fieldSeq="${status.index}" value="Y" class="custom-control-input fieldStatusChk" />
												<label class="custom-control-label" for="fieldStatus_${status.index }"><span>상태값 사용</span></label>
											</div>
											<div class="custom-control custom-checkbox custom-control-inline mr-3">
												<input type="checkbox" id= "fieldSelectSearch_${status.index }" name="cmsBbsMstVOList[${status.index-1 }].fieldSelectSearchYn" fieldSeq="${status.index}" value="Y" class="custom-control-input" />
												<label class="custom-control-label" for="fieldSelectSearch_${status.index }"><span>셀렉트 검색사용</span></label>
											</div>
											
											<div class="custom-control custom-checkbox custom-control-inline" >
												<input type="checkbox" value="Y" id="useYn_${status.index }" name="cmsBbsMstVOList[${status.index-1 }].useYn" class="custom-control-input useYnChk" <c:if test="${status.index eq 1}">checked disabled</c:if>/>
												<c:if test="${status.index eq 1}"><input type="hidden" value="Y" name="cmsBbsMstVOList[${status.index-1 }].useYn" class="custom-control-input" ></c:if>
												<label class="custom-control-label" for="useYn_${status.index }"><span>사용 여부</span></label>
											</div>
										</div>
									</div>
								<c:if test="${status.index % 2 == 0 }">
									</div>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${not empty addFieldList }">
							<c:forEach var="ad" items="${addFieldList }" varStatus="status">
								<c:if test="${status.count % 2 == 1 }">
									<div class="row">
								</c:if>
									<div class="fieldNmDiv <c:if test="${ad.fieldStatusYn eq 'Y' }">col-lg-4</c:if><c:if test="${ad.fieldStatusYn ne 'Y' }">col-lg-6</c:if>">
										<div class="form-group" id="divAddFiled_${status.count }">
											<input type="hidden" name="cmsBbsMstVOList[${status.index }].seq" value="${ad.seq}"/>
											<label><c:if test="${status.index eq 0}"><i class="fad fa-check"></i></c:if>여분필드명 <c:out value="${status.count }"/></label>
											<input type="text" class="form-control mb-2" required value="<c:out value="${ad.fieldNm}"/>" name="cmsBbsMstVOList[${status.index }].fieldNm" id="fieldNm_${ad.seq}">
										
											<div class="custom-control custom-checkbox custom-control-inline mr-3">
												<input type="checkbox" id="fieldDt_${status.count }" name="cmsBbsMstVOList[${status.index }].fieldDtYn" value="Y" fieldSeq="${ad.seq}" <c:if test="${ad.fieldDtYn eq 'Y' }">checked</c:if> class="custom-control-input dt" />
												<label class="custom-control-label" for="fieldDt_${status.count }"><span>날짜1</span></label>
											</div>
											<div class="custom-control custom-checkbox custom-control-inline mr-3">
												<input type="checkbox" id= "fieldTerm_${status.count }" name="cmsBbsMstVOList[${status.index }].fieldTermYn" fieldSeq="${ad.seq}" <c:if test="${ad.fieldTermYn eq 'Y' }">checked</c:if> value="Y" class="custom-control-input dt" />
												<label class="custom-control-label" for="fieldTerm_${status.count }"><span>날짜2(기간)</span></label>
											</div>
											<div class="custom-control custom-checkbox custom-control-inline mr-3">
												<input type="checkbox" id="fieldStatus_${status.count }" name="cmsBbsMstVOList[${status.index }].fieldStatusYn" <c:if test="${ad.fieldStatusYn eq 'Y' }">checked</c:if> value="Y" fieldSeq="${ad.seq }" class="custom-control-input fieldStatusChk" />
												<label class="custom-control-label" for="fieldStatus_${status.count }"><span>상태값 사용</span></label>
											</div>
											<div class="custom-control custom-checkbox custom-control-inline mr-3">
												<input type="checkbox" id="fieldSelectSearch_${status.count }" name="cmsBbsMstVOList[${status.index }].fieldSelectSearchYn" <c:if test="${ad.fieldSelectSearchYn eq 'Y' }">checked</c:if> value="Y" fieldSeq="${ad.seq }" class="custom-control-input" />
												<label class="custom-control-label" for="fieldSelectSearch_${status.count }"><span>셀렉트 검색사용</span></label>
											</div>
											<div class="custom-control custom-checkbox custom-control-inline">
												<input type="checkbox" value="Y" id="useYn_${status.count }" name="cmsBbsMstVOList[${status.index }].useYn" <c:if test="${ad.useYn eq 'Y' }">checked</c:if> class="custom-control-input useYnChk"  <c:if test="${ad.seq eq 1}">checked disabled</c:if>/>
												<c:if test="${ad.seq eq 1}"><input type="hidden" value="Y" name="cmsBbsMstVOList[${status.index }].useYn" class="custom-control-input" ></c:if>
												<label class="custom-control-label" for="useYn_${status.count }"><span>사용 여부</span></label>
											</div>
										</div>
									</div>
									<c:if test="${ad.fieldStatusYn eq 'Y' }">
										<div class='col-lg-2 fielsStatusSeqDiv_${status.count }'>
											<div class='form-group'>
												<label>상태 기준값 선택</label>
												<select class='form-control mb-2 fieldStatusSeqSelect' name='cmsBbsMstVOList[${status.index }].fieldStatusSeq'>
													<option value="">상태 기준값 선택</option>
													<c:forEach var="ad2" items="${addFieldList }" varStatus="status2">
														<c:if test="${status2.first }">
															<option value="0" <c:if test="${ad.fieldStatusSeq eq '0' }">selected</c:if>>첨부파일</option>
														</c:if>
														<c:if test="${not empty ad2.fieldStatusSeq and ad2.fieldStatusSeq ne '0'}">
															<option value="${ad2.fieldStatusSeq }" <c:if test="${ad2.fieldStatusSeq eq ad.fieldStatusSeq }">selected</c:if>>여분필드<c:out value="${ad2.fieldStatusSeq }"/></option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
									</c:if>
								<c:if test="${status.count % 2 == 0 }">
									</div>
								</c:if>
							</c:forEach>
						</c:if>
						<div class="row headListRow">
			              <div class="col-lg-12">
			                <div class="form-group">
			                  <label>리스트 표시(리스트 형태)</label>
			                  <div class="table-responsive" data-simplebar>
			                    <table class="table table-middle">
			                      <thead class="thead-dark">
			                        <tr>
			                          <th>번호</th>
			                          <c:if test="${empty tableHead }">
				                        	<c:forEach begin="0" end="4" step="1" varStatus="status">
				                        	  <input type="hidden" name="cmsBbsMstVOList2[${status.index}].sort" value="${status.index}"/>
					                          <th>
					                          <c:choose>
					                              	<c:when test="${status.index eq 0}">
					                              		<input type="hidden" name="cmsBbsMstVOList2[${status.index}].seq" value="1">
					                           			 <select class="form-control fieldSelect" name="cmsBbsMstVOList2[${status.index}].seq" disabled="disabled">
					                            			<option value="<c:out value='1'/>" selected >여분필드1</option>
					                            		 </select>
						                            </c:when>
						                            <c:otherwise>
							                            <select class="form-control fieldSelect" name="cmsBbsMstVOList2[${status.index}].seq" >
							                            	<option value="0" >사용안함</option>
	 													</select>						                            	
						                            </c:otherwise>
												 </c:choose>
					                          </th>
				                          </c:forEach>
			                          </c:if>
			                          <c:if test="${not empty tableHead }">
				                        	<c:forEach var="th" items="${tableHead }" varStatus="status">
				                        	  <input type="hidden" name="cmsBbsMstVOList2[${status.index}].sort" value="${status.index}"/>
					                          <th>
					                           <c:choose>
						                           <c:when test="${status.index eq 0}">
						                          	 	<input type="hidden" name="cmsBbsMstVOList2[${status.index}].seq" value="1">
					                           			 <select class="form-control fieldSelect" name="cmsBbsMstVOList2[${status.index}].seq" disabled="disabled">
					                            			<option value="<c:out value='1'/>" selected >여분필드1</option>
					                            		 </select>
						                           </c:when>
							                       <c:otherwise>
							                            <select class="form-control fieldSelect"  name="cmsBbsMstVOList2[${status.index}].seq">
							                            <option value="0" <c:if test="${th.seq eq '0' }">selected</c:if>>사용안함</option>
							                            <c:forEach var="ad" items="${addFieldList }" varStatus="st">
							                            	<c:if test="${ad.useYn eq 'Y' }">
							                            		<option value="<c:out value='${ad.seq }'/>" <c:if test="${th.seq eq ad.seq  }">selected</c:if>>여분필드<c:out value='${ad.seq }'/></option>
							                            	</c:if>
							                            </c:forEach>
							                            </select>
						                            </c:otherwise>
												 </c:choose>
					                          </th>
				                          </c:forEach>
			                          </c:if>
			                          <th>조회수</th>
			                          <th>작성일</th>
			                        </tr>
			                      </thead>
			                    </table>
			                  </div> <!-- end table-responsive -->
			                </div>
			              </div>
			            </div>
						<div class="btn-group bottom-btn float-right">
							<button type="button" class="btn btn-primary" onclick="javascript:fn_submit();">저장</button>
							<c:if test="${searchVO.bbsId ne ''}">
								<button type="button" class="btn btn-danger" onclick="javascript:fn_delete()">삭제</button>
							</c:if>
							<button type="button" class="btn btn-secondary" onclick="javascript:fn_mngList();">목록</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<!-- /Start Content -->

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />