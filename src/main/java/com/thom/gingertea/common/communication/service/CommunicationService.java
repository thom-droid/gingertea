package com.thom.gingertea.common.communication.service;

import com.thom.gingertea.common.communication.dto.WebClientDto;

public interface CommunicationService {
    <T> T sendRequest(WebClientDto.Request<T> request);
}
