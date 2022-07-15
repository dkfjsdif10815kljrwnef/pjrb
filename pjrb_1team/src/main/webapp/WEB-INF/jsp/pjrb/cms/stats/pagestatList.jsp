<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script type="text/javascript" src="<c:url value='/html/cms/ckeditor4/ckeditor.js'/>"></script>

<script>
$(document).ready(function() 
{
	$("#searchDate").val('${searchVO.searchDate }');
});


function fnLinkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/stats/pageStatList.do'/>";
  	document.listForm.submit();	
}

function fnEngYn(engYn){
	document.listForm.engYn.value = engYn;
	document.listForm.action = "<c:url value='/cms/stats/pageStatList.do'/>";
    document.listForm.submit();
}

function fnSearch(){
	
	var dateArr = $("#searchDate").val();
	 
	if(dateArr!=null && dateArr!="")
	{
		document.listForm.searchDate.value = dateArr;
		dateArr = dateArr.split("~");
		$("#startDate").val(dateArr[0]);
		$("#endDate").val(dateArr[1]+" 23:59");
	}
	
	if($("#startDate").val().length > 0 && $("#endDate").val().length > 0){
		if($("#startDate").val() > $("#endDate").val()){
			alert('기간을 확인 하세요');
			return false;
		}
	}
	
	document.listForm.action = "<c:url value='/cms/stats/pageStatList.do'/>";
    document.listForm.submit();
}

</script>

