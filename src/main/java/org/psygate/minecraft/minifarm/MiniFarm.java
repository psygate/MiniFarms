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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author psygate (https://github.com/psygate)
 */
public class MiniFarm extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        getConfig().options().copyHeader(true);
        saveConfig();

        int searchRadius = getConfig().getInt("searchRadius");
        Material farmBlockType = Material.valueOf(getConfig().getString("farmBlockType").toUpperCase());
        if (getConfig().getBoolean("Crops")) {
            getServer().getPluginManager().registerEvents(new Croplistener(searchRadius, farmBlockType), this);
        }

        if (getConfig().getBoolean("Carrots")) {
            getServer().getPluginManager().registerEvents(new Carrotlistener(searchRadius, farmBlockType), this);
        }

        if (getConfig().getBoolean("Potatoes")) {
            getServer().getPluginManager().registerEvents(new Potatolistener(searchRadius, farmBlockType), this);
        }

        if (getConfig().getBoolean("Melons")) {
            getServer().getPluginManager().registerEvents(new Melonlistener(searchRadius, farmBlockType), this);
        }

        if (getConfig().getBoolean("Pumpkins")) {
            getServer().getPluginManager().registerEvents(new Pumpkinlistener(searchRadius, farmBlockType), this);
        }
    }

}
