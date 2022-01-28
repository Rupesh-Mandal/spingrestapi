package com.softkali.spingrestapi.controller;

import com.google.zxing.WriterException;
import com.softkali.spingrestapi.model.EmployeeModel;
import com.softkali.spingrestapi.service.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Controller
@RestController
public class EmployeeController {

    private PDFGeneratorService pdfGeneratorService;

    public EmployeeController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }


    @Value("${app.name: null}")
    private String appName;

    @Value("${app.version: null}")
    private String appVersion;

    @GetMapping("/app")
    public String getAppDetails(){
        return appName+" "+appVersion;
    }

    @GetMapping("/employees")
    public String getEmoployees(){
        return "huuuur";
    }

     @GetMapping("/employees/{id}")
    public String getEmoployee(@PathVariable("id") Long id){
        return "huuuur";
    }

    @DeleteMapping("/employees")
    public String deletEmployee (@RequestParam("id") Long id){
        return "deleting employee by id "+id;
    }

    @PostMapping("/employees")
    public String saveEmployee(@RequestBody EmployeeModel employeeModel){
        return "saving the employee details tjho database"+employeeModel;
    }

    @PutMapping("/employees/{id}")
    public EmployeeModel updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeModel employeeModel){
        System.out.println(id);
        return employeeModel;
    }

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response) throws IOException, WriterException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);
    }
}
