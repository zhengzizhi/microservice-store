package com.contoso.cart;

import java.io.Serializable;

import com.contoso.order.Order;

public class CheckoutResult implements Serializable {
 
	private static final long serialVersionUID = -4037686032533844276L;
	
	private String resultMessage;
    private Order order;

    public CheckoutResult() {
    }

    public CheckoutResult(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public CheckoutResult(String resultMessage, Order order) {
        this.resultMessage = resultMessage;
        this.order = order;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CheckoutResult{" +
                "resultMessage='" + resultMessage + '\'' +
                '}';
    }
}
