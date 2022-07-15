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
	<c:if test='${searchVO.searchCnd == "time" || searchVO.searchCnd == "day" || empty searchVO.searchCnd }'>
		$("#searchDateD").val('${searchVO.searchDate }');
	</c:if>
});


function fnLinkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/stats/conStatList.do'/>";
  	document.listForm.submit();	
}

function fnSearchCnd(cnd){
	document.listForm.pageIndex.value = 1;
	document.listForm.searchCnd.value = cnd;
	/* document.listForm.action = "<c:url value='/cms/stats/conStatList.do'/>";
    document.listForm.submit(); */
	$("#startDate").val("");
    $("#endDate").val("");
    $("#searchDateD").val("");
    $("#searchDate").val("");
	fnSearch();
}

function fnEngYn(engYn){
	document.listForm.pageIndex.value = 1;
	document.listForm.engYn.value = engYn;
	document.listForm.action = "<c:url value='/cms/stats/conStatList.do'/>";
    document.listForm.submit();
}

function fnSearch(){
	
	var dateArr = $("#searchDateD").val();
	 
	if(dateArr!=null && dateArr!="")
	{
		document.listForm.searchDate.value = dateArr;
		dateArr = dateArr.split("~");
		$("#startDate").val(dateArr[0]);
		/* $("#endDate").val(dateArr[1]+" 23:59"); */
		$("#endDate").val(dateArr[1]);
	}
	else
	{
		if($("#startDateD").val()!=null && $("#startDateD").val()!="")
		{
			<c:if test='${searchVO.searchCnd == "month" }'>
			var sDate = $("#startDateD").val().substring(0,7);
			$("#startDate").val(sDate+"-01");
			</c:if>
			<c:if test='${searchVO.searchCnd == "year" }'>
			var sDate = $("#startDateD").val().substring(0,7);
			$("#startDate").val(sDate+"-01-01");
			</c:if>
		}
		if($("#endDateD").val()!=null && $("#endDateD").val()!="")
		{
			<c:if test='${searchVO.searchCnd == "month" }'>
				var eDate = $("#endDateD").val().substring(0,7);
				var lastDate = new Date(eDate.substring(0,4), eDate.substring(5,7), 0).getDate();
				$("#endDate").val(eDate+"-"+lastDate);
			</c:if>
			<c:if test='${searchVO.searchCnd == "year" }'>
				var eDate = $("#endDateD").val().substring(0,7);
				var lastDate = new Date(eDate.substring(0,4), 12, 0).getDate();
				$("#endDate").val(eDate+"-12-"+lastDate);
			</c:if>
		}
	}
	
	if($("#startDate").val().length > 0 && $("#endDate").val().length > 0){
		if($("#startDate").val() > $("#endDate").val()){
			alert('기간을 확인 하세요');
			return false;
		}
	}
	
	document.listForm.action = "<c:url value='/cms/stats/conStatList.do'/>";
    document.listForm.submit();
}

</script>

