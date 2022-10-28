package com.flab.bigtrader.apppush.application;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AppPushOperation {
	BLOCKING,
	NON_BLOCKING;

	private static final Map<String, AppPushOperation> POSSIBLE_OPERATIONS = cachePossibleOperations();

	public static boolean isPossibleOperation(String targetOperation) {
		if (targetOperation == null || targetOperation.isBlank()) {
			return false;
		}

		String convertTargetOperation = convertDashToUnderBar(targetOperation).toUpperCase();
		AppPushOperation appPushOperation = POSSIBLE_OPERATIONS.get(convertTargetOperation);
		return appPushOperation != null;
	}

	private static String convertDashToUnderBar(String targetOperation) {
		return targetOperation.replace("-", "_");
	}

	private static Map<String, AppPushOperation> cachePossibleOperations() {
		return new ConcurrentHashMap<>(
			Arrays.stream(values())
				.collect(Collectors.toUnmodifiableMap(
						Enum::name,
						Function.identity()
					)
				)
		);
	}
}
