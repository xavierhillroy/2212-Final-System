/**
 * @author Xavier Hill Roy
 * CS2212 - Intro to Software Engineering
 * @purpose This class implements the Factory design pattern and acts as the factory for the strategy
 */
public class StrategyFactory {
    /**
     *A factory that Determines what strategy object to create based on what strategy the user has selected
     * @param strategy
     * @return appropriate subclass strategy object based on what the user enters as their desired strategy
     */
    public Strategy create(String strategy){
        switch (strategy) {
            case "GammaSqueeze":
                return new StrategyGammaSqueeze();
            case "FactorInvesting":
                return new StrategyFactorInvesting();
            case "ValueInvesting":
                return new StrategyValueInvesting();
            case "MomentumInvesting":
                return new StrategyMomentumInvesting();
            default:
                return new StrategyMomentumInvesting();
        }
    }
}
