package org.example;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-09
 */
@Service
public class ElasticRestService implements IElasticRestService {
    private final Logger logger = LoggerFactory.getLogger(ElasticRestService.class);

    @Autowired
    private CommonConfig config;

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        return headers;
    }

    /**
     * 使用get /index/type/id调用elasticsearch一条不存在的记录代码异常并返回404问题的解决方案
     * @param sqlid
     * @return
     * @throws Exception
     */
    @Override
    public String getAppidBySqlId(String sqlid) throws Exception {
        HttpHeaders headers = getHeader();
        HttpEntity requestEntity = new HttpEntity(headers);
        //http://x.x.x.x:9200/hiveid/sqlid/sql-001
        String url = config.getEsUrl() + "/hiveid/sqlid/" + sqlid;
        logger.info("SQLID:{},调用esURL:{}", sqlid, url);
        String appid = null;
        try {
            ResponseEntity<JSONObject> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, JSONObject.class);
            JSONObject body = result.getBody();
            JSONObject source = body.getJSONObject("_source");
            appid = source.getString("appid");
            logger.info("SQLID:{},appID:{}", sqlid, appid);
        } catch (HttpClientErrorException e) {
            String bodyAsString = e.getResponseBodyAsString();
            logger.info(bodyAsString);
            return "NotFound";
        } catch (Exception e) {
            String error = "SQLID:" + sqlid + ",调用失败,url:" + url;
            logger.error(error, e);
            throw new Exception(error + e.toString());
        }
        return appid;
    }
}
