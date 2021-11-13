package com.thatguysapp.quickpoll.domain;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "VOTE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="OPTION_ID")
    private POption option;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public POption getOption() {
        return option;
    }

    public void setOption(POption option) {
        this.option = option;
    }
}

