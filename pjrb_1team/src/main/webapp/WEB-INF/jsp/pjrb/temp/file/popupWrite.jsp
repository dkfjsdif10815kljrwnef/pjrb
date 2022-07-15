<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<%-- <script type="text/javascript" src="<c:url value='/html/cms/ckeditor4/ckeditor.js'/>"></script> --%>

<script>
function fn_popupList() {
	document.frm.action = '<c:url value="/temp/popupList.do"/>';
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
			document.frm.action = '<c:url value="/temp/popupList.do"/>';
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
        <div class="page-title-right">
          <ol class="breadcrumb m-0">
            <li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
            <li class="breadcrumb-item">사이트</li>
            <li class="breadcrumb-item active">팝업 관리</li>
          </ol>
        </div>
        <h4 class="page-title">팝업 관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  
  <form class="needs-validation" novalidate id="frm" name="frm" method="post">
  <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
  <input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>"/>
  <input type="hidden" name="searchStatus" value="<c:out value='${searchVO.searchStatus}'/>"/>
  <input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
  <input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
  <input type="hidden" name="act" value="write" />
  <input type="hidden" name="seq" value="<c:out value='${result.seq}'/>" />
  <input type="hidden" name="detail" value="<c:out value='${result.detail}'/>">
  <input type="hidden" name="atchFileId" value="<c:out value='${result.atchFileId}' />">
  <input type="hidden" name="fileSn" value="">
  
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
          	<div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>팝업 제목</label>
                  <input type="text" class="form-control" value="<c:out value="${result.title }"/>" required name="title">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>링크 주소</label>
                  <input type="text" class="form-control" value="<c:out value="${result.address }"/>" required name="address">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-3">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>시작(일)</label>
                  <input type="text" class="form-control" data-provide="datepicker" data-date-autoclose="true" value="<c:out value="${result.startDate }"/>" required name="startDate">
                </div>
              </div>
              <div class="col-lg-3">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>시작(시간)</label>
                  <select class="form-control" required name="startTime">
                    <option value="">선택</option>
                    <c:forEach var="hour" begin="0" end="23">
						<c:if test="${hour < 10 }">
							<option value="0${hour }" <c:if test="${result.startTime eq hour }">selected</c:if>>0<c:out value="${hour }"/>:00</option>
						</c:if>
						<c:if test="${hour >= 10 }">
							<option value="${hour }" <c:if test="${result.startTime eq hour }">selected</c:if>><c:out value="${hour }"/>:00</option>
						</c:if>
					</c:forEach>
                  </select>
                </div>
              </div>
              <div class="col-lg-3">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>종료(일)</label>
                  <input type="text" class="form-control" data-provide="datepicker" data-date-autoclose="true" value="<c:out value="${result.endDate }"/>" required name="endDate">
                </div>
              </div>
              <div class="col-lg-3">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>종료(시간)</label>
                  <select class="form-control" required name="endTime">
                    <option value="">선택</option>
                    <c:forEach var="hour" begin="0" end="23">
						<c:if test="${hour < 10 }">
							<option value="0${hour }" <c:if test="${result.endTime eq hour }">selected</c:if>>0<c:out value="${hour }"/>:00</option>
						</c:if>
						<c:if test="${hour >= 10 }">
							<option value="${hour }" <c:if test="${result.endTime eq hour }">selected</c:if>><c:out value="${hour }"/>:00</option>
						</c:if>
					</c:forEach>
                  </select>
                </div>
              </div>
            </div>
            <%-- <div class="row">
              <div class="col-lg-6">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>가로 사이즈</label>
                  <input type="text" class="form-control" value="<c:out value="${result.widthSize }"/>" required name="widthSize">
                </div>
              </div>
              <div class="col-lg-6">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>세로 사이즈</label>
                  <input type="text" class="form-control" value="<c:out value="${result.heightSize }"/>" required name="heightSize">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-6">
                <div class="form-group">
                  <label>가로 위치</label>
                  <input type="text" class="form-control" value="<c:out value="${result.widthLocation }"/>" required name="widthLocation">
                </div>
              </div>
              <div class="col-lg-6">
                <div class="form-group">
                  <label>세로 위치</label>
                  <input type="text" class="form-control" value="<c:out value="${result.heightLocation }"/>" required name="heightLocation">
                </div>
              </div>
            </div> --%>
            <%-- <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label>팝업 종류</label>
                  <div>
                    <div class="custom-control custom-radio custom-control-inline">
                      <input type="radio" id="customRadio3" name="flag" class="custom-control-input" value="1" <c:if test="${result.flag eq '1' }">checked</c:if>>
                      <label class="custom-control-label" for="customRadio3"><span>새창</span></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                      <input type="radio" id="customRadio4" name="flag" class="custom-control-input" value="2" checked>
                      <label class="custom-control-label" for="customRadio4"><span>레이어</span></label>
                    </div>
                  </div>
                </div>
              </div>
            </div> --%>
            <input type="hidden" name="flag" value="2">
            <%-- <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>팝업 내용</label>
                  <textarea class="textArea" placeholder="" id="ckeditor"><c:out value="${result.detail}"/></textarea>
                </div>
              </div>
            </div> --%>
            <div class="row">
				<div class="col-lg-12">
					<div class="form-group">
						<label><i class="fad fa-check"></i>첨부파일</label>
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
              <button type="button" class="btn btn-danger" onclick="javascript:fn_popupList();">취소</button>
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