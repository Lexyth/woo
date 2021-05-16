package uk.ac.sheffield.assignment2021;

import uk.ac.sheffield.assignment2021.codeprovided.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;


public class WineSampleCellar extends AbstractWineSampleCellar {
    /**
     * Constructor - reads wine sample datasets and list of queries from text file,
     * and initialises the wineSampleRacks Map
     *
     * @param redWineFilename
     * @param whiteWineFilename
     */
    public WineSampleCellar(String redWineFilename, String whiteWineFilename) {
        super(redWineFilename, whiteWineFilename);
    }

    @Override
    public WinePropertyMap parseWineFileLine(String line) throws IllegalArgumentException {
        WinePropertyMap map = new WinePropertyMap();
        String[] properties = line.split(";");
        map.put(WineProperty.FixedAcidity, Double.parseDouble(properties[0]));
        map.put(WineProperty.VolatileAcidity, Double.parseDouble(properties[1]));
        map.put(WineProperty.CitricAcid, Double.parseDouble(properties[2]));
        map.put(WineProperty.ResidualSugar, Double.parseDouble(properties[3]));
        map.put(WineProperty.Chlorides, Double.parseDouble(properties[4]));
        map.put(WineProperty.FreeSulfurDioxide, Double.parseDouble(properties[5]));
        map.put(WineProperty.TotalSulfurDioxide, Double.parseDouble(properties[6]));
        map.put(WineProperty.Density, Double.parseDouble(properties[7]));
        map.put(WineProperty.PH, Double.parseDouble(properties[8]));
        map.put(WineProperty.Sulphates, Double.parseDouble(properties[9]));
        map.put(WineProperty.Alcohol, Double.parseDouble(properties[10]));
        map.put(WineProperty.Quality, Double.parseDouble(properties[11]));
        
        return map;
        
    }

    @Override
    public void updateCellar() {
        // TODO delete next line and implement
        List<WineSample> combined = new ArrayList();
        combined.addAll(wineSampleRacks.get(WineType.RED));
        combined.addAll(wineSampleRacks.get(WineType.WHITE));
        
        wineSampleRacks.put(WineType.ALL, combined);
        
        
    }

    @Override
    public double getMinimumValue(WineProperty wineProperty, List<WineSample> wineList)
    throws NoSuchElementException {
    double min = Integer.MAX_VALUE;

    for(WineSample wineSample : wineList){
        double num = wineSample.getProperty(wineProperty);
        if (num < min) min = num;
    }
    return(min);
}

    @Override
    public double getMaximumValue(WineProperty wineProperty, List<WineSample> wineList)
            throws NoSuchElementException {
                double max = Integer.MIN_VALUE;

                for(WineSample wineSample : wineList){
                    double num = wineSample.getProperty(wineProperty);
                    if (num > max) max = num;
                }
                return(max);
    }

    @Override
    public double getMeanAverageValue(WineProperty wineProperty, List<WineSample> wineList)
            throws NoSuchElementException {
                double sum = 0;
                for (WineSample wineSample: wineList){
                    double num = wineSample.getProperty(wineProperty);
                    sum += num;                   
                }
                double average = sum/wineList.size();
                return(average);
                
            }
        
    

    @Override
    public List<WineSample> getFirstFiveWines(WineType type) {

        return new ArrayList<>();
    }
}
