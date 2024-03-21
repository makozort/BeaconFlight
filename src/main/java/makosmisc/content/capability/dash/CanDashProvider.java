package makosmisc.content.capability.dash;

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

public class CanDashProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<CanDash> DASH_CAP= CapabilityManager.get(new CapabilityToken<CanDash>() { });

    private CanDash dash = null;
    public final LazyOptional<CanDash > optional = LazyOptional.of(this::createPlayerDash);

    private CanDash createPlayerDash() {
        if (this.dash == null) {
            this.dash = new CanDash();
        }
        return this.dash;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == DASH_CAP) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerDash().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        createPlayerDash().loadNBTData(tag);
    }
}
