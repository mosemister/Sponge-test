package com.github.lukamon24.testsponge.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;

public class Fly implements CommandExecutor {

    @Override
    public CommandResult execute(CommandContext context) throws CommandException {
        if(context.subject() instanceof Player) {
            Player player = (Player) context;
            if(context.hasPermission("testsponge.fly")) {
                if(player.get(Keys.GAME_MODE).equals(GameModes.SURVIVAL)) {
                    player.offer(Keys.CAN_FLY, true);
                    player.sendMessage(Component.text("Flight enabled!").color(NamedTextColor.AQUA));
                }
                else {
                    player.sendMessage(Component.text("Please check if you are in the right gamemode!").color(NamedTextColor.RED));
                }
            }
            else {
                player.sendMessage(Component.text("No Permission!").color(NamedTextColor.DARK_RED));
            }
        }
        else {
            Sponge.server().sendMessage(Component.text("You need to be a player to do this!").color(NamedTextColor.DARK_RED));
        }
        return CommandResult.success();
    }
}
