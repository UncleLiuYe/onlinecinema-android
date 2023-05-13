package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TicketApi {
    @GET("ticket/list")
    Call<Result<List<Ticket>>> list();
}
