package FuncInterfaceDemo.src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FuncInterfaceDemo {
	
	public static void main(String[] args) {
		
		// Function Interface
		Foo foo = parameter -> parameter + " from lambda";
		String result = add("Message", foo);
		System.out.println(result);
		
		// Function standard library example using map
		Map<String, Integer> nameMap = new HashMap<>();
		Integer value = nameMap.computeIfAbsent("John", s -> s.length());
		System.out.println(value);
		
		value = nameMap.computeIfAbsent("John", String::length);
		System.out.println(value);

		// Function interface compose
		Function<Integer, String> intToString = Object::toString;
		Function<String ,String> quote = s -> "'" + s + "'";
		Function<Integer, String> quoteIntToString = quote.compose(intToString);
		System.out.println(quoteIntToString.apply(5));
		

		// BiFunction example
		Map<String, Integer> salaries = new HashMap<>();
		salaries.put("John", 40000);
		salaries.put("Freddy", 30000);
		salaries.put("Samuel", 50000);

		salaries.replaceAll((name, oldValue) -> name.equals("Freddy") ? oldValue : oldValue + 10000);
		for (Map.Entry<String, Integer> m : salaries.entrySet()) {
			System.out.println(m.getKey() + " : " + m.getValue());
		}
		
		IntToLongFunction intToLong = x -> x * 1000000;
		System.out.println(intToLong.applyAsLong(3));
		
		
		// Supplier functional interface example
		Supplier<Double> lazyValue = () -> { return 9d; };
		Double valueSquare = squareLazy(lazyValue);
		System.out.println(valueSquare);
		
		int[] fibs = {0, 1};
		Stream<Integer> fibonacci = Stream.generate(() -> {
			int seq = fibs[1];
			int fib3 = fibs[0] + fibs[1];
			fibs[0] = fibs[1];
			fibs[1] = fib3;
			return seq;
		}).limit(30);
		System.out.println(fibonacci.collect(Collectors.toList()));
		
		// Consumer functional interface example
		List<String> names = Arrays.asList("John", "Freddy", "Samuel");
		names.forEach(name -> System.out.println("Hello " + name));
		
		salaries.forEach((k, v) -> System.out.println(k + " : " + v));	
		
		// BiConsumer
		Map<String, Integer> ages = new HashMap<>();
		ages.put("John", 25);
		ages.put("Freddy", 24);
		ages.put("Samuel", 30);

		ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));
		
		ToDoubleBiFunction<Integer, Double> randBiFunc = (x, y) -> Math.sin(x + y);
		System.out.println(randBiFunc.applyAsDouble(5, 5d));
		
		
		// Predicate functional interface example
		List<String> nameList = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");

		List<String> namesWithA = nameList.stream()
		  .filter(n -> n.startsWith("A"))
		  .collect(Collectors.toList());
		namesWithA.forEach(s -> System.out.println(s));
		
		IntPredicate isEvenNumber = no -> no % 2 == 0;
		System.out.println(isEvenNumber.test(23));
		
		
		// Operator interfaces example
		// unary Operator
		nameList.replaceAll(n -> n.toUpperCase());
		nameList.forEach(s -> System.out.println(s));
		
		nameList.replaceAll(String::toLowerCase);
		nameList.forEach(s -> System.out.println(s));
		
		// Aggregation using reduction
		List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);

		int sum = values.stream()
		  .reduce(0, (i1, i2) -> i1 + i2);
		
		System.out.println(sum);
		
		BinaryOperator<String> shipFunc = (x, y) -> x.substring(0, x.length() / 2) + y.substring(y.length() / 2, y.length());
		System.out.println(shipFunc.apply(nameList.get(0), nameList.get(1)));
		
		String myString = "Test";

		MyFactory myFactory = (chars) -> {
			return myString + ":" + new String(chars);
		};
		
		char[] charA = {'a', 'b', 'c'};
		System.out.println(myFactory.create(charA));
	}
	
	@FunctionalInterface
	public interface Foo {
		String method(String string);
	}
	
	public static String add(String string, Foo foo) {
		return foo.method(string);
	}
	
	public static double squareLazy(Supplier<Double> lazyValue) {
		return Math.pow(lazyValue.get(), 2);
	}
	
	public interface MyFactory {
		public String create(char[] chars); 
		}
}