<form id="searchVO" name="listForm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }"/>
<input type="hidden" name="startDate" id="startDate" value="<c:out value='${searchVO.startDate }'/>"/>
<input type="hidden" name="endDate" id="endDate" value="<c:out value='${searchVO.endDate }'/>"/>
<input type="hidden" name="searchCnd" id="searchCnd" value="${searchVO.searchCnd }"/>
<input type="hidden" name="searchDate" id="searchDate" value="${searchVO.searchDate }"/>
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
            <li class="breadcrumb-item active">접속 통계</li>
          </ol>
        </div>
        <h4 class="page-title">접속 통계</h4>
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
          <ul class="nav nav-tabs nav-bordered mb-4">
            <li class="nav-item">
                <a class="nav-link <c:if test='${searchVO.searchCnd == "time" || empty searchVO.searchCnd }'>active</c:if>" href="#" onclick="fnSearchCnd('time'); return false;">시간별</a>
              </li>
              <li class="nav-item">
                <a class="nav-link <c:if test='${searchVO.searchCnd == "day" }'>active</c:if>" href="#" onclick="fnSearchCnd('day'); return false;">일별</a>
              </li>
              <li class="nav-item">
                <a class="nav-link <c:if test='${searchVO.searchCnd == "month" }'>active</c:if>" href="#" onclick="fnSearchCnd('month'); return false;">월별</a>
              </li>
			  <li class="nav-item">
                <a class="nav-link <c:if test='${searchVO.searchCnd == "year" }'>active</c:if>" href="#" onclick="fnSearchCnd('year'); return false;">연도별</a>
              </li>
          </ul>
          <div class="alert alert-light bg-light text-dark sch_wrap">
            <div class="input-group col-sm-12">
              <div class="input-group col-sm">
              	<c:if test='${searchVO.searchCnd == "time" || searchVO.searchCnd == "day" || empty searchVO.searchCnd }'>
                	<input type="text" class="form-control" data-toggle="date-picker" data-cancel-class="btn-warning" id="searchDateD" value="${searchVO.searchDate }">
                </c:if>
                
                <c:if test='${searchVO.searchCnd == "month" }'>
	                <input type="text" class="form-control" data-provide="datepicker" data-date-format="yyyy-MM" data-date-min-view-mode="1" data-date-autoclose="true" id="startDateD" value="${searchVO.startDate }">
	                <span>~</span>
	                <input type="text" class="form-control" data-provide="datepicker" data-date-format="yyyy-MM" data-date-min-view-mode="1" data-date-autoclose="true" id="endDateD" value="${searchVO.endDate }">
                </c:if>
                <c:if test='${searchVO.searchCnd == "year" }'>
	                <input type="text" class="form-control" data-provide="datepicker" data-date-min-view-mode="2" data-date-format="yyyy" data-date-autoclose="true" id="startDateD" value="${searchVO.startDate }">
	                <span>~</span>
	                <input type="text" class="form-control" data-provide="datepicker" data-date-min-view-mode="2" data-date-format="yyyy" data-date-autoclose="true" id="endDateD" value="${searchVO.endDate }">
                </c:if>
                <div class="input-group-append">
                  <button class="btn btn-primary" type="button" onclick="fnSearch();">Search</button>
                </div>
              </div>
            </div>
          </div>
          <script src="/js/cms/highcharts.js"></script>
          <script src="/js/cms/series-label.js"></script>
          <script src="/js/cms/exporting.js"></script>
          <div id="chart01" style="width: 98%; height: 400px; margin: 20px auto; padding: 0 1%"></div>
          <script>
            // Data retrieved from http://vikjavev.no/ver/index.php?spenn=2d&sluttid=16.06.2015.
            Highcharts.setOptions({
              lang: {
                thousandsSep: ','
              }
            })
            Highcharts.chart('chart01', {
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
                type: 'line',
                scrollablePlotArea: {
                  //minWidth: 600,
                  scrollPositionX: 1
                },
                style: {
                  fontFamily: 'NotoSansKR'
                }
              },
              credits: {
                enabled: false
              },
              title: {
                text: '방문자수 추이',
                align: 'left'
              },
              xAxis: {
                type: 'datetime',
                dateTimeLabelFormats: {
                  millisecond: '%H:%M:%S.%L',
                  second: '%H:%M:%S',
                  minute: '%H:%M',
                  hour: '%H:%M',
                  day: '%e. %b',
                  week: '%e. %b',
                  month: '%b \'%y',
                  year: '%Y'
                },
                labels: {
                  overflow: 'justify'
                },
                minorGridLineWidth: 1,
                gridLineWidth: 1,
                alternateGridColor: null,
                categories: [
                	<c:forEach var="resultinfo" items="${resultChart}" varStatus="status">
					<c:if test="${empty searchVO.searchCnd}">
						'<c:out value="${resultinfo.parseDate}"/>',
					</c:if>
					<c:if test="${searchVO.searchCnd eq 'time'}">
						'<c:out value="${resultinfo.parseDate}"/>',
					</c:if>
					<c:if test="${searchVO.searchCnd eq 'day'}">
						'<c:out value="${fn:substring(resultinfo.parseDate,0,10 )}"/>',
					</c:if>
					<c:if test="${searchVO.searchCnd eq 'month'}">
						'<c:out value="${fn:substring(resultinfo.parseDate,0,7 )}"/>',
					</c:if>
					<c:if test="${searchVO.searchCnd eq 'year'}">
						'<c:out value="${fn:substring(resultinfo.parseDate,0,4 )}"/>',
					</c:if>
				</c:forEach>
                	] //x-축 데이터 현재시간 포함 이전 시간표시 (총12)
              },
              yAxis: {
                title: {
                  text: ''
                },
                minorGridLineWidth: 1,
                gridLineWidth: 1,
                alternateGridColor: null
              },
              tooltip: {
                /*formatter: function() {
                return  '<b>' + this.series.name +'</b><br/>' +
                Highcharts.dateFormat('%Y-%m-%d %H:%M',new Date(this.x)) + ',' + this.y + '명';
                },*/
                crosshair: true,
                pointFormat: "접속자: {point.y:,.0f} 명"
              },
              plotOptions: {
                spline: {
                  lineWidth: 4,
                  states: {
                    hover: {
                      lineWidth: 5
                    }
                  },
                  marker: {
                    enabled: false
                  },
                  tickInterval: 3600 * 1000, // one hour
                  //pointStart: Date.UTC (2019, 6, 17, 0, 0, 0)
                },
                series: {
                  includeInCSVExport: false
                }
              },
              series: [{
					name: 'PC',
					color:'#7cb5ec',
					data: [
						<c:forEach var="resultinfo" items="${resultChart}" varStatus="status">
							<c:out value="${resultinfo.pc}"/>,
						</c:forEach>
						]
					}, {
						name: 'Mobile',
						color:'#f7a35c',
						data: [
							<c:forEach var="resultinfo" items="${resultChart}" varStatus="status">
								<c:out value="${resultinfo.mobile}"/>,
							</c:forEach>
							]
					}, {
						name: 'Total',
						color:'#fc3c3c',  
						data: [
							<c:forEach var="resultinfo" items="${resultChart}" varStatus="status">
								<c:out value="${resultinfo.total}"/>,
							</c:forEach>
							]
					}],
              navigation: {
                menuItemStyle: {
                  fontSize: '10px'
                }
              }
            });
          </script>
          <div class="dt-buttons col-sm-12 mb-3">
            <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excel('stats','1');"><span>엑셀 다운로드</span></button>
          </div>
          <div data-simplebar class="table-responsive">
            <table class="table mb-0 table-hover">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">접속일시</th>
                  <th scope="col">PC</th>
                  <th scope="col">Mobile</th>
                  <th scope="col">Total</th>
                </tr>
              </thead>
              <tbody>
              	<c:set var="totCnt" value="0"/>
				<c:set var="totRecordCnt" value="0"/>
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
					  <td><c:out value="${result.parseDate}"/></td>
					  <td><c:out value="${result.pc}"/></td>
					  <td><c:out value="${result.mobile}"/></td>
					  <td><c:out value="${result.total }"/></td> 
					</tr>
					<c:set var="totCnt" value="${totCnt+result.total}"/>
					<c:set var="totRecordCnt" value="${totRecordCnt+1}"/>
				</c:forEach>
              </tbody>
              <tfoot>
                <tr>
                  <td colspan="3">평균</td>
                  <td>
                  	<c:if test="${totCnt != 0}">
                  	<fmt:formatNumber value="${(totCnt / totRecordCnt)}" pattern="#.##"/>
                  	</c:if>
                  </td>
                </tr>
              </tfoot>
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
 