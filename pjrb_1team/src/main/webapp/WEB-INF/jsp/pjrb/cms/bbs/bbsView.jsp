<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>

function fn_egov_downFile(atchFileId, fileSn){
	window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
}

function fn_bbsList() {
	document.frm.action = '<c:url value="/cms/bbs/bbsList.do"/>';
	document.frm.submit();
}

function getDataFromTheEditor() {
    return theEditor.getData();
}

function fn_bbsModify() {
	document.frm.action = '<c:url value="/cms/bbs/bbsModify.do"/>';
	document.frm.submit();
}

function fn_delete() {
	if (confirm('삭제하시겠습니까?')) {
		var url = '<c:url value="/cms/bbs/bbsDelete.do"/>';
		
		var formData = new FormData($("#frm")[0]);
		
		$.ajax({
	        type : 'post',
	        url : url,
	        data : formData,
	        enctype : "multipart/form-data",
	        async: false,
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
            <li class="breadcrumb-item"><a href="javascript:void(0)">사이트</a></li>
            <li class="breadcrumb-item active">게시판 관리</li>
          </ol>
        </div>
        <h4 class="page-title">게시판 관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
          <form class="needs-validation" novalidate id="frm" name="frm" method="post">
            <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
            <input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>"/>
			<input type="hidden" name="bbsId" value="<c:out value='${searchVO.bbsId}'/>"/>
			<input type="hidden" name="nttId" value="<c:out value='${searchVO.nttId}'/>"/>
            <table class="table mb-0 table-hover">
              <tbody>
                <tr>
                	<th>제목</th>
                  	<td><c:out value="${result.nttSj }"/></td>
                </tr>
                <tr>
                	<th>등록자</th>
                  	<td><c:out value="${result.frstRegisterId }"/></td>
                </tr>
                <tr>
                	<th>등록일</th>
                	<td><c:out value="${fn:substring(result.frstRegistPnttm,0,10) }"/></td>
				</tr>
				<c:if test="${mResult.ntceYn eq 'Y' }">
					<tr>
	                	<th>시작일</th>
	                	<td><c:out value="${result.ntceBgnde }"/></td>
					</tr>
					<tr>
	                	<th>종료일</th>
	                	<td><c:out value="${result.ntceEndde }"/></td>
					</tr>
				</c:if>
                <tr>
                	<th>사용여부</th>
                  	<td>
                  		<c:if test="${result.useAt eq 'Y' }">사용</c:if>
                  		<c:if test="${result.useAt eq 'N' }">미사용</c:if>
                  	</td>
                </tr>
                <c:if test="${mResult.bbsMainYn ne '' }">
	                <tr>
	                	<th>공지여부</th>
	                  	<td>
	                  		<c:if test="${result.mainYn eq '1' }">Y</c:if>
	                  		<c:if test="${empty result.mainYn or result.mainYn eq '0' }">N</c:if>
	                  	</td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle1 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle1 }"/></th>
	                  	<td><c:out value="${result.fieldDetail1 }"/></td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle2 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle2 }"/></th>
	                  	<td><c:out value="${result.fieldDetail2 }"/></td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle3 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle3 }"/></th>
	                  	<td><c:out value="${result.fieldDetail3 }"/></td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle4 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle4 }"/></th>
	                  	<td><c:out value="${result.fieldDetail4 }"/></td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle5 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle5 }"/></th>
	                  	<td><c:out value="${result.fieldDetail5 }"/></td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle6 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle6 }"/></th>
	                  	<td><c:out value="${result.fieldDetail6 }"/></td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle7 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle7 }"/></th>
	                  	<td><c:out value="${result.fieldDetail7 }"/></td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle8 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle8 }"/></th>
	                  	<td><c:out value="${result.fieldDetail8 }"/></td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle9 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle9 }"/></th>
	                  	<td><c:out value="${result.fieldDetail9 }"/></td>
	                </tr>
                </c:if>
                <c:if test="${mResult.fieldTitle10 ne '' }">
	                <tr>
	                	<th><c:out value="${mResult.fieldTitle10 }"/></th>
	                  	<td><c:out value="${result.fieldDetail10 }"/></td>
	                </tr>
                </c:if>
                <c:import url="/common/bbsSelectFile.do" charEncoding="utf-8">        
		        	<c:param name="param_atchFileId" value='${result.atchFileId}' />
		        	<c:param name="param_gubun" value="board" />
		        	<c:param name="param_flag" value="view" />
		        </c:import>
                <tr>
                	<td colspan="2">
						<c:out value='${result.nttCn}' escapeXml="false"/>                	
                	</td>
                </tr>
               </tbody>
             </table>
             <div class="btn-group bottom-btn float-right">
              <button type="button" class="btn btn-warning" onclick="javascript:fn_bbsModify()">수정</button>
              <c:if test="${result.nttId ne '' && result.nttId ne '0'}">
              	<button type="button" class="btn btn-danger" onclick="javascript:fn_delete()">삭제</button>
              </c:if>
              <button type="button" class="btn btn-secondary" onclick="javascript:fn_bbsList()">취소</button>
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