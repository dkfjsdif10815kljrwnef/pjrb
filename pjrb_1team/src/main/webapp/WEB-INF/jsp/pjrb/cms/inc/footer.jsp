<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

</div>
</div>
</div>
<!-- END wrapper -->
<!-- Right Sidebar -->
<div class="right-bar">
    <div class="rightbar-title">
        <a href="javascript:void(0);" class="right-bar-toggle float-right">
            <i class="fal fa-times"></i>
        </a>
        <h5 class="m-0">설정</h5>
    </div>
    <div class="rightbar-content" data-simplebar>
        <div class="p-3">
            <div class="alert alert-warning" role="alert">
                좌측 메뉴 및 전체 색상 변경이 가능합니다.
            </div>
            <!-- Settings -->
            <h5 class="mt-3">테마</h5>
            <hr class="mt-1" />
            <div class="custom-control custom-switch mb-1">
                <input type="radio" class="custom-control-input" name="color-scheme-mode" value="light" id="light-mode-check" checked />
                <label class="custom-control-label" for="light-mode-check"><span>라이트 모드</span></label>
            </div>
            <div class="custom-control custom-switch mb-1">
                <input type="radio" class="custom-control-input" name="color-scheme-mode" value="dark" id="dark-mode-check" />
                <label class="custom-control-label" for="dark-mode-check"><span>다크 모드</span></label>
            </div>
            <!-- Left Sidebar-->
            <h5 class="mt-4">좌측 메뉴</h5>
            <hr class="mt-1" />
            <div class="custom-control custom-switch mb-1">
                <input type="radio" class="custom-control-input" name="theme" value="default" id="default-check" checked />
                <label class="custom-control-label" for="default-check"><span>기본</span></label>
            </div>
            <div class="custom-control custom-switch mb-1">
                <input type="radio" class="custom-control-input" name="theme" value="light" id="light-check" />
                <label class="custom-control-label" for="light-check"><span>라이트</span></label>
            </div>
            <div class="custom-control custom-switch mb-3">
                <input type="radio" class="custom-control-input" name="theme" value="dark" id="dark-check" />
                <label class="custom-control-label" for="dark-check"><span>다크</span></label>
            </div>
            <button type="button" class="btn btn-block btn-xs btn-success mt-5">저장</button>
        </div> <!-- end padding-->
    </div>
</div>
<div class="rightbar-overlay"></div>

<!-- Right-bar -->
<script src="${pageContext.request.contextPath}/js/cms/common.js"></script>
<script src="${pageContext.request.contextPath}/js/cms/jquery-ui.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/cms/calendar.js"></script> --%>

 <!-- <script>
    ClassicEditor
        .create(document.querySelector('.editor'), {

            toolbar: {
                items: [
                    'heading',
                    '|',
                    'bold',
                    'italic',
                    'link',
                    'bulletedList',
                    'numberedList',
                    '|',
                    'fontColor',
                    'fontBackgroundColor',
                    '|',
                    'indent',
                    'outdent',
                    '|',
                    'imageUpload',
                    'blockQuote',
                    'insertTable',
                    'mediaEmbed',
                    'undo',
                    'redo'
                ]
            },
            language: 'ko',
            image: {
                toolbar: [
                    'imageTextAlternative',
                    'imageStyle:full',
                    'imageStyle:side'
                ]
            },
            table: {
                contentToolbar: [
                    'tableColumn',
                    'tableRow',
                    'mergeTableCells'
                ]
            },
            licenseKey: '',

        })
        .then(editor => {
            window.editor = editor;








        })
        .catch(error => {
            console.error('Oops, something went wrong!');
            console.error('Please, report the following error on https://github.com/ckeditor/ckeditor5/issues with the build id and the error stack trace:');
            console.warn('Build id: 9fbdrbvz15iq-ezz1beuvban9');
            console.error(error);
        });
</script> -->
</body>

</html>