package com.justeam.justock_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Table(name = "orders")
@Getter @Setter
public class finishOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private order order;

    @Column(nullable = false)
    private Date dataEntrega;

    @Column(nullable = false)
    private Date dataEmissao;

    @Column(nullable = false)
    private String statusPagamento;

    @Column(nullable = false)
    private String statusPedido;

    
}
