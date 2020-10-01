package com.example.android.topic41.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Theme {
    private String requestParameter;
    private Map<String, String> requestParameterValues;
    private final String DEFAULT_KEY = "Software";
    private final String DEFAULT_VALUE = "software";

    public Theme() {
        requestParameterValues = new HashMap<>();
        requestParameter = "q";
        requestParameterValues.put(DEFAULT_KEY, DEFAULT_VALUE);
        requestParameterValues.put("General", "general");
        requestParameterValues.put("Health", "health");
        requestParameterValues.put("Science", "science");
        requestParameterValues.put("Technology", "technology");
    }

    public String getRequestParameter() {
        return requestParameter;
    }


    public Map<String, String> getRequestParameterValues() {
        return requestParameterValues;
    }


    public List<String> getRequestParameterKeys() {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> requestParameterValue : requestParameterValues.entrySet()) {
            if (requestParameterValue.getKey().equals(DEFAULT_KEY)){
                list.add(0, requestParameterValue.getKey());
            } else {
                list.add(requestParameterValue.getKey());
            }
        }
        return list;
    }
}
