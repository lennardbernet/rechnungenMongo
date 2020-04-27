package com.projekt.controller;

import com.projekt.mysql.api.AdressService;
import com.projekt.mysql.model.Adress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "adress")
public class AdressController {
    private AdressService adressService;

    @Autowired
    public AdressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping(value = "getAdresses")
    public ResponseEntity<List<Adress>> getAdresses() {
        return ok(adressService.getAdresses());
    }

    @PostMapping(value = "saveAdress")
    public void saveAdress(
            @RequestParam(name="street") String street,
            @RequestParam(name="streetnumber") int streetnumber,
            @RequestParam(name="zip_code") int zipCode,
            @RequestParam(name="place") String place
            ) {
        System.out.println(street);
        Adress adress = new Adress(street, streetnumber, zipCode, place);
        System.out.println("adress controller: "+adress.getStreet());
        adressService.saveAdress(adress);
    }

    @PostMapping(value = "removeAdress")
    public void removeAdress(Adress adress) {
        adressService.removeAdress(adress);
    }

    @PutMapping(value = "updateAdress")
    public void updateAdress(Adress adress) {
        adressService.updateAdress(adress);
    }


}
