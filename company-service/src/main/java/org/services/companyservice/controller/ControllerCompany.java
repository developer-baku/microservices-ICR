package org.services.companyservice.controller;

import org.services.companyservice.dto.RequestCompany;
import org.services.companyservice.dto.ResponseCompany;
import org.services.companyservice.service.ServiceCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class ControllerCompany {
    @Autowired
    private ServiceCompany companyService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompany> getCompany(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.getCompany(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseCompany>> getAllCompany() {
        var v =companyService.getAllCompany();
        return new ResponseEntity<>(v,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody RequestCompany requestCompany) {
          companyService.updateCompany(requestCompany);return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping ("/delete")
    public ResponseEntity<Void> delete(@RequestBody RequestCompany requestCompany) {
        companyService.deleteCompany(requestCompany);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody RequestCompany requestCompany) {
        companyService.addCompany(requestCompany);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
