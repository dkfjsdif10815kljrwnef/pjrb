package egovframework.com.cmm;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
/**
 * ImagePaginationRenderer.java 클래스
 *
 * @author 서준식
 * @since 2011. 9. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2011. 9. 16.   서준식       이미지 경로에 ContextPath추가
 * </pre>
 */
public class CmsImagePaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;

	public CmsImagePaginationRenderer() {

	}

	public void initVariables(){
		firstPageLabel    = "<li><a href=\"?pageIndex={1}\" class=\"page-link\" onclick=\"{0}({1});return false; \"><i class=\"fal fa-angle-double-left\"></i></a></li>";
        previousPageLabel = "<li class=\"paginate_button page-item previous\"><a href=\"?pageIndex={1}\" class=\"page-link\" onclick=\"{0}({1});return false; \"><i class=\"fal fa-angle-left\"></i></a></li>";
        currentPageLabel  = "<li class=\"paginate_button page-item active\"><a href=\"#\" class=\"page-link\">{0}</a></li>";
        otherPageLabel    = "<li class=\"paginate_button page-item\"><a href=\"?pageIndex={1}\" class=\"page-link\" onclick=\"{0}({1});return false; \">{2}</a></li>";
        nextPageLabel     = "<li class=\"paginate_button page-item next\"><a href=\"?pageIndex={1}\" class=\"page-link\" onclick=\"{0}({1});return false; \"><i class=\"fal fa-angle-right\"></i></a></li>";
        lastPageLabel     = "<li><a href=\"?pageIndex={1}\" class=\"page-link\" onclick=\"{0}({1});return false; \"><i class=\"fal fa-angle-double-right\"></i></a></li>";
	}



	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}
