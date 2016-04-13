/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.items;

import mods.railcraft.common.core.IVariantEnum;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.plugins.forge.RailcraftRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Locale;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ItemCircuit extends ItemRailcraft {

    public ItemCircuit() {
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public void initializeDefinintion() {
        for (EnumCircuit circuit : EnumCircuit.VALUES) {
            ItemStack stack = new ItemStack(this, 1, circuit.ordinal());
            RailcraftRegistry.register(stack);
        }
    }

    @Override
    public void defineRecipes() {
        CraftingPlugin.addRecipe(new ItemStack(this, 1, EnumCircuit.CONTROLLER.ordinal()),
                " #S",
                "BGR",
                "SRL",
                'L', "gemLapis",
                '#', Items.repeater,
                'G', "ingotGold",
                'S', new ItemStack(Blocks.wool, 1, 14),
                'R', "dustRedstone",
                'B', "slimeball");
        CraftingPlugin.addRecipe(new ItemStack(this, 1, EnumCircuit.RECEIVER.ordinal()),
                " #S",
                "BGR",
                "SRL",
                'L', "gemLapis",
                '#', Items.repeater,
                'G', "ingotGold",
                'S', new ItemStack(Blocks.wool, 1, 13),
                'R', "dustRedstone",
                'B', "slimeball");
        CraftingPlugin.addRecipe(new ItemStack(this, 1, EnumCircuit.SIGNAL.ordinal()),
                " #S",
                "BGR",
                "SRL",
                'L', "gemLapis",
                '#', Items.repeater,
                'G', "ingotGold",
                'S', new ItemStack(Blocks.wool, 1, 4),
                'R', "dustRedstone",
                'B', "slimeball");
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (EnumCircuit circuit : EnumCircuit.VALUES) {
            list.add(new ItemStack(this, 1, circuit.ordinal()));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int damage = stack.getItemDamage();
        if (damage < 0 || damage >= EnumCircuit.VALUES.length)
            return "";
        switch (EnumCircuit.VALUES[damage]) {
            case CONTROLLER:
                return "item.railcraft.part.circuit.controller";
            case RECEIVER:
                return "item.railcraft.part.circuit.receiver";
            case SIGNAL:
                return "item.railcraft.part.circuit.signal";
            default:
                return "";
        }
    }

    public enum EnumCircuit implements IVariantEnum {

        CONTROLLER(Items.comparator),
        RECEIVER(Blocks.redstone_torch),
        SIGNAL(Items.repeater);
        public static EnumCircuit[] VALUES = values();
        private Object alternate;

        EnumCircuit(Object alt) {
            this.alternate = alt;
        }

        @Override
        public Object getAlternate() {
            return alternate;
        }

        @Nonnull
        @Override
        public Class<? extends ItemRailcraft> getParentClass() {
            return ItemCircuit.class;
        }

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ENGLISH);
        }
    }

}
