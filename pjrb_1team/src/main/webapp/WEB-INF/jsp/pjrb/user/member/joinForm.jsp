<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/user/inc/header.do" />
<script type="text/javaScript" language="javascript" >
document.title = "회원가입 | 메디컬융합소재실용화센터";
</script>


<script type="text/javascript" src="/js/enc/aes.js"></script>
<script type="text/javascript" src="/js/enc/pbkdf2.js"></script>
<script type="text/javascript" src="/js/enc/AesUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/cms/common.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
</script>

<div class="member_wrap join_wrap">
    <div class="top">
        <h2>회원가입</h2>
        <ul class="join_process">
            <li><i class="far fa-user-unlock"></i><span><strong>01</strong>본인인증</span></li>
            <li><i class="fal fa-ballot-check"></i><span><strong>02</strong>약관동의</span></li>
            <li class="active"><i class="fal fa-keyboard"></i><span><strong>03</strong>정보입력</span></li>
            <li><i class="far fa-user-check"></i><span><strong>04</strong>가입완료</span></li>
        </ul>
    </div>
    <script>
    </script>
    <div class="member_cont_wrap join_cont_wrap">
        <div class="member_cont join_cont">                        
            <div class="info_wrap">
                <ul class="type">
                    <li class="tab_title current" data-tab="tab01">일반회원</li>
                    <li class="tab_title" data-tab="tab02">기업(기관)회원</li>
                </ul>
                <form name="frm" id="frm" method="post">
                	<input type="hidden" name="authtype" value="<c:out value='${authtype }'/>"/>
                	<input type="hidden" name="name" value="<c:out value='${name }'/>"/>
                	<input type="hidden" name="birthdate" value="<c:out value='${birthdate }'/>"/>
                	<input type="hidden" name="gender" value="<c:out value='${gender }'/>"/>
                	<input type="hidden" name="mobile_co" value="<c:out value='${mobile_co }'/>"/>
                	<input type="hidden" name="mobileno" value="<c:out value='${mobileno }'/>"/>
                	<input type="hidden" name="di" value="<c:out value='${di }'/>"/>
                	<input type="hidden" name="ci" value="<c:out value='${ci }'/>"/>
                	<input type="hidden" name="businessno" value="<c:out value='${businessno }'/>"/>
                	<input type="hidden" name="receivedata" value="<c:out value='${receivedata }'/>"/>
                	<input type="hidden" name="receivedata" value="<c:out value='${receivedata }'/>"/>
                	<!--카카오로그인 -->
                	<input type="hidden" name="email" value="<c:out value='${memberVO.email }'/>"/>
					<div id="loadForm">
					</div>
				</form>
            </div>
            <div class="btngroup btngroup02 alignC">
                <button type="button" class="btn_l btn" onclick="location.href='${pageContext.request.contextPath}/user/member/join.do'">취소</button>
                <button type="button" class="btn_w btn" onclick="javascript:fn_submit();">가입완료</button>
            </div>
        </div>
    </div>
</div>
<c:import url="/user/inc/footer.do" />