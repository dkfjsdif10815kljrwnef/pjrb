<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${gubun eq 'board' || gubun eq 'proposal' }">
	<div id="fileInput">
		<c:if test="${flag eq 'view' || flag eq 'write' }">
			<c:forEach var="fileVO" items="${fileList}" varStatus="status">
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
							<c:if test="${flag eq 'write' }">
								<div class="col-auto">
									<a href="javascript:void(0);" class="btn btn-link btn-lg text-muted" onclick="javascript:fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>',this)" title="삭제"><i class="fad fa-trash-alt"></i></a>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${gubun eq 'board' && flag eq 'write' }">
			<%-- <c:if test="${fileListCnt > 0 }">
				<br>
			</c:if> --%>
			<c:forEach var="index" begin="1" end="${mResult.atchFileNum - fileListCnt }" varStatus="status">
				<div class="mb-3">
					<input type="file" class="form-control" name="file_<c:out value="${index }"/>" onchange="javascript:fn_checkFileSize(this);">
				</div>
			</c:forEach>
		</c:if>
		
		<c:if test="${gubun eq 'proposal' }">
			<c:forEach var="index" begin="1" end="${5-fileListCnt }" varStatus="status">
				<div class="mb-3">
					<input type="file" class="form-control" name="file_<c:out value="${index }"/>">
				</div>
			</c:forEach>
		</c:if>
	</div>
</c:if>

<c:if test="${gubun eq 'userBoard' || gubun eq 'userProposal' }">
	<c:if test="${!empty fileList }">
		<tr>
			<c:if test="${flag eq 'view_eng' }">
				<th>file</th>
			</c:if>
			<c:if test="${flag eq 'view' }">
				<th>${menuUrl }첨부파일</th>
			</c:if>
			<td <c:if test="${gubun eq 'userProposal' }">colspan="3"</c:if>>
				<c:forEach var="fileVO" items="${fileList}" varStatus="status">
					<a href="javascript:;" class="attach" onclick="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')"><c:out value="${fileVO.orignlFileNm}" /></a>
				</c:forEach>
			</td>
		</tr>
	</c:if>
</c:if>


<c:if test="${gubun eq 'banner' }">
	<div id="bannerDiv">
		<c:if test="${flag eq 'write' }">
			<div class="mb-3"><input type="file" class="form-control" id="file_0" name="file_0"></div>
		</c:if>
		<c:if test="${flag ne 'write' }">
			<c:if test="${fileListCnt == 0}">
				<div class="mb-3"><input type="file" class="form-control" id="file_0" name="file_0"></div>
			</c:if>
		</c:if>
		<c:forEach var="fileVO" items="${fileList}" varStatus="status">
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
								<a href="javascript:void(0);" class="btn btn-link btn-lg text-muted" onclick="javascript:fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>',this)" title="삭제"><i class="fad fa-trash-alt"></i></a>
							</div>
						</div>
					</div>
				</div>
			<%-- <span class="file-name"> <a href="javascript:void(0);" onclick="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')"><c:out value="${fileVO.orignlFileNm}" /></a> <a href="javascript:void(0);" onclick="javascript:fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>',this)" title="삭제"><i class="fal fa-times"></i></a>
			</span> --%>
		</c:forEach>
	</div>
</c:if>

<c:if test="${gubun eq 'thumbnail' }">
	<div id="thumbnailDiv">
		<c:if test="${not empty fileList }">
			<div class="card mb-0 shadow-none border mb-3">
					<div class="p-3">
						<div class="row align-items-center">
							<div class="col-auto pl-3">
								<div class="avatar-sm">
									<span class="avatar-title rounded"><i class="fad fa-file"></i></span>
								</div>
							</div>
							<div class="col pl-0">
								<a href="javascript:void(0);" class="text-muted" onclick="javascript:fn_egov_downFile('<c:out value="${fileList[0].atchFileId}"/>','<c:out value="${fileList[0].fileSn}"/>')"><c:out value="${fileList[0].orignlFileNm}" /></a>
							</div>
							<c:if test="${flag eq 'write' }">
								<div class="col-auto">
									<a href="javascript:void(0);" class="btn btn-link btn-lg text-muted" onclick="javascript:fn_egov_deleteThumbFile('<c:out value="${fileList[0].atchFileId}"/>','<c:out value="${fileList[0].fileSn}"/>',this)" title="삭제"><i class="fad fa-trash-alt"></i></a>
								</div>
							</c:if>
						</div>
					</div>
					<img id="preImg1" src="<c:url value='/cmm/fms/getImage.do?atchFileId=${fileList[0].atchFileId}&fileSn=${fileList[0].fileSn}'/>" style="width: 236px; height: auto; border: 1px solid #ccc;">
				</div>
		</c:if>
		<p style="font-style: oblique; font-size: 11pt; color: red;"> ※ 포맷: jpg, gif, png</p>
		<c:if test="${empty fileList }">
			<div class="mb-3">
				<input type="file" class="form-control" name="fileThumbnail" onchange="javascript:fn_checkThumbnail(this);">
			</div>
		</c:if>
	</div>
</c:if>
<c:if test="${gubun eq 'video'}">
	<div id="divVideoFile">
	<c:if test="${not empty fileList }">
		<div class="card mb-0 shadow-none border mb-3">
			<div class="p-3">
				<div class="row align-items-center">
					<div class="col-auto pl-3">
						<div class="avatar-sm">
							<span class="avatar-title rounded"><i class="fad fa-file"></i></span>
						</div>
					</div>
					<div class="col pl-0">
						<a href="javascript:void(0);" class="text-muted" onclick="javascript:fn_egov_downFile('<c:out value="${fileList[0].atchFileId}"/>','<c:out value="${fileList[0].fileSn}"/>')"><c:out value="${fileList[0].orignlFileNm}" /></a>					</div>
					<c:if test="${flag eq 'write' }">
						<div class="col-auto">
							<a href="javascript:void(0);" class="btn btn-link btn-lg text-muted" onclick="javascript:fn_egov_deleteVideoFile('<c:out value="${fileList[0].atchFileId}"/>','<c:out value="${fileList[0].fileSn}"/>',this)" title="삭제"><i class="fad fa-trash-alt"></i></a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${empty fileList }">
		<div class="mb-3">
			<input type="file" class="form-control" id="videoFile" name="videoFile" onchange="javascript:fn_checkVideoSize(this);">
		</div>
	</c:if>
	</div>
	<div id="divVideoUrl" style="display: none;">
		<div class="row">
			<div class="col-lg-12">
				<div class="form-group">
					<label>동영상URL</label>
					<input type="text" class="form-control" id="videoUrl" name="videoUrl" >
				</div>
			</div>
		</div>
	</div>
</c:if>
