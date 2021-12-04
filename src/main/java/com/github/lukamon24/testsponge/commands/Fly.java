package com.github.lukamon24.testsponge.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;

import java.util.Optional;

public class Fly implements CommandExecutor {

    @Override
    public CommandResult execute(CommandContext context) {
        if (!(context.subject() instanceof Player player)) {
            return CommandResult.error(Component.text("Player only command").color(NamedTextColor.RED));
        }
        Optional<GameMode> opMode = player.get(Keys.GAME_MODE);
        if (opMode.isEmpty()) {
            return CommandResult.error(Component.text("Unknown gamemode").color(NamedTextColor.RED));
        }
        player.offer(Keys.CAN_FLY, true);
        player.sendMessage(Component.text("Flight enabled!").color(NamedTextColor.AQUA));
        return CommandResult.success();
    }
}
