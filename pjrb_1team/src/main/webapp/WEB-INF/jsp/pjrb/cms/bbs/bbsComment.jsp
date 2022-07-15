<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
	.btnComment{
	 font-size: 12pt; padding-left: 20px; padding-right: 20px;
	}
	
	.tbComment{
		border-bottom: 1px solid #ddd;
	}

</style>
<script>
	
	function fn_showDiv(flag,index){
		if(flag=="comment"){
			$("#tbComment_"+index).show();
			$("#tbUpdate_"+index).hide();
		}else if(flag ="update"){
			$("#tbUpdate_"+index).show();
			$("#tbComment_"+index).hide();
		}
	}
	
	function fn_cancel(index){
		$("#tbComment_"+index).hide();
		$("#tbUpdate_"+index).hide();
		$("#cn_"+index).val('');
	}
	function fn_cmSubmit(index,key) {
		var cn = $("#cn_"+index).val();
		
		var formData = new FormData($("#cmFrm")[0]);
		var url = '<c:url value="/cms/bbs/bbsCommentInsert.do"/>';
		
		if(key){
			formData.append("parentKey",key)
		}
		
		
		formData.append("cn",cn)
				
		$.ajax({
			type : 'post',
			url : url,
			data : formData,
			async : false,
			processData : false,
			contentType : false,
			success : function(d) {
				$("#commentDiv").load("<c:url value='/cms/bbs/bbsCommnetList.do'/>",$("#cmFrm").serialize());
			},
			error : function(error) {

			}
		});
	}
	
	function fn_cmUpdate(index,seq) {
		var cn = $("#cn_"+index).val();
		
		var formData = new FormData($("#cmFrm")[0]);
		var url = '<c:url value="/cms/bbs/bbsCommentUpdate.do"/>';
		
		formData.append("cn",cn)
		formData.append("seq",seq)
				
		$.ajax({
			type : 'post',
			url : url,
			data : formData,
			async : false,
			processData : false,
			contentType : false,
			success : function(d) {
				$("#commentDiv").load("<c:url value='/cms/bbs/bbsCommnetList.do'/>",$("#cmFrm").serialize());
			},
			error : function(error) {

			}
		});
	}
	
    function fn_cmDelete(seq) {
     var formData = new FormData();	
     formData.append("seq",seq);
     
      if(!confirm("삭제하시겠습니까?")){
  		return;
  		}
       $.ajax({
    		url:"<c:url value='/cms/bbs/bbsCommentDelete.do'/>",
    		data:formData,
      		type:"POST",
      		async : false,
			processData : false,
			contentType : false,
			success:function(){
      		  alert("삭제되었습니다.");
      		$("#commentDiv").load("<c:url value='/cms/bbs/bbsCommnetList.do'/>",$("#cmFrm").serialize());
      	   }
       })
    }
</script>
<c:if test="${empty commentList  }">
	<table class="table table-middle">
		<tr>
			<td style="font-size: 13pt;font-style: inherit;">등록된 댓글이 없습니다.</td>
		</tr>
	</table>
