<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>

function fn_submit(){
	var frm = document.frm;
	
	if($("input[name=menuTitle]").val() == ""){
		alert("메뉴명을 입력해 주세요.");
		$("input[name=menuTitle]").focus();
		return;
	}
	
	if($("input[name=menuDesc]").val() == ""){
		alert("메뉴그룹을 입력해 주세요.");
		$("input[name=menuDesc]").focus();
		return;
	}
	
	if(!confirm("저장하시겠습니까?")){
		return;
	}
	
	frm.action = '<c:url value="/cms/menu/menuUpdate.do"/>';
	frm.submit();
}

function fn_insertUrlSelf(eng)
{
	$("input[name='menuUrl"+eng+"']").attr("readonly",false);
}

function fn_deletePageMng()
{
	if (confirm('삭제하시겠습니까?')) {
		$("#emplyrId").val("");
		$("#mngDepart").val("");
		$("#mngName").val("");
		$("#mngPhone").val("");
	}
}


</script>

<form method="post" name="frm">
<input type="hidden" name="menuType" value='<c:out value="${selectMenuInfo.menuType }"/>'>
<input type="hidden" name="menuId" value='<c:out value="${selectMenuInfo.menuId }"/>'>
<input type="hidden" name="parentMenuId" value='<c:out value="${selectMenuInfo.parentMenuId }"/>'>
<input type="hidden" name="emplyrId" id="emplyrId" value='<c:out value="${selectMenuInfo.emplyrId }"/>'>

      <div class="row">
        <div class="col-lg-6">
          <div class="form-group">
            <label><i class="fad fa-check"></i>메뉴명</label>
            <input type="text" name="menuTitle" class="form-control" value='<c:out value="${selectMenuInfo.menuTitle }"/>' required>
          </div>
        </div>
        <div class="col-lg-3">
          <div class="form-group">
            <label>&nbsp;</label>
            <input type="text" class="form-control" value='<c:out value="${selectMenuInfo.menuId }"/>' readonly>
          </div>
        </div>
        <div class="col-lg-3">
          <div class="form-group">
            <label>&nbsp;</label>
            <input type="text" class="form-control" value='<c:out value="${selectMenuInfo.parentMenuId }"/>' readonly>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="form-group">
            <label><i class="fad fa-check"></i>메뉴그룹</label>
            <input type="text" name="menuDesc" class="form-control" value='<c:out value="${selectMenuInfo.menuDesc }"/>' required>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-6">
          <div class="form-group">
            <label>메뉴 콘텐츠</label>
            <input type="text" name="menuUrl" id="menuUrl" class="form-control" value='<c:out value="${selectMenuInfo.menuUrl }"/>' readonly>
          </div>
        </div>
        <div class="col-lg-2">
          <div class="form-group">
            <label>&nbsp;</label>
            <button type="button" class="btn btn-block btn-primary" data-toggle="modal" data-target="#modal01" onclick="fn_contentModal('');">콘텐츠</button>
          </div>
        </div>
        <div class="col-lg-2">
          <div class="form-group">
            <label>&nbsp;</label>
            <button type="button" class="btn btn-block btn-secondary" data-toggle="modal" data-target="#modal01" onclick="fn_bbsModal('');">게시판</button>
          </div>
        </div>
        <div class="col-lg-2">
          <div class="form-group">
            <label>&nbsp;</label>
            <button type="button" class="btn btn-block btn-success" onclick="fn_insertUrlSelf('');">URL입력</button>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label>페이지 담당부서</label>
            <input type="text" class="form-control" id="mngDepart" readonly value='<c:out value="${selectMngInfo.department }"/>'>
          </div>
        </div>
        <div class="col-lg-2">
          <div class="form-group">
            <label>담당자</label>
            <input type="text" class="form-control" id="mngName" readonly value='<c:out value="${selectMngInfo.userNm }"/>'>
          </div>
        </div>
        <div class="col-lg-3">
          <div class="form-group">
            <label>담당자(연락처)</label>
            <input type="text" class="form-control" id="mngPhone" readonly value='<c:out value="${selectMngInfo.phone }"/>'>
          </div>
        </div>
        <div class="col-lg-2">
          <div class="form-group">
            <label>&nbsp;</label>
            <button type="button" class="btn btn-block btn-primary" data-toggle="modal" data-target="#modal01" onclick="fn_pageMngModal();">선택</button>
          </div>
        </div>
        <div class="col-lg-2">
          <div class="form-group">
            <label>&nbsp;</label>
            <button type="button" class="btn btn-block btn-danger" onclick="fn_deletePageMng();">삭제</button>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="form-group">
            <div class="label">메뉴 이동방식</div>
            <div class="custom-control custom-radio custom-control-inline">
              <input type="radio" id="isBlankRadio1" name="isBlank" class="custom-control-input" value="N" <c:if test="${selectMenuInfo.isBlank == 'N' }">checked</c:if>>
              <label class="custom-control-label" for="isBlankRadio1"><span>페이지 이동</span></label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
              <input type="radio" id="isBlankRadio2" name="isBlank" class="custom-control-input" value="Y" <c:if test="${selectMenuInfo.isBlank == 'Y' }">checked</c:if>>
              <label class="custom-control-label" for="isBlankRadio2"><span>새창 이동</span></label>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="form-group">
            <div class="label">마우스 우클릭 차단</div>
            <div class="custom-control custom-radio custom-control-inline">
              <input type="radio" id="isRmouseRadio1" name="isRmouse" class="custom-control-input" value="Y" <c:if test="${selectMenuInfo.isRmouse == 'Y' }">checked</c:if>>
              <label class="custom-control-label" for="isRmouseRadio1"><span>허용</span></label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
              <input type="radio" id="isRmouseRadio2" name="isRmouse" class="custom-control-input" value="N" <c:if test="${selectMenuInfo.isRmouse == 'N' }">checked</c:if>>
              <label class="custom-control-label" for="isRmouseRadio2"><span>차단</span></label>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="form-group">
            <div class="label">사용여부</div>
            <div class="custom-control custom-radio custom-control-inline">
              <input type="radio" id="isUseRadio1" name="menuUse" class="custom-control-input" value="Y" <c:if test="${selectMenuInfo.menuUse == 'Y' }">checked</c:if>>
              <label class="custom-control-label" for="isUseRadio1"><span>사용</span></label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
              <input type="radio" id="isUseRadio2" name="menuUse" class="custom-control-input" value="N" <c:if test="${selectMenuInfo.menuUse == 'N' }">checked</c:if>>
              <label class="custom-control-label" for="isUseRadio2"><span>미사용</span></label>
            </div>
          </div>
        </div>
      </div>
</form>
<div class="space10"></div>
<div class="board_write_btngroup alignR">
	<button type="button" class="btn btn-primary" onclick="javascript:fn_submit();">저장</button>
</div>

