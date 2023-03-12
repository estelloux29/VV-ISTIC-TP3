# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer
R1. 
Pour tester nous avons déterminé les caractéristiques et les blocs suivants:
- isValidDAte: 
1. valeur de l'année:     <0 ,     0 ,    année bissextile valide ,      année "normale" valide
1. valeur du mois:        <0  ,    0  ,   31 jours {1,3,5,7,8,10,12},    30 jours {4,6,9,11} ,     2 ,      >12
1. valeur du jour:        <0,      0 ,    >=1 et <=max(mois, année),      >max(mois, année) 

Cela est aussi valable pour les méthodes  nextDate, previousDate et compareTo. 

- Pour isLeapYear, seuls l'année est prise en compte et on a 4 blocs. 
1. valeur de l'année:   <0 ,     0   ,   année bissextile valide  ,     année "normale" valide



R2. 
Tout d'abord nous avons choisi des inputs dates 'happyPath', pour vérifier en premier lieu la validité des tests les plus évidents.
La couverture initiale avec un minimum de 'base choice' était de 100% des méthodes mais seulement 64% des lignes de code.
Nous avons créé de nouveaux tests qui ont révélé des erreurs dans nos méthodes, notamment il a fallu modifier isValidDate. Ensuite il a fallu améliorer la méthode NextDate qui était erronée au niveau du passage du 29 février 2020 (mois de février d'une année bissextile). DE même pour le 31 décembre il y avait un souci au niveau du changement d'année, qui n'avait pas été révélé avant de tester ces blocks particuliers.
L'élaboration de nouveaux cas de tests nous a permis d'améliorer le code sur 2 méthodes.

R3. 
Une méthode comporte un prédicat qui utilise plus de 2 opérateurs booléens: isLeapYear()
Elle doit tenir compte de toute combinaison de valeurs de vérité de toutes les clauses et garantir toutes les valeurs possibles pour le prédicat.
En réalisant l'analyse de chaque expression et des combinaisons possibles, il est ressorti qu'il faut tester absolument 3 cas:
a) une année multiple de 4, non multiple de 100 , non multiple de 400.(exemple: 1992)
b) une année multiple de 4, multiple de 100, multiple de 400.(exemple: 2000) 
c) une année non  multiple de 4, non multiple de 100 non multiple de 400.(exemple: 2021) 
Les cas de test de a) (isLeapYearLogicCoverage) et b) (isLeapYearLogicCoverage2) ont été ajoutés. Par contre le cas c était déjà testé initialement donc pas besoin d'ajouter un test ici.


6. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.
DAns un premier temps les résultats ont été médiocres: 
couverture des lignes = 77%
mutants générés = 59, seulement un peu plus de la moitié killed = 61%

![image](https://user-images.githubusercontent.com/106377460/224506893-62cf9f97-2a20-4d4c-88bf-51687ff7c746.png)

En reprenant le code et en ajoutant des méthodes cela a permis d'augmenter significativement la couverture des lignes(91%). 90 mutations ont été générées et 73 Killed(81%). 

![image](https://user-images.githubusercontent.com/106377460/224508903-22a06c7a-cd0a-46fc-a9ea-ece46313abba.png)

Cela permet de constater l'efficacité de l'outil PIT et l'aide qu'il peut apporter dans l'amélioration de nos pratiques de développeur.
