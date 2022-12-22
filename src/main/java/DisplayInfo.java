/**
 * @author Xavier Hill Roy
 * CS2212 - Intro to Software Engineering
 * @purpose Interface communicates with main system providing data from the trading system component
 */
import java.util.ArrayList;

public interface DisplayInfo {
    ArrayList<StrategyResult> getTradeLog();
}
