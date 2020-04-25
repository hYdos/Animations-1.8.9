/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.BlockPos
 */
package me.hydos.animations.config;

import com.google.common.collect.Lists;

import java.util.List;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class CommandAnimationsConfig
implements ICommand {
    private List<String> aliases = Lists.newArrayList();

    public CommandAnimationsConfig() {
        this.aliases.add("animationsconfig");
        this.aliases.add("animationconfig");
        this.aliases.add("animations");
        this.aliases.add("animation");
        this.aliases.add("oldanimations");
        this.aliases.add("oldanimation");
        this.aliases.add("anconfig");
    }

    public String getCommandName() {
        return this.aliases.get(0);
    }

    public String getCommandUsage(ICommandSender sender) {
        return this.aliases.get(0);
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Config.getInstance().openGui();
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public int compareTo(ICommand o) {
        return 0;
    }

    public List<String> getCommandAliases() {
        return this.aliases;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }
}

