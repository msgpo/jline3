/*
 * Copyright (c) 2002-2015, the original author or authors.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package org.jline.utils;

import java.io.File;

public class OSUtils {

    public static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().contains("win");

    public static final boolean IS_CYGWIN = IS_WINDOWS
            && System.getenv("PWD") != null
            && System.getenv("PWD").startsWith("/");

    public static String TTY_COMMAND;
    public static String STTY_COMMAND;
    public static String INFOCMP_COMMAND;

    static {
        String tty;
        String stty;
        String infocmp;
        if (OSUtils.IS_CYGWIN) {
            tty = "tty.exe";
            stty = "stty.exe";
            infocmp = "infocmp.exe";
            String path = System.getenv("PATH");
            if (path != null) {
                String[] paths = path.split(";");
                for (String p : paths) {
                    if (new File(p, "tty.exe").exists()) {
                        tty = new File(p, "tty.exe").getAbsolutePath();
                    }
                    if (new File(p, "stty.exe").exists()) {
                        stty = new File(p, "stty.exe").getAbsolutePath();
                    }
                    if (new File(p, "infocmp.exe").exists()) {
                        infocmp = new File(p, "infocmp.exe").getAbsolutePath();
                    }
                }
            }
        } else {
            tty = "tty";
            stty = "stty";
            infocmp = "infocmp";
        }
        TTY_COMMAND = tty;
        STTY_COMMAND = stty;
        INFOCMP_COMMAND = infocmp;
    }

}
