package com.bobocode.lesson11;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DemoApp2 {

    public static void main(String[] args) {
        //String urlString = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=15&api_key=DEMO_KEY";
        String urlString = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=14&api_key=DEMO_KEY";

        var restTemplate = new RestTemplate();
        var jsonResponse = restTemplate.getForObject(urlString, JsonNode.class);
        List<String> images = StreamSupport.stream(jsonResponse.get("photos").spliterator(), false)
                .map(p -> p.get("img_src"))
                .map(JsonNode::asText).toList();

        Optional<Map.Entry<String, Long>> entry = images.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(
                                        Function.identity(),
                                        e ->
                                                restTemplate.headForHeaders(restTemplate.headForHeaders(e).getLocation()).getContentLength()),
                                map ->
                                        map.entrySet().stream().min(Comparator.comparingLong(Map.Entry::getValue))
                        ));

        if (entry.isPresent()) {
            System.out.println("img_src: " + entry.get().getKey());
            System.out.println("size: " + entry.get().getValue());
        }
    }

}
