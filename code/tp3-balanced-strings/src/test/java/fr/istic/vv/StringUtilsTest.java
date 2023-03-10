package fr.istic.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    private String chEqOnlyVocab="{}[]()";
    private String ch1Paire="{}";
    private String chEqImbrique="{[][]}({}{})";
    private String chVide="";
    private String chTooMuchOpen="{{[[[[[]]]]}}";
    private String chTooMuchClosed="{{(((()))))}}";
    private String chEqLong = "{[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]}";
    private String chDqOtherVoc="{[][a]}({})";


    @Test
    @DisplayName("Une chaîne avec uniquement des éléments du vocabulaire sans imbrication qui répond au spécification")
    void chaineEquilibreeAvecUniquementVoc(){
        assertTrue(StringUtils.isBalanced(chEqOnlyVocab), "une chaîne avec uniquement des éléments du vocabulaire sans imbrication est équilibrée  ");
    }

    @Test
    @DisplayName("Une chaîne avec une seule paire d'accolade")
    void chaineUnePaire(){
        assertTrue(StringUtils.isBalanced(ch1Paire), "une chaîne avec une paire est équilibrée");
    }

    @Test
    @DisplayName("Une chaîne avec uniquement des éléments du vocabulaire et plusieurs imbrications répond aux spécifications")
    void chaineAvecImbriquation(){
        assertTrue(StringUtils.isBalanced(chEqImbrique), "une chaîne avec plusieurs imbrications respectent specs");
    }

    @Test
    @DisplayName("Une chaîne vide répond aux spécifications")
    void chaineVide(){
       assertTrue(StringUtils.isBalanced(chVide), "une chaîne vide est équilibrée");
    }

    @Test
    @DisplayName("Une chaîne avec un élement ouvrant en trop ne répond pas aux specs")
    void chaineTropOpenElement(){
        assertFalse(StringUtils.isBalanced(chTooMuchOpen), "Une chaîne avec un élement ouvrant en trop est déséquilibrée");
    }

    @Test
    @DisplayName("Une chaîne avec un élement fermant en trop est déséquilibrée")
    void chaineTropClosedElement(){
        assertFalse(StringUtils.isBalanced(chTooMuchClosed), "Une chaîne avec un élement fermant en trop est déséquilibrée");
    }


    @Test
    @DisplayName("Une chaîne longue répond aux spécifications")
    void chaineAvecLongueChaine(){
        assertTrue(StringUtils.isBalanced(chEqLong), "Une longue chaine avec un nombre égal d'accolades ouvrantes et fermantes est équilibrée");
    }

    @Test
    @DisplayName("Une chaîne avec un autre élément du vocabulaire")
    void chaineAvecVocAutre(){
        assertFalse(StringUtils.isBalanced(chDqOtherVoc), "Une chaîne avec un autre élément du vocabulaire est déséquilibrée");
    }


}