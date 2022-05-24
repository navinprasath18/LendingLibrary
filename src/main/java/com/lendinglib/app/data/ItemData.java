package com.lendinglib.app.data;

import com.lendinglib.app.entities.ItemType;
import lombok.Data;

import javax.persistence.Column;

@Data
public class ItemData {

    String itemName;

    ItemType itemType;

    String description;

    int costToRent;

    int count_available;

    int total_count;

}
