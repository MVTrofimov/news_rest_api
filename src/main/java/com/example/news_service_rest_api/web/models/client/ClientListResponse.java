package com.example.news_service_rest_api.web.models.client;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClientListResponse {

    private List<ClientResponse> clients = new ArrayList<>();

}
