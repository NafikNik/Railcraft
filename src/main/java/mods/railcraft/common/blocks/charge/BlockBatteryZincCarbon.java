/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2018
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.blocks.charge;

import mods.railcraft.api.charge.IBatteryBlock;
import mods.railcraft.common.items.ItemCharge;
import mods.railcraft.common.items.RailcraftItems;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;

/**
 * Created by CovertJaguar on 11/8/2018 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class BlockBatteryZincCarbon extends BlockBatteryDisposable {
    private static final ChargeSpec CHARGE_SPEC = new ChargeSpec(ConnectType.BLOCK, 0.01,
            new IBatteryBlock.Spec(IBatteryBlock.State.DISPOSABLE, 75_000, 8.0, 0.6));

    @Override
    public void defineRecipes() {
        CraftingPlugin.addRecipe(getStack(),
                "TWT",
                "ASC",
                "ABC",
                'T', RailcraftItems.CHARGE, ItemCharge.EnumCharge.TERMINAL,
                'A', RailcraftItems.CHARGE, ItemCharge.EnumCharge.ELECTRODE_ZINC,
                'C', RailcraftItems.CHARGE, ItemCharge.EnumCharge.ELECTRODE_CARBON,
                'W', RailcraftItems.CHARGE, ItemCharge.EnumCharge.SPOOL_MEDIUM,
                'S', "dustSaltpeter",
                'B', Items.WATER_BUCKET);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 1;
    }

    @Override
    protected ChargeSpec getChargeSpec(IBlockState state) {
        return CHARGE_SPEC;
    }
}
