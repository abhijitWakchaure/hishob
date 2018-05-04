package com.darkroomlabs.hishob;

import java.util.LinkedList;

class DataManager {
    private static DataManager instance = null;

    private LinkedList<String> paymentModes;

    public static DataManager getInstance() {
        if(instance == null) {
            instance = new DataManager();
            instance.loadPaymentModes();
        }
        return instance;
    }


    private void loadPaymentModes() {
        paymentModes = new LinkedList<>();
        paymentModes.add("Cash");
        paymentModes.add("Splitwise");
        paymentModes.add("HDFC Debit");
        paymentModes.add("HDFC Credit");
    }

    public LinkedList<String> getPaymentModes() {
        return paymentModes;
    }
}
