package samsara.slf4j;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;


public class SamsaraLogger extends MarkerIgnoringBase
{
    private static final int LOG_LEVEL_TRACE = LocationAwareLogger.TRACE_INT;
    private static final int LOG_LEVEL_DEBUG = LocationAwareLogger.DEBUG_INT;
    private static final int LOG_LEVEL_INFO = LocationAwareLogger.INFO_INT;
    private static final int LOG_LEVEL_WARN = LocationAwareLogger.WARN_INT;
    private static final int LOG_LEVEL_ERROR = LocationAwareLogger.ERROR_INT;

    private currentLogLevel = LOG_LEVEL_INFO;

    public SamsaraLoggger(String name)
    {
        this.name = name;

    }

    protected boolean isLevelEnabled(int logLevel)
    {
        return (logLevel >= currentLogLevel);
    }

    private void printWarning()
    {
        //TODO
        //Print warning if SAMARA_API_URL Environment variable is not given
    }

    private void log(int level, String msg, Throwable t)
    {
        if (isLevelEnabled(level))
            {
                //TODO
                //pass on to clojure 
            }
    }


    private void formatAndLog(int level, String format, Object arg1, Object arg2)
    {
        if(isLevelEnabled(level))
        {
            FormattingTuple tp = MessageFormatter.format(format, arg1, arg2);
            log(level, tp.getMessage(), tp.getThrowable());
        }
    }

    private void formatAndLog(int level, String format, Object... arguments)
    {
        if(isLevelEnabled(level))
        {
            FormattingTuple tp = MessageFormatter.arrayFormat(format, arguments);
            log(level, tp.getMessage(), tp.getThrowable());
        }
    }


    public boolean isTraceEnabled()
    {
        return isLevelEnabled(LOG_LEVEL_TRACE);
    }

    public void trace(String msg)
    {
        log(LOG_LEVEL_TRACE, msg, null);
    }

    public void trace(String msg, Throwable t)
    {
        log(LOG_LEVEL_TRACE, msg, t);
    }

    public void trace(String format, Object param1)
    {
        formatAndLog(LOG_LEVEL_TRACE, format, param1, null);
    }


    public void trace(String format, Object param1, Object param2)
    {
        formatAndLog(LOG_LEVEL_TRACE, format, param1, param2)
    }

    public void trace(String format, Object... paramArray)
    {
        formatAndLog(LOG_LEVEL_TRACE, format, paramArray); 
    }



    public boolean isDebugEnabled()
    {
        return isLevelEnabled(LOG_LEVEL_DEBUG);
    }

    public void debug(String msg)
    {
        log(LOG_LEVEL_DEBUG, msg, null);
    }

    public void debug(String msg, Throwable t)
    {
        log(LOG_LEVEL_DEBUG, msg, t);
    }

    public void debug(String format, Object param1)
    {
        formatAndLog(LOG_LEVEL_DEBUG, format, param1, null);
    }


    public void debug(String format, Object param1, Object param2)
    {
        formatAndLog(LOG_LEVEL_DEBUG, format, param1, param2)
    }

    public void debug(String format, Object... paramArray)
    {
        formatAndLog(LOG_LEVEL_DEBUG, format, paramArray); 
    }



    public boolean isInfoEnabled()
    {
        return isLevelEnabled(LOG_LEVEL_INFO);
    }

    public void info(String msg)
    {
        log(LOG_LEVEL_INFO, msg, null);
    }

    public void info(String msg, Throwable t)
    {
        log(LOG_LEVEL_INFO, msg, t);
    }

    public void info(String format, Object param1)
    {
        formatAndLog(LOG_LEVEL_INFO, format, param1, null);
    }


    public void info(String format, Object param1, Object param2)
    {
        formatAndLog(LOG_LEVEL_INFO, format, param1, param2)
    }

    public void info(String format, Object... paramArray)
    {
        formatAndLog(LOG_LEVEL_INFO, format, paramArray); 
    }



    public boolean isWarnEnabled()
    {
        return isLevelEnabled(LOG_LEVEL_WARN);
    }

    public void warn(String msg)
    {
        log(LOG_LEVEL_WARN, msg, null);
    }

    public void warn(String msg, Throwable t)
    {
        log(LOG_LEVEL_WARN, msg, t);
    }

    public void warn(String format, Object param1)
    {
        formatAndLog(LOG_LEVEL_WARN, format, param1, null);
    }


    public void warn(String format, Object param1, Object param2)
    {
        formatAndLog(LOG_LEVEL_WARN, format, param1, param2)
    }

    public void warn(String format, Object... paramArray)
    {
        formatAndLog(LOG_LEVEL_WARN, format, paramArray); 
    }



    public boolean isErrorEnabled()
    {
        return isLevelEnabled(LOG_LEVEL_ERROR);
    }

    public void error(String msg)
    {
        log(LOG_LEVEL_ERROR, msg, null);
    }

    public void error(String msg, Throwable t)
    {
        log(LOG_LEVEL_ERROR, msg, t);
    }

    public void error(String format, Object param1)
    {
        formatAndLog(LOG_LEVEL_ERROR, format, param1, null);
    }


    public void error(String format, Object param1, Object param2)
    {
        formatAndLog(LOG_LEVEL_ERROR, format, param1, param2)
    }

    public void error(String format, Object... paramArray)
    {
        formatAndLog(LOG_LEVEL_ERROR, format, paramArray); 
    }
}
