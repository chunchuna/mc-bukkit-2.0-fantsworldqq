package com.chun._.Npc;

import com.chun._._;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.plugin.java.JavaPlugin;

public class EmptyTrait extends Trait {

    public EmptyTrait() {
        super("emptytrait");
    }

    // 以下是一些你可能需要覆盖的方法。

    @Override
    public void onAttach() {
       // _.instance.getServer().getLogger().info(npc.getName() + " has been attached with EmptyTrait!");
    }

    @Override
    public void onRemove() {
        //_.instance.getServer().getLogger().info(npc.getName() + " has been detached from EmptyTrait!");
    }

    @Override
    public void run() {
        // 这个方法会在每个服务器刻度上被调用。

    }

    // 你可以添加更多的方法，以便在 Trait 被添加到 NPC 或从 NPC 移除时执行特定的操作。
}
