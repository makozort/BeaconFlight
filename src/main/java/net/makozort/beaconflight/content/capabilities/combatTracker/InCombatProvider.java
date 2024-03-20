package net.makozort.beaconflight.content.capabilities.combatTracker;


import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class InCombatProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<InCombat> COMBAT_CAP = CapabilityManager.get(new CapabilityToken<InCombat>() { });

    private InCombat inCombat = null;
    public final LazyOptional<InCombat> optional = LazyOptional.of(this::createPlayerCombat);

    private InCombat createPlayerCombat() {
        if (this.inCombat == null) {
            this.inCombat = new InCombat();
        }
        return this.inCombat;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == COMBAT_CAP) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerCombat().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        createPlayerCombat().loadNBTData(tag);
    }
}
