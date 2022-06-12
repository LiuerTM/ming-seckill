package ind.liuer.seckill.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * cookie工具类
 *
 * @author Mingの
 * @date 2022/6/12 15:44
 */
public class CookieUtil {

    public static final Logger log = LoggerFactory.getLogger(CookieUtil.class);

    public static final String LOCAL_HOST = "localhost";
    public static final String HTTP_PREFIX = "http://";
    public static final String SLASH = "/";
    public static final String COLON = ":";

    public static final String SPOT_REGEX = "\\.";

    public static final int DOMAIN_LENGTH = 3;


    /**
     * 获取cookie值
     *
     * @param request    request
     * @param cookieName cookie名称
     * @return cookie值
     */
    public static String getCookieVal(HttpServletRequest request, String cookieName) {
        return getCookieVal(request, cookieName, false);
    }

    /**
     * 获取cookie值
     *
     * @param request    request
     * @param cookieName cookie名称
     * @param isEncode   是否编码
     * @return cookie值
     */
    public static String getCookieVal(HttpServletRequest request, String cookieName, boolean isEncode) {
        String encodingStr = isEncode ? StandardCharsets.UTF_8.toString() : null;
        return getCookieVal(request, cookieName, encodingStr);
    }

    /**
     * 获取cookie值
     *
     * @param request     request
     * @param cookieName  cookie名称
     * @param encodingStr 编码
     * @return cookie值
     */
    public static String getCookieVal(HttpServletRequest request, String cookieName, String encodingStr) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || !StringUtils.hasLength(cookieName)) {
            return null;
        }
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    if (StringUtils.hasLength(encodingStr)) {
                        return URLEncoder.encode(cookie.getValue(), encodingStr);
                    } else {
                        return cookie.getValue();
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            log.error("获取cookie值解码失败：{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置cookie值（不设置过期时间、不编码）
     *
     * @param request    request
     * @param response   response
     * @param cookieName cookie名称
     * @param cookieVal  cookie值
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,
                                 String cookieName, String cookieVal) {
        setCookie(request, response, cookieName, cookieVal, -1);
    }

    /**
     * 设置cookie值（设置过期时间、不编码）
     *
     * @param request      request
     * @param response     response
     * @param cookieName   cookie名称
     * @param cookieVal    cookie值
     * @param cookieMaxAge cookie存活时间
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,
                                 String cookieName, String cookieVal, int cookieMaxAge) {
        setCookie(request, response, cookieName, cookieVal, cookieMaxAge, false);
    }

    /**
     * 设置cookie值（编码、不设置过期时间）
     *
     * @param request    request
     * @param response   response
     * @param cookieName cookie名称
     * @param cookieVal  cookie值
     * @param isEncode   cookie值是否编码（默认UTF-8）
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,
                                 String cookieName, String cookieVal, boolean isEncode) {
        setCookie(request, response, cookieName, cookieVal, -1, isEncode);
    }

    /**
     * 设置cookie值（设置过期时间、编码）
     *
     * @param request      request
     * @param response     response
     * @param cookieName   cookie名称
     * @param cookieVal    cookie值
     * @param cookieMaxAge cookie存活时间
     * @param isEncode     cookie值是否编码（默认UTF-8）
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,
                                 String cookieName, String cookieVal, int cookieMaxAge, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieVal, cookieMaxAge, isEncode);
    }

    /**
     * 设置cookie值（设置过期时间、编码）
     *
     * @param request      request
     * @param response     response
     * @param cookieName   cookie名称
     * @param cookieVal    cookie值
     * @param cookieMaxAge cookie存活时间
     * @param encodeStr    cookie值编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,
                                 String cookieName, String cookieVal, int cookieMaxAge, String encodeStr) {
        doSetCookie(request, response, cookieName, cookieVal, cookieMaxAge, encodeStr);
    }


    /**
     * 删除cookie（带cookie域名）
     *
     * @param request    request
     * @param response   response
     * @param cookieName cookie名称
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * 设置cookie
     *
     * @param request      request
     * @param response     response
     * @param cookieName   cookie名称
     * @param cookieVal    cookie值
     * @param cookieMaxAge cookie过期时间
     * @param isEncode     cookie值是否编码（默认UTF-8）
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName, String cookieVal, int cookieMaxAge, boolean isEncode) {
        String encodeStr = isEncode ? StandardCharsets.UTF_8.toString() : null;
        doSetCookie(request, response, cookieName, cookieVal, cookieMaxAge, encodeStr);
    }

    /**
     * 设置cookie
     *
     * @param request      request
     * @param response     response
     * @param cookieName   cookie名称
     * @param cookieVal    cookie值
     * @param cookieMaxAge cookie过期时间
     * @param encodeStr    cookie值编码
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName, String cookieVal, int cookieMaxAge, String encodeStr) {
        try {
            if (cookieVal == null) {
                cookieVal = "";
            } else {
                if (StringUtils.hasLength(encodeStr)) {
                    cookieVal = URLEncoder.encode(cookieVal, encodeStr);
                }
            }
            Cookie cookie = new Cookie(cookieName, cookieVal);
            if (cookieMaxAge > 0) {
                cookie.setMaxAge(cookieMaxAge);
            }
            if (request != null) {
                String domainName = getDomainName(request);
                if (!LOCAL_HOST.equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath(SLASH);
            response.addCookie(cookie);
        } catch (Exception e) {
            log.error("设置cookie失败：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取域名
     *
     * @param request request
     * @return 域名
     */
    private static String getDomainName(HttpServletRequest request) {
        String domainName;
        String serverName = request.getRequestURL().toString();
        if (!StringUtils.hasLength(serverName)) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            if (serverName.startsWith(HTTP_PREFIX)) {
                serverName = serverName.substring(7);
            }
            int end = serverName.length();
            if (serverName.contains(SLASH)) {
                end = serverName.indexOf(SLASH);
            }
            serverName = serverName.substring(0, end);
            String[] domains = serverName.split(SPOT_REGEX);
            int len = domains.length;
            if (len > DOMAIN_LENGTH) {
                domainName = domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len > 1) {
                domainName = domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }
        if (domainName.indexOf(COLON) > 0) {
            String[] ary = domainName.split(COLON);
            domainName = ary[0];
        }
        return domainName;
    }
}
