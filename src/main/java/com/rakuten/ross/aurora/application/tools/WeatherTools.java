package com.rakuten.ross.aurora.application.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WeatherTools {

	@Tool(description = "读取指定位置的天气")
	public String get_current_weather(String city, float x, float y) {
		log.info("读取指定位置的天气");
		return """
				北京当前温度：12°C
				天气状况：雾霾
				体感温度：12°C
				今天天气：大部分地区多云，最低气温9°C
				空气质量：轻度污染 (51-100)，主要污染物 PM2.5 75 μg/m³
				风速：轻风 (2 - 5 公里/小时)，西南风 1级
				湿度：78%
				能见度：能见度差 (1 - 2 公里)，2 公里
				气压：1018 hPa
				露点：8°C""";
	}

}