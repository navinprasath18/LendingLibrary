package com.lendinglib.app.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name = "items")
@Getter
@Setter
@Entity
public class Item extends BaseEntity {
	
	@Column(name = "name")
	String itemName;

    @Column(name = "item_type")
    ItemType itemType;

    @Column(name = "description")
    String description;

    int costToRent;

    int count_available;

    int total_count;

    public void addCount()
    {
        this.count_available++;
    }
    public void reduceCount()
    {
        this.count_available--;
    }

}
