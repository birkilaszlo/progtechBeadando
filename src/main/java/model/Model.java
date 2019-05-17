package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Birki László
 */

public class Model {

    public Model() {
    }

    /**
     * Véletlen szám generálása
     * @param min véletlenszám generálás intervallum alsó határa
     * @param max véletlenszám generálás intervallum felső határa
     * @param db hány darab véletlen szám készüljön
     * @return különböző véletlenszámokat tartalamzó lista
     */
    public List<Integer> generateRandomNumbers(int min, int max, int db){
        Set<Integer> rn = new HashSet<Integer>();
        int number = 0;
        while(rn.size() != 4){
            number = (int) ((Math.random() * max) + min);
            rn.add(number);
        }
        List<Integer> randomNumber = new ArrayList<Integer>(rn);
        return randomNumber;
    }

    /**
     * véletlen szám generálása min és max között
     * @return generált véletlenszám
     */
    public int generateCorrentAnswerPlace(int min, int max){
        int correct = 0;
        correct = (int)((Math.random() * max) + min);
        return correct;
    }

    /**
     * Új szó vizsgálata hogy megfelel-e bizonyos követelményeknek,
     * ahhoz hogy adatbázisba rögzíthető legyen
     * @param newWord vizsgálandó szó
     * @return true ha megfele, false, ha nem megfelelő a szó
     */
    public boolean checkNewWordsString(String newWord){
        if(newWord.length() == 0)
            return false;
        if(newWord.length() > 60)
            return false;

        return true;
    }

    /**
     * Új szó felületen beírt szavak ellenőrzése hogy szerepel e már az adatbázis szavak táblájában
     * Az ellenőrzés nem kis és nagybetű érzékeny
     * @param type annak eldöntése hogy az angol vagy a magyar szavak között történjen a keresés
     * @param word keresendő szó
     * @param lista A lista amiben a keresés történik
     * @return true, ha a szó szerepel az adatbázisban, false ha a szó nem szerepel az adatbázisban
     */
    public boolean searchWordInList(String type, String word, List<szavak> lista){
        for (int i = 0; i < lista.size(); i++) {
            if(type.equals("angol")) {
                if (lista.get(i).getAngol().toUpperCase().equals(word.toUpperCase()))
                    return true;
            }
            if(type.equals("magyar")) {
                if (lista.get(i).getMagyar().toUpperCase().equals(word.toUpperCase()))
                    return true;
            }
        }
        return false;
    }



}
