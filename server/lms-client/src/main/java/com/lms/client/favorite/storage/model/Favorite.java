package com.lms.client.favorite.storage.model;

import com.lms.atom.book.storage.model.Resource;
import com.lms.client.client.storage.model.Client;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Favorite {

    private static final long serialVersionUID = -3009157732242241607L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Resource resource;
    @ManyToOne
    private Client client;
    private Date actionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}
