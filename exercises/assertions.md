# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
R1: Cela échoue car on multiplie un int et un float. L'assertion devrait être assertEquals qui prend cela en compte. 

R2. La méthode assertEquals vérifie si deux objets ont la même valeur, alors que la méthode assertSame vérifie s'ils sont identiques en termes de référence mémoire.
```java
 List<String> list1 = Arrays.asList("un", "deux", "trois");
 List<String> list2 = new ArrayList<>(list1);
// assertEquals : Vérifie si les valeurs sont égales
assertEquals(list1, list2);  // succès car les deux listes contiennent les mêmes valeurs
// assertSame : Vérifie si les références sont les mêmes
assertSame(list1, list2); // échec car les deux listes sont stockées dans des objets différents
```

R3. Si l'on souhaite marquer une fonctionnalité qui n'a pas encore été implémentée, on peut utiliser fail comme substitut temporaire  en signalant qu'elle n'a pas encore été implémentée. Par exemple :  

```java
public void test1() {
// TODO
fail("fonctionnalité à implémenter");
}
```

R4. Lors de la lecture du rapport de test, il est plus facile de catégoriser les tests en fonction de ce qu’ils détectent. Ainsi l’ajout de l’annotation d’assertThrows apporte plus de lisibilité car elle indique spécifiquement qu'une exception doit être levée.
!!!! à compléter !!!!
