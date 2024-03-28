package core_algorithms;

import problems.Game;

public class Minimax<S, A> {
    private final Game<S, A> game;
    private final boolean pruning;

    public record Best<A> (int value, A action){}

    public Minimax(Game<S, A> game, boolean pruning) {
        this.game = game;
        this.pruning = pruning;
    }

    public A minimaxSearch(S state){
        if (!pruning){
            Best<A> max = maxValue(state);
            return max.action;
        }
        Best<A> max = maxPrunedValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return max.action;
    }

    private Best<A> maxPrunedValue(S state, int alpha, int beta) {

        int maxValue = Integer.MIN_VALUE;
        A maxAction = null;
        if(game.isTerminal(state)){
            maxValue = game.utility(state);
        } else {
            for(A action : game.actions(state)){
                S newState = game.execute(action, state);
                Best<A> min = minPrunedValue(newState, alpha, beta);
                if(min.value() > maxValue){
                    maxValue = min.value();
                    maxAction = action;
                    alpha = Math.max(alpha,maxValue);
                }
                if (maxValue >= beta){
                    return new Best<> (maxValue, maxAction);
                }
                game.undo(action, newState);
            }

        }
        return new Best<> (maxValue, maxAction);
    }

    private Best<A> minPrunedValue(S state, int alpha, int beta) {
        int minValue = Integer.MAX_VALUE;
        A minAction = null;
        if (game.isTerminal(state)){
            minValue = game.utility(state);
        }else {
            for (A action : game.actions(state)){
                S newState = game.execute(action, state);
                Best<A> max = maxValue(newState);
                if(max.value() < minValue){
                    minValue = max.value();
                    minAction = action;
                    beta = Math.min(beta, minValue);
                }
                if (minValue <= alpha){
                    return new Best<>(minValue, minAction);
                }
                game.undo(action, newState);
            }
        }
        return new Best<>(minValue, minAction);
    }

    public Best<A> maxValue(S state){
        int maxValue = Integer.MIN_VALUE;
        A maxAction = null;
        if(game.isTerminal(state)){
            maxValue = game.utility(state);
        } else {
            for(A action : game.actions(state)){
                S newState = game.execute(action, state);
                Best<A> min = minValue(newState);
                if(min.value() > maxValue){
                    maxValue = min.value();
                    maxAction = action;
                }
                game.undo(action, newState);
            }

        }
        return new Best<> (maxValue, maxAction);
    }

    public Best<A> minValue(S state){
        int minValue = Integer.MAX_VALUE;
        A minAction = null;
        if (game.isTerminal(state)){
            minValue = game.utility(state);
        }else {
            for (A action : game.actions(state)){
                S newState = game.execute(action, state);
                Best<A> max = maxValue(newState);
                if(max.value() < minValue){
                    minValue = max.value();
                    minAction = action;
                }
                game.undo(action, newState);
            }
        }
        return new Best<>(minValue, minAction);
    }
}
