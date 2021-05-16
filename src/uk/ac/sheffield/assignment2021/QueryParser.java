package uk.ac.sheffield.assignment2021;

import uk.ac.sheffield.assignment2021.codeprovided.AbstractQueryParser;
import uk.ac.sheffield.assignment2021.codeprovided.Query;
import uk.ac.sheffield.assignment2021.codeprovided.SubQuery;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class QueryParser extends AbstractQueryParser {

    @Override
    public List < Query > readQueries(List < String > queryTokens) throws IllegalArgumentException {
        List < Query > queryList = new ArrayList < > ();
        WineType wineType = null;
        int i = 0;
        while (i < queryTokens.size()) {

            if (queryTokens.get(i).equals("select")) {
                if (queryTokens.get(i + 2).equals("or")) {
                    wineType = WineType.ALL;
                    i += 5;
                } else if (queryTokens.get(i + 1).equals("white")) {
                    wineType = WineType.WHITE;
                    i += 3;
                } else if (queryTokens.get(i + 1).equals("red")) {
                    wineType = WineType.RED;
                    i += 3;
                }

                List < SubQuery > subQueryList = new ArrayList < > ();
                while (i < queryTokens.size() && !queryTokens.get(i).equals("select")) {
                    if (queryTokens.get(i).equals("and")) {
                        i++;
                    }
                    WineProperty wineProperty = null;
                    try {
                        wineProperty = WineProperty.fromFileIdentifier(queryTokens.get(i - 1));
                    } catch (NoSuchElementException nsee) {
                        nsee.printStackTrace();
                    }
                    i++;
                    String operator = queryTokens.get(i);
                    i++;
                    Double value = 0.0;
                    try {
                        value = Double.parseDouble(queryTokens.get(i));
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }
                    subQueryList.add(new SubQuery(wineProperty, operator, value));
                    i++;
                }
                queryList.add(new Query(subQueryList, wineType));
            }
        }
        return queryList;
    }
}