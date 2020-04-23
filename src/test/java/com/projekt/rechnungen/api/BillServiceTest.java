package com.projekt.rechnungen.api;

import com.projekt.rechnungen.model.Bill;
import com.projekt.rechnungen.rest.BillController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BillServiceTest {

    @Autowired
    private BillController billController;

    @Test
    public void getAllBills(){
        ResponseEntity<List<Bill>> allBills = billController.getAllBills();
        assertNotNull(billController.getAllBills());
    }

}
