package com.ndp.knowsharing.Utils.Files.FileNameString;

import java.util.Optional;

public class FileNameStringUtil {
    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}