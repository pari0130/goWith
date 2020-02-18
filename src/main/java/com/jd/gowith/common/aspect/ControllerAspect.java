package com.jd.gowith.common.aspect;//package com.jd.gowith.common.aspect;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.ocs.common.component.PasswordComponent;
//import com.ocs.common.component.vo.ManageSession;
//import com.ocs.common.util.PasswordUtil;
//import lombok.extern.slf4j.Slf4j;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.BooleanUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mobile.device.Device;
//import org.springframework.mobile.device.DeviceUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.ocs.common.constrants.CodeConstrants;
//import com.ocs.common.security.vo.SecurityUserVO;
//import com.ocs.common.util.OcsStringUtil;
//import com.ocs.common.util.SecuritySessionUtil;
//import com.ocs.common.vo.OcsComVO;
//
///**
// * Controller Aspect
// * @author DEV
// *
// */
//@Aspect
//@Slf4j
//public class ControllerAspect {
//
//	//@Autowired
//	//private PasswordComponent passwordComponent;
//
//	/**
//	 * FRONT CONTROLLER ASPECT
//	 * @param joinPoint
//	 * @return
//	 * @throws Throwable
//	 */
//	/*
//	 * EDUBASE "|| execution(* edubank.learn..*Controller.*(..))" add
//	 */
//	@Around("(execution(* com..controller.*Controller.*(..)) || execution(* edubank.learn..*Controller.*(..))) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
//		
//		log.info("ControllerAspect...");
//		
//		SecurityUserVO loginUser = SecuritySessionUtil.getLoginUserNotException();
//
//		/** request **/
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		
//		/** response **/
//		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
//		 
//		String ajaxHeaderValue = request.getHeader(CodeConstrants.AJAX_HEADER_NAME);
//		
//		/** ajax 비동기 통신인지 확인 **/
//		Boolean isAjax = StringUtils.equals(ajaxHeaderValue, CodeConstrants.AJAX_HEADER_VALUE);
//		
//		/** 파일 다운로드인지 확인 **/
//		Boolean isFileDownLoad = StringUtils.equals(CodeConstrants.AJAX_FILE_DOWNLOAD_VALUE, request.getParameter(CodeConstrants.AJAX_FILE_DOWNLOAD_FLAG));
//		
//		/** 현재 요청이 mobile, device, pc 인지 확인하는 객체 **/
//		Device device = DeviceUtils.getCurrentDevice(request);
//		
//		/** HTTP SESSION  **/
//		HttpSession session = request.getSession();
//				
//				
//		/** 비밀번호 변경 필요 유무 **/
//		Boolean isNeedChangePwd = BooleanUtils.isTrue( (Boolean) session.getAttribute(CodeConstrants.PWD_CHG_DTM_SESSION_KEY) );
//		if( isNeedChangePwd ){
//			/** 세션의 데이터를 지우고 request 에 세팅한다. 비밀번호 변경 요청을  로그인이후에 한번만 열수 있도록. **/
//			session.removeAttribute(CodeConstrants.PWD_CHG_DTM_SESSION_KEY);
//			
//			addRequestParam(request, CodeConstrants.PWD_CHG_DTM_SESSION_KEY, CodeConstrants.PWD_CHG_DTM_SESSION_KEY);
//			log.info("#####################################################################");
//			log.info("# NEED PWD CHANGE");
//			log.info("#####################################################################");
//			
//		}
//		
//		/** Controller argument **/
//		Object[] args = joinPoint.getArgs();
//		
//		/**
//		 * Controller 입력값중 AuthVO 를 상속받은 객체가 존재시
//		 * 세션 로그인 정보를 주입한다.
//		 */
//		if( loginUser != null ){
//
////			ManageSession manageSession= passwordComponent.getManageSession(session.getId());
////
////			if(manageSession==null){
////							loginUser=null;
////			session.removeAttribute(CodeConstrants.LOGIN_FAIL_VO_SESSION_NAME);
////			return "redirect:/j_spring_security_logout.do";
////			}
//
//
//
//
//			if( ArrayUtils.isNotEmpty(args) ){
//				for (Object arg : args) {
//					if( arg instanceof OcsComVO ){
//						OcsComVO ocsComVO = (OcsComVO) arg;
//						ocsComVO.setLoginUser(loginUser);
//						ocsComVO.setDevice(device);
//					}
//				}
//			}
//		}
//		
//		
//		/**
//		 * epki 인증 세션 처리 
//		 */
//		boolean isSignVerify = (request.getSession().getAttribute("delfinoNonce") != null) ? true : false;
//		String uri = request.getRequestURI();
//		
//		log.debug("■uri : {}", uri);
//		log.debug("■isAjax : {}", isAjax);
//		log.debug("■세션보유여부 {}",isSignVerify);
//		log.debug("■session value : {}",request.getSession().getAttribute("delfinoNonce"));
//		if(OcsStringUtil.isUrlMatch("/lms/**/*.do", uri)) {
//			log.debug("□lms접근");
//			if(!isSignVerify) {
//				log.debug("□권한 없음 403페이지 이동");
//				return "redirect:/error/403.do"; 
//			}
//		} else if(OcsStringUtil.isUrlMatch("/file/*.do", uri)) {
//			log.debug("□file접근");
//			// skip
//		} else if(isAjax) {
//			log.debug("□ajax접근");
//			// skip
//		} else if(OcsStringUtil.isUrlMatch("/error/*.do", uri)) {
//			log.debug("□error접근");
//			// skip
//		} else {
//			log.debug("□세션 초기화");
//			if(isSignVerify) {
//				session.removeAttribute("delfinoNonce");
//			}
//		}	
//		
//		Object result = joinPoint.proceed();
//		
//		if( isAjax ){
//			/** AJAX 인 경우 응답값을 세팅한다. **/
//			Map<String, Object> ajaxResult = new HashMap<String, Object>();
//			
//			ajaxResult.put(CodeConstrants.AJAX_RES_CODE, CodeConstrants.AJAX_RES_CODE_SUCCESS);
//			ajaxResult.put(CodeConstrants.AJAX_RES_MESSAGE, "");
//			ajaxResult.put(CodeConstrants.AJAX_RES_DATA, result);
//			
//			return ajaxResult;
//		}else{
//			if( isFileDownLoad ){
//				if( response != null ){
//					/** 파일 다운로드인 경우 쿠키 설정 **/
//					response.setHeader("Set-Cookie", "fileDownload=true; path=/");
//				}
//			}else{
//				/** 페이지 호출시에 현재 요청 URL 을 세팅한다. 페이지 태그에서 사용된다. **/
//				String url = request.getRequestURI().substring(request.getContextPath().length());
//				addRequestParam(request, CodeConstrants.COMMON_REQUEST_URL, url);
//			}
//			return result;
//		}
//	}
//	
//	/**
//	 * REQUEST 에 데이터 추가
//	 * @param request
//	 * @param name
//	 * @param value
//	 */
//	public void addRequestParam(HttpServletRequest request, String name, Object value ){
//		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
//		request.setAttribute(name, value);
//		RequestContextHolder.setRequestAttributes(attributes);
//	}
//			
//
//}
