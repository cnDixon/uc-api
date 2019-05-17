package com.kdg.cores.pubCores;

import com.kdg.cores.entity.ArgOptions;
import com.kdg.cores.tools.TimeTools;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class ArgsParser {

    private static Logger logger = Logger.getLogger(ArgsParser.class.getSimpleName());

    public static ArgOptions argsParser(String[] args) {

        Options options = new Options();
        options.addOption("d", true, "date");
        options.addOption("k", true, "key");
        options.addOption("a", true, "accounts");
        options.addOption("g", true, "agents");
        options.addOption("t", true, "redis keys");

        try {
            DefaultParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            String date = cmd.getOptionValue("d", TimeTools.getDateStr(-1));
            String accountKey = cmd.getOptionValue("k");
            String[] accounts = cmd.getOptionValues("a");
            String[] agents = cmd.getOptionValues("g");
            String[] redisKeys = cmd.getOptionValues("t");

            if (TimeTools.dateStrParse(date) == 0) {
                logger.error("input date str format error, check pls.");
                System.exit(1);
            }
            if (null == accountKey) accountKey = "uc_accounts_v2_" + date;

            if (null == accounts) accounts = new String[]{};

            if (null == agents) agents = new String[]{};

            if (null == redisKeys) redisKeys = new String[]{};

            return new ArgOptions(date, accountKey, Arrays.asList(redisKeys), Arrays.asList(accounts), Arrays.asList(agents));
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("args format error, check pls.", e);
            System.exit(1);
            return null;
        }
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(argsParser(args));
    }
}
