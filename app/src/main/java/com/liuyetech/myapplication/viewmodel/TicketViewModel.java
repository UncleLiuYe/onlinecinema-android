package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.Ticket;
import com.liuyetech.myapplication.repository.TicketRepository;

import java.util.List;

public class TicketViewModel extends ViewModel {
    public LiveData<List<Ticket>> list() {
        return TicketRepository.TICKET_REPOSITORY.list();
    }
}
