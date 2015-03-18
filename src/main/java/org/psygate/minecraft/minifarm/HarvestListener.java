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

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.event.Listener;

/**
 *
 * @author psygate (https://github.com/psygate)
 */
public class HarvestListener implements Listener {

    private final static BlockFace[] directions = new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
    private final int searchRadius;
    private final Material farmBlockType;
    protected final Random rand = new Random();

    public HarvestListener(final int searchRadius, final Material farmBlockType) {
        if (searchRadius <= 0) {
            throw new IllegalArgumentException("Search radius must be >= 1.");
        }

        if (farmBlockType == null) {
            throw new IllegalArgumentException("Farm block type cannot be null.");
        }

        this.searchRadius = searchRadius;
        this.farmBlockType = farmBlockType;
    }

    protected Block findFarm(final Block growing) {
        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int z = -searchRadius; z <= searchRadius; z++) {
                if (x == 0 && z == 0) {
                    continue;
                }
                final Block block = growing.getRelative(x, 0, z);
                if (block.getType().equals(farmBlockType)) {
                    return block;
                }
            }
        }

        return null;
    }

    protected Chest[] findChests(final Block farm) {
        ArrayList<Chest> chests = new ArrayList<>();
        for (BlockFace face : directions) {

            final Block block = farm.getRelative(face.getModX(), 1, face.getModZ());
            if (block.getType().equals(Material.CHEST)) {
                chests.add((Chest) block.getState());
            }
        }

        if (chests.size() >= 2) {
            return chests.toArray(new Chest[0]);
        } else {
            return null;
        }
    }
}
