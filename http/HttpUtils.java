package org.example.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.cert.X509Certificate;

/**
 * 通用http发送方法
 *
 * @author ruoyi
 */
public class HttpUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url   发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
        InputStream in = null;
        StringBuffer bs = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection urlcon = (HttpURLConnection) realUrl.openConnection();
            urlcon.setRequestProperty("Accept-Charset", "utf-8");
            urlcon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlcon.connect();// 获取连接  
            in = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(
                    in, "utf-8"));

            String line = null;
            while ((line = buffer.readLine()) != null) {
                bs.append(line);
            }
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendGet Exception,url=" + url, e);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                log.error("关闭流Exception", e);
                ;
            }
        }
        return bs.toString();
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url) {
        InputStream in = null;
        StringBuffer bs = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection urlcon = (HttpURLConnection) realUrl.openConnection();
            urlcon.setRequestMethod("POST");
            urlcon.connect();// 获取连接  
            in = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(
                    in, "utf-8"));

            String line = null;
            while ((line = buffer.readLine()) != null) {
                bs.append(line);
            }
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendGet Exception,url=" + url, e);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                log.error("关闭流Exception", e);
                ;
            }
        }
        return bs.toString();
    }

    public static String sendSSLPost(String url, String param) {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try {
            log.info("sendSSLPost - {}", urlNameString);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while ((ret = br.readLine()) != null) {
                if (ret != null && !ret.trim().equals("")) {
                    result.append(new String(ret.getBytes("ISO-8859-1"), "utf-8"));
                }
            }
            log.info("recv - {}", result);
            conn.disconnect();
            br.close();
        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
        }
        return result.toString();
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}