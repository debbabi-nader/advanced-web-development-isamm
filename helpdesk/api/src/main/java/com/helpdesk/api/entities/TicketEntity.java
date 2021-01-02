package com.helpdesk.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "TICKETS")
public class TicketEntity implements Serializable {

    private static final long serialVersionUID = 5002619542626697367L;

    @Id
    private String id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = true)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "CREATED_BY")
    private UserEntity createdBy;

    @OneToMany(
        mappedBy = "ticket",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    private List<TicketStatusEntity> statuses;

    public TicketEntity() {
        super();
    }

    public TicketEntity(
            String label,
            String description) {

        this.label = label;
        this.description = description;

    }

    public TicketEntity(
            String label,
            String description,
            UserEntity createdBy) {

        this.label = label;
        this.description = description;
        this.createdBy = createdBy;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public List<TicketStatusEntity> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<TicketStatusEntity> statuses) {
        if (this.statuses == null) {
            this.statuses = new ArrayList<>();
        } else {
            this.statuses.clear();
        }
        if (statuses != null) {
            this.statuses.addAll(statuses);
        }
    }

}
