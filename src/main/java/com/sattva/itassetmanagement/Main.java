package com.sattva.itassetmanagement;

import com.sattva.itdatabase.DTO.AllocationDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;

import java.sql.Connection;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConnectionFactory.getInstance().open();
        ArrayList<AllocationDTO> allocations = new Datasource(conn).getAllocationsByEmployeeNotWaiting("ABC123");

        for(AllocationDTO allocationDTO : allocations)
            System.out.println(allocationDTO.getType());

        String date = java.time.LocalDate.now().toString();
        System.out.println(date);
    }
}
