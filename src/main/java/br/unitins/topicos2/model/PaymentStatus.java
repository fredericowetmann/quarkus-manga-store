package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaymentStatus {

    PENDING(1, "Pending"),
    COMPLETED(2, "Completed"),
    FAILED(2, "Failed");
    

    private final Integer id;
    private final String label;

    PaymentStatus(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static PaymentStatus valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (PaymentStatus paymentstatus : PaymentStatus.values()) {
            if (paymentstatus.getId().equals(id))
                return paymentstatus;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
    
}
