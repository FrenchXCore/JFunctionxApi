package eu.frenchxcore.tools;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public final class XLogger {

    private final static XLogger INSTANCE;

    private ConfigurationBuilder<BuiltConfiguration> configurationBuilder = null;
    private Logger _logger = null;
    private final static Level LEVEL = Level.ALL;

    static {
        //if (DataStoreController.getEngineStatus() == DataStoreEngineStatus.ONLINE) {
            System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        //}
        System.setProperty("log4j2.skipJansi", "false");
        INSTANCE = new XLogger();
    }
    
    private XLogger() {
        this.configurationBuilder = ConfigurationBuilderFactory.newConfigurationBuilder();
        this.configurationBuilder.setStatusLevel(LEVEL);
        this.configurationBuilder.setConfigurationName("RollingBuilder");
        // create a layout builder
        LayoutComponentBuilder layoutBuilder = this.configurationBuilder.newLayout("PatternLayout").addAttribute("pattern", "%d{DEFAULT} %-5p %m{ansi} %throwable{full} %n").addAttribute("charset", "UTF-8");

        // create a console appender
        AppenderComponentBuilder appenderBuilder = this.configurationBuilder
                .newAppender("StdoutAppender", "CONSOLE")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(layoutBuilder);
        this.configurationBuilder.add(appenderBuilder);

        this.configurationBuilder.add(
                this.configurationBuilder.newAsyncLogger("General", LEVEL)
                        .add(this.configurationBuilder.newAppenderRef("StdoutAppender"))
                .addAttribute("additivity", false)
        );

        LoggerContext ctx = Configurator.initialize(this.configurationBuilder.build());
        ctx.updateLoggers();
        ctx.start();
        this._logger = LogManager.getLogger("General");
    }

    public static Logger getMainLogger() {
        return INSTANCE._logger;
    }

    public void logTitle(String title) {
        this.logTitle(Level.INFO, title, null);
    }

    public void logTitle(String title, Throwable t) {
        this.logTitle(Level.FATAL, title, t);
    }

    public void logTitle(Level level, String title, Throwable t) {
        int length = title.length();
        String header = "\r\n";
        header += "=======";
        for (int i = 0; i < length; i++) {
            header += "=";
        }
        header += "=======\r\n";
        String log = header;
        log += "=====> ";
        log += title;
        log += " <=====";
        log += header;
        this._logger.log(level, log, t);
    }

}
