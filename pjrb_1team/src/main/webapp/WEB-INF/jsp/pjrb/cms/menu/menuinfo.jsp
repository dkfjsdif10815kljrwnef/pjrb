<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 <c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cms/themes/default/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cms/jstree/jstree.js"></script> --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

<script>
$(document).ready(function() {
	//$("#menuWrite").load('<c:url value="/cms/menu/menuWrite.do"/>',{parentMenuCode:0});
});

$(function () {
    var data = [
    	/* { "id" : "0", "parent" : "#", "text" : "육아종합지원센터" },  */
		<c:forEach var="result" items="${resultList}" varStatus="status">
			<c:if test="${result.parentMenuId == '0'}">
				<c:set var="parentId" value="0"/>
			</c:if>
			<c:if test="${result.parentMenuId != '0'}">
				<c:set var="parentId" value="${result.parentMenuId }"/>
			</c:if>
			{ "id" : "<c:out value='${result.menuId }'/>", "parent" : "<c:out value='${parentId}'/>", "text" : "<c:out value='${result.menuTitle }'/>" }
			<c:if test="${!status.last}">
			,
			</c:if>
		</c:forEach>
    ];
    
	$("#jstree").jstree({
		"core" : {
			"themes" : {
			      "variant" : "large"
			},			
			"check_callback" : true,
			"data": data
		}
	}).bind("loaded.jstree", function (event, data) {
	    $(this).jstree("open_all");
	    /* $(this).jstree("open_node", $("#0"));
	    $(this).jstree("open_node", $("#1")); */
	    
	    var formData = new FormData();
		var jsonNodes = $('#jstree').jstree(true).get_json('#', { flat: true });
		$.each(jsonNodes, function (i, val) {
			formData.append("CmsMenuVOList["+i+"].menuId",$(this)[0].id);
			formData.append("CmsMenuVOList["+i+"].menuNav",$('#jstree').jstree().get_path($(this)[0], ','));
			formData.append("CmsMenuVOList["+i+"].menuIdPath",$('#jstree').jstree().get_path($(this)[0], ',' ,true));
		//	console.log($('#jstree').jstree().get_path($(this)[0], ' > ' ,true))
		//	console.log($('#jstree').jstree().get_path($(this)[0], ' > ' ))
		})
		$.ajax({
			url:"<c:url value='/cms/menu/updateMenuNavAjax.do'/>",
			data:formData,
			type:"POST",
			processData : false,
            contentType : false,
			success:function(){
				
			}
		})
	    
	});

});

function fn_menuadd(){
	var menu_code = '0';
	if($('#jstree').jstree('get_selected') != ''){
		menu_code = String($('#jstree').jstree('get_selected'));	
	}
	
	$("#menuWrite").load('<c:url value="/cms/menu/menuWrite.do"/>',{parentMenuId:menu_code});
}

function fn_menumod(){
	var menu_code = String($('#jstree').jstree('get_selected'));
	if(menu_code != ''){
		$("#menuWrite").load('<c:url value="/cms/menu/menuModify.do"/>',{menuId:menu_code,menuType:"<c:out value="${searchVO.menuType}"/>"});
	}else{
		alert("메뉴를 선택해주세요.");
	}
}

function fn_menudel(){
	var menu_code = String($('#jstree').jstree('get_selected'));
	if(menu_code != ''){
		if (confirm('삭제하시겠습니까?')) {
			var menuArr = $('#jstree').jstree("get_selected", true)[0].children_d+","+menu_code;
			var delFrm = document.delFrm;
			delFrm.menuId.value = menuArr;
			delFrm.action = '<c:url value="/cms/menu/menuDelete.do"/>';
			delFrm.submit();
		}
	}else{
		alert("메뉴를 선택해주세요.");
	}
}

function fn_menusort(){
	var menu_code = '0';
	if($('#jstree').jstree('get_selected') != ''){
		menu_code = String($('#jstree').jstree('get_selected'));
		if(String($("#jstree").jstree().get_node( menu_code ).children) == ""){
			return;
		}
	}else{
		return;
	}
	
	$("#menuWrite").load('<c:url value="/cms/menu/menuSort.do"/>',{parentMenuId:menu_code,menuType:"<c:out value="${searchVO.menuType}"/>"});
}

