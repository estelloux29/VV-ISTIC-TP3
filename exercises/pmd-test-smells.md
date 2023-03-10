# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer
Nous avons étudié différents tests smells en cours: le piggybacking (avoir plusieurs oracles pour le même cas de test. Trop d'assertions dans le même cas de test ce empêchent la lisibilité), HappyPath sans tester aux limites, hidden dependency (test qui a besoin de données peuplées avant le run), useless assert, flackyTest (tests non déterministes dûs aux éxécutions parallèles).
Comme nous l'avons vu en classe, un test ne doit tester qu'une seule chose (responsabilité unique) et, pour ce faire, il ne doit comporter que quelques assertions. Le nombre maximum d'assertions n'est évidemment pas clairement établi mais c'est une mauvaise odeur si une douzaine d'assertions se trouvent dans le même cas de test.  Comme dans "testChainedClosure()" dans le commons.collections4.ClosureUtilsTest.java où 13 assertions sont faites dans le même test. Il est difficile de comprendre pourquoi le test échoue. Il gagnerait à être découpé en 2 ou 3 cas de test plus petits. Ici, nous coupons le cas de test en deux et le dupliquons :

 import org.junit.Test;
 import static org.junit.jupiter.api.Assertions.assertAll;
 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertNotNull;
 import static org.junit.jupiter.api.Assertions.assertSame;
 import static org.junit.jupiter.api.Assertions.assertThrows;
 import java.util.ArrayList;import java.util.Collection;
 import java.util.Collections;import java.util.HashMap;
 import java.util.Map;
 
public class testExample {    

@Test    
@SuppressWarnings("unchecked")    
public void testChainedClosure() {
MockClosure<Object> a = new MockClosure<>(); 
  MockClosure<Object> b = new MockClosure<>(); 
  ClosureUtils.chainedClosure(a, b).execute(null); 
  assertEquals(1, a.count);
  assertEquals(1, b.count); 
  a = new MockClosure<>();  
  b = new MockClosure<>();  
  ClosureUtils.<Object>chainedClosure(a, b, a).execute(null); 
  assertEquals(2, a.count);     
  assertEquals(1, b.count);     
  a = new MockClosure<>();      
  b = new MockClosure<>();     
  final Collection<Closure<Object>> coll = new ArrayList<>();  
  coll.add(b);   
  coll.add(a); 
  coll.add(b);   
  ClosureUtils.<Object>chainedClosure(coll).execute(null);   
  assertEquals(1, a.count);    
  assertEquals(2, b.count);  
  }    
  
  @Test   
  @SuppressWarnings("unchecked")  
  public void testChainedClosure2() {  
  MockClosure<Object> a = new MockClosure<>();  
  MockClosure<Object> b = new MockClosure<>();  
  ClosureUtils.chainedClosure(a, b).execute(null); 
  a = new MockClosure<>();  
  b = new MockClosure<>();     
  ClosureUtils.<Object>chainedClosure(a, b, a).execute(null);  
  a = new MockClosure<>();   
  b = new MockClosure<>();       
  final Collection<Closure<Object>> coll = new ArrayList<>();   
  coll.add(b); 
  coll.add(a);      
  coll.add(b);     
  ClosureUtils.<Object>chainedClosure(coll).execute(null);   
  assertSame(NOPClosure.INSTANCE, ClosureUtils.<Object>chainedClosure());     
  assertSame(NOPClosure.INSTANCE, ClosureUtils.<Object>chainedClosure(Collections.<Closure<Object>>emptyList()));   
  assertAll( () -> assertThrows(NullPointerException.class, 
  () -> ClosureUtils.chainedClosure(null, null)), 
  () -> assertThrows(NullPointerException.class,           
  () -> ClosureUtils.<Object>chainedClosure((Closure[]) null)), 
  () -> assertThrows(NullPointerException.class,       
  () -> ClosureUtils.<Object>chainedClosure((Collection<Closure<Object>>) null)),  
  () -> assertThrows(NullPointerException.class, () -> ClosureUtils.<Object>chainedClosure(null, null)),    
  () -> {          
  final Collection<Closure<Object>> finalColl = new ArrayList<>();   
  finalColl.add(null);               
  finalColl.add(null);               
  assertThrows(NullPointerException.class, () -> ClosureUtils.chainedClosure(finalColl));    
  });   
  }}



