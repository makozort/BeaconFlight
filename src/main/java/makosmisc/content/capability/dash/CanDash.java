package makosmisc.content.capability.dash;

import net.minecraft.nbt.CompoundTag;

public class CanDash {

    private boolean dash;

    public int waveDashTimer;

    private static final int MAX_WAVE_TIMER = 20;
    public boolean canDash(){
        return dash;
    }

    public boolean canWaveDash(){
        return this.waveDashTimer > 0;
    }

    public void setDash(boolean bool) {
        this.dash = bool;
    }

    public void setWaveDash() {
        this.waveDashTimer = MAX_WAVE_TIMER;
    }

    public void stopWaveDash() {
        this.waveDashTimer = 0;
    }

    public void minWaveDash() {
        this.waveDashTimer--;
    }

    public void copyFrom(CanDash source) {
        this.dash = source.dash;
    }
    public void saveNBTData(CompoundTag tag) {
        tag.putBoolean("dash",dash);
        tag.putInt("wavedash",this.waveDashTimer);
    }

    public void loadNBTData(CompoundTag tag) {
        dash = tag.getBoolean("dash");
        waveDashTimer =  tag.getInt("wavedash");
    }
}
