package com.impactflux.terracraft.items;

import com.impactflux.terracraft.TerraCraft;

public enum TerraItemTypes {
    AXE(0),
    SWORD(1), 
    SHOVEL(2), 
    PICKAXE(3),
    HOE(4),
    SICKLE(5);
    
    
    public int value;

    private TerraItemTypes(int value) {
            this.value = value;
    }
};
