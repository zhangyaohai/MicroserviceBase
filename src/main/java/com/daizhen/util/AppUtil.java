package com.daizhen.util;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class AppUtil {

    @Autowired
    private LoadBalancerClient loadBalancer;

    public URI getRestUrl(String serviceId, String fallbackUri) {
        URI uri = null;
        try {
            ServiceInstance instance = loadBalancer.choose(serviceId);
            uri = instance.getUri();

        } catch (RuntimeException e) {
            uri = URI.create(fallbackUri);
        }

        return uri;
    }

    public <T> ResponseEntity<T> createOkResponse(T body) {
        return createResponse(body, HttpStatus.OK);
    }

    public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }

    public <T> T json2Object(ResponseEntity<String> response, Class<T> clazz) {
        try {

            return (T) JSON.parseObject(response.getBody(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public <T> List<T> json2Objects(ResponseEntity<String> response, Class<T> clazz) {
        try {

            return JSON.parseArray(response.getBody(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
