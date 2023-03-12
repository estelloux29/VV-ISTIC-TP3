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
- vocabulaire : 2 blocs ( alphabetSpec ={[(}]) => doit être testé avec d’autres blocs, vocabulaireAutre	∉ alphabetSpec => renvoie faux)
- isBalanced (2 blocs : true ou false)
- paire ([0-1];[1;10][1;100]...)

Dans un premier temps, nous avons déterminé les inputs "Happy Paths" : longueur de chaîne à 0(chaîne vide), une seule paire, des paires imbriquées ou non, une chaîne longue (>100 caractères).

Puis nous avons testé les limites du système (test de robustesse) : ajout d'un élement autre de l'alphabet correspondant au spec, ajout d'un élément ouvrant ou fermant en trop 

R2. La couverture du code était initialement à 92%, un retour à false n’était alors pas exécuté. Nous avons ajouté 2 cas de tests pour le cas où il n'y a q'un seul élément ouvrant ou un seul fermant. La couverture de code est alors montée à 100%.

R3 : pas de logic coverage pour cette question

R4 : Après exécution du PIT, nous avons fait une refonte du code pour ne plus utiliser la récursivité qui ne présentait pas d’intérêt ici car nous pouvions utiliser une boucle for et une pile. 
Résultats du PIT : 26 mutants tués et 1 survivant 
![image](https://user-images.githubusercontent.com/113097128/224544006-b9c41380-b6a1-4676-987c-74b392e2d084.png)


Ajout d’un cas de test dans le cas d'un mauvais appariement
![image](https://user-images.githubusercontent.com/113097128/224544066-c6049c9f-3d42-4d89-bd16-2d106c34eb46.png)

=> 27 mutants tués sur 27.
![image](https://user-images.githubusercontent.com/113097128/224544097-aa462a69-7318-4c3a-87d5-fc63bae21a41.png)


