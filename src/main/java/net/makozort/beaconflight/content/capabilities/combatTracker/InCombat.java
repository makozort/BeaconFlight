package net.makozort.beaconflight.content.capabilities.combatTracker;

import net.minecraft.nbt.CompoundTag;

public class InCombat {

    public static final int MAX_TIMER = 200;

    private int combatTimer;

    private boolean inCombat;


    public boolean checkCombat(){
        return this.combatTimer > 0;
    }
    public int getCombat(){
        return this.combatTimer;
    }
    public void setCombatTimer() {
        this.combatTimer = MAX_TIMER;
    }

    public void setInCombat(boolean b) {this.inCombat = b;}

    public boolean getInCombat() {return this.inCombat;}

    public void minCombat() {
        this.combatTimer--;
    }
    public void copyFrom(InCombat source) {
        this.combatTimer = source.combatTimer;
    }
    public void saveNBTData(CompoundTag tag) {
        tag.putInt("combatTimer",combatTimer);
    }
    public void loadNBTData(CompoundTag tag) {
        combatTimer = tag.getInt("combatTimer");
    }
}
