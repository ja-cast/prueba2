package com.lifebank.factory.responseFactory;

import com.lifebank.factory.responseFactory.response.IResponse;

public interface IResponseFactory<T> {
    public IResponse createResponse(int responseCode);
}
