package com.ndy.island.party.invite;

import com.ndy.AldarUninhabitedPlugin;
import com.ndy.island.generator.IslandInviteGenerator;
import com.ndy.island.generator.executer.Generator;
import com.ndy.island.party.invite.storage.IslandInviteSchedulerStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IslandPartyInviteScheduler implements Runnable {

    private IslandPartyInvite invite;
    private int taskID = -1, time = 15;

    public IslandPartyInviteScheduler(IslandPartyInvite invite) {
        this.invite = invite;
    }

    public void cancelTask() {
        Bukkit.getScheduler().cancelTask(taskID);
        IslandInviteSchedulerStorage.remove(this);
    }

    @Override
    public void run() {
        time--;

        if(invite.isAccept()) {
            cancelTask();
            runGenerator();
            return;
        }
        if(time == 0) {
            cancelTask();
        }
    }

    public void startTask() { taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(AldarUninhabitedPlugin.instance, this, 0L, 20L); }
    public IslandPartyInvite getInvite() { return invite; }

    public boolean equals(Player receiver) {
        return invite.getReceiver().equals(receiver);
    }

    private void runGenerator() {
        Generator generator = new Generator().addGenerateAble(new IslandInviteGenerator(invite));
        generator.dispose();
    }
}