function fn_contentModal()
{
	$("input[name='menuUrl']").attr("readonly",true);
	
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/menu/contentListPopup.do'/>",
		data:{
			"pageIndex": "1"/* ,
			"eng" : eng */
		},
		//dataType:'json',
		dataType:'html',
		async:false,
		success:function(returnData, status){
			
			if(status == "success") {
				$("#modal01").html(returnData);
			}else{ 
				alert("ERROR!");return;
			}
		}
	});
}

function fn_bbsModal()
{
	$("input[name='menuUrl']").attr("readonly",true);
	
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/menu/bbsListPopup.do'/>",
		data:{
			"pageIndex": "1"/* ,
			"eng" : eng */
		},
		//dataType:'json',
		dataType:'html',
		async:false,
		success:function(returnData, status){
			
			if(status == "success") {
				$("#modal01").html(returnData);
			}else{ 
				alert("ERROR!");return;
			}
		}
	});
}

function fn_pageMngModal(){
	
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/menu/pageManagerListPopup.do'/>",
		data:{
			"pageIndex": "1",
		},
		//dataType:'json',
		dataType:'html',
		async:false,
		success:function(returnData, status){
			
			if(status == "success") {
				$("#modal01").html(returnData);
			}else{ 
				alert("ERROR!");return;
			}
		}
	});
}

function fn_menuType(n){
	location.href = "<c:url value='/cms/menu/menuinfo.do'/>?menuType="+n;
}
</script>


<form method="post" name="delFrm">
	<input type="hidden" name="menuId" value="">
	<input type="hidden" name="menuType" value='<c:out value="${searchVO.menuType }"/>'>
</form>

<div class="container-fluid">
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <ol class="breadcrumb m-0">
            <li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
            <li class="breadcrumb-item"><a href="javascript:void(0)">관리자</a></li>
            <li class="breadcrumb-item active"> 메뉴 관리</li>
          </ol>
        </div>
        <h4 class="page-title">메뉴 관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  
  <div class="row">
	<!-- start page title -->
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
          <ul class="nav nav-tabs nav-bordered mb-4">
			    <li class="nav-item">
			      <a href="javascript:void(0);" onclick="javascript:fn_menuType('1');" class="nav-link <c:if test="${searchVO.menuType == null || searchVO.menuType eq '' || searchVO.menuType eq '1'}">active</c:if>">국문</a>
			    </li>
			    <li class="nav-item">
			      <a href="javascript:void(0);" onclick="javascript:fn_menuType('2');" class="nav-link <c:if test="${searchVO.menuType eq '2' }">active</c:if>">영문</a>
			    </li>
		  </ul> <!-- end nav-->
		  <c:set var="menuType" value="${searchVO.menuType }" scope="application"/>
          <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css">
          <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script> -->
          <div class="row">
            <div class="col-lg-4">
              <div class="user-menu-title">
                <ul>
                  <li><i class="fal fa-folders"></i>사용자메뉴</li>
                </ul>
              </div>
              <div id="jstree"></div>
            </div>
            <div id="menuWrite" class="col-lg-8 needs-validation" novalidate></div> 
          </div>
          <div class="btn-group bottom-btn float-right">
            <button type="button" class="btn btn-success" onclick="javascript:fn_menuadd();">추가</button>
            <button type="button" class="btn btn-warning" onclick="javascript:fn_menumod();">수정</button>
            <button type="button" class="btn btn-danger" onclick="javascript:fn_menudel();">삭제</button>
            <button type="button" class="btn btn-primary" onclick="javascript:fn_menusort();">정렬</button>
          </div>
        </div> <!-- end card body-->
      </div> <!-- end card -->
    </div><!-- end col-->
  </div>
</div>


<!-- 모달창 -->
<div class="modal fade" id="modal01" tabindex="-1" aria-labelledby="콘텐츠 선택" aria-hidden="true">
</div>

 <c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" /> 