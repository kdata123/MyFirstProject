package kr.kdata.filter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IPAddressUtil {
	public static String getIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			log.info("🍳 Proxy-Client-IP : " + ip);
			if (ip == null) {
				ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
				log.info("🍳 WL-Proxy-Client-IP : " + ip);
				if (ip == null) {
					ip = request.getHeader("HTTP_CLIENT_IP");
					log.info("🍳 HTTP_CLIENT_IP : " + ip);
					if (ip == null) {
						ip = request.getHeader("HTTP_X_FORWARDED_FOR");
						log.info("🍳 HTTP_X_FORWARDED_FOR : " + ip);
						if (ip == null) {
							ip = request.getRemoteAddr();
						}
					}
				}
			}
		}
		log.info("🍳 Result : IP Address : " + ip);
		return ip;

	}
}
