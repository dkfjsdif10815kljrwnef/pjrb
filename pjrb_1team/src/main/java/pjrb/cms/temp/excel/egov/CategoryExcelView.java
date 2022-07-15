package pjrb.cms.temp.excel.egov;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class CategoryExcelView extends AbstractExcelView {
	private static final Logger LOGGER  = LoggerFactory.getLogger(CategoryExcelView.class);
	 
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook wb, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HSSFCell cell = null;
 
        LOGGER.debug("### buildExcelDocument start !!!");
 
        HSSFSheet sheet = wb.createSheet("User List");
        sheet.setDefaultColumnWidth(12);
 
        // put text in first cell
        cell = getCell(sheet, 0, 0);
        setText(cell, "User List");
 
        // set header information
        setText(getCell(sheet, 2, 0), "id");
        setText(getCell(sheet, 2, 1), "name");
        setText(getCell(sheet, 2, 2), "description");
        setText(getCell(sheet, 2, 3), "use_yn");
        setText(getCell(sheet, 2, 4), "reg_user");
 
        LOGGER.debug("### buildExcelDocument cast");
 
 
        Map<String, Object> map= (Map<String, Object>) model.get("categoryMap");
        List<Object> categories = (List<Object>) map.get("category");
 
        boolean isVO = false;
 
        if (categories.size() > 0) {
        	Object obj = categories.get(0);
        	isVO = obj instanceof UsersVO;
        }
 
        for (int i = 0; i < categories.size(); i++) {
 
        	if (isVO) {	// VO
 
        		LOGGER.debug("### buildExcelDocument VO : {} started!!", i);
 
        		UsersVO category = (UsersVO) categories.get(i);
 
	            cell = getCell(sheet, 3 + i, 0);
	            setText(cell, category.getId());
 
	            cell = getCell(sheet, 3 + i, 1);
	            setText(cell, category.getName());
 
	            cell = getCell(sheet, 3 + i, 2);
	            setText(cell, category.getDescription());
 
	            cell = getCell(sheet, 3 + i, 3);
	            setText(cell, category.getUseYn());
 
	            cell = getCell(sheet, 3 + i, 4);
	            setText(cell, category.getRegUser());
 
	            LOGGER.debug("### buildExcelDocument VO : {} end!!", i);
 
        	 } else {	// Map
 
        		LOGGER.debug("### buildExcelDocument Map : {} started!!", i);
 
        		Map<String, String> category = (Map<String, String>) categories.get(i);
 
 	            cell = getCell(sheet, 3 + i, 0);
 	            setText(cell, category.get("id"));
 
 	            cell = getCell(sheet, 3 + i, 1);
 	            setText(cell, category.get("name"));
 
 	            cell = getCell(sheet, 3 + i, 2);
 	            setText(cell, category.get("description"));
 
 	            cell = getCell(sheet, 3 + i, 3);
 	            setText(cell, category.get("useyn"));
 
 	            cell = getCell(sheet, 3 + i, 4);
 	            setText(cell, category.get("reguser"));
 
 	            LOGGER.debug("### buildExcelDocument Map : {} end!!", i);
        	 }
        }
    }
}
