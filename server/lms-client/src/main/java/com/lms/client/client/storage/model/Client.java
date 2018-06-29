package com.lms.client.client.storage.model;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
