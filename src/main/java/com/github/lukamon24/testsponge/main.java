package com.github.lukamon24.testsponge;

import com.google.inject.Inject;
import net.kyori.adventure.text.Component;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Server;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

import com.github.lukamon24.testsponge.commands.Fly;

@Plugin("testsponge")
public class Main {

    private final PluginContainer container;
    private final Logger logger;

    @Inject
    Main(final PluginContainer container, final Logger logger) {
        this.container = container;
        this.logger = logger;
    }

    @Listener
    public void onConstructPlugin(final ConstructPluginEvent event) {
        // Perform any one-time setup
        this.logger.info("Constructing testsponge");
    }

    @Listener
    public void onRegisterCommands(final RegisterCommandEvent<Command.Parameterized> event) {
        //Reg cmd
        event.register(this.container, Command.builder().executor(new Fly()).permission("testsponge.fly").shortDescription(Component.text("Just a fly command!")).build(), "fly");
    }

    @Listener
    public void onServerStarting(final StartingEngineEvent<Server> event) {
        // Any setup per-game instance. This can run multiple times when
        // using the integrated (singleplayer) server.
        logger.info("Enjoy!");
    }

    @Listener
    public void onServerStopping(final StoppingEngineEvent<Server> event) {
        logger.info("Goodbye and thank you!");
    }

}
