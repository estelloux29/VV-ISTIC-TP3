# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written so far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

R1 : Nous avons 4 caractéristiques de la méthode à partitionner : 
- longueur : 2 blocs (longueur=0-> renvoie true; longueur>0: doit être testé avec d’autres blocs)
- vocabulaire : 2 blocs ( vocabulaire1 ={[(}]) => doit être testé avec d’autres blocs, vocabulaireAutre	∉vocabulaire1 => renvoie faux)
- isBalanced (2 blocs : true ou false)
- paire ([0-1];[1;10][1;100]...)

R2. La couverture du code était initialement à 92%, un retour à false n’était alors pas exécuté. La suppression de cette méthode n’a pas modifié le résultat des tests, il est alors apparu que ce retour était inutile. En la supprimant, la couverture est montée à 100%.

R4 : ajout de cas de test avec un seul élément ouvrant et un seul élément fermant.

R5 : refonte du code pour ne plus utiliser la récursivité qui ne présentait pas d’intérêt ici car nous pouvions utiliser une boucle for et une pile. 
Résultats du PIT : 26 mutants tués et 1 survivant 
Ajout d’un cas de test avec un mauvais appariement => 27 mutants tués.
