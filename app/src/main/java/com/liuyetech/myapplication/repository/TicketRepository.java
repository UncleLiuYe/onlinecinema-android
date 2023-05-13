package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.RoomBasicInfo;
import com.liuyetech.myapplication.entity.Ticket;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.network.TicketApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class TicketRepository {
    public static final TicketRepository TICKET_REPOSITORY = new TicketRepository();

    private final TicketApi ticketApi;

    private TicketRepository() {
        ticketApi = RetrofitUtils.getInstance().create(TicketApi.class);
    }

    public LiveData<List<Ticket>> list() {
        MutableLiveData<List<Ticket>> data = new MutableLiveData<>();
        ticketApi.list().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<Ticket>>> call, @NonNull Response<Result<List<Ticket>>> response) {
                if (response.isSuccessful()) {
                    Result<List<Ticket>> result = response.body();
                    if (result != null && result.getCode() == 200) {
                        data.setValue(result.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<Ticket>>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }
}
