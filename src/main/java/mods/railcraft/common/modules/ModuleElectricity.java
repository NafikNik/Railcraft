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
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.common.blocks.RailcraftBlocksOld;
import mods.railcraft.common.blocks.frame.BlockFrame;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.delta.EnumMachineDelta;
import mods.railcraft.common.blocks.machine.epsilon.EnumMachineEpsilon;
import mods.railcraft.common.blocks.tracks.EnumTrack;
import mods.railcraft.common.items.ItemPlate.EnumPlate;
import mods.railcraft.common.items.RailcraftItems;
import mods.railcraft.common.items.RailcraftPartItems;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.util.crafting.RotorRepairRecipe;
import net.minecraft.init.Items;

/**
 * @author CovertJaguar <http://www.railcraft.info/>
 */
@RailcraftModule("electricity")
public class ModuleElectricity extends RailcraftModulePayload {

    public ModuleElectricity() {
        setEnabledEventHandler(new ModuleEventHandler() {
            @Override
            public void construction() {
                add(RailcraftItems.electricMeter);
            }

            @Override
            public void preInit() {
                BlockFrame.registerBlock();

                EnumMachineAlpha alpha = EnumMachineAlpha.TURBINE;
                if (alpha.register()) {
                    CraftingPlugin.addRecipe(alpha.getItem(3),
                            "BPB",
                            "P P",
                            "BPB",
                            'P', RailcraftItems.plate.getRecipeObject(EnumPlate.STEEL),
                            'B', "blockSteel");

                    RailcraftPartItems.getTurbineRotor();

                    CraftingPlugin.addRecipe(new RotorRepairRecipe());

//                ItemStack rotor = RailcraftPartItems.getTurbineRotor();
//                rotor.setItemDamage(25000);
//                CraftingPlugin.addShapelessRecipe(rotor, RailcraftPartItems.getTurbineRotor());
                }

                EnumMachineEpsilon epsilon = EnumMachineEpsilon.ELECTRIC_FEEDER;
                if (epsilon.register())
                    CraftingPlugin.addRecipe(epsilon.getItem(),
                            "PCP",
                            "CCC",
                            "PCP",
                            'P', RailcraftItems.plate.getRecipeObject(EnumPlate.TIN),
                            'C', "ingotCopper");

                epsilon = EnumMachineEpsilon.ELECTRIC_FEEDER_ADMIN;
                epsilon.register();

                epsilon = EnumMachineEpsilon.FORCE_TRACK_EMITTER;
                if (epsilon.register()) {
                    if (RailcraftBlocksOld.registerBlockTrack())
                        EnumTrack.registerTrack(EnumTrack.FORCE);
                    CraftingPlugin.addRecipe(epsilon.getItem(),
                            "PCP",
                            "CDC",
                            "PCP",
                            'P', RailcraftItems.plate.getRecipeObject(EnumPlate.TIN),
                            'D', "blockDiamond",
                            'C', "ingotCopper");
                }

                epsilon = EnumMachineEpsilon.FLUX_TRANSFORMER;
                if (epsilon.register())
                    CraftingPlugin.addRecipe(epsilon.getItem(2),
                            "PGP",
                            "GRG",
                            "PGP",
                            'P', RailcraftItems.plate.getRecipeObject(EnumPlate.COPPER),
                            'G', "ingotGold",
                            'R', "blockRedstone");

                EnumMachineDelta delta = EnumMachineDelta.WIRE;
                if (delta.register()) {
                    RailcraftCraftingManager.rollingMachine.addRecipe(
                            delta.getItem(8),
                            "LPL",
                            "PCP",
                            "LPL",
                            'C', "blockCopper",
                            'P', Items.paper,
                            'L', "ingotLead");
                }

            }
        });
    }
}
