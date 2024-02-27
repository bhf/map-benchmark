package com.bhf.heuristics;

import com.bhf.jmhresults.JMHResult;

public class RatioHeuristic implements DerivedHeuristicCalculator {

    final DerivedHeuristicCalculator numerator;
    final DerivedHeuristicCalculator denominator;
    final String name;
    double heuristicVal;

    public RatioHeuristic(DerivedHeuristicCalculator numerator, DerivedHeuristicCalculator denominator, String heuristicName) {
        super();
        this.numerator = numerator;
        this.denominator = denominator;
        this.name=heuristicName;
    }

    @Override
    public void calculateHeuristic(JMHResult res) {
        if (null != res) {
            numerator.calculateHeuristic(res);
            denominator.calculateHeuristic(res);

            double numeratorVal = numerator.getHeuristicValue();
            double denominatorVal = denominator.getHeuristicValue();

            if (numeratorVal > 0 && denominatorVal > 0) {
                heuristicVal = numeratorVal / denominatorVal;
            }
        }
    }

    @Override
    public double getHeuristicValue() {
        return heuristicVal;
    }

    public DerivedHeuristicCalculator getNumerator() {
        return numerator;
    }

    public DerivedHeuristicCalculator getDenominator() {
        return denominator;
    }

    @Override
    public String getHeuristicName()
    {
        return name;
    }
}
