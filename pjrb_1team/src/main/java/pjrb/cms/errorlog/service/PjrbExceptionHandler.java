package pjrb.cms.errorlog.service;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.BaseException;
import egovframework.rte.fdl.cmmn.exception.BaseRuntimeException;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.ptl.mvc.bind.exception.AbstractAnnotationExceptionHandler;

/**
 * 에러로그 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.18
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.18  권대성          최초 생성 
 *  
 */

@ControllerAdvice
public class PjrbExceptionHandler extends AbstractAnnotationExceptionHandler{

	@Resource(name="errorLogService")
	private ErrorLogService errorLogService;

	private static final Logger logger = LoggerFactory.getLogger(PjrbExceptionHandler.class);
	
	@Override
	public ModelAndView handleException(Exception e) {
		/*logger.info("Exception발생");
		e.printStackTrace();*/
		
		StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
             
        String classNm= String.valueOf(e.getClass());       
        String exception  =  error.toString();      
        String message  =  e.getMessage();
        
        ErrorLogVO pjrbErrorLogVO = new ErrorLogVO();
        pjrbErrorLogVO.setException(exception);
        //pjrbErrorLogVO.setMessage(classNm + " : " +message);
        pjrbErrorLogVO.setMessage(classNm);
        try {
        	errorLogService.insertPjrbErrorLog(pjrbErrorLogVO);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
		ModelAndView model = new ModelAndView();
		//model.addObject("exceptionMsg", "Exception발생");
		//model.addObject("methodName", "handleException");
		model.setViewName("egovframework/com/cmm/error/egovError");
		return model;
	}

	@Override
	public ModelAndView handleRuntimeException(RuntimeException e) {
		StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
             
        String classNm= String.valueOf(e.getClass());       
        String exception  =  error.toString();      
        String message  =  e.getMessage();
        
        ErrorLogVO pjrbErrorLogVO = new ErrorLogVO();
        pjrbErrorLogVO.setException(exception);
        //pjrbErrorLogVO.setMessage(classNm + " : " +message);
        pjrbErrorLogVO.setMessage(classNm);
        try {
        	errorLogService.insertPjrbErrorLog(pjrbErrorLogVO);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
		ModelAndView model = new ModelAndView();
		//model.addObject("exceptionMsg", "Exception발생");
		//model.addObject("methodName", "handleException");
		model.setViewName("egovframework/com/cmm/error/egovError");
		return model;
	}

	@Override
	public ModelAndView handleBaseException(BaseException e) {
		StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
             
        String classNm= String.valueOf(e.getClass());       
        String exception  =  error.toString();      
        String message  =  e.getMessage();
        
        ErrorLogVO pjrbErrorLogVO = new ErrorLogVO();
        pjrbErrorLogVO.setException(exception);
        //pjrbErrorLogVO.setMessage(classNm + " : " +message);
        pjrbErrorLogVO.setMessage(classNm);
        try {
        	errorLogService.insertPjrbErrorLog(pjrbErrorLogVO);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
		ModelAndView model = new ModelAndView();
		//model.addObject("exceptionMsg", "Exception발생");
		//model.addObject("methodName", "handleException");
		model.setViewName("egovframework/com/cmm/error/egovError");
		return model;
	}

	@Override
	public ModelAndView handleEgovBizException(EgovBizException e) {
		StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
             
        String classNm= String.valueOf(e.getClass());       
        String exception  =  error.toString();      
        String message  =  e.getMessage();
        
        ErrorLogVO pjrbErrorLogVO = new ErrorLogVO();
        pjrbErrorLogVO.setException(exception);
        //pjrbErrorLogVO.setMessage(classNm + " : " +message);
        pjrbErrorLogVO.setMessage(classNm);
        try {
        	errorLogService.insertPjrbErrorLog(pjrbErrorLogVO);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
		ModelAndView model = new ModelAndView();
		//model.addObject("exceptionMsg", "Exception발생");
		//model.addObject("methodName", "handleException");
		model.setViewName("egovframework/com/cmm/error/egovError");
		return model;
	}

	@Override
	public ModelAndView handleFdlException(FdlException e) {
		StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
             
        String classNm= String.valueOf(e.getClass());       
        String exception  =  error.toString();      
        String message  =  e.getMessage();
        
        ErrorLogVO pjrbErrorLogVO = new ErrorLogVO();
        pjrbErrorLogVO.setException(exception);
        //pjrbErrorLogVO.setMessage(classNm + " : " +message);
        pjrbErrorLogVO.setMessage(classNm);
        try {
        	errorLogService.insertPjrbErrorLog(pjrbErrorLogVO);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
		ModelAndView model = new ModelAndView();
		//model.addObject("exceptionMsg", "Exception발생");
		//model.addObject("methodName", "handleException");
		model.setViewName("egovframework/com/cmm/error/egovError");
		return model;
	}
	
	
	public ModelAndView EgovXssException(BaseRuntimeException e) {
		StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
             
        String classNm= String.valueOf(e.getClass());       
        String exception  =  error.toString();      
        String message  =  e.getMessage();
        
        ErrorLogVO pjrbErrorLogVO = new ErrorLogVO();
        pjrbErrorLogVO.setException(exception);
        //pjrbErrorLogVO.setMessage(classNm + " : " +message);
        pjrbErrorLogVO.setMessage(classNm);
        try {
        	errorLogService.insertPjrbErrorLog(pjrbErrorLogVO);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
		ModelAndView model = new ModelAndView();
		//model.addObject("exceptionMsg", "Exception발생");
		//model.addObject("methodName", "handleException");
		model.setViewName("egovframework/com/cmm/error/egovError");
		return model;
	}
	
} 
