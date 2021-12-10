package com.igortullio.hering.core.domain;


import com.igortullio.hering.core.domain.enumeration.UserField;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class Precondition {

    private static final BiPredicate<String, String> startsWith = String::startsWith;
    private static final BiPredicate<String, String> endsWith = String::endsWith;
    private static final BiPredicate<String, String> equalsString = String::equals;
    private static final BiPredicate<Integer, Integer> greaterThan = (x, y) -> x > y;
    private static final BiPredicate<Integer, Integer> lessThan = (x, y) -> x < y;
    private static final BiPredicate<Integer, Integer> equalsInteger = Integer::equals;

    private static final List<BiPredicate> BI_PREDICATE_LIST = Arrays.asList(
            startsWith,
            endsWith,
            equalsString,
            greaterThan,
            lessThan,
            equalsInteger
    );

    private UserField userField;
    private String conditionString;
    private Integer conditionInteger;
    private Integer biPredicatePosition;
    private BiPredicate<?, ?> biPredicate;

    public Precondition(UserField userField, String conditionString, Integer conditionInteger, Integer biPredicatePosition) {
        this.userField = userField;
        this.conditionString = conditionString;
        this.conditionInteger = conditionInteger;
        this.biPredicatePosition = biPredicatePosition;
        this.biPredicate = BI_PREDICATE_LIST.get(biPredicatePosition);
    }

    public UserField getUserField() {
        return userField;
    }

    public void setUserField(UserField userField) {
        this.userField = userField;
    }

    public String getConditionString() {
        return conditionString;
    }

    public void setConditionString(String conditionString) {
        this.conditionString = conditionString;
    }

    public Integer getConditionInteger() {
        return conditionInteger;
    }

    public void setConditionInteger(Integer conditionInteger) {
        this.conditionInteger = conditionInteger;
    }

    public BiPredicate getBiPredicate() {
        return biPredicate;
    }

    public void setBiPredicate(BiPredicate biPredicate) {
        this.biPredicate = biPredicate;
    }

    public Integer getBiPredicatePosition() {
        return biPredicatePosition;
    }

    public void setBiPredicatePosition(Integer biPredicatePosition) {
        this.biPredicatePosition = biPredicatePosition;
    }

}
