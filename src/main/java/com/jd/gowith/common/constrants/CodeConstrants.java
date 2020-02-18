package com.jd.gowith.common.constrants;

public class CodeConstrants {

	/**
	 * 화면상에서 AJAX 통신시 헤드 구분 명
	 */
	public static final String AJAX_HEADER_NAME = "_AJAX_HEADER_NAME_";
	
	/**
	 * 화면상에서 AJAX 통신시 헤드 구분 값
	 */
	public static final String AJAX_HEADER_VALUE = "AJAX";
	
	
	public static final String AJAX_RES_CODE = "RES_CODE";
	
	public static final String AJAX_RES_MESSAGE = "RES_MESSAGE";
	
	
	public static final String AJAX_RES_DATA = "RES_DATA";
	
	
	/**
	 * AJAX 응답코드 성공
	 */
	public static final String AJAX_RES_CODE_SUCCESS = "0000";
	
	/**
	 * AJAX 응답코드 실패
	 */
	public static final String AJAX_RES_CODE_FAIL = "8888";
	
	/**
	 * AJAX 권한 없음
	 */
	public static final String AJAX_RES_CODE_ACCESS_DENIED = "7777";
	
	/**
	 * AJAX 응답코드 에러
	 */
	public static final String AJAX_RES_CODE_ERROR = "9999";
	
	
	public static final String AJAX_RES_CODE_500 = "500";
	public static final String AJAX_RES_CODE_503 = "503";
	public static final String AJAX_RES_CODE_400 = "400";
	public static final String AJAX_RES_CODE_403 = "403";
	public static final String AJAX_RES_CODE_404 = "404";
	public static final String AJAX_RES_CODE_405 = "405";
	
	/**
	 * AJAX 파일 다운로드 구분값
	 */
	public static final String AJAX_FILE_DOWNLOAD_FLAG = "AJAX_FILEDOWNLOAD_FLAG";
	
	/**
	 * AJAX 파일 다운로드 유무
	 */
	public static final String AJAX_FILE_DOWNLOAD_VALUE = "true";
	
	
	/**
	 * 로그인 실패시 세션에 담기는 객체 명 
	 */
	public static final String LOGIN_FAIL_VO_SESSION_NAME = "_LOGIN_FAIL_VO_SESSION_NAME_";
	/**
	 * 로그인 실패  유효하지 않는 비밀번호
	 */
	public static final String LOGIN_FAIL_NOT_VALID_PASSWORD = "NOT_VALID_PASSWORD";
	/**
	 * 로그인 실패 존재하지 않는 사용자
	 */
	public static final String LOGIN_FAIL_NON_USER = "NON_USER";
	
	/**
	 * 로그인 실패 아이디가 공백인유저
	 */
	public static final String LOGIN_FAIL_WHITE_USER = "WHITE_USER";
	
	
	/**
	 * 로그인 실패 비밀번호 10 회 틀려서 계정 잠김
	 */
	public static final String LOGIN_FAIL_LOCKED_USER = "LOCKED_USER";
	/**
	 * 로그인 실패 비밀번호 5회 틀려서 자동입력 방지 필요
	 */
	public static final String LOGIN_FAIL_NEED_SHADOW = "NEED_SHADOW";
	
	/**
	 * 로그인 실패 관리자의 승인이 필요합니다. 
	 */
	public static final String LOGIN_FAIL_NEED_MANAGER_CONFIRM = "NEED_MANAGER_CONFIRM";

	/**
	 * 로그인 권한이 존재하지 않습니다.
	 */
	public static final String LOGIN_FAIL_NON_ROLE = "NON_ROLE";
	
	
	/**
	 * 로그인 실패 기타
	 */
	public static final String LOGIN_FAIL_ETC = "ETC";
	/**
	 * 로그인 성공시 쿠키 에 사용자 아이디 저장하는 NAME
	 */
	public static final String LOGIN_COOKIE_USERID_NAME = "OCS_LOGIN_USER_ID";
	
	/**
	 * 보안 문자 세션 키
	 */
	public static final String CAPTCHA_SESSION_KEY = "_CAPTCHA_SESSION_KEY_";
	
	/**
	 * 비밀번호 변경일 세션키
	 */
	public static final String PWD_CHG_DTM_SESSION_KEY = "_PWD_CHG_DTM_SESSION_KEY_";
	
	/**
	 * 요청 URL
	 */
	public static final String COMMON_REQUEST_URL = "_COMMON_REQUEST_URL_";
	

	/**
	 * 테스트 코드
	 * 코드성 데이터는 CODE_ 를 PREFIX 해주세요.
	 */
	public static final String CODE_TEST = "100033";
	
	/**
	 * YES CODE
	 */
	public static final String CODE_YES = "Y";
	
	/**
	 * NO CODE
	 */
	public static final String CODE_NO = "N";


	public static final String WEBRTC_DOMAIN_URL = ".classon.kr";
}
