package rs.ac.ni.pmf.marko.web;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OptionalDemo {

	@Value
	private static class Pair {
		int a;
		int b;
	}

	private static final List<Pair> elements = Arrays.asList(new Pair(1, 2), new Pair(1, 3), new Pair(3, 4));

	private Integer field = 10;

	public Optional<Pair> findPair(final int a, final List<Pair> pairs) {
		for (final Pair pair : pairs) {
			if (pair.getA() == a) {
				return Optional.of(pair);
			}
		}

		return Optional.empty();
	}

	public static void main(final String[] args) {
//		OptionalDemo demo = new OptionalDemo();

		final int a = 14;

		final Pair pair = OptionalDemo.elements.stream()
				.filter(p -> p.getA() == a)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Pair not found"));
		OptionalDemo.log.info(pair.toString());

//		elements.stream()
//			.filter(pair -> pair.getA() == a)
//			.map(Pair::toString)
//			.forEach(log::info);
////			.forEach(pair -> log.info(pair.toString()))
//		;

//		Pair pair = demo.findPair(a, elements).orElse(new Pair(0, 0));
//		log.info(pair.toString());

//		Pair pair = demo.findPair(a, elements).orElseThrow(() -> new IllegalArgumentException("Pair not found"));
//		log.info(pair.toString());

//		Optional<Pair> optionalPair = demo.findPair(a, elements);
//
//		if (optionalPair.isPresent()) {
//			Pair pair = optionalPair.get();
//			log.info(pair.toString());
//		} else {
//			log.error("Pair '{}' not found", a);
//		}

	}
}
