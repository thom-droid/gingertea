package com.thom.gingertea.domain.supplier.service;

import com.thom.gingertea.common.communication.service.AbstractCommunicationService;
import com.thom.gingertea.config.properties.WebClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SupplierServerServiceImpl extends AbstractCommunicationService {
    @Autowired
    public SupplierServerServiceImpl(WebClient webClient, WebClientProperties webClientProperties) {
        super(webClient, webClientProperties, webClientProperties.getExternal().getSupplier());
    }

}
