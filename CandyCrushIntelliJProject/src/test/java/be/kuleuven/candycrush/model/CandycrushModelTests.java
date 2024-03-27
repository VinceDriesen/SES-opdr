package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.controller.LoginController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class CandycrushModelTests {

//    @Test
//    public void gegevenNaam_wanneerDatGoedIngevuld_naamUitkomst(){
//        CandycrushModel model = new CandycrushModel("Vince");
//        String result = model.getSpeler();
//        assert (result.equals("Vince"));
//    }
//
//    @Test
//    public void gegevenGrid_wanneerGetIndexFromRowColum_indexUitkomst() {
//        CandycrushModel model = new CandycrushModel("Vince");
//        var result = model.getIndexFromRowColumn(2,3);
//        assertThat(result).isEqualTo(23);
//    }
//
//    @Test
//    public void gegevenGrid_wanneerGetScore_scoreUitkomst() {
//        CandycrushModel model = new CandycrushModel("Vince");
//        var result = model.getScore();
//        assertThat(result).isEqualTo(0);
//    }
//
//    @Test
//    public void gegevenGrid_wannerGetWidth_widthUitkomst() {
//        CandycrushModel model = new CandycrushModel("Vince");
//        var result = model.getWidth();
//        assertThat(result).isEqualTo(10);
//    }
//
//    @Test
//    public void gegevenGrid_wannerGetWidth_heightUitkomst() {
//        CandycrushModel model = new CandycrushModel("Vince");
//        var result = model.getHeight();
//        assertThat(result).isEqualTo(10);
//    }
//
//    @Test
//    public void gegevenGrid_wannerGetSpeelbord_arrayListSizeUitkomst() {
//        CandycrushModel model = new CandycrushModel("Vince");
//        var result = model.getSpeelbord().size();
//        assertThat(result).isEqualTo(10*10);
//    }
//
//    @Test
//    public void generateGrid_wannerGetSpeelbord_arrayListSizeUitkomst() {
//        CandycrushModel model = new CandycrushModel("Vince");
//
//        ArrayList<Integer> array = new ArrayList<>();
//        for(int i = 0; i < model.getHeight()*model.getWidth(); i++) {
//            array.add(0);
//        }
//        model.setGrid(array);
//        for(int i = 0; i < model.getHeight()*model.getWidth(); i++) {
//            var result = model.getSpeelbord().get(i);
//            assertThat(result).isEqualTo(0);
//        }
//    }
//
//    @Test
//    public void generateGrid_wannerChangeNeigbours_allNeighboursSameUitkomst() {
//        CandycrushModel model = new CandycrushModel("Vince");
//
//        ArrayList<Integer> array = new ArrayList<>();
//        for(int i = 0; i < model.getHeight()*model.getWidth(); i++) {
//            array.add(0);
//        }
//        model.setGrid(array);
//        model.changeNeighbours(25);
//        var result = model.getScore();
//
//        assertThat(result).isEqualTo(9);
//    }
//
//    @Test
//    public void generateNewGrid_wanneerResetAll_allNumbersRandom() {
//        CandycrushModel model = new CandycrushModel("Vince");
//
//        ArrayList<Integer> array = new ArrayList<>();
//        for(int i = 0; i < model.getHeight()*model.getWidth(); i++) {
//            array.add(0);
//        }
//        model.setGrid(array);
//
//        model.resetAll();
//        var result = model.getSpeelbord();
//        assertThat(result).isNotEqualTo(array);
//    }
//
//    @Test
//    public void generateNewField_wanneerCandyWithIndexSelected_moetKleinerZijnDan5enGroterDan0() {
//        CandycrushModel model = new CandycrushModel("Vince");
//        model.candyWithIndexSelected(5);
//        for(int i = 0; i < model.getHeight()*model.getWidth(); i++) {
//            var result = model.getSpeelbord().get(i);
//            assertThat(result).isLessThan(6);
//            assertThat(result).isGreaterThan(0);
//        }
//    }


}
