package com.example.taskmanagement.util;

import com.example.taskmanagement.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Predicate;

public class CustomerUtil {

    private static final String CUSTOMER_FILE = "src/main/resources/data/customers.json";
    private static final TypeReference<List<Customer>> TYPE = new TypeReference<>() {};

    public static List<Customer> getAllCustomers() {
        return JsonUtil.getAll(CUSTOMER_FILE, TYPE);
    }

    public static void addCustomer(Customer customer) {
        JsonUtil.add(CUSTOMER_FILE, customer, TYPE);
    }

    public static void updateCustomer(Customer customer) {
        JsonUtil.edit(CUSTOMER_FILE, c -> c.getId().equals(customer.getId()), customer, TYPE);
    }

    public static void deleteCustomerById(String id) {
        JsonUtil.delete(CUSTOMER_FILE, c -> c.getId().equals(id), TYPE);
    }

    public static Customer getById(String id) {
        return JsonUtil.getOne(CUSTOMER_FILE, TYPE, c -> c.getId().equals(id));
    }
}