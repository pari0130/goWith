package com.jd.gowith.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateTimeUtil {
	
	// View영역에선 LocalDateTime 타입을 모르기 때문에 인식할수 있도록 toStringDateTime을 통해 문자열로 날짜형식을 변경해서 등록
	public static String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
