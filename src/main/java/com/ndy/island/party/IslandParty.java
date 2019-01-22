package com.ndy.island.party;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IslandParty {

    private List<String> partyMemberList = new ArrayList<>();

    public IslandParty() {}

    public void joinPartyMember(Player player) {
        String uuidStr = player.getUniqueId().toString();
        if(!partyMemberList.contains(uuidStr)) {
            partyMemberList.add(uuidStr);

            sendMessagePartyMembers(player.getName() + " §a님이 파티에 참가하셨습니다.");
            /* Adding Data */
        }
    }

    public void quitPartyMember(Player player) {
        String uuidStr = player.getUniqueId().toString();
        if(partyMemberList.contains(uuidStr)) {
            partyMemberList.remove(uuidStr);

            sendMessagePartyMembers(player.getName() + " §c님이 파티에서 나가셨습니다.");
            /* Removing Data */
        }
    }

    public boolean contains(String uuid) {
        return this.partyMemberList.contains(uuid);
    }

    public void sendMessagePartyMembers(String message) {
        for(String uuid : partyMemberList) {
            Player player = Bukkit.getPlayer(UUID.fromString(uuid));

            if(player != null) {
                player.sendMessage(message);
            }
        }
    }

    public int getPartyMemberSize() { return partyMemberList.size(); }

}
