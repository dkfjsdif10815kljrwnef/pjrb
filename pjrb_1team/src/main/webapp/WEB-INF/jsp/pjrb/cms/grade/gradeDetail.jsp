<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

		<input type="hidden" name="grade" value="<c:out value="${authDetail.grade }"/>">
              <div class="row">
                <div class="col-lg-12">
                  <div class="form-group">
                    <label>권한명</label>
                    <input type="text" class="form-control" value="<c:out value="${authDetail.gradeNm }"/>" <c:if test="${authDetail.grade eq '1' }">readonly</c:if> name="gradeNm">
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="table-responsive col-lg-12">
                  <table class="table mb-0 table-middle">
                    <thead class="thead-dark">
                      <tr>
                        <th scope="col">메뉴</th>
                        <th scope="col">사용유무</th>
                      </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="cm" items="${detail }" varStatus="status">
	                      <tr>
	                        <th scope="row" rowspan="1" class="align-middle"><c:out value="${cm.menuNm }"/></th>
	                        <td rowspan="1">
	                          <input type="checkbox" id="switch${status.index }" <c:if test="${authDetail.auth.indexOf(cm.menuKey) > -1 }">checked</c:if> data-switch="bool" value="<c:out value='${cm.menuKey }'/>" name="auth" <c:if test="${loginVO.sessionGrade ne '0' and authDetail.grade eq '1' }">disabled</c:if>/>
	                          <label for="switch${status.index }" data-on-label="사용" data-off-label="미사용"></label>
	                        </td>
	                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div> <!-- end table-responsive -->
              </div>
                
		
                
