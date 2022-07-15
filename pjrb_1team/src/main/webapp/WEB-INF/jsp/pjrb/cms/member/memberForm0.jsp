<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="rank0">
	<input type="hidden" name="status" value=""/>
	<!-- 관리자 가입 폼 -->
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label><i class="fad fa-check"></i>아이디</label>
				<div class="input-group">
					<!-- 회원정보 수정시에는 readonly -->
					<input type="text" class="form-control" placeholder="" aria-label="<c:out value="${memberView.emplyrId }"/>" value="<c:out value="${memberView.emplyrId }"/>" required name="emplyrId" <c:if test="${memberView.emplyrId != null && memberView.emplyrId ne '' }">readonly</c:if>>
					<c:if test="${memberView.emplyrId == null || memberView.emplyrId eq '' }">
						<div class="input-group-append">
							<button class="btn btn-light" type="button" onclick="javascript:fn_memberIdChk();">중복체크</button>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
				<label><i class="fad fa-check"></i>이름</label>
				<input type="text" class="form-control" value="<c:out value="${memberView.userNm }"/>" required name="userNm">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label> <i class="fad fa-check"></i>비밀번호 <c:if test="${memberView.emplyrId != null && memberView.emplyrId ne '' }">
						<input type="checkbox" class="custom-checkbox" value="1" name="pwChange" id="pwChange">
					</c:if>
				</label>
				<input type="password" class="form-control" required name="password" id="pw1" autocomplete="off">
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
				<label><i class="fad fa-check"></i>비밀번호 확인</label>
				<input type="password" class="form-control" required id="pw2" autocomplete="off">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label>부서</label>
				<input type="text" class="form-control" value="<c:out value="${memberView.department }"/>" required name="department">
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
				<label>직급</label>
				<input type="text" class="form-control" value="<c:out value="${memberView.position }"/>" required name="position">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label>이메일</label>
				<div class="input-group">
					<!-- 회원정보 수정시에는 readonly -->
					<input type="text" class="form-control" placeholder="" aria-label="<c:out value="${memberView.email }"/>" value="<c:out value="${memberView.email }"/>" required name="email">
					<%-- <c:if test="${memberView.email == null || memberView.email eq '' }">
						<div class="input-group-append">
							<button class="btn btn-light" type="button" >중복체크</button>
						</div>
					</c:if> --%>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
				<label>연락처</label>
				<input type="text" class="form-control phoneNumber" data-threshold="13" maxlength="13" data-toggle="maxlength" required name="phone" value="<c:out value="${memberView.phone }"/>">
			</div>
		</div>
	</div>
	<c:if test="${memberView.emplyrId != null && memberView.emplyrId != ''}">
		<div class="row">
			<div class="col-lg-4">
				<div class="form-group">
					<label>비밀번호 오류 횟수</label>
					<input type="text" class="form-control" value="<c:out value="${memberView.wrongCnt }"/>" readonly>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="form-group">
					<label>비밀번호 변경일</label>
					<div class="input-group">
						<input type="text" class="form-control" value="<c:out value="${fn:substring(memberView.changeDate,0,10) }"/>" readonly>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="form-group">
					<label>마지막로그인 날짜</label>
					<div class="input-group">
						<input type="text" class="form-control" value="<c:out value="${fn:substring(memberView.lastLoginDate,0,10) }"/>" readonly>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="row">
		<div class="col-lg-3">
			<div class="form-group">
				<label>사용여부</label>
				<div>
					<input type="checkbox" id="switch1" <c:if test="${memberView.status eq 'Y' }">checked</c:if> data-switch="bool" />
					<label for="switch1" data-on-label="사용" data-off-label="미사용"></label>
				</div>
			</div>
		</div>
	</div>
	<div class="btn-group bottom-btn float-right">
		<c:if test="${memberView.emplyrId != null && memberView.emplyrId != ''}">
			<button type="button" class="btn btn-success" onclick="javascript:fn_pwInit();">비밀번호 오류 초기화</button>
		</c:if>
		<button type="button" class="btn btn-primary" onclick="javascript:fn_submit();">저장</button>
		<button type="button" class="btn btn-danger" onclick="javascript:fn_memberList();">취소</button>
	</div>
</div>
