<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>
function fn_excelUpload(){
	document.excelFrm.action = '<c:url value="/temp/excel/Uploadlist.do"/>';
	document.excelFrm.submit();
}
function fn_egovExcelUpload(){
	document.excelFrm.action = '<c:url value="/egov/excelDownload.do"/>';
	document.excelFrm.submit();
}
function fn_excelDownload(){
	location.href='<c:url value="/temp/excel/Download.do"/>';
}
</script>

<form id="excelFrm" name="excelFrm" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
	<input type="file" style="display: none" id="excelFile" name="excelFile" onchange="javascript:fn_excelUpload()" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
</form>
 <div class="dt-buttons col-sm-12 mb-3">
   <button class="btn btn-secondary flaot-left" type="button" onclick="$('#excelFile').click()"><span>엑셀 업로드</span></button>
   <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excelDownload();"><span>엑셀 다운로드</span></button>
   <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_egovExcelUpload();"><span>엑셀 EGOV 다운로드</span></button>
 </div>
 <div data-simplebar class="table-responsive">
  <table class="table mb-0 table-hover table-middle">
    <thead class="thead-dark">
      <tr>
      	<c:forEach var="th" items="${cellHead}" varStatus="status">
        <th scope="col"><c:out value="${th}"/></th>
        </c:forEach>
      </tr>
    </thead>
    <tbody>
       <c:forEach var="result" items="${excelList}" varStatus="status">
       <tr>
        <c:forEach var="th" items="${cellHead}" varStatus="status">
        <td>${result[th]}</td>
        </c:forEach>       
       </tr>
   	   </c:forEach>
    <c:if test="${excelList == null || empty excelList }">
    	<tr class="nodata noprint">
        		<td colspan="7">등록된 데이터가 없습니다.</td>
       </tr>
    </c:if>
 </tbody>
 </table>
</div> <!-- end table-responsive -->
<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />