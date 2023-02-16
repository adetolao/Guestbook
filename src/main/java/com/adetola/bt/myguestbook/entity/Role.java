package com.adetola.bt.myguestbook.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public enum Role {
    USER,
    ADMIN
}
