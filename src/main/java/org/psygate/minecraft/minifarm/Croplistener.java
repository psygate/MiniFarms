/*
 * The MIT License
 *
 * Copyright 2015 psygate (https://github.com/psygate)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.psygate.minecraft.minifarm;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

/**
 *
 * @author psygate (https://github.com/psygate)
 */
public class Croplistener extends HarvestListener {

    public Croplistener(int searchRadius, Material farmBlockType) {
        super(searchRadius, farmBlockType);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void checkGrowth(final BlockGrowEvent ev) {
        if (Material.CROPS == ev.getBlock().getType()) {
            final Block farmblock = findFarm(ev.getBlock());
            if (farmblock != null) {
                Chest[] chests = findChests(farmblock);
                if (chests != null) {
                    Crops data = (Crops) ev.getNewState().getData();
                    if (data.getState() == CropState.RIPE) {
                        chests[0].getInventory().addItem(new ItemStack(Material.SEEDS, rand.nextInt(2) + 1));
                        chests[1].getInventory().addItem(new ItemStack(Material.WHEAT, rand.nextInt(3) + 1));
                        ev.getNewState().setData(new Crops(CropState.SEEDED));
                    }

                }
            }
        }
    }

}
