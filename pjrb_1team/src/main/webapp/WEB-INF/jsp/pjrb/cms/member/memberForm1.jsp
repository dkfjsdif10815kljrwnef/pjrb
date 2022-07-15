<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="rank1">
	<input type="hidden" name="status" value=""/>
	<input type="hidden" name="deleteAt" value=""/>
	<!-- 일반회원 가입 폼 -->
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
	<div class="row">
		<div class="col-lg-3">
			<div class="form-group">
				<label><i class="fad fa-check"></i>우편번호</label>
				<div class="input-group">
					<input type="text" class="form-control" id="postcode" required readonly value="<c:out value="${memberView.zip }"/>" name="zip">
					<div class="input-group-append">
						<button class="btn btn-light" type="button" onclick="sample6_execDaumPostcode()">우편번호</button>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-5">
			<div class="form-group">
				<label><i class="fad fa-check"></i>주소</label>
				<input type="text" class="form-control" id="sample6_address" required value="<c:out value="${memberView.adres }"/>" name="adres">
			</div>
		</div>
		<div class="col-lg-4">
			<div class="form-group">
				<label><i class="fad fa-check"></i>상세주소</label>
				<input type="text" class="form-control" id="sample6_detailAddress" required value="<c:out value="${memberView.detailAdres }"/>" name="detailAdres">
				<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
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
		<div class="col-lg-1">
			<div class="form-group">
				<label>사용여부</label>
				<div>
					<input type="checkbox" id="switch1" <c:if test="${memberView.status eq 'Y' }">checked</c:if> data-switch="bool" />
					<label for="switch1" data-on-label="사용" data-off-label="미사용"></label>
				</div>
			</div>
		</div>
		<div class="col-lg-1">
			<div class="form-group">
				<label>탈퇴여부</label>
				<div>
					<input type="checkbox" id="switch2" <c:if test="${memberView.deleteAt eq 'Y' }">checked</c:if> data-switch="bool" onclick="javascript:fn_deleteAtChange();" />
					<label for="switch2" data-on-label="탈퇴" data-off-label="미탈퇴"></label>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="form-group">
				<label>탈퇴사유</label>
				<input type="text" class="form-control" id="deleteReason" name="deleteReason" value="<c:out value="${memberView.deleteReason }"/>" <c:if test="${memberView.deleteAt eq 'N' }">readonly</c:if>>
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
<script src="${pageContext.request.contextPath}/js/cms/common.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.
							document.getElementById("sample6_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample6_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('postcode').value = data.zonecode;
						document.getElementById("sample6_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress")
								.focus();
					}
				}).open();
	}
</script>