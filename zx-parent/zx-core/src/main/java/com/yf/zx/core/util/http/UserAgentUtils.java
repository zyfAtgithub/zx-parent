package com.yf.zx.core.util.http;

import net.sf.json.JSONObject;
import nl.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

public class UserAgentUtils {

    public static UserAgent getClientInfo(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        System.out.println(request.getHeader("User-Agent"));
        System.out.println("客户端浏览器:" + userAgent.getBrowser().toString());
        System.out.println("客户端操作系统:" + userAgent.getOperatingSystem().toString());
        return  userAgent;
    }

    public static String[] userAgentArr4Test = {
            "Mozilla/5.0(Linux; U; Android 4.3; zh-cn; R8007 Build/JLS36C) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
            "Mozilla/5.0(Linux; U; Android 4.3; zh-cn; R8007 Build/JLS36C) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 V1_AND_SQ_5.0.0_146_YYB_D QQ/5.0.0.2215",
            "Mozilla/5.0(Linux; U; Android 4.3; zh-cn; SM-N9009 Build/JSS15J) AppleWebKit/537.36(KHTML, like Gecko)Version/4.0 MQQBrowser/5.3 Mobile Safari/537.36",
            "Mozilla/5.0(Linux; Android 4.2.2; zh-cn; SCH-I959 Build/JDQ39) AppleWebKit/535.19(KHTML, like Gecko) Version/1.0 Chrome/18.0.1025.308 Mobile Safari/535.19",
            "Mozilla/5.0(Linux; U; Android 4.3; zh-CN; SM-N9009 Build/JSS15J) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0 UCBrowser/9.9.2.467U3/0.8.0 Mobile Safari/533.1",
    "Mozilla/5.0(Linux; U; Android 4.1.2; zh-CN; Coolpad 5891Build/JZO54K) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0 UCBrowser/9.9.3.478U3/0.8.0 Mobile Safari/533.1",
    "Mozilla/5.0(Linux; U; Android 4.1.2; zh-cn; Coolpad 5891Build/JZO54K) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30V1_AND_SQ_5.0.0_146_YYB2_D QQ/5.0.0.2215",
    "Mozilla/5.0(iPhone; CPU iPhone OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1(KHTML, like Gecko) Version/7.0 Mobile/11B554a Safari/9537.53",
    "Mozilla/5.0(iPhone; CPU iPhone OS 7_1_2 like Mac OS X) AppleWebKit/537.51.2(KHTML, like Gecko) Mobile/11D257 QQ/5.0.0.165",
    "Mozilla/5.0(Linux; Android 4.3; zh-cn; SAMSUNG-GT-I9308_TD/1.0Android/4.3Release/11.15.2013Browser/AppleWebKit534.30Build/JSS15J) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Mozilla/5.0(Linux; U; Android 4.1.1; zh-cn; GT-N7100 Build/JRO03C) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 SogouMSE,SogouMobileBrowser/3.2.3",
    "Mozilla/5.0(Linux; U; Android 4.2.2; zh-cn; SCH-I959 Build/JDQ39) AppleWebKit/534.24(KHTML, like Gecko) Version/4.0 Mobile Safari/534.24T5/2.0 baidubrowser/5.0.3.10(Baidu; P1 4.2.2)",
    "Mozilla/5.0(Linux; U; Android 4.2.2; zh-cn; SCH-I959 Build/JDQ39) AppleWebKit/534.24(KHTML, like Gecko) Version/4.0 Mobile Safari/534.24T5/2.0",
    "Mozilla/5.0(Linux; Android 4.3; SM-N9009 Build/JSS15J) AppleWebKit/537.36(KHTML, like Gecko) Chrome/37.0.2062.117Mobile Safari/537.36 OPR/24.0.1565.82529",
    "Mozilla/5.0(Linux; U; Android 4.4.4; zh-CN; Nexus 4Build/KTU84P) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0UCBrowser/9.9.2.467U3/0.8.0 Mobile Safari/533.1",
    "Mozilla/5.0(Linux; U; Android 4.0.4; zh-cn; HUAWEI C8825D Build/HuaweiC8825D) AppleWebKit/534.24(KHTML, like Gecko) Version/4.0 Mobile Safari/534.24T5/2.0 baidubrowser/5.2.3.0(Baidu; P1 4.0.4)",
    "Mozilla/5.0(Linux; U; Android 4.0.4; zh-cn; HUAWEI C8825D Build/HuaweiC8825D) AppleWebKit/537.36(KHTML, like Gecko)Version/4.0MQQBrowser/5.3 Mobile Safari/537.36",
    "Mozilla/5.0(Linux; Android 4.0.4; HUAWEI C8825D Build/HuaweiC8825D) AppleWebKit/537.36(KHTML, like Gecko) Chrome/37.0.2062.117Mobile Safari/537.36",
    "Mozilla/5.0(Linux; U; Android 4.0.4; zh-cn; HUAWEI C8825D Build/HuaweiC8825D) AppleWebKit/535.19(KHTML, like Gecko) Version/4.0LieBaoFast/2.12.0 Mobile Safari/535.19",
    "Opera/9.80(Android; Opera Mini/7.0.31907/34.2499; U; zh) Presto/2.8.119Version/11.10",
    "Mozilla/5.0(Linux; U; Android 4.0.4; zh-cn; HW-HUAWEI_C8825D/C8825DV100R001C92B943SP01; 480*800; CTC/2.0) AppleWebKit/534.30 Mobile Safari/534.30",
    "Mozilla/5.0(Linux; U; Android 4.4.2; zh-CN; SGP521 Build/17.1.2.A.0.314) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0UCBrowser/9.9.2.467U3/0.8.0 Mobile Safari/533.1",
    "Mozilla/5.0(Linux; Android 4.4.2; SGP521 Build/17.1.2.A.0.314) AppleWebKit/537.36(KHTML, like Gecko) Chrome/37.0.2062.117Safari/537.36",
    "Mozilla/5.0(Linux; U; Android 4.0.4; zh-CN; HUAWEI C8825D Build/HuaweiC8825D) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0UCBrowser/9.8.5.442U3/0.8.0 Mobile Safari/533.1",
    "Mozilla/5.0(Linux; U; Android 4.1.1; zh-cn; GT-N7100 Build/JRO03C) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Mozilla/5.0(Linux; Android 4.4.2; zh-cn; SAMSUNG-SM-N9009 Build/KOT49H) AppleWebKit/537.36(KHTML, like Gecko) Version/1.5Chrome/28.0.1500.94Mobile Safari/537.36",
    "Mozilla/5.0(Linux; U; Android 4.2.2; zh-CN; HTC HTL22 Build/JDQ39) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0UCBrowser/9.9.2.467U3/0.8.0 Mobile Safari/533.1",
    "Mozilla/5.0(Linux; Android 4.3; SM-N9009 Build/JSS15J) AppleWebKit/537.36(KHTML, like Gecko) Chrome/37.0.2062.117Mobile Safari/537.36",
    "Mozilla/5.0(Linux; U; Android 4.2.1; zh-cn; AMOI A920W Build/JOP40D) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Mozilla/5.0(Linux; Android 4.3; SM-N9009 Build/JSS15J) AppleWebKit/537.36(KHTML, like Gecko) Chrome/36.0.1985.135Mobile Safari/537.36",
    "Mozilla/5.0(Linux; U; Android 4.1.1; zh-CN; GT-N7100 Build/JRO03C) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0UCBrowser/9.9.2.467U3/0.8.0 Mobile Safari/533.1",
    "Mozilla/5.0(Linux; U; Android 4.3; zh-cn; R8007 Build/JLS36C) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Huawei U8800    Android 2.3.3  Baidu 2.2  Mozilla/5.0(Linux; U; Android 2.3.5; zh-cn) AppleWebKit/530.17(KHTML, like Gecko) FlyFlow/2.2Version/4.0 Mobile Safari/530.17",
    "Huawei U8800    Android 2.3.3  UC 8.7 Mozilla/5.0(Linux; U; Android 2.3.5; zh-cn; U8800 Build/HuaweiU8800) UC AppleWebKit/534.31(KHTML, like Gecko) Mobile Safari/534.31",
    "Meizu MX M031   Android 4.0.3  Chrome 18  Mozilla/5.0(Linux; Android 4.0.3; M031 Build/IML74K) AppleWebKit/535.19(KHTML, like Gecko) Chrome/18.0.1025.166Mobile Safari/535.19",
    "Meizu MX M031   Android 4.0.3  Opera 12.1 Opera/9.80(Android 4.0.3; Linux; Opera Mobi/ADR-1210241511) Presto/2.11.355Version/12.10",
    "Meizu MX M031   Android 4.0.3  -built-in *     Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn; M031 Build/IML74K) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Meizu MX M031   Android 4.0.3  Baidu 2.2  Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn) AppleWebKit/530.17(KHTML, like Gecko) FlyFlow/2.2Version/4.0 Mobile Safari/530.17",
    "Meizu MX M031   Android 4.0.3  UC 8.7 Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn; M031 Build/IML74K) UC AppleWebKit/534.31(KHTML, like Gecko) Mobile Safari/534.31",
    "Meizu M9    Android 4.0.3  QQ 3.7 MQQBrowser/3.7/Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn; M9 Build/IML74K) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30   Normal Mode",
    "Meizu M9    Android 4.0.3  QQ 3.5 MQQBrowser/3.5/Adr (Linux; U; 4.0.3; zh-cn; M9 Build/Flyme 1.0.1;640*960)   Speed Mode  有用(0)",
    "Meizu M9    Android 4.0.3  -built-in *     Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn; M9 Build/IML74K) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Meizu M9    Android 4.0.3  QQ 3.5 MQQBrowser/3.5/Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn; M9 Build/IML74K) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30   Normal Mode",
    "Meizu MX M031   Android 4.0.3  Maxthon 2.7    Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn; M031 Build/IML74K) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Huawei U8800    Android 2.3.3  Maxthon 2.7    Mozilla/5.0(Linux; U; Android 2.3.5; zh-cn; U8800 Build/HuaweiU8800) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
    "Huawei U8800    Android 2.3.3  QQ 3.7 MQQBrowser/3.7/Adr (Linux; U; 2.3.5; zh-cn; U8800 Build/U8800V100R001C00B528G002;480*800)   Speed Mode",
    "Huawei U8800    Android 2.3.3  Dolphin 9.1    Mozilla/5.0(Linux; U; Android 2.3.5; zh-cn; U8800 Build/HuaweiU8800) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
    "Huawei U8800    Android 2.3.3  QQ 3.7 MQQBrowser/3.7/Mozilla/5.0(Linux; U; Android 2.3.5; zh-cn; U8800 Build/HuaweiU8800) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 Normal Mode",
    "Huawei U8800    Android 2.3.3  -built-in *     Mozilla/5.0(Linux; U; Android 2.3.5; zh-cn; U8800 Build/HuaweiU8800) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
    "Samsung P6200(GALAXY Tab)   Android 3.2    -built-in *     Mozilla/5.0(Linux; U; Android 3.2; zh-cn; GT-P6200 Build/HTJ85B) AppleWebKit/534.13(KHTML, like Gecko) Version/4.0Safari/534.13",
    "Huawei U8800    Android 2.3.3  Maxthon 4.0    Mozilla/5.0(Linux; U; Android 2.3.5; zh-cn; U8800 Build/HuaweiU8800) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
    "Meizu MX M031   Android 4.0.3  Baidu 2.3  Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn; M031 Build/IML74K) AppleWebKit/530.17(KHTML, like Gecko) FlyFlow/2.3Version/4.0 Mobile Safari/530.17baidubrowser/023_1.41.3.2_diordna_069_046/uzieM_51_3.0.4_130M/1200a/963E77C7DAC3FA587DF3A7798517939D%7C408994110686468/1",
    "Huawei U8800    Android 2.3.3  Baidu 2.3  Mozilla/5.0(Linux; U; Android 2.3.5; zh-cn; U8800 Build/HuaweiU8800) AppleWebKit/530.17(KHTML, like Gecko) FlyFlow/2.3Version/4.0 Mobile Safari/530.17baidubrowser/042_1.6.3.2_diordna_008_084/IEWAUH_01_5.3.2_0088U/1001a/BE44DF7FABA8768B2A1B1E93C4BAD478%7C898293140340353/1",
    "Huawei U8800    Android 2.3.3  Dolphin 9.2    Mozilla/5.0(Linux; U; Android 2.3.5; zh-cn; U8800 Build/HuaweiU8800) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 ",
    "HTC S720e(One X)    Android 4.0.3  -built-in *     Mozilla/5.0(Linux; U; Android 4.0.4; zh-cn; HTC S720e Build/IMM76D) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "HTC S720e(One X)    Android 4.0.3  UC 8.7 Mozilla/5.0(Linux; U; Android 4.0.4; zh-cn; HTC S720e Build/IMM76D) UC AppleWebKit/534.31(KHTML, like Gecko) Mobile Safari/534.31",
    "Meizu MX M031   Android 4.0.3  Dolphin Min 2.3    Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn; M031 Build/IML74K) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Meizu MX M031   Android 4.0.3  QQ 4.0 MQQBrowser/4.0/Mozilla/5.0(Linux; U; Android 4.0.3; zh-cn; M031 Build/IML74K) AppleWebKit/533.1(KHTML, like Gecko) Mobile Safari/533.1",
    "Meizu M9    Android 4.0.3  QQ 3.7 MQQBrowser/3.7/Adr (Linux; U; 4.0.3; zh-cn; M9 Build/Flyme 1.0.1;640*960)",
    "Meizu MX2 M040  Android 4.1    UC 9.4 Mozilla/5.0(Linux; U; Android 4.1.1; zh-CN; M040 Build/JRO03H) AppleWebKit/533.1(KHTML, like Gecko) Version/4.0UCBrowser/9.4.1.362U3/0.8.0 Mobile Safari/533.1",
    "Meizu MX2 M040  Android 4.1    Chrome 31  Mozilla/5.0(Linux; Android 4.1.1; M040 Build/JRO03H) AppleWebKit/537.36(KHTML, like Gecko) Chrome/31.0.1650.59Mobile Safari/537.36",
    "Meizu MX2 M040  Android 4.1    猎豹 2.8 Mozilla/5.0(Linux; Android 4.1.1; M040 Build/JRO03H) AppleWebKit/537.36(KHTML, like Gecko) Chrome/28.0.1500.64Mobile Safari/537.36",
    "Meizu MX2 M040  Android 4.1    Baidu 4.1  Mozilla/5.0(Linux; U; Android 4.1.1; zh-cn; M040 Build/JRO03H) AppleWebKit/534.24(KHTML, like Gecko) Version/4.0 Mobile Safari/534.24T5/2.0baidubrowser/4.2.4.0(Baidu; P1 4.1.1)",
    "Meizu MX M031   Android 4.1    -built-in *     Mozilla/5.0(Linux; U; Android 4.1.1; zh-cn; M031 Build/JRO03H) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Meizu MX M031   Android 4.1    UC 8.8 Mozilla/5.0(Linux; U; Android 4.1.1; zh-CN; M031 Build/JRO03H) AppleWebKit/534.31(KHTML, like Gecko) UCBrowser/8.8.3.278U3/0.8.0 Mobile Safari/534.31",
    "Meizu MX2 M040  Android 4.1    QQ 4.1 Mozilla/5.0(Linux; U; Android 4.1.1; zh-cn; M040 Build/JRO03H) AppleWebKit/533.1(KHTML, like Gecko)Version/4.0MQQBrowser/4.1Mobile Safari/533.1",
    "Meizu MX2 M040  Android 4.1    -built-in *     Mozilla/5.0(Linux; U; Android 4.1.1; zh-cn; M040 Build/JRO03H) AppleWebKit/534.30(KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
    "Samsung P6200(GALAXY Tab)   Android 3.2    QQ HD 2.1  Mozilla/5.0(Macintosh; U; Intel Mac OS X 10_6_3; en-us) AppleWebKit/533.16(KHTML, like Gecko) Version/5.0Safari/533.16",
    "Samsung P6200(GALAXY Tab)   Android 3.2    UC HD 2.3  Mozilla/5.0(Macintosh; U; Intel Mac OS X 10_6_3) AppleWebKit/534.31(KHTML, like Gecko) Chrome/17.0.558.0Safari/534.31UCBrowser/2.3.1.257",
};
    public static void main(String[] args) {
//        for (String userAgentStr : userAgentArr4Test) {
//            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
//            System.out.println(userAgent.getOperatingSystem().getDeviceType().getName());
//       }
        UserAgent userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Linux; U; Android 5.1.1; zh-cn; vivo X7 Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.2 Mobile Safari/537.36");
        System.out.println(userAgent);
    }
}