<form id="searchVO" name="listForm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }"/>
<input type="hidden" name="startDate" id="startDate" value="<c:out value='${searchVO.startDate }'/>"/>
<input type="hidden" name="endDate" id="endDate" value="<c:out value='${searchVO.endDate }'/>"/>
<input type="hidden" name="engYn" id="engYn" value="${searchVO.engYn }"/>
<div class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <ol class="breadcrumb m-0">
            <li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
            <li class="breadcrumb-item"><a href="javascript:void(0);">통계</a></li>
            <li class="breadcrumb-item active">페이지 통계</li>
          </ol>
        </div>
        <h4 class="page-title">페이지 통계</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
          <ul class="nav nav-tabs nav-bordered mb-2">
            <li class="nav-item">
                <a class="nav-link <c:if test='${searchVO.engYn eq "N" || empty searchVO.engYn }'>active</c:if>" href="#" onclick="fnEngYn('N'); return false;">국문</a>
              </li>
              <li class="nav-item">
                <a class="nav-link <c:if test='${searchVO.engYn eq "Y" }'>active</c:if>" href="#" onclick="fnEngYn('Y'); return false;">영문</a>
              </li>
          </ul>
          <div class="alert alert-light bg-light text-dark sch_wrap">
            <div class="input-group col-sm-12">
              <div class="input-group col-sm">
                <input type="text" class="form-control" data-toggle="date-picker" data-cancel-class="btn-warning" name="searchDate" id="searchDate" value="${searchVO.searchDate }">
                <div class="input-group-append">
                  <button class="btn btn-primary" type="submit" onclick="fnSearch();">Search</button>
                </div>
              </div>
            </div>
          </div>
          <script src="/js/cms/highcharts.js"></script>
          <script src="/js/cms/series-label.js"></script>
          <script src="/js/cms/exporting.js"></script>
          <div id="chart05" style="width: 98%; height: 600px; margin: 20px auto; padding: 0 1%"></div>
          <script>
            Highcharts.setOptions({
              lang: {
                thousandsSep: ','
              }
            })
            Highcharts.setOptions({
              colors: ['#727cf5', '#6c757d', '#0acf97', '#fa5c7c', '#ffbc00', '#39afd1', '#ffadf4', '#313a46']
            });
            Highcharts.chart('chart05', {
              exporting: {
                allowHTML: true,
                chartOptions: {
                  chart: {
                    style: {
                      fontSize: "8px",
                      fontFamily: "Arial"
                    },
                  },
                  plotOptions: {
                    series: {
                      dataLabels: {
                        enabled: true,
                        //format: '{point.name} {point.y:.f}',
                      }
                    }
                  }
                }
              },
              chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie',
                style: {
                  fontFamily: 'NotoSansKR'
                }
              },
              credits: {
                enabled: false
              },
              title: {
                text: '페이지 뷰 비율'
              },
              tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b><br>페이지뷰: <b>{point.y:,.0f}'
              },
              plotOptions: {
                pie: {
                  allowPointSelect: true,
                  cursor: 'pointer',
                  dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                  }
                }
              },
              series: [{"name":"비율","colorByPoint":true,"data":[            
						 <c:set var="totViewCnt" value="0"/>
						<c:forEach var="resultinfo" items="${resultList}" varStatus="status">
							<c:set var="totViewCnt" value="${totViewCnt+resultinfo.viewCount}"></c:set>
						</c:forEach>
						
						<c:forEach var="resultinfo" items="${resultList}" varStatus="status">
							{name: "${resultinfo.menuTitle }", y: <fmt:formatNumber value="${((resultinfo.viewCount)/(totViewCnt)) *100}" pattern="#.##"/>, z:"${resultinfo.viewCount}"},
						</c:forEach> 
						 ]}]
            });
          </script>
          <div class="dt-buttons col-sm-12 mb-3">
            <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excel('stats','2');"><span>엑셀 다운로드</span></button>
          </div>
          <div data-simplebar class="table-responsive">
            <table class="table mb-0 table-hover">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">메뉴명</th>
                  <th scope="col">URL</th>
                  <th scope="col">페이지뷰</th>
                  <th scope="col">비율</th>
                </tr>
              </thead>
              <tbody>
              	<c:set var="totViewCnt" value="0"/>
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<c:set var="totViewCnt" value="${totViewCnt+result.viewCount}"></c:set>
				</c:forEach>
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
					  <td><c:out value="${result.menuTitle}"/></td>
					  <td><a href="${result.url}" target="_blank"><c:out value="${result.url}"/></a></td>
					  <td><c:out value="${result.viewCount}"/></td>
					  <td><fmt:formatNumber value="${((result.viewCount+0.0)/(totViewCnt+0.0)) *100}" pattern="#.##"/>%</td> 
					</tr>
				</c:forEach>
            </table>
          </div> <!-- end table-responsive -->
          <div class="row mt-4">
			<div class="col-sm-12 col-md-5">
			  <div class="dataTables_length"><label>목록개수
				  <select class="custom-select mb-3" id="recordCountPerPage" name="recordCountPerPage" onchange="javascript:fnSearch();">
					<option value="10" <c:if test="${searchVO.recordCountPerPage == 10 }">selected</c:if>>10</option>
					<option value="15" <c:if test="${searchVO.recordCountPerPage == 15 }">selected</c:if>>15</option>
					<option value="20" <c:if test="${searchVO.recordCountPerPage == 20 }">selected</c:if>>20</option>
					<option value="30" <c:if test="${searchVO.recordCountPerPage == 30 }">selected</c:if>>30</option>
					<option value="40" <c:if test="${searchVO.recordCountPerPage == 40 }">selected</c:if>>40</option>
					<option value="50" <c:if test="${searchVO.recordCountPerPage == 50 }">selected</c:if>>50</option>
					<option value="100" <c:if test="${searchVO.recordCountPerPage == 100 }">selected</c:if>>100</option>
				  </select></label></div>
			</div>
			<div class="col-sm-12 col-md-7">
			  <div class="dataTables_paginate paging_simple_numbers">
				<ul class="pagination pagination-rounded">
					<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fnLinkPage" />
				</ul>
			  </div>
			</div>
		  </div>
        </div> <!-- end card body-->
      </div> <!-- end card -->
    </div><!-- end col-->
  </div>
</div>
</form>
 <c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" /> 
 