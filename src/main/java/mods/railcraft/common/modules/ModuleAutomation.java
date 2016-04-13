/*******************************************************************************
 * Copyright (c) CovertJaguar, 2011-2016
 * http://railcraft.info
 *
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 ******************************************************************************/
package mods.railcraft.common.modules;

import mods.railcraft.api.core.RailcraftModule;
import mods.railcraft.api.core.items.IToolCrowbar;
import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.aesthetics.cube.BlockCube;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.alpha.TamingInteractHandler;
import mods.railcraft.common.blocks.machine.gamma.EnumMachineGamma;
import mods.railcraft.common.carts.EnumCart;
import mods.railcraft.common.carts.ItemBoreHeadDiamond;
import mods.railcraft.common.carts.ItemBoreHeadIron;
import mods.railcraft.common.carts.ItemBoreHeadSteel;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.items.ItemPlate.EnumPlate;
import mods.railcraft.common.items.RailcraftItems;
import mods.railcraft.common.modules.orehandlers.BoreOreHandler;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.plugins.forge.RailcraftRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@RailcraftModule("automation")
public class ModuleAutomation extends RailcraftModulePayload {
    public ModuleAutomation() {
        setEnabledEventHandler(new ModuleEventHandler() {

            @Override
            public void construction() {
                MinecraftForge.EVENT_BUS.register(new BoreOreHandler());
                add(RailcraftBlocks.detector);
            }

            @Override
            public void preInit() {
                BlockCube.registerBlock();

                EnumMachineGamma gamma = EnumMachineGamma.DISPENSER_CART;
                if (gamma.register())
                    CraftingPlugin.addRecipe(gamma.getItem(),
                            "ML",
                            'M', Items.minecart,
                            'L', Blocks.dispenser);

                EnumMachineAlpha alpha = EnumMachineAlpha.FEED_STATION;
                if (alpha.register()) {
                    ItemStack stack = alpha.getItem();
                    CraftingPlugin.addRecipe(stack,
                            "PCP",
                            "CSC",
                            "PCP",
                            'P', "plankWood",
                            'S', RailcraftModuleManager.isModuleEnabled(ModuleFactory.class) ? RailcraftItems.plate.getRecipeObject(EnumPlate.STEEL) : "blockIron",
                            'C', new ItemStack(Items.golden_carrot));

                    MinecraftForge.EVENT_BUS.register(new TamingInteractHandler());
                }

                alpha = EnumMachineAlpha.TRADE_STATION;
                if (alpha.register()) {
                    ItemStack stack = alpha.getItem();
                    CraftingPlugin.addRecipe(stack,
                            "SGS",
                            "EDE",
                            "SGS",
                            'D', new ItemStack(Blocks.dispenser),
                            'G', "paneGlass",
                            'E', "gemEmerald",
                            'S', RailcraftModuleManager.isModuleEnabled(ModuleFactory.class) ? RailcraftItems.plate.getRecipeObject(EnumPlate.STEEL) : "blockIron");
                }

                // Define Bore
                EnumCart cart = EnumCart.BORE;
                if (cart.setup()) {
                    CraftingPlugin.addRecipe(cart.getCartItem(),
                            "ICI",
                            "FCF",
                            " S ",
                            'I', "blockSteel",
                            'S', Items.chest_minecart,
                            'F', Blocks.furnace,
                            'C', Items.minecart);

                    String tag = "tool.bore.head.diamond";
                    if (RailcraftConfig.isItemEnabled(tag)) {
                        Item item = new ItemBoreHeadDiamond();
                        RailcraftRegistry.register(item);
                        CraftingPlugin.addRecipe(new ItemStack(item),
                                "III",
                                "IDI",
                                "III",
                                'I', "ingotSteel",
                                'D', "blockDiamond");
                    }

                    tag = "tool.bore.head.steel";
                    if (RailcraftConfig.isItemEnabled(tag)) {
                        Item item = new ItemBoreHeadSteel();
                        RailcraftRegistry.register(item);
                        CraftingPlugin.addRecipe(new ItemStack(item),
                                "III",
                                "IDI",
                                "III",
                                'I', "ingotSteel",
                                'D', "blockSteel");
                    }

                    tag = "tool.bore.head.iron";
                    if (RailcraftConfig.isItemEnabled(tag)) {
                        Item item = new ItemBoreHeadIron();
                        RailcraftRegistry.register(item);
                        CraftingPlugin.addRecipe(new ItemStack(item),
                                "III",
                                "IDI",
                                "III",
                                'I', "ingotSteel",
                                'D', "blockIron");
                    }
                }

                // Define Track Relayer Cart
                cart = EnumCart.TRACK_RELAYER;
                if (cart.setup())
                    CraftingPlugin.addRecipe(cart.getCartItem(),
                            "YLY",
                            "RSR",
                            "DMD",
                            'L', new ItemStack(Blocks.redstone_lamp),
                            'Y', "dyeYellow",
                            'R', new ItemStack(Items.blaze_rod),
                            'D', new ItemStack(Items.diamond_pickaxe),
                            'S', "blockSteel",
                            'M', new ItemStack(Items.minecart));

                // Define Undercutter Cart
                cart = EnumCart.UNDERCUTTER;
                if (cart.setup())
                    CraftingPlugin.addRecipe(cart.getCartItem(),
                            "YLY",
                            "RSR",
                            "DMD",
                            'L', new ItemStack(Blocks.redstone_lamp),
                            'Y', "dyeYellow",
                            'R', new ItemStack(Blocks.piston),
                            'D', new ItemStack(Items.diamond_shovel),
                            'S', "blockSteel",
                            'M', new ItemStack(Items.minecart));

                cart = EnumCart.TRACK_LAYER;
                if (cart.setup())
                    CraftingPlugin.addRecipe(cart.getCartItem(),
                            "YLY",
                            "ESE",
                            "DMD",
                            'Y', "dyeYellow",
                            'L', new ItemStack(Blocks.redstone_lamp),
                            'E', new ItemStack(Blocks.anvil),
                            'S', "blockSteel",
                            'D', new ItemStack(Blocks.dispenser),
                            'M', new ItemStack(Items.minecart));

                cart = EnumCart.TRACK_REMOVER;
                if (cart.setup())
                    CraftingPlugin.addRecipe(cart.getCartItem(),
                            "YLY",
                            "PSP",
                            "CMC",
                            'Y', "dyeYellow",
                            'L', new ItemStack(Blocks.redstone_lamp),
                            'P', new ItemStack(Blocks.sticky_piston),
                            'S', "blockSteel",
                            'C', IToolCrowbar.ORE_TAG,
                            'M', new ItemStack(Items.minecart));
            }
        });
    }
}
