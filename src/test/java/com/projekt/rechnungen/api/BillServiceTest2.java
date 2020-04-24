package com.projekt.rechnungen.api;

import com.projekt.rechnungen.model.Adress;
import com.projekt.rechnungen.model.Bill;
import com.projekt.rechnungen.rest.BillController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class BillServiceTest2 {

    @InjectMocks
    private BillController billController;

    @Mock
    private BillService billServiceMock;

    @Test
    public void getAllBills(){
        Bill bill = new Bill("lennard", "bernet", 500, "CH123", new Date(20150423), new Adress("zürcherstrasse", 2, 8154, "oberglatt"));
        List<Bill> list = new ArrayList<>();
        list.add(bill);

        when(billServiceMock.getAllBills()).thenReturn(list);

        List<Bill> listMocked = billController.getAllBills().getBody();

        System.out.println(listMocked.get(0).getFirstname());

        assertEquals(listMocked,list);
    }

}