</c:if>
<form id="cmFrm">
<input type="hidden" name="bbsId" value="${cmtBbsId }">
<input type="hidden" name="nttNo" value="${cmtNttNo }">
<input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
<c:forEach var="result" items="${commentList}" varStatus="status">
	<c:if test="${empty result.parentKey}">
		<div style="font-size: 11pt; border-bottom: 1px solid #ddd; padding: 15px;">
			<div style="font-weight: bold;">${result.regId }</div>
			<div>
				<span style="font-size: 10pt;">${result.regDate }</span> 
				<a href="javascript:void(0);" onclick="javascript:fn_showDiv('comment','${status.index}');">댓글</a> 
				<a href="javascript:void(0);" onclick="javascript:fn_showDiv('update','${status.index}');">수정</a> 
				<a href="javascript:void(0);" onclick="javascript:fn_cmDelete('${result.seq}');">삭제</a>
			</div>
			<hr>
			<div style="padding: 15px; white-space: pre;">${result.cn}</div>
		</div>
		<table class="table table-middle" class="tbComment"  id="tbUpdate_${status.index}" style="display: none;">
			<colgroup>
				<col width="90%" >
				<col width="10%" >
			</colgroup>
			<tr>
				<td><textarea rows="5" class="form-control" style="font-size: 11pt;" id="cn_${status.index}"></textarea> </td>
				<td>
					<div>
						<input type="button" class="btn btn-primary btnComment" value="수정"  onclick="javascript:fn_cmUpdate('${status.index}','${result.seq}');">
						<input type="button" class="btn btn-primary btnComment" value="수정취소" onclick="javascript:fn_cancel('${status.index}');">
					</div>
				</td>
			</tr>
		</table>
		<table class="table table-middle" class="tbComment" id="tbComment_${status.index}" style="display: none;">
			<colgroup>
				<col width="90%" >
				<col width="10%" >
			</colgroup>
			<tr>
				<td><textarea rows="5" class="form-control" style="font-size: 11pt;" id="cn_cmt_${status.index}"></textarea> </td>
				<td>
					<div>
						<input type="button" class="btn btn-primary btnComment" value="댓글등록" onclick="javascript:fn_cmSubmit('cmt_${status.index}','${result.seq}');">
						<input type="button" class="btn btn-primary btnComment" value="취소" onclick="javascript:fn_cancel('${status.index}');">
					</div>
				</td>
			</tr>
		</table>
	</c:if>
	<c:forEach var="result2" items="${commentList2}" varStatus="status2">
		<c:if test="${result.seq eq result2.parentKey }">
			<div style="font-size: 11pt; border-bottom: 1px solid #ddd; padding: 15px; background-color: #ddd; margin-left: 45px;">
				<div style="font-weight: bold;">${result2.regId }</div>
				<div>
					<span style="font-size: 10pt;">${result2.regDate }</span> 
					<a href="javascript:void(0);" onclick="javascript:fn_showDiv('update','${status2.index }_${status.index}');">수정</a> 
					<a href="javascript:void(0);" onclick="javascript:fn_cmDelete('${result2.seq}');">삭제</a>
				</div>
				<hr>
				<div style="padding: 15px; white-space: pre;">${result2.cn }</div>
			</div>
			<table class="table table-middle" class="tbComment" id="tbUpdate_${status2.index }_${status.index}" style="display: none;">
				<colgroup>
					<col width="90%" >
					<col width="10%" >
				</colgroup>
				<tr>
					<td><textarea rows="5" class="form-control" style="font-size: 11pt;" id="cn_${status2.index }_${status.index}"></textarea> </td>
					<td>
						<div>
							<input type="button" class="btn btn-primary btnComment" value="수정" onclick="javascript:fn_cmUpdate('${status2.index }_${status.index}','${result2.seq}');" >
							<input type="button" class="btn btn-primary btnComment" value="수정취소" onclick="javascript:fn_cancel('${status2.index }_${status.index}');">
						</div>
					</td>
				</tr>
			</table>
		</c:if>
	</c:forEach>
</c:forEach>
	<br>
	<table class="table table-middle" class="tbComment" id="tbCommentInsert">
		<colgroup>
			<col width="90%" >
			<col width="10%" >
		</colgroup>
		<tr>
			<td><textarea rows="5" class="form-control" style="font-size: 11pt;" id="cn_"></textarea> </td>
			<td>
				<input type="button" class="btn btn-primary btnComment" value="등록" onclick="javascript:fn_cmSubmit('','');">
			</td>
		</tr>
	</table>

	</form>
	<div class="col-sm-12 col-md-7">
       <div class="dataTables_paginate paging_simple_numbers">
           <ul class="pagination pagination-rounded">
           	<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fn_bbsCommentList" />
           </ul>
         </div>
       </div>