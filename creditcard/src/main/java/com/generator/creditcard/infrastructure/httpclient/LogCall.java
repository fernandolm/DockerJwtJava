package com.generator.creditcard.infrastructure.httpclient;

import com.generator.creditcard.domain.request.Log;
import com.generator.creditcard.domain.response.TicketResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LogCall {
    @POST("log")
    Call<TicketResponse> send(@Body Log logCallInput);
}
