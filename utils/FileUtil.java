package com.nwd.algo.common.util;

import org.springframework.core.io.ByteArrayResource;

public class FileUtil {

	public static ByteArrayResource formatByteFile(byte[] file, String fileName) {
		return new ByteArrayResource(file) {
			@Override
			public String getFilename() {
				return fileName;
			}
		};
	}
}
